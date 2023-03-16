package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MicroGameGUI extends JFrame {
    private JPanel fondo;
    private JLabel titulo;
    private JLabel maximo;
    private JLabel minimo;
    private JLabel aumento;
    private JLabel decremento;
    private JLabel imagen;
    private JButton jugar;
    private JTextField entradaMaximo;
    private JTextField entradaMinimo;
    private JTextField entradaAumento;
    private JTextField entradaDecremento;
    
    public MicroGameGUI () {
        super ("MicroGame");
        this.fondo = new JPanel();
        this.titulo = new JLabel();
        this.maximo = new JLabel();
        this.minimo = new JLabel();
        this.aumento = new JLabel();
        this.decremento = new JLabel();
        this.imagen = new JLabel();
        this.jugar = new JButton();
        this.entradaMaximo = new JTextField();
        this.entradaMinimo = new JTextField();
        this.entradaAumento = new JTextField();
        this.entradaDecremento = new JTextField();
        
        iniciarComponentes ();
        iniciarVentana ();
    }
    
    private void iniciarComponentes () {
        this.fondo.setBounds(0, 0, 600, 680);
        this.fondo.setBackground(Color.LIGHT_GRAY);
        this.fondo.setLayout(null);
        
        this.titulo.setText("MicroGame");
        this.titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.titulo.setFont(new Font("Forte", Font.BOLD, 48));
        this.titulo.setBounds(100, 20, 420, 50); 
        
        this.maximo.setText("Máxima capacidad de atributos:");
        this.maximo.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.maximo.setBounds(20, 100, 400, 30);
        this.entradaMaximo.setBounds(380, 100, 150, 30);
        
        this.minimo.setText("Mínima capacidad de atributos:");
        this.minimo.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.minimo.setBounds(20, 140, 400, 30);
        this.entradaMinimo.setBounds(380, 140, 150, 30);
        
        this.aumento.setText("Escala de aumento:");
        this.aumento.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.aumento.setBounds(20, 180, 400, 30);
        this.entradaAumento.setBounds(380, 180, 150, 30);
        
        this.decremento.setText("Escala de decremento:");
        this.decremento.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        this.decremento.setBounds(20, 220, 400, 30);
        this.entradaDecremento.setBounds(380, 220, 150, 30);
        
        this.imagen.setBounds(140, 340, 300, 250);
        ImageIcon imagen = new ImageIcon ("micro.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(this.imagen.getWidth(), this.imagen.getHeight(), Image.SCALE_DEFAULT));
        this.imagen.setIcon (icono);
        
        this.jugar.setText("JUGAR");
        this.jugar.setFont(new Font("Comic Sans Ms", Font.BOLD, 28));
        this.jugar.setBounds(200, 280, 180, 40);
        this.jugar.setBackground(Color.GREEN);
        this.jugar.setFocusable(false);
        
        this.fondo.add(this.jugar);
        this.fondo.add(this.titulo);
        this.fondo.add(this.maximo);
        this.fondo.add(this.minimo);
        this.fondo.add(this.aumento);
        this.fondo.add(this.decremento);
        this.fondo.add(this.entradaMaximo);
        this.fondo.add(this.entradaMinimo);
        this.fondo.add(this.entradaAumento);
        this.fondo.add(this.entradaDecremento);
        this.fondo.add(this.imagen);
        
    }
    
    private void iniciarVentana () {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.GRAY);
        this.setSize(600, 680); 
        this.setResizable(false);
        this.setLayout(null);
        this.add(this.fondo);
        this.setVisible(true);
    }

    public JButton getJugar() {
        return jugar;
    }

    public JTextField getEntradaMaximo() {
        return entradaMaximo;
    }

    public JTextField getEntradaMinimo() {
        return entradaMinimo;
    }

    public JTextField getEntradaAumento() {
        return entradaAumento;
    }

    public JTextField getEntradaDecremento() {
        return entradaDecremento;
    }
    
    
}
