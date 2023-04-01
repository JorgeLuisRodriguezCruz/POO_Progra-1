package model;
import controller.Controlador;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import view.Mapa;

public class GestorPartida {
    private Controlador controlador;
    private ArrayList<Organismo> organismos;
    private ArrayList<Alimento> alimentos;
    private int turno;
    private int maxCanpacidad;
    private int minCanpacidad;
    private int escalaIncremento;
    private int escalaDecremento;

    public GestorPartida(Controlador controlador) {
        this.controlador = controlador; 
        this.organismos = new ArrayList<Organismo>();
        this.alimentos = new ArrayList<Alimento>(); 
        this.turno = 0;
        this.maxCanpacidad = 100;
        this.minCanpacidad = 0;
        this.escalaIncremento = 1;
        this.escalaDecremento = 1;
    }
    
    public void setSimulacionFactoresDeCambio (int maximo, int minimo, int incremento, int decremento) { 
        this.maxCanpacidad = maximo;
        this.minCanpacidad = minimo;
        this.escalaIncremento = incremento;
        this.escalaDecremento = decremento;
    }
    
    private boolean comprobarHayAlimento (int x, int y) { 
        for (int i = 0; i < this.alimentos.size(); i++) {
            Alimento alimento = this.alimentos.get(i);
            if (alimento.getCoordenadas()[0] == x && alimento.getCoordenadas()[1] == y)
                return true;
        } 
        return false;
    }
    
    public void crearOrganismos (ArrayList<JButton> organismos) {
        Random rand = new Random();
        for (int i = 0; i < organismos.size(); i++) { //JButton get = organismos.get(i);
            int tipo = rand.nextInt(2);
            
            if (tipo == 1)
                this.organismos.add(new Velocista ());
            else
                this.organismos.add(new Visionador()); 
            this.organismos.get(i).setCoordenadas(organismos.get(i).getX(), organismos.get(i).getY());    
        } 
    }
    
    public void crearAlimentos (int[][] coordsAlimentos) {
        
        for (int i = 0; i < 21; i++) {
            if (i <= 6) {
                this.alimentos.add(new Energizante());
                //Estas cordenadas indican la posicion en la matriz de JLabel del mapa.
                this.alimentos.get(i).setCoordenadas(coordsAlimentos[i][0], coordsAlimentos[i][1]);
            }
            if (i > 6 && i <= 13){ 
                this.alimentos.add(new Vision());
                this.alimentos.get(i).setCoordenadas(coordsAlimentos[i][0], coordsAlimentos[i][1]);
            }
            if (i > 13){ 
                this.alimentos.add(new Velocidad());
                this.alimentos.get(i).setCoordenadas(coordsAlimentos[i][0], coordsAlimentos[i][1]);
            }
        }
    }

    public ArrayList<Organismo> getOrganismos() {
        return organismos;
    }

    public void moverNpcs () {
        if (this.turno == 0)
            return;
        
        for (int i = this.turno; i < organismos.size(); i++) {
            int direccionMover = this.organismos.get(i).elegirDireccion(this.organismos, this.alimentos, i);

            switch(direccionMover){
                case 0: // Izquierda
                    this.controlador.moverEnLineaRecta(i, -1, 0);
                    break;
                case 1: // Arriba
                    this.controlador.moverEnLineaRecta(i, 0, -1);
                    break;
                case 2: // Derecha
                    this.controlador.moverEnLineaRecta(i, 1, 0);
                    break;
                case 3: // Abajo
                    this.controlador.moverEnLineaRecta(i, 0, 1);
                    break;
                default:
                    break;
            }
        }
        this.turno = 0; 
    }

