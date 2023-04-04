package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.GestorPartida;
import model.Organismo;
import view.Mapa;
import view.Informacion;
import view.MicroGameGUI;

public class Controlador implements ActionListener, KeyListener {
    private Mapa mapa;
    private MicroGameGUI configuracion;
    private GestorPartida gestor;
    private ArrayList<Informacion> infomacion;
    private boolean automacNPCS;

    public Controlador () { 
        this.configuracion = new MicroGameGUI(); 
        this.configuracion.setVisible(false);    //this.configuracion.setVisible(true);
        this.gestor = new GestorPartida(this);
        this.mapa = new Mapa(this); this.mapa.setVisible(true);
        this.infomacion = new ArrayList<Informacion> ();
        this.automacNPCS = true;
        
        this.establecerComunicacion();
        this.iniciarOrganismos();
        this.gestor.mostrarVision(this.mapa.getCasillas());
        this.mapa.repaint();
    }
    
    public Controlador (Mapa pMapa) {
        this.mapa = pMapa;
        this.mapa.setVisible(true);
        this.configuracion = new MicroGameGUI(); 
        this.configuracion.setVisible(false);
        this.infomacion = new ArrayList<Informacion> ();
        this.automacNPCS = true;
        
        this.establecerComunicacion();
        this.gestor.mostrarVision(this.mapa.getCasillas());
        this.mapa.repaint();
    }
    
    public Controlador (MicroGameGUI pConfiguracion) {
        this.mapa = new Mapa(this);
        this.configuracion = pConfiguracion;
        this.infomacion = new ArrayList<Informacion> ();
        this.automacNPCS = true;
        
        this.establecerComunicacion();
    }
    
    private void establecerComunicacion () { 
        this.configuracion.getJugar().addActionListener(this);
        
        for (int i = 0; i < this.mapa.getOrganismos().size(); i++) {
            this.mapa.getOrganismos().get(i).addKeyListener(this);
            this.mapa.getOrganismos().get(i).addActionListener(this);
        }
        this.mapa.getSiguiente().addKeyListener(this);
        this.mapa.getSiguiente().addActionListener(this);
        this.mapa.getAutomatico().addKeyListener(this);
        this.mapa.getAutomatico().addActionListener(this);
    }
    
    public void actualizarInformacion (){
        ArrayList<Organismo> arrsyListorg = this.gestor.getOrganismos();
        //System.out.println("Dentro de actualizarInfo size orga: "+ arrsyListorg.size());
        for (int i = 0; i < arrsyListorg.size(); i++) {
            Organismo org = arrsyListorg.get(i); 
            this.infomacion.get(i).actualisarDatos(org.getEdad(), org.getVision(), org.getEnergia(), org.getVelocidad());
        }
    }

//Se manejan los diversos tipos de movimientos de los organismos

    public void mover (int x, int y, int turno) {

        JButton orgBoton = this.mapa.getOrganismos().get(turno);
        Organismo org = this.gestor.getOrganismos().get(turno);
        
        int posEnMapX = (orgBoton.getX() - 299) / 13; posEnMapX--;
        int posEnMapY = orgBoton.getY() / 13; posEnMapY--;
         
        if (posEnMapX >= 0 && posEnMapX < 50 && posEnMapY >= 0 && posEnMapY < 50) {
            
            this.mapa.getCasillas()[ posEnMapX ][ posEnMapY ].setBackground(Color.WHITE);
            
            if (orgBoton.getX() + (13 * x) < 312){
                orgBoton.setLocation(312,  orgBoton.getY() + (13 * y));
                org.setCoordenadas(312,  orgBoton.getY() + (13 * y));
                return;
            }
            if (orgBoton.getX() + (13 * x) > 949){
                orgBoton.setLocation(949,  orgBoton.getY() + (13 * y));
                org.setCoordenadas(949,  orgBoton.getY() + (13 * y));
                return;
            }
            if (orgBoton.getY() + (13 * y) < 13){
                orgBoton.setLocation(orgBoton.getX() + (13 * x),  13);
                org.setCoordenadas(orgBoton.getX() + (13 * x),  13);
                return;
            }
            if (orgBoton.getY() + (13 * y) > 650){
                orgBoton.setLocation(orgBoton.getX() + (13 * x),  650);
                org.setCoordenadas(orgBoton.getX() + (13 * x),  650);
                return;
            } 
            orgBoton.setLocation(orgBoton.getX() + (13 * x),  orgBoton.getY() + (13 * y));
            org.setCoordenadas(orgBoton.getX(),  orgBoton.getY());
            
        }
    }

//Movimiento realizado en linea recta (controlado)

