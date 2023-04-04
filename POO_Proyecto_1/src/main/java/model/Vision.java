package model;
 
public class Vision extends Alimento {

    public Vision() {
        super ();
    }
    
    @Override
    public void serComido (Organismo org) {
        org.setVision(org.getVision()+this.cantidad);
        
    }
}
