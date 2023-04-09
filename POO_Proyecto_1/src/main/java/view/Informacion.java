package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.dnd.DropTarget;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Informacion extends JFrame {
    private JPanel fondo;
    private JLabel tipo;
    private JLabel edad;
    private JLabel vision;
    private JLabel energia;
    private JLabel velocidad;
    
//Se crea la ventana secundaria de información que aparecerá en el mapa al presionar el "botón" organismo
    public Informacion(){ 
        super("Información");

        this.fondo = new JPanel();
        this.tipo = new JLabel ("Indefinido");
        this.edad = new JLabel("Edad: " + 0);
        this.vision = new JLabel("Visión: " + 0);
        this.energia = new JLabel("Energía: " + 0);
        this.velocidad = new JLabel("Velocidad: " + 0);
        
        this.iniciarComponenetes();
        this.init();
    }
    
    public Informacion(String tipo, int ed, int vis, int en, int vel) {
        super("Informacion");

        this.fondo = new JPanel();
        this.tipo = new JLabel (tipo);
        this.edad = new JLabel("Edad: " + ed);
        this.vision = new JLabel("Vision: " + vis);
        this.energia = new JLabel("Energia: " + en);
        this.velocidad = new JLabel("Velocidad: " + vel);
        
        this.iniciarComponenetes();
        this.init();
    }
//Se inician diversos componentes de la ventana "Información"

    private  void iniciarComponenetes () {
        this.fondo.setBounds(0, 0, 720, 480);
        this.fondo.setBackground(Color.LIGHT_GRAY);
        this.fondo.setLayout(null);
        
        int posX = 150, posY = 25, espacio = 40; 
        
        this.tipo.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.tipo.setBounds(posX, posY - 15, 400, 30);
        
        this.edad.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.edad.setBounds(posX, posY + (espacio * 1), 400, 30);
         
        this.vision.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.vision.setBounds(posX, posY + (espacio * 2), 400, 30);
        
        this.energia.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.energia.setBounds(posX, posY + (espacio * 3), 400, 30);
        
        this.velocidad.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.velocidad.setBounds(posX, posY + (espacio * 4), 400, 30);
        
        this.fondo.add(this.tipo);
        this.fondo.add(this.edad);
        this.fondo.add(this.vision);
        this.fondo.add(this.energia);
        this.fondo.add(this.velocidad);
         
    }
    
    private void init() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setSize(420, 280); 
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(false);
        
        this.add(this.fondo);
    }
//Se actualizan los diversos atributos y datos de cada organismo 

    public void actualisarDatos (int edad, int vision, int energia, int velocidad) { 
        this.edad.setText("Edad: " + edad);
        this.vision.setText("Visión: " + vision);
        this.energia.setText("Energía: " + energia);
        this.velocidad.setText("Velocidad: " + velocidad);
    }
    
    
}
