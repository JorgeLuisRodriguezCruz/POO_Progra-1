package model;

import java.awt.Color;
import java.util.Random;

public class Energizante extends Alimento {

    public Energizante() {
        super ();
        this.cantidad = (new Random().nextInt(5))+1;
    }
    
    @Override
    public void serComido (Organismo org, int maximo, int minimo, int incremento, int decremento) {
        int nuevaCantUno = org.getEnergia()+(this.cantidad*incremento);
        if (nuevaCantUno <= maximo)
            org.setEnergia(nuevaCantUno);
        else
            org.setEnergia(maximo);
        
        int nuevaCantDos = org.getVelocidad() - (1*decremento);
        if (nuevaCantDos >= minimo)
            org.setVelocidad(nuevaCantDos);
        else
            org.setVelocidad(minimo);
    }

    @Override
    public Color getColor (){
        return new Color(255, 255, 102);
    }
    }
