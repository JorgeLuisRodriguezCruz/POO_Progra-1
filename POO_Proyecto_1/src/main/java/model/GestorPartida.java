package model;
import controller.Controlador;
import java.util.ArrayList;
import view.Mapa;

public class GestorPartida {
    private Controlador controlador;
    private ArrayList<Organismo> organismos;
    private ArrayList<Alimento> alimentos;

    public GestorPartida(Controlador controlador) {
        this.controlador = controlador;
        this.organismos = new ArrayList<Organismo>();
        this.alimentos = new ArrayList<Alimento>(); 
    }
    
    
}