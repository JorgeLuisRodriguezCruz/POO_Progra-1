package model;
import view.Mapa;
import view.Informacion;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

public class Organismo extends JButton {
 
    public Organismo(int x, int y) {
        super();
        this.setBackground(Color.RED);
        this.setBounds(x, y, 50, 50);
    }

    public Organismo() {
    }

    public void setCoordenadas(int x, int y) {
    }




    // private int[] coordenadas;
    // private int edad;
    // private int vision;
    // private int energia;
    // private int velocidad;

}