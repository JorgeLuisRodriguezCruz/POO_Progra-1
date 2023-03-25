package controller;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import view.Mapa;
import view.MicroGameGUI;
//import view.MicrogamerGUI;

public class Main {

    public static void main(String[] args) {
        System.out.println("Qué pasa? Algun problema?");
        
        //Mapa vent = new Mapa (); 
        //Controlador co = new Controlador(vent);
        
        //MicrogamerGUI uimicro = new MicrogamerGUI();
        MicroGameGUI a = new MicroGameGUI ();
        Controlador coco = new Controlador( a);
    }
}