    public void simularSiguiente () {
        if (this.turno != 0) {
            int direccionMover = this.organismos.get(this.turno).elegirDireccion(this.organismos, this.alimentos, this.turno);
            
            switch(direccionMover){
                case 0: // Izquierda
                    this.controlador.moverEnLineaRecta(this.turno, -1, 0);
                    break;
                case 1: // Arriba
                    this.controlador.moverEnLineaRecta(this.turno, 0, -1);
                    break;
                case 2: // Derecha
                    this.controlador.moverEnLineaRecta(this.turno, 1, 0);
                    break;
                case 3: // Abajo
                    this.controlador.moverEnLineaRecta(this.turno, 0, 1);
                    break;
                default:
                    break;
            }
            if (this.turno >= this.organismos.size() - 1)
                this.turno = 0;
            else
                this.turno++;
        }
    }
    
    public void limpiarVista (JLabel[][] mapa) {
        Organismo orgEnTurno = orgEnTurno = this.organismos.get(this.turno);
         
        int posOrgX = (orgEnTurno.getCoordenadas()[0] - 299) / 13;
        int posOrgY = orgEnTurno.getCoordenadas()[1] / 13;
        
        int indxInicialX = (posOrgX - orgEnTurno.getVision()) - 1;
        if (indxInicialX < 0)
            indxInicialX = 0;
        int indxFinalX = (posOrgX + orgEnTurno.getVision()) - 1;
        if (indxFinalX > 49)
            indxFinalX = 49;
        int indxInicialY = (posOrgY - orgEnTurno.getVision()) - 1;
        if (indxInicialY < 0)
            indxInicialY = 0;
        int indxFinalY = (posOrgY + orgEnTurno.getVision()) - 1;
        if (indxFinalY > 49)
            indxFinalY = 49; 
        
        for (int i = indxInicialX; i <= indxFinalX; i++) { 
            for (int j = indxInicialY; j <= indxFinalY; j++) {
                JLabel casilla = mapa[i][j]; 
                if (this.comprobarHayAlimento(i, j) ==  false) {
                    casilla.setBackground(Color.WHITE);
                }
            }
        } 
    }
    
    public void mostrarVision (JLabel[][] mapa) { 
        Organismo orgEnTurno = orgEnTurno = this.organismos.get(this.turno);
        
        int posOrgX = (orgEnTurno.getCoordenadas()[0] - 299) / 13;
        int posOrgY = orgEnTurno.getCoordenadas()[1] / 13; //posOrgY--;
        
        /*System.out.println("Turno:" + this.turno + " JB_coord_x = "+ org_JB.getX()+ " JB_coord_y = "+ org_JB.getY());
        System.out.println("Turno:" + this.turno + " coord_x = "+ orgEnTurno.getCoordenadas()[0] + " coord_y = "+ orgEnTurno.getCoordenadas()[1]);
        System.out.println("Turno:" + this.turno + " pos_x = "+ posOrgX + " pos_y = "+ posOrgY);
        System.out.println("Turno:" + this.turno + " ind_x = "+ (posOrgX-1) + " ind_y = "+ (posOrgY-1) );*/
         
        int indxInicialX = (posOrgX - orgEnTurno.getVision())-1;
        int indxFinalX = (posOrgX + orgEnTurno.getVision())-1;
        int indxInicialY = (posOrgY - orgEnTurno.getVision())-1;
        int indxFinalY = (posOrgY + orgEnTurno.getVision())-1;
        
        
        if (indxInicialX < 0) 
            indxInicialX = 0; 
        if (indxFinalX > 49)
            indxFinalX = 49;
        if (indxInicialY < 0)
            indxInicialY = 0;
        if (indxFinalY > 49)
            indxFinalY = 49; 
         
        for (int i = indxInicialX; i <= indxFinalX; i++) { 
            for (int j = indxInicialY; j <= indxFinalY; j++) {
                JLabel casilla = mapa[i][j]; 
                if (this.comprobarHayAlimento(i, j) ==  false) {
                    casilla.setBackground(Color.LIGHT_GRAY);
                }
            }
        }
        
    }
    
    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    public int getTurno() {
        return turno;
    } 
    
}