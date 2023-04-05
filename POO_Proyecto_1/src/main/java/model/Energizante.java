package model;

import java.awt.Color;

public class Energizante extends Alimento {

    public Energizante() {
        super ();
    }
    
    @Override
    public void serComido (Organismo org) {
        org.setEnergia(org.getEnergia()+this.cantidad);
        
    }

    @Override
    public Color getColor (){
        return new Color(255, 255, 102);
    }
    }
