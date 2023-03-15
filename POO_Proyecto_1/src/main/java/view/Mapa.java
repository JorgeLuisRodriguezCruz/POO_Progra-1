package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mapa extends JFrame {
    private JPanel principal = new JPanel();
    private JLabel casillas[][];
    private JButton boton;
    private JButton YUGADOR;
    private int cordenadas [];
    

    public Mapa() {
        super("Ventana");
        this.casillas = new JLabel[50][50];
        this.principal = new JPanel();
        this.cordenadas = new int[2];
        this.boton = new JButton();
        this.YUGADOR = new JButton();
        this.iniciarComponentes();
        this.iniciarVentana();
    }
    
    private void iniciarComponentes () {
        this.principal.setBounds(0, 0, 1115, 1000);
        this.principal.setBackground(Color.BLACK);
        this.principal.setLayout(null);
        this.principal.add(this.YUGADOR);
        this.principal.add(this.boton);
        
        for (int i=0; i<50; i++){
            for (int j=0; j<50; j++) {
                this.casillas[i][j] = new JLabel();
                this.casillas[i][j].setBounds((i+1)*13, (j+1)*13, 10, 10);
                this.casillas[i][j].setBackground(Color.WHITE);
                this.casillas[i][j].setOpaque(true);
                this.principal.add(this.casillas[i][j]);
            }
        }
        
        this.cordenadas[0] = 24;
        this.cordenadas[1] = 24;
        this.boton.setBounds(700, 13, 60, 23);
        this.YUGADOR.setBounds(663, this.cordenadas[1], 12, 12);
        this.YUGADOR.setBackground(Color.ORANGE);
        this.YUGADOR.setOpaque(true);
    }
    
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

    public JButton getYUGADOR() {
        return YUGADOR;
    }

    public JLabel[][] getCasillas() {
        return casillas;
    }

    public int[] getCordenadas() {
        return cordenadas;
    }
     
}
