package view;

import controller.Controlador;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Alimento;
import model.Energizante;
import model.Organismo;
import model.Vision;

public class Mapa extends JFrame {
    private Controlador controlador;
    private ArrayList<JButton> organismos;
    private JButton mostrarVision;
    private JButton simular;
    private JButton automatico;
    private JPanel principal;
    private JPanel informacionJuego;
    private JLabel casillas[][];
    
    private JLabel npcs;
    private JLabel jugadores;
    private JLabel alimentoVelocidad;
    private JLabel alimentoEnergia;
    private JLabel alimentoVision;
    
    private JLabel colorNpcs;
    private JLabel colorJugadores;
    private JLabel colorAlimentoVelocidad;
    private JLabel colorAlimentoEnergia;
    private JLabel colorAlimentoVision; 
    
    public Mapa() {
        super("Ventana");
        
        this.organismos = new ArrayList<JButton>(); // Inicialización de la lista organismos
        this.casillas = new JLabel[50][50];
        this.principal = new JPanel();
        this.mostrarVision = new JButton("Mostrar vision");
        this.simular = new JButton("Simular");
        this.automatico = new JButton("NPC automaticos");
        
        this.npcs = new JLabel("NPC");
        this.jugadores = new JLabel("Jugador");
        this.alimentoVision = new JLabel("Comida vision"); 
        this.alimentoEnergia = new JLabel("Comida energia");
        this.alimentoVelocidad = new JLabel("Comida velocidad");
        
        this.colorNpcs = new JLabel();
        this.colorJugadores = new JLabel();
        this.colorAlimentoVision = new JLabel();
        this.colorAlimentoEnergia = new JLabel();
        this.colorAlimentoVelocidad = new JLabel(); 
        
        
        this.iniciarComponentes ();
        this.generarAlimentos();
        this.iniciarVentana();
        
    }

    public Mapa(Controlador controlador) {
        super("Ventana");
        this.controlador = controlador;
        this.organismos = new ArrayList<JButton>(); // Inicialización de la lista organismos
        this.casillas = new JLabel[50][50];
        this.principal = new JPanel();
        this.mostrarVision = new JButton("Mostrar vision");
        this.simular = new JButton("Simular");
        this.automatico = new JButton("NPC automaticos");
        
        this.npcs = new JLabel("NPC");
        this.jugadores = new JLabel("Jugador");
        this.alimentoVision = new JLabel("Comida vision"); 
        this.alimentoEnergia = new JLabel("Comida energia");
        this.alimentoVelocidad = new JLabel("Comida velocidad");
        
        this.colorNpcs = new JLabel();
        this.colorJugadores = new JLabel();
        this.colorAlimentoVision = new JLabel();
        this.colorAlimentoEnergia = new JLabel();
        this.colorAlimentoVelocidad = new JLabel(); 
        
        
        this.iniciarComponentes ();
        this.generarAlimentos();
        this.iniciarVentana();
    }
 
    private void generarAlimentos() {
        Random rand = new Random(); 
        int coordsAlimentos [][] = new int[21][2];
        
        int i = 0;
        while (i <= 20) {
            int x = rand.nextInt(50);
            int y = rand.nextInt(50);  
            
            if (this.casillas[x][y].getBackground() == Color.WHITE) { 
                coordsAlimentos [i][0] = x;
                coordsAlimentos [i][1] = y;
                if (i <= 6)
                    this.casillas[x][y].setBackground(Color.BLUE);  //energia
                if (i > 6 && i <= 13)
                    this.casillas[x][y].setBackground(Color.YELLOW);//vision
                if (i > 13)
                    this.casillas[x][y].setBackground(Color.GREEN); // velocidad
                i++;
            }
        }
        this.controlador.pasarCoordsAlimentos(coordsAlimentos);
    }
 
    private void crearOrganismos() {
        //El (0, 0) del mapa es X = 312, Y = 13.
        //El centro (25, 25) del mapa es X = 624, Y = 325.
        //El (50, 50) del mapa es X = 949, Y = 650.
        Random rand = new Random();
        
        this.organismos.add(new JButton());  
        this.principal.add(this.organismos.get(0));
        this.organismos.get(0).setBounds(624, 325, 12, 12);
        this.organismos.get(0).setBackground(Color.RED);
        this.organismos.get(0).setOpaque(true);
        
        for (int i = 1; i <= 5; i++) { 
            
            int x = rand.nextInt(50);
            int y = rand.nextInt(50);
            
            this.organismos.add(new JButton()); 
            this.principal.add(this.organismos.get(i)); 
            this.organismos.get(i).setBounds(13*(x+1) + 299, 13*(y+1), 12, 12);
            this.organismos.get(i).setBackground(Color.MAGENTA);
            this.organismos.get(i).setOpaque(true);
            
            //this.casillas[x][y].setBackground(Color.magenta);
        }
    }

