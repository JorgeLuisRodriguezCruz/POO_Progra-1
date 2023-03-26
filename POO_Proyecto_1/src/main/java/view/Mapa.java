package view;

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
    public ArrayList<JButton> organismos;
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
    
    private int cordenadas [];

    public Mapa() {
        super("Ventana");
        this.organismos = new ArrayList<JButton>(); // Inicialización de la lista organismos
        this.casillas = new JLabel[50][50];
        this.principal = new JPanel();
        this.cordenadas = new int[2];
        
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
        
        for (int i = 0; i <= 20; i++) { 
            int x = rand.nextInt(50);
            int y = rand.nextInt(50); 
            if (i <= 6)
                this.casillas[x][y].setBackground(Color.BLUE);
            if (i > 6 && i <= 13)
                this.casillas[x][y].setBackground(Color.YELLOW);
            if (i > 13)
                this.casillas[x][y].setBackground(Color.GREEN);
        }
    }
 
    private void crearOrganismos() {
        //El (0, 0) del mapa es X = 312, Y = 13.
        //El centro (25, 25) del mapa es X = 637, Y = 325.
        //El (50, 50) del mapa es X = 949, Y = 650.
        Random rand = new Random();
        
        this.organismos.add(new JButton());  
        this.principal.add(this.organismos.get(0));
        this.organismos.get(0).setBounds(637, 325, 12, 12);
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
        this.cordenadas[0] = 24;
        this.cordenadas[1] = 24;
        
        int sizeFont = 16;
        int widthFont = 140; 
        int xCordTxt = 40;
        int xCordColor = xCordTxt + 150;

        // Agregar label y cuadro para la NPC 
        this.npcs.setForeground(Color.WHITE);
        this.npcs.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.npcs.setBounds(xCordTxt, 340, widthFont, 20); 
 
        this.colorNpcs.setBounds(xCordColor, 340, 20, 20);
        this.colorNpcs.setOpaque(true);
        this.colorNpcs.setBackground(Color.MAGENTA); 
        
        // Agregar label y cuadro para el jugador  
        this.jugadores.setForeground(Color.WHITE);
        this.jugadores.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.jugadores.setBounds(xCordTxt, 220,widthFont, 20);
        
        this.colorJugadores.setBounds(xCordColor, 220, 20, 20);
        this.colorJugadores.setOpaque(true);
        this.colorJugadores.setBackground(Color.RED); 
                 
        // Agregar label y cuadro para la comida vision 
        this.alimentoVision.setForeground(Color.WHITE);
        this.alimentoVision.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.alimentoVision.setBounds(xCordTxt, 310,widthFont, 20); 
 
        this.colorAlimentoVision.setBounds(xCordColor, 310, 20, 20);
        this.colorAlimentoVision.setOpaque(true);
        this.colorAlimentoVision.setBackground(Color.YELLOW); 
 
        // Agregar label y cuadro para la comida energia 
        this.alimentoEnergia.setForeground(Color.WHITE);
        this.alimentoEnergia.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.alimentoEnergia.setBounds(xCordTxt, 280,widthFont, 20); 
 
        this.colorAlimentoEnergia.setBounds(xCordColor, 280, 20, 20);
        this.colorAlimentoEnergia.setOpaque(true);
        this.colorAlimentoEnergia.setBackground(Color.BLUE); 
                
        // Agregar label y cuadro para la comida velocidad 
        this.alimentoVelocidad.setForeground(Color.WHITE);
        this.alimentoVelocidad.setFont(new Font("Comic Sans Ms", Font.BOLD, sizeFont));
        this.alimentoVelocidad.setBounds(xCordTxt, 250, widthFont, 20); 
 
        this.colorAlimentoVelocidad.setBounds(xCordColor, 250, 20, 20);
        this.colorAlimentoVelocidad.setOpaque(true);
        this.colorAlimentoVelocidad.setBackground(Color.GREEN); 
       
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

    public int[] getCordenadas() {
        return cordenadas;
    }

    public ArrayList<JButton> getOrganismos() {
        return organismos;
    }

}
