package model;
import controller.Controlador;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
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
    
    public void crearOrganismos (ArrayList<JButton> organismos) {
        Random rand = new Random(); 
        
        for (int i = 0; i < organismos.size(); i++) { //JButton get = organismos.get(i);
            int tipo = rand.nextInt(2);
            System.out.println("tipo = " + tipo);
            
            if (tipo == 1)
                this.organismos.add(new Velocista ());
            else
                this.organismos.add(new Visionador()); 
            this.organismos.get(i).setCoordenadas(organismos.get(i).getX(), organismos.get(i).getY());    
        }
    }
    
    public void crearAlimentos (int[][] coordsAlimentos) {
        
        for (int i = 0; i < 21; i++) {
            if (i <= 6) {
                this.alimentos.add(new Energizante());
                //Estas cordenadas indican la posicion en la matriz de JLabel del mapa.
                this.alimentos.get(i).setCoordenadas(coordsAlimentos[i][0], coordsAlimentos[i][1]);
            }
            if (i > 6 && i <= 13){ 
                this.alimentos.add(new Vision());
                this.alimentos.get(i).setCoordenadas(coordsAlimentos[i][0], coordsAlimentos[i][1]);
            }
            if (i > 13){ 
                this.alimentos.add(new Velocidad());
                this.alimentos.get(i).setCoordenadas(coordsAlimentos[i][0], coordsAlimentos[i][1]);
            }
        }
    }
    
    
}