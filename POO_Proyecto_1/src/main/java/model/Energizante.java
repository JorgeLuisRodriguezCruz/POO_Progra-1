package model;

import java.awt.Color;
import java.util.Random;

public class Energizante extends Alimento {

    public Energizante() {
        super ();
        this.cantidad = (new Random().nextInt(5))+1;
    }
    
    @Override
    public void serComido (Organismo org) {
        org.setEnergia(org.getEnergia()+this.cantidad);
        org.setVelocidad(org.getVelocidad() - 1);
    }

    @Override
    public Color getColor (){
        return new Color(255, 255, 102);
    }
    }
