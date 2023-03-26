package model;
import view.Mapa;
import view.Informacion;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

public class Organismo { 
    protected int[] coordenadas;
    protected int edad;
    protected int vision; 
    protected int energia;
    protected int velocidad;
 
    public Organismo () {
        this.coordenadas[0] = 0; 
        this.coordenadas[1] = 0;
        this.edad = 1;
        this.vision = 4; 
        this.energia = 4;
        this.velocidad = 2;
        
    }

}