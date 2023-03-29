package model;

import java.util.ArrayList;
import java.util.Random;

public class Visionador extends Organismo {
    
    public Visionador () {
        super ();
    }
    
    @Override
    public int elegirDireccion (ArrayList<Organismo> organismos, ArrayList<Alimento> alimentos, int turno) {        
        Random rand = new Random();  
        int ramdom = rand.nextInt(4);
        
        if (this.organismoALaVista(organismos, turno)){
            return ramdom;
        }
        if (this.alimentosALaVista(alimentos)){
            return ramdom;
        } 
        return ramdom;
    }
}
