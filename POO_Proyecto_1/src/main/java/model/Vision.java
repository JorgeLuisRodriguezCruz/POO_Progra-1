package model;
 
import java.awt.Color;

public class Vision extends Alimento {

    public Vision() {
        super ();
    }
    
    @Override
    public void serComido (Organismo org) {
        org.setVision(org.getVision()+this.cantidad);
        
    }

    @Override
    public Color getColor (){
        return new Color(51, 153, 255);
    }
    
}
