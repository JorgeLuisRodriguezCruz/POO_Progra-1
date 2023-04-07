package model;
 
import java.awt.Color;

public class Vision extends Alimento {

    public Vision() {
        super ();
    }
    
    @Override
    public void serComido (Organismo org, int maximo, int minimo, int incremento, int decremento) {
        int nuevaCantidad = org.getVision()+(this.cantidad*incremento);
        if (nuevaCantidad <= maximo)
            org.setVision(nuevaCantidad);
    }

    @Override
    public Color getColor (){
        return new Color(51, 153, 255);
    }
    
}
