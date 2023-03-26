package model;


public class Alimento {
    protected int[] coordenadas;
    protected int cantidad;
    protected int turnosParaReaparecer;

    public Alimento() {
        this.coordenadas = new int[2];
        this.coordenadas[0] = 0; 
        this.coordenadas[1] = 0;
        this.cantidad = 4;
        this.turnosParaReaparecer = 0;

    }

    public void serComido (Organismo org) { }

    public void setCoordenadas(int x, int y) {
        this.coordenadas[0] = x;
        this.coordenadas[1] = y;
    }

}
    

