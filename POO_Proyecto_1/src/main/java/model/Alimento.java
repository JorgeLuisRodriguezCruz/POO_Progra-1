package model;

public class Alimento {
    private int cordenadad[];
    private int cantidad;
    private int turnosParaReaparecer;

    public Alimento() {
        this.cordenadad[0] = 0; this.cordenadad[1] = 0;
        this.cantidad = 1;
        this.turnosParaReaparecer = 0;
    }
    
    public void serComido (Organismo org) { 
    }
    
}
