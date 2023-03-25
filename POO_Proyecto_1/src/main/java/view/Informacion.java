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
    private JLabel edad;
    private JLabel vision;
    private JLabel energia;
    private JLabel velocidad;
    

    public Informacion(int ed, int vis, int en, int vel) {
        super("Informacion");

        this.fondo = new JPanel();
        this.edad = new JLabel("Edad: " + ed);
        this.vision = new JLabel("Vision: " + vis);
        this.energia = new JLabel("Energia: " + en);
        this.velocidad = new JLabel("Velocidad: " + vel);
        
        this.iniciarComponenetes();
        this.init();
    }
    
    private  void iniciarComponenetes () {
        this.fondo.setBounds(0, 0, 720, 480);
        this.fondo.setBackground(Color.LIGHT_GRAY);
        this.fondo.setLayout(null);
        
        this.edad.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.edad.setBounds(150, 20, 400, 30);
         
        this.vision.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.vision.setBounds(150, 60, 400, 30);
        
        this.energia.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.energia.setBounds(150, 100, 400, 30);
        
        this.velocidad.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.velocidad.setBounds(145, 140, 400, 30);
        
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
        this.setVisible(true);
        
        this.add(this.fondo);
    }
    
}
