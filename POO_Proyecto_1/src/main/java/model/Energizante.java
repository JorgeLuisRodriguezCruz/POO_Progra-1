package model;

public class Energizante extends Alimento {

    public Energizante() {
        super ();
    }
    
    @Override
    public void serComido (Organismo org) {
        org.setEnergia(org.getEnergia()+this.cantidad);
        
    }
}
