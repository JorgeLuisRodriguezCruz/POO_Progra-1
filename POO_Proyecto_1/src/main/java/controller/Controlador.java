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
    
    public void moverTemporal (int x, int y, int turno) {
        this.mapa.getCasillas()[ this.mapa.getCordenadas()[0] ][ this.mapa.getCordenadas()[1] ].setBackground(Color.WHITE);
        if (this.mapa.getCordenadas()[0]+x != -1 && this.mapa.getCordenadas()[0]+x != 50) {
            this.mapa.getOrganismos().get(turno).setLocation(this.mapa.getOrganismos().get(turno).getX() + (13*x), this.mapa.getOrganismos().get(turno).getY());
            this.mapa.getCordenadas()[0] = this.mapa.getCordenadas()[0]+x; 
        } if (this.mapa.getCordenadas()[1]+y != -1 && this.mapa.getCordenadas()[1]+y != 50) {
            this.mapa.getOrganismos().get(turno).setLocation(this.mapa.getOrganismos().get(turno).getX(), this.mapa.getOrganismos().get(turno).getY() + (13*y) );
            this.mapa.getCordenadas()[1] = this.mapa.getCordenadas()[1]+y; 
        }
        this.mapa.getCasillas()[ this.mapa.getCordenadas()[0] ][ this.mapa.getCordenadas()[1] ].setBackground(Color.RED);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.mapa.getOrganismos().size(); i++) {
            if (e.getSource() == this.mapa.getOrganismos().get(i)) {
                Informacion info = new Informacion (22, 23, 24, 25);
            }
        }
        
        if (e.getSource() == this.mapa.getBoton()) { 
            this.mapa.getCasillas()[24][24].setBackground(Color.RED);
            this.mapa.getCasillas()[0][0].setBackground(Color.BLUE);
            this.mapa.getCasillas()[49][49].setBackground(Color.BLUE);
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
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                moverTemporal(-1, 0, 0); 
                break;
            case 38:
                moverTemporal(0, -1, 0); 
                break;
            case 39:
                moverTemporal(1, 0, 0); 
                break;
            case 40:
                moverTemporal(0, 1, 0); 
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    
}
