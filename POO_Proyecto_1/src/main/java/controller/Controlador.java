package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import view.Mapa;
import view.Informacion;
import view.MicroGameGUI;

public class Controlador implements ActionListener, KeyListener {
    private Mapa mapa;
    private MicroGameGUI configuracion;

    public Controlador () {
        this.mapa = new Mapa();
        this.configuracion = new MicroGameGUI(); 
        this.configuracion.setVisible(false);
        
        this.establecerComunicacion();
    }
    
    public Controlador (Mapa pMapa) {
        this.mapa = pMapa;
        this.mapa.setVisible(true);
        this.configuracion = new MicroGameGUI(); 
        this.configuracion.setVisible(false);
        
        this.establecerComunicacion();
    }
    
    public Controlador (MicroGameGUI pConfiguracion) {
        this.mapa = new Mapa();
        this.configuracion = pConfiguracion;
        
        this.establecerComunicacion();
    }
    
    private void establecerComunicacion () {
        this.mapa.getBoton().addActionListener(this); 
        this.mapa.getBoton().addKeyListener(this);
        this.configuracion.getJugar().addActionListener(this);
        
        for (int i = 0; i < this.mapa.getOrganismos().size(); i++) {
            this.mapa.getOrganismos().get(i).addKeyListener(this);
            this.mapa.getOrganismos().get(i).addActionListener(this);
        }
    }
    
    //Funciones (temporal y linea recta) que permiten moverse en una linea recta una determinada cantidad de casillas

    public void moverTemporal (int x, int y, int turno) {

        int newX = this.mapa.getCordenadas()[0] + x;
        int newY = this.mapa.getCordenadas()[1] + y;
        if (newX >= 0 && newX < 50 && newY >= 0 && newY < 50) {
            this.mapa.getCasillas()[this.mapa.getCordenadas()[0]][this.mapa.getCordenadas()[1]].setBackground(Color.WHITE);
            this.mapa.getOrganismos().get(turno).setLocation(this.mapa.getOrganismos().get(turno).getX() + (13 * x), this.mapa.getOrganismos().get(turno).getY() + (13 * y));
            this.mapa.getCordenadas()[0] = newX;
            this.mapa.getCordenadas()[1] = newY;
            this.mapa.getCasillas()[this.mapa.getCordenadas()[0]][this.mapa.getCordenadas()[1]].setBackground(Color.RED);
        }
    }
        
        public void moverEnLineaRecta(int cuadros, String direccion, int turno) {
            switch(direccion) {
                case "arriba":
                    moverTemporal(0, -cuadros, turno);
                    break;
                case "abajo":
                    moverTemporal(0, cuadros, turno);
                    break;
                case "izquierda":
                    moverTemporal(-cuadros, 0, turno);
                    break;
                case "derecha":
                    moverTemporal(cuadros, 0, turno);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < this.mapa.getOrganismos().size(); i++) {
                if (e.getSource() == this.mapa.getOrganismos().get(i)) {
                    Informacion info = new Informacion (22, 23, 24, 25);
                }
            }
            
            //Boton que no tiene funcionalidad
            
            // if (e.getSource() == this.mapa.getBoton()) { 
            //     this.mapa.getCasillas()[24][24].setBackground(Color.RED);
            //     this.mapa.getCasillas()[0][0].setBackground(Color.BLUE);
            //     this.mapa.getCasillas()[49][49].setBackground(Color.BLUE);
            // }
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
            moverEnLineaRecta(4, "izquierda", 0);
                break;
            case 38: // flecha arriba
            moverEnLineaRecta(4, "arriba", 0);
                break;
            case 39: // flecha derecha
            moverEnLineaRecta(4, "derecha", 0);
                break;
            case 40: // flecha abajo
            moverEnLineaRecta(4, "abajo", 0);
                break;

            default:

                break;
        }
    }

        @Override
        public void keyReleased(KeyEvent e) { }

        @Override
        public void keyTyped(KeyEvent arg0) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
        }
    
}
