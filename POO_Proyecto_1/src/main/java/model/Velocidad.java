package model;

public class Velocidad extends Alimento {

    public Velocidad() {
        super ();
    }
    
    @Override
    public void serComido (Organismo org) {
        org.setVelocidad(org.getVelocidad()+this.cantidad);
    }
}
