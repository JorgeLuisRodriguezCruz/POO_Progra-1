package model;

public class Organismo {
    private int cordenadas[];
    private int edad;
    private int vision;
    private int energia;
    private int velocidad;

    public Organismo() { 
        this.cordenadas[0] = 0; this.cordenadas[1] = 0;
        this.edad = 1;
        this.vision = 4;
        this.energia = 9;
        this.velocidad = 3;
    }
    
    public int elegirDireccion () {
        return 0;
    }
    
    public void moverse () {
        
    }
    
}
