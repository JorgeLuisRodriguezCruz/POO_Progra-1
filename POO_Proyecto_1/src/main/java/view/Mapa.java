package view;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mapa extends JFrame {
    private ArrayList<JButton> organismos;
    private JPanel principal = new JPanel(); 
    private JLabel casillas[][];
    private JButton boton;
    private int cordenadas [];
    

    public Mapa() {
        super("Ventana");
        this.organismos = new ArrayList<JButton> ();
        this.casillas = new JLabel[50][50];
        this.principal = new JPanel();
        this.cordenadas = new int[2];
        this.boton = new JButton();
        this.iniciarComponentes();
        this.iniciarVentana();
    }
    
    private void iniciarComponentes () {
        this.cordenadas[0] = 24;
        this.cordenadas[1] = 24;
        this.principal.setBounds(0, 0, 1115, 1000);
        this.principal.setBackground(Color.BLACK);
        this.principal.setLayout(null);
        
        this.organismos.add(new JButton());
        this.principal.add(this.boton);  
        this.principal.add(this.organismos.get(0));
        
        this.organismos.get(0).setBounds(325, 325, 12, 12);
        this.organismos.get(0).setBackground(Color.ORANGE);
        this.organismos.get(0).setOpaque(true);
        this.boton.setBounds(700, 13, 60, 23);
        
        for (int i=0; i<50; i++){
            for (int j=0; j<50; j++) {
                this.casillas[i][j] = new JLabel();
                this.casillas[i][j].setBounds((i+1)*13, (j+1)*13, 10, 10);
                this.casillas[i][j].setBackground(Color.WHITE);
                this.casillas[i][j].setOpaque(true);
                this.principal.add(this.casillas[i][j]);
            }
        } 
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
