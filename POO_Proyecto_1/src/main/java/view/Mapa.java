package view;

import java.awt.Color;
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
    private ArrayList<JButton> organismos;
    private JPanel principal = new JPanel(); 
    private JLabel casillas[][];
    private JButton boton;
    private int cordenadas [];

    private ArrayList<Alimento> alimentosEnergia;
    private ArrayList<Alimento> alimentosVision;
    private ArrayList<Alimento> alimentosVelocidad;
    private ArrayList<Organismo> crearOrganismos;

    public Mapa() {
        super("Ventana");
        this.organismos = new ArrayList<JButton>(); // Inicialización de la lista organismos
        this.casillas = new JLabel[50][50];
        this.principal = new JPanel();
        this.cordenadas = new int[2];
        this.boton = new JButton();
        this.iniciarComponentes();
        this.iniciarVentana();

        //va en el gestor de partida

        this.alimentosEnergia = new ArrayList<Alimento>();
        this.alimentosVision = new ArrayList<Alimento>();
        this.alimentosVelocidad = new ArrayList<Alimento>();
        this.generarAlimentos();
        this.crearOrganismos= new ArrayList<Organismo>(); // Llamada al método crear Organismos
        this.crearOrganismos();
        
    }

//-----------------------------------------------Se generan alimentos aleatoriamente de los diversos tipos-------------------------

    private void generarAlimentos() {
        Random rand = new Random();
        HashSet<String> coordenadasUsadas = new HashSet<String>();

        //------------ Generar alimentos de energía--------------
        for (int i = 0; i < 5; i++) {
            Alimento alimento = new Alimento();

        // Asignar coordenadas aleatorias, asegurándose de que no choquen con otros alimentos
            int x, y;
            do {
                x = rand.nextInt(50);
                y = rand.nextInt(50);
            } while (coordenadasUsadas.contains(x + "," + y));
            coordenadasUsadas.add(x + "," + y);
            alimento.setCoordenadas(x, y);


        // Agregar alimento energia a la lista y pintar en el mapa
            this.alimentosEnergia.add(alimento);
            this.casillas[x][y].setBackground(Color.BLUE);
        }

        // ---------------Generar alimentos de visión-----------------
        for (int i = 0; i < 5; i++) {
            Alimento alimento = new Alimento();

            // Asignar coordenadas aleatorias, asegurándose de que no choquen con otros alimentos
            int x, y;
            do {
                x = rand.nextInt(50);
                y = rand.nextInt(50);
            } while (coordenadasUsadas.contains(x + "," + y));
            coordenadasUsadas.add(x + "," + y);
            alimento.setCoordenadas(x, y);

            // Agregar alimento vision a la lista y pintar en el mapa
            this.alimentosVision.add(alimento);
            this.casillas[x][y].setBackground(Color.YELLOW);
        }

            //--------------Generar alimentos de velocidad-------------
            for (int i = 0; i < 5; i++) {
                Alimento alimento = new Alimento();
    
                // Asignar coordenadas aleatorias, asegurándose de que no choquen con otros alimentos
                int x, y;
                do {
                    x = rand.nextInt(50);
                    y = rand.nextInt(50);
                } while (coordenadasUsadas.contains(x + "," + y));
                coordenadasUsadas.add(x + "," + y);
                alimento.setCoordenadas(x, y);
    
                // Agregar alimento velocidad a la lista y pintar en el mapa
                this.alimentosVelocidad.add(alimento);
                this.casillas[x][y].setBackground(Color.GREEN);
            }
    }
