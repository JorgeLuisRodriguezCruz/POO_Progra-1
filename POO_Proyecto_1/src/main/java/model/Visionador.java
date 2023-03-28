package model;

import java.util.ArrayList;
import java.util.Random;

public class Visionador extends Organismo {
    
    public Visionador () {
        super ();
    }
    
    @Override
    public int elegirDireccion (ArrayList<Organismo> organismos, ArrayList<Alimento> alimentos) {        
        Random rand = new Random(); 
        return rand.nextInt(4);
    }
}
