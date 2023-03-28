package model;

import java.util.ArrayList;
import java.util.Random;

public class Velocista extends Organismo {
    
    public Velocista () {
        super ();
    }
    
    @Override
    public int elegirDireccion (ArrayList<Organismo> organismos, ArrayList<Alimento> alimentos) {
        Random rand = new Random(); 
        return rand.nextInt(4);
    }
}
