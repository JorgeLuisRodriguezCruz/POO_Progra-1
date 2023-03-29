package model;

import java.util.ArrayList;
import java.util.Random;

public class Velocista extends Organismo {
    
    public Velocista () {
        super ();
    }
    
    @Override
    public int elegirDireccion (ArrayList<Organismo> organismos, ArrayList<Alimento> alimentos, int turno) {
        Random rand = new Random(); 
        int ramdom = rand.nextInt(4);
        return ramdom;
    }
}