    private void iniciarComponentes () {
        //this.cordenadas[0] = 24;
        //this.cordenadas[1] = 24;
        
        int sizeFont = 16;
        int widthFont = 140; 
        int xCordTxt = 40;
        int yCordTxt = 130;
        int xCordColor = xCordTxt + 150;

        // Agregar label y cuadro para la NPC 
        this.npcs.setForeground(Color.WHITE);
        this.npcs.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.npcs.setBounds(xCordTxt, yCordTxt + 120, widthFont, 20); 
 
        this.colorNpcs.setBounds(xCordColor, yCordTxt + 120, 20, 20);
        this.colorNpcs.setOpaque(true);
        this.colorNpcs.setBackground(Color.MAGENTA); 
        
        // Agregar label y cuadro para el jugador  
        this.jugadores.setForeground(Color.WHITE);
        this.jugadores.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.jugadores.setBounds(xCordTxt, yCordTxt,widthFont, 20);
        
        this.colorJugadores.setBounds(xCordColor, yCordTxt, 20, 20);
        this.colorJugadores.setOpaque(true);
        this.colorJugadores.setBackground(Color.RED); 
                 
        // Agregar label y cuadro para la comida vision 
        this.alimentoVision.setForeground(Color.WHITE);
        this.alimentoVision.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.alimentoVision.setBounds(xCordTxt, yCordTxt + 90,widthFont, 20); 
 
        this.colorAlimentoVision.setBounds(xCordColor, yCordTxt + 90, 20, 20);
        this.colorAlimentoVision.setOpaque(true);
        this.colorAlimentoVision.setBackground(Color.YELLOW); 
 
        // Agregar label y cuadro para la comida energia 
        this.alimentoEnergia.setForeground(Color.WHITE);
        this.alimentoEnergia.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.alimentoEnergia.setBounds(xCordTxt, yCordTxt + 60,widthFont, 20); 
 
        this.colorAlimentoEnergia.setBounds(xCordColor, yCordTxt + 60, 20, 20);
        this.colorAlimentoEnergia.setOpaque(true);
        this.colorAlimentoEnergia.setBackground(Color.BLUE); 
                
        // Agregar label y cuadro para la comida velocidad 
        this.alimentoVelocidad.setForeground(Color.WHITE);
        this.alimentoVelocidad.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.alimentoVelocidad.setBounds(xCordTxt, yCordTxt + 30, widthFont, 20); 
 
        this.colorAlimentoVelocidad.setBounds(xCordColor, yCordTxt + 30, 20, 20);
        this.colorAlimentoVelocidad.setOpaque(true);
        this.colorAlimentoVelocidad.setBackground(Color.GREEN);
        
        this.mostrarVision.setBounds(xCordTxt, yCordTxt + 180, widthFont + 20, 26);
        this.mostrarVision.setForeground(Color.BLACK);
        this.mostrarVision.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.mostrarVision.setFocusable(false);
        this.mostrarVision.setBackground(new Color(224, 224, 224)); 
        
        this.simular.setBounds(xCordTxt, yCordTxt + 220, widthFont - 36, 26);
        this.simular.setForeground(Color.BLACK);
        this.simular.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.simular.setFocusable(false);
        this.simular.setBackground(new Color(224, 224, 224));
         
        this.automatico.setBounds(xCordTxt, yCordTxt + 260, widthFont + 20, 26);
        this.automatico.setForeground(Color.BLACK);
        this.automatico.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont - 2));
        this.automatico.setFocusable(false);
        this.automatico.setBackground(new Color(102, 255, 102));
        
        this.principal.add(this.mostrarVision);
        this.principal.add(this.simular);
        this.principal.add(this.automatico);
        
        this.principal.add(this.npcs);
        this.principal.add(this.jugadores);
        this.principal.add(this.alimentoVision);
        this.principal.add(this.alimentoEnergia);
        this.principal.add(this.alimentoVelocidad);
        this.principal.add(this.colorNpcs);
        this.principal.add(this.colorJugadores);
        this.principal.add(this.colorAlimentoVision);
        this.principal.add(this.colorAlimentoEnergia);
        this.principal.add(this.colorAlimentoVelocidad); 
        
        this.principal.setBounds(0, 0, 1115, 1000);
        this.principal.setBackground(Color.GRAY);
        this.principal.setLayout(null);
        
        this.crearOrganismos();

        for (int i=0; i<50; i++){
            for (int j=0; j<50; j++) {
                this.casillas[i][j] = new JLabel();
                this.casillas[i][j].setBounds((i+1)*13 +300, (j+1)*13, 10, 10);
                this.casillas[i][j].setBackground(Color.WHITE);
                this.casillas[i][j].setOpaque(true);
                this.principal.add(this.casillas[i][j]);
            }
        } 
    }

    private void iniciarVentana () {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setSize(1110, 720); 
        this.setResizable(false);
        this.setLayout(null);
        this.add(principal);
        this.setVisible(false);
    }

    public JLabel[][] getCasillas() {
        return casillas;
    }

    public ArrayList<JButton> getOrganismos() {
        return organismos;
    }

    public JButton getMostrarVision() {
        return mostrarVision;
    }

    public JButton getSimular() {
        return simular;
    }

    public JButton getAutomatico() {
        return automatico;
    }

    
}