    public void moverEnLineaControlado (int turno, int x, int y, int cantidad) {
        Organismo org = this.gestor.getOrganismos().get(turno);
        
        for (int i = 1; i <= cantidad; i++) {
            mover(x, y, turno);
        }
        org.setEdad(org.getEdad()+1);
        org.setEnergia(org.getEnergia()-1);
    }
//Movimiento realizado en linea recta 

    public void moverEnLineaRecta(int turno, int x, int y) {
        Organismo org = this.gestor.getOrganismos().get(turno);
        
        for (int i = 1; i <= org.getVelocidad(); i++) {
            mover(x, y, turno);
        }
        org.setEdad(org.getEdad()+1);
        org.setEnergia(org.getEnergia()-1);
    }

//Se inician  los organismos (la posición [0] es el jugador principal; los demás son los NPC)

    public void iniciarOrganismos () { 
        this.gestor.crearOrganismos(this.mapa.getOrganismos()); 
        ArrayList<Organismo> organismos = this.gestor.getOrganismos();
        for (int i = 0; i < organismos.size(); i++) {
            Organismo org = organismos.get(i);
            this.infomacion.add(new Informacion(org.identificarse(), org.getEdad(), org.getVision(), org.getEnergia(), org.getVelocidad()));
        }
    }
    
    public void pasarCoordsAlimentos (int[][] coordsAlimentos) {
        this.gestor.crearAlimentos(coordsAlimentos);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.mapa.getOrganismos().size(); i++) {
            if (e.getSource() == this.mapa.getOrganismos().get(i)) { 
                this.infomacion.get(i).setVisible(true);
                return;
            }
        }
        if (e.getSource() == this.mapa.getSiguiente()){
            this.gestor.limpiarVista(mapa.getCasillas());
            this.mapa.repaint(); 
            if (this.automacNPCS){ 
                this.gestor.moverAutomatico();//this.gestor.moverNpcs();
                this.gestor.mostrarVision(this.mapa.getCasillas());
                this.mapa.repaint();
            }
            else { 
                this.gestor.simularSiguiente();
                this.gestor.mostrarVision(mapa.getCasillas()); 
                this.mapa.repaint();  
            }
            this.actualizarInformacion();
        }
        if (e.getSource() == this.mapa.getAutomatico()){
            this.automacNPCS = !this.automacNPCS;
            if (this.automacNPCS)
                this.mapa.getAutomatico().setBackground(new Color(102, 255, 102));
            else
                this.mapa.getAutomatico().setBackground(new Color(255, 102, 102));
        }
        if (e.getSource() == this.configuracion.getJugar()) {
            try {
                int maximo = Integer.parseInt(this.configuracion.getEntradaMaximo().getText());
                int minimo = Integer.parseInt(this.configuracion.getEntradaMinimo().getText()); 
                int escalaIncremento = Integer.parseInt(this.configuracion.getEntradaAumento().getText()); 
                int escalaDecremento = Integer.parseInt(this.configuracion.getEntradaDecremento().getText()); 
                this.mapa.setVisible(true);
                this.gestor.setSimulacionFactoresDeCambio(maximo, minimo, escalaIncremento, escalaDecremento);
                this.configuracion.setVisible(false);
            } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ERROR: Los valores digitados deben de ser enteros. Digite nuevamente los valores.");
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) { 
        int turno = this.gestor.getTurno(); //Esto es solo representativo cuando este implementado la gestion del turno, se quita. 
        if (turno != 0)
            return;
        this.gestor.limpiarVista(mapa.getCasillas()); 
        switch (e.getKeyCode()) {
            case 37: // flecha izquierda
                this.moverEnLineaRecta(turno, -1, 0);
                break;
            case 38: // flecha arriba
                this.moverEnLineaRecta(turno, 0, -1);
                break;
            case 39: // flecha derecha
                this.moverEnLineaRecta(turno, 1, 0);
                break;
            case 40: // flecha abajo
                this.moverEnLineaRecta(turno, 0, 1);
                break; 
            default: 
                break;
        }  
        if (this.automacNPCS){
            this.gestor.setTurno(turno + 1);
            this.gestor.moverAutomatico();//this.gestor.moverNpcs();
            this.gestor.mostrarVision(this.mapa.getCasillas());
            this.mapa.repaint();
        }
        else {
            this.gestor.setTurno(turno + 1);
            this.gestor.mostrarVision(mapa.getCasillas());
            this.mapa.repaint();
        }
        this.actualizarInformacion();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
    
}