//----------------------------------Se crean organismos aleatoriamente------------------------------------------------
    private void crearOrganismos() {
        Random rand = new Random();
        HashSet<String> coordenadasUsadas = new HashSet<String>();

        //------------ Generar Organismos--------------
        for (int i = 0; i < 5; i++) {
            Organismo organismo = new Organismo();

        // Asignar coordenadas aleatorias, asegurándose de que no choquen con otros alimentos
            int x, y;
            do {
                x = rand.nextInt(50);
                y = rand.nextInt(50);
            } while (coordenadasUsadas.contains(x + "," + y));
            coordenadasUsadas.add(x + "," + y);
            organismo.setCoordenadas(x, y);


        // Agregar alimento energia a la lista y pintar en el mapa
            this.crearOrganismos.add(organismo);
            this.casillas[x][y].setBackground(Color.magenta);
        }

    }

        private void iniciarComponentes () {
            this.cordenadas[0] = 24;
            this.cordenadas[1] = 24;
    //---------------Se crea panel a la izquierda del mapa donde aparece información de los colores de cada jugador--------------

            // Agregar nuevo panel a la izquierda del panel principal
            JPanel panelLateral = new JPanel();
            panelLateral.setBounds(0, 0, 205, 670);
            panelLateral.setBackground(Color.LIGHT_GRAY);
            panelLateral.setLayout(null);
            this.principal.add(panelLateral);

            // Agregar label y cuadro para el jugador
            JLabel jugadorLabel = new JLabel("Jugador");
            jugadorLabel.setBounds(10, 220, 100, 20);
            panelLateral.add(jugadorLabel);

            JLabel jugadorCuadro = new JLabel();
            jugadorCuadro.setBounds(130, 220, 20, 20);
            jugadorCuadro.setOpaque(true);
            jugadorCuadro.setBackground(Color.RED);
            panelLateral.add(jugadorCuadro);

            // Agregar label y cuadro para la comida velocidad
            JLabel comidaVelo = new JLabel("Comida velocidad");
            comidaVelo.setBounds(10, 250, 100, 20);
            panelLateral.add(comidaVelo);

            JLabel comidaVeloCuadro = new JLabel();
            comidaVeloCuadro.setBounds(130, 250, 20, 20);
            comidaVeloCuadro.setOpaque(true);
            comidaVeloCuadro.setBackground(Color.GREEN);
            panelLateral.add(comidaVeloCuadro);

            // Agregar label y cuadro para la comida energia
            JLabel comidaEnergia = new JLabel("Comida energía");
            comidaEnergia.setBounds(10, 280, 100, 20);
            panelLateral.add(comidaEnergia);

            JLabel comidaEnergiaCuadro = new JLabel();
            comidaEnergiaCuadro.setBounds(130, 280, 20, 20);
            comidaEnergiaCuadro.setOpaque(true);
            comidaEnergiaCuadro.setBackground(Color.BLUE);
            panelLateral.add(comidaEnergiaCuadro);

            // Agregar label y cuadro para la comida vision
            JLabel comidaVision = new JLabel("Comida visión");
            comidaVision.setBounds(10, 310, 100, 20);
            panelLateral.add(comidaVision);

            JLabel comidaVisionCuadro = new JLabel();
            comidaVisionCuadro.setBounds(130, 310, 20, 20);
            comidaVisionCuadro.setOpaque(true);
            comidaVisionCuadro.setBackground(Color.YELLOW);
            panelLateral.add(comidaVisionCuadro);

            // Agregar label y cuadro para la NPC

            JLabel LabelNpc = new JLabel("NPC");
            LabelNpc.setBounds(10, 340, 100, 20);
            panelLateral.add(LabelNpc);

            JLabel NpcCuadro = new JLabel();
            NpcCuadro.setBounds(130, 340, 20, 20);
            NpcCuadro.setOpaque(true);
            NpcCuadro.setBackground(Color.magenta);
            panelLateral.add(NpcCuadro);

    // Se crea 
            this.principal.setBounds(0, 0, 1115, 1000);
            this.principal.setBackground(Color.BLACK);
            this.principal.setLayout(null);
            this.organismos.add(new JButton());
            this.principal.add(this.boton);  
            this.principal.add(this.organismos.get(0));
            
            // this.organismos.get(0).setBounds(325, 325, 12, 12);
            // this.organismos.get(0).setBackground(Color.RED);
            // this.organismos.get(0).setOpaque(true);
            this.boton.setBounds(950, 300, 60, 23);
            
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

    // Se inicia la ventana

        private void iniciarVentana () {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setBackground(Color.WHITE);
            this.setSize(1110, 750); 
            this.setResizable(false);
            this.setLayout(null);
            this.add(principal);
            this.setVisible(false);
        }

        public JButton getBoton() {
            return boton;
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
