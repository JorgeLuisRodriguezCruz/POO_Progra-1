package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Alimento;
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
    int cantMoviJugador;
    int keyDireccion;

    public Controlador () { 
        this.configuracion = new MicroGameGUI(); 
        this.configuracion.setVisible (true);
        this.gestor = new GestorPartida(this);
        this.mapa = new Mapa(this); 
        this.mapa.setVisible (false);
        this.infomacion = new ArrayList<Informacion> ();
        this.automacNPCS = true;
        this.cantMoviJugador = -1;
        this.keyDireccion = -1;
        
        this.establecerComunicacion();
        this.iniciarOrganismos();
        this.gestor.mostrarVision(this.mapa.getCasillas());
        this.mapa.repaint();
    }

//Se establece la comunicación entre las diversas partes esenciales del funcionamiento del juego

    private void establecerComunicacion () { 
        this.configuracion.getJugar().addActionListener(this);
        
        for (int i = 0; i < this.mapa.getOrganismos().size(); i++) {
            this.mapa.getOrganismos().get(i).addKeyListener(this);
            this.mapa.getOrganismos().get(i).addActionListener(this);
        }
        this.mapa.getTerminarTurno().addKeyListener(this);
        this.mapa.getTerminarTurno().addActionListener(this);
        this.mapa.getSiguiente().addKeyListener(this);
        this.mapa.getSiguiente().addActionListener(this);
        this.mapa.getAutomatico().addKeyListener(this);
        this.mapa.getAutomatico().addActionListener(this);
    }
    
//Se actualiza la información correspondiente a cada organismo

    public void actualizarInformacion (){
        ArrayList<Organismo> arrsyListOrg = this.gestor.getOrganismos();
 
        for (int i = 0; i < arrsyListOrg.size(); i++) {
            Organismo org = arrsyListOrg.get(i); 
            this.infomacion.get(i).actualisarDatos(org.getEdad(), org.getVision(), org.getEnergia(), org.getVelocidad());
        }
    }
//Se cambia de posicion del organismo en el mapa de juego (matriz)

    public void cambiarPosicionOrg (int indice){
        Organismo org = this.gestor.getOrganismos().get(indice);
        JButton orgJB = this.mapa.getOrganismos().get(indice);
        boolean bandera = true;
        
        while (bandera) {
            int x = new Random().nextInt(50);
            int y = new Random().nextInt(50);
            JLabel[][] mapa = this.mapa.getCasillas();
            
            if (mapa[x][y].getBackground() == Color.WHITE && this.mapa.hayOrgPosicion(x, y) == false) {
                org.reiniciarAtributos();
                org.setCoordenadas( 299 +( (x+1) * 13), (y+1) * 13);
                orgJB.setLocation(299 +( (x+1) * 13), (y+1) * 13);
                bandera = false;
            }
        }
    }
//Se cambia posición de alimentos en el mapa de juego (matriz)
    public void cambiarPosicionAlim (int indice){
        Alimento alimento = this.gestor.getAlimentos().get(indice);
        int posAlimen_X = alimento.getCoordenadas()[0], posAlimen_Y = alimento.getCoordenadas()[1];
        boolean bandera = true;
        
        while (bandera) {
            int x = new Random().nextInt(50);
            int y = new Random().nextInt(50);
            JLabel[][] mapa = this.mapa.getCasillas();
            
            if (mapa[x][y].getBackground() == Color.WHITE && this.mapa.hayOrgPosicion(x, y) == false) {
                mapa[ posAlimen_X ][ posAlimen_Y ].setBackground(Color.WHITE);
                mapa[x][y].setBackground(alimento.getColor());
                alimento.setCoordenadas(x, y);
                bandera = false;
            }
        }
    }
