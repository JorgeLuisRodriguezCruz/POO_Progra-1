package model;

import java.awt.Color;

public class Velocidad extends Alimento {

    public Velocidad() {
        super ();
    }
    
    @Override
    public void serComido (Organismo org) {
        org.setVelocidad(org.getVelocidad()+this.cantidad);
    }

    @Override
    public Color getColor (){
        return new Color(178, 255, 102);
    }
    }
