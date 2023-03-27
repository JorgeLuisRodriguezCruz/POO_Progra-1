package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.GestorPartida;
import view.Mapa;
import view.Informacion;
import view.MicroGameGUI;

public class Controlador implements ActionListener, KeyListener {
    private Mapa mapa;
    private MicroGameGUI configuracion;
    private GestorPartida gestor;

    public Controlador () {
        
        this.configuracion = new MicroGameGUI(); 
        this.configuracion.setVisible(false);
        this.gestor = new GestorPartida(this);
        this.mapa = new Mapa(this);
        this.mapa.setVisible(true);
        
        this.establecerComunicacion();
        this.iniciarOrganismos();
    }
    
    public Controlador (Mapa pMapa) {
        this.mapa = pMapa;
        this.mapa.setVisible(true);
        this.configuracion = new MicroGameGUI(); 
        this.configuracion.setVisible(false);
        
        this.establecerComunicacion();
    }
    
    public Controlador (MicroGameGUI pConfiguracion) {
        this.mapa = new Mapa(this);
        this.configuracion = pConfiguracion;
        
        this.establecerComunicacion();
    }
    
    private void establecerComunicacion () { 
        this.configuracion.getJugar().addActionListener(this);
        
        for (int i = 0; i < this.mapa.getOrganismos().size(); i++) {
            this.mapa.getOrganismos().get(i).addKeyListener(this);
            this.mapa.getOrganismos().get(i).addActionListener(this);
        }
    }
    
    public void mover (int x, int y, int turno) {

        JButton org = this.mapa.getOrganismos().get(turno);
        
        int posEnMapX = (org.getX() - 299) / 13; posEnMapX--;
        int posEnMapY = org.getY() / 13; posEnMapY--;
         
        if (posEnMapX >= 0 && posEnMapX < 50 && posEnMapY >= 0 && posEnMapY < 50) {
            
            this.mapa.getCasillas()[ posEnMapX ][ posEnMapY ].setBackground(Color.WHITE);
            
            if (org.getX() + (13 * x) < 312){
                org.setLocation(312,  org.getY() + (13 * y));
                return;
            }
            if (org.getX() + (13 * x) > 949){
                org.setLocation(949,  org.getY() + (13 * y));
                return;
            }
            if (org.getY() + (13 * y) < 13){
                org.setLocation(org.getX() + (13 * x),  13);
                return;
            }
            if (org.getY() + (13 * y) > 650){
                org.setLocation(org.getX() + (13 * x),  650);
                return;
            }
            org.setLocation(org.getX() + (13 * x),  org.getY() + (13 * y));
            
        }
    }
        
    public void moverEnLineaRecta(int cuadros, int turno, int x, int y) {
        
        for (int i = 0; i < cuadros; i++) {
            mover(x, y, turno);
        }
    }

    public void iniciarOrganismos () {
        this.gestor.crearOrganismos(this.mapa.getOrganismos());
    }
    
    public void pasarCoordsAlimentos (int[][] coordsAlimentos) {
        this.gestor.crearAlimentos(coordsAlimentos);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.mapa.getOrganismos().size(); i++) {
            if (e.getSource() == this.mapa.getOrganismos().get(i)) {
                Informacion info = new Informacion (22, 23, 24, 25);
            }
        } 
        if (e.getSource() == this.configuracion.getJugar()) {
            try {
                Integer.parseInt(this.configuracion.getEntradaMaximo().getText());
                Integer.parseInt(this.configuracion.getEntradaMinimo().getText()); 
                Integer.parseInt(this.configuracion.getEntradaAumento().getText()); 
                Integer.parseInt(this.configuracion.getEntradaDecremento().getText()); 
                this.mapa.setVisible(true);
                this.configuracion.setVisible(false);
            } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ERROR: Los valores digitados deben de ser enteros. Digite nuevamente los valores.");
            }
        }
    }
        
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37: // flecha izquierda
                moverEnLineaRecta(4, 0, -1, 0);
                break;
            case 38: // flecha arriba
                moverEnLineaRecta(4, 0, 0, -1);
                break;
            case 39: // flecha derecha
                moverEnLineaRecta(4, 0, 1, 0);
                break;
            case 40: // flecha abajo
                moverEnLineaRecta(4, 0, 0, 1);
                break; 
            default: 
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
    
}
