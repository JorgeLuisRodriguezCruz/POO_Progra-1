package controller;

import view.Mapa;
import view.MicroGameGUI;
import view.MicrogamerGUI;

public class Main {

    public static void main(String[] args) {
        System.out.println("Qué pasa? Algun problema?");
        
        //Mapa vent = new Mapa ();
        //MicrogamerGUI uimicro = new MicrogamerGUI();
        MicroGameGUI a = new MicroGameGUI ();
        Controlador coco = new Controlador( a);
    }
}
