package controller;

import view.Mapa;

public class Main {

    public static void main(String[] args) {
        System.out.println("Qué pasa? Algun problema?");
        
        Mapa vent = new Mapa ();
        Controlador control = new Controlador(vent);
    }
}
