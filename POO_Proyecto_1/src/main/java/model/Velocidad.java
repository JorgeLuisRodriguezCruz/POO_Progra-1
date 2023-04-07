package model;

import java.awt.Color;

public class Velocidad extends Alimento {

    public Velocidad() {
        super ();
    }
    
    @Override
    public void serComido (Organismo org, int maximo, int minimo, int incremento, int decremento) {
        int nuevaCantidad = org.getVelocidad()+ (this.cantidad*incremento);
        if (nuevaCantidad <= maximo)
            org.setVelocidad(nuevaCantidad);
    }

    @Override
    public Color getColor (){
        return new Color(178, 255, 102);
    }
    }
