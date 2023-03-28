package model;
import controller.Controlador;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import view.Mapa;

public class GestorPartida {
    private Controlador controlador;
    private ArrayList<Organismo> organismos;
    private ArrayList<Alimento> alimentos;
    private int turno;

    public GestorPartida(Controlador controlador) {
        this.controlador = controlador; 
        this.organismos = new ArrayList<Organismo>();
        this.alimentos = new ArrayList<Alimento>();
        this.turno = 0;
        
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
        
        for (int i = this.turno; i < organismos.size(); i++) {
            int direccionMover = this.organismos.get(i).elegirDireccion(this.organismos, this.alimentos);

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

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    public int getTurno() {
        return turno;
    } 
    
}