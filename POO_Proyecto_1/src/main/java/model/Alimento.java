package model;

import java.awt.Color;


public class Alimento {
    protected int[] coordenadas;
    protected int cantidad;
    protected int turnosParaReaparecer;

    public Alimento() {
        this.coordenadas = new int[2];
        this.coordenadas[0] = 0; 
        this.coordenadas[1] = 0;
        this.cantidad = 1;
        this.turnosParaReaparecer = 0; 
    }

    public void serComido (Organismo org, int maximo, int minimo, int incremento, int decremento) { }

    public int[] getCoordenadas() {
        return coordenadas;
    } 
//Se obtienen las coordenadas del alimento respecto a la matriz

    public void setCoordenadas(int x, int y) {
        this.coordenadas[0] = x;
        this.coordenadas[1] = y;
    }

    public Color getColor (){
        return Color.WHITE;
    }
    
}
    