//Método para mostrar mensaje en caso de que la energía llegue a 0 de un organismo
    public void juegoPerdido(){
        JOptionPane.showMessageDialog(null, "El juego ha terminado porque se ha quedado sin energía", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }     
//Se comprueba la posición que un organismo tiene con respecto a otro organismo
    public void coprobarPosicion (Organismo organismo) { 
        int pos_X = organismo.getCoordenadas()[0], pos_Y = organismo.getCoordenadas()[1];
        int indxCoincidencia = this.gestor.comprobarCoincidenciaOrganismos(pos_X, pos_Y, this.gestor.getTurno());
        if (indxCoincidencia != -1) {
            indxCoincidencia = this.gestor.incidenciaOrganismos(this.gestor.getTurno(), indxCoincidencia);
            if (indxCoincidencia == 0)
                juegoPerdido();
            this.cambiarPosicionOrg(indxCoincidencia);
            return;
        }
        indxCoincidencia = this.gestor.comprobarCoincidenciaAlimento(((pos_X - 299) / 13) - 1, (pos_Y / 13) - 1);
        if (indxCoincidencia != -1) { 
            this.gestor.getAlimentos().get(indxCoincidencia).serComido(organismo, this.gestor.getMaxCanpacidad(), 
                    this.gestor.getMinCanpacidad(), this.gestor.getEscalaIncremento(), this.gestor.getEscalaDecremento());
            this.cambiarPosicionAlim(indxCoincidencia);
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
            this.coprobarPosicion(org);
        }
    }

//Movimiento realizado en linea recta (controlado)

    public void moverEnLineaControlado (int turno, int x, int y, int cantidad) {
        Organismo org = this.gestor.getOrganismos().get(turno);
        if (org.getEnergia() > this.gestor.getMinCanpacidad()) {
            for (int i = 1; i <= cantidad; i++) {
                mover(x, y, turno);
            }
            int nuevaEdad = org.getEdad()+(1*this.gestor.getEscalaIncremento());
            int nuevaEnergia = org.getEnergia()-(1*this.gestor.getEscalaDecremento());
            int nuevaVision = org.getVision()-(1*this.gestor.getEscalaDecremento());
            
            if (nuevaEdad > this.gestor.getMaxCanpacidad())
                nuevaEdad = this.gestor.getMaxCanpacidad();
            if (nuevaEnergia < this.gestor.getMinCanpacidad())
                nuevaEnergia = this.gestor.getMinCanpacidad();
            if (nuevaVision < this.gestor.getMinCanpacidad())
                nuevaVision = this.gestor.getMinCanpacidad();
            
            org.setEdad(nuevaEdad);
            org.setEnergia(nuevaEnergia);
            org.setVision(nuevaVision);
            return;
        }
        if (turno == 0) {
         juegoPerdido();
        }
    }

//Movimiento realizado en linea recta 

    public void moverEnLineaRecta(int turno, int x, int y) {
        Organismo org = this.gestor.getOrganismos().get(turno); 
        if (org.getEnergia() >= this.gestor.getMinCanpacidad()) {
            for (int i = 1; i <= org.getVelocidad(); i++) {
                mover(x, y, turno);
            }
            int nuevaEdad = org.getEdad()+(1*this.gestor.getEscalaIncremento());
            int nuevaEnergia = org.getEnergia()-(1*this.gestor.getEscalaDecremento());
            int nuevaVision = org.getVision()-(1*this.gestor.getEscalaDecremento());
            
            if (nuevaEdad > this.gestor.getMaxCanpacidad())
                nuevaEdad = this.gestor.getMaxCanpacidad();
            if (nuevaEnergia < this.gestor.getMinCanpacidad())
                nuevaEnergia = this.gestor.getMinCanpacidad();
            if (nuevaVision < this.gestor.getMinCanpacidad())
                nuevaVision = this.gestor.getMinCanpacidad();
            

            org.setEdad(nuevaEdad);
            org.setEnergia(nuevaEnergia);
            org.setVision(nuevaVision);
            return;
        }
        
        if (turno == 0) {
            juegoPerdido();
        }
    }

//Se inician  los organismos (la posición [0] es el jugador principal; los demás son los NPC)

    public void iniciarOrganismos () { 
        this.gestor.crearOrganismos(this.mapa.getOrganismos()); 
        ArrayList<Organismo> organismos = this.gestor.getOrganismos();
        for (int i = 0; i < organismos.size(); i++) {
            Organismo org = organismos.get(i);
            this.infomacion.add(new Informacion(org.identificarse(), org.getEdad(), org.getVision(), org.getEnergia(), org.getVelocidad()));
        }
        this.cantMoviJugador = this.gestor.getOrganismos().get(0).getVelocidad();
        this.mapa.getCantMovimientos().setText("Movimientos: "+this.cantMoviJugador);
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
        if (e.getSource() == this.mapa.getTerminarTurno()){
            int turno = this.gestor.getTurno();
            if ( turno == 0) {
                this.gestor.limpiarVista(mapa.getCasillas()); 
                this.mapa.getCantMovimientos().setText("Movimientos: "+0);
                this.keyDireccion = -1;
                this.cantMoviJugador = this.gestor.getOrganismos().get(turno).getVelocidad();
                
                Organismo org = this.gestor.getOrganismos().get(0);
                int nuevaEdad = org.getEdad()+(1*this.gestor.getEscalaIncremento());
                int nuevaEnergia = org.getEnergia()-(1*this.gestor.getEscalaDecremento());
                int nuevaVision = org.getVision()-(1*this.gestor.getEscalaDecremento());

                if (nuevaEdad > this.gestor.getMaxCanpacidad())
                    nuevaEdad = this.gestor.getMaxCanpacidad();
                if (nuevaEnergia < this.gestor.getMinCanpacidad())
                    juegoPerdido();
                if (nuevaVision < this.gestor.getMinCanpacidad())
                    nuevaVision = this.gestor.getMinCanpacidad();

                org.setEdad(nuevaEdad);
                org.setEnergia(nuevaEnergia);
                org.setVision(nuevaVision);
                
                if (this.automacNPCS) {
                    this.gestor.setTurno(turno + 1);
                    this.gestor.moverAutomatico(); 
                    this.mapa.getCantMovimientos().setText("Movimientos: "+this.cantMoviJugador);
                }
                else {
                    this.gestor.setTurno(turno + 1);
                    this.mapa.getCantMovimientos().setText("Movimientos: "+0);
                }
            }
            this.gestor.mostrarVision(this.mapa.getCasillas());
            this.mapa.repaint();
            this.actualizarInformacion();
            return;
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
                if (this.gestor.getTurno() == 0){ 
                    this.cantMoviJugador = this.gestor.getOrganismos().get(0).getVelocidad();
                    this.mapa.getCantMovimientos().setText("Movimietos: "+this.cantMoviJugador);
                }
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
        int turno = this.gestor.getTurno();
        if (turno != 0 || e.getKeyCode() < 37 || e.getKeyCode() > 40)
            return;
        this.gestor.limpiarVista(mapa.getCasillas()); 
        switch (e.getKeyCode()) {
            case 37: // flecha izquierda
                if (this.keyDireccion == 37 || this.keyDireccion == -1) {
                    this.keyDireccion = 37;
                    this.mover(-1, 0, turno);
                    break;
                } else
                    break;
            case 38: // flecha arriba
                if (this.keyDireccion == 38 || this.keyDireccion == -1) {
                    this.keyDireccion = 38;
                    this.mover(0, -1, turno);
                    break;
                } else
                    break;
            case 39: // flecha derecha
                if (this.keyDireccion == 39 || this.keyDireccion == -1) {
                    this.keyDireccion = 39;
                    this.mover(1, 0, turno);
                    break;
                } else
                    break;
            case 40: // flecha abajo
                if (this.keyDireccion == 40 || this.keyDireccion == -1) {
                    this.keyDireccion = 40;
                    this.mover(0, 1, turno);
                    break;
                } else
                    break;
            default: 
                return;
        }  
        if (this.keyDireccion != e.getKeyCode()) {
            this.gestor.mostrarVision(this.mapa.getCasillas());
            this.mapa.repaint();
            return;
        }
        if (this.cantMoviJugador == 1) { 
            this.mapa.getCantMovimientos().setText("Movimientos: "+0);
            this.keyDireccion = -1;
            this.cantMoviJugador = this.gestor.getOrganismos().get(turno).getVelocidad();
            
            Organismo org = this.gestor.getOrganismos().get(0);
            int nuevaEdad = org.getEdad()+(1*this.gestor.getEscalaIncremento());
            int nuevaEnergia = org.getEnergia()-(1*this.gestor.getEscalaDecremento());
            int nuevaVision = org.getVision()-(1*this.gestor.getEscalaDecremento());
            
            if (nuevaEdad > this.gestor.getMaxCanpacidad())
                nuevaEdad = this.gestor.getMaxCanpacidad();
            if (nuevaEnergia < this.gestor.getMinCanpacidad())
                juegoPerdido();
            if (nuevaVision < this.gestor.getMinCanpacidad())
                nuevaVision = this.gestor.getMinCanpacidad();
            
            org.setEdad(nuevaEdad);
            org.setEnergia(nuevaEnergia);
            org.setVision(nuevaVision);
            
            if (this.automacNPCS) {
                this.gestor.setTurno(turno + 1);
                this.gestor.moverAutomatico(); 
                this.mapa.getCantMovimientos().setText("Movimientos: "+this.cantMoviJugador);
            }
            else {
                this.gestor.setTurno(turno + 1);
                this.mapa.getCantMovimientos().setText("Movimientos: "+0);
            }
        } else {
            if(this.cantMoviJugador == -1) {
                this.cantMoviJugador = this.gestor.getOrganismos().get(turno).getVelocidad();
            } else {
                this.cantMoviJugador = this.cantMoviJugador - 1;
                this.mapa.getCantMovimientos().setText("Movimientos: "+this.cantMoviJugador);
            }
        }
        this.gestor.mostrarVision(this.mapa.getCasillas());
        this.mapa.repaint();
        this.actualizarInformacion();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
    
}
