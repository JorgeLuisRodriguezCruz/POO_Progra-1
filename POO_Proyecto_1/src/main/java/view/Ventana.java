
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana extends JFrame implements ActionListener, KeyListener {
    private JPanel principal = new JPanel();
    private JLabel casillas[][];
    private JButton boton;
    private JButton YUGADOR;
    private int cordenadas [];
    

    public Ventana() {
        super("Ventana");
        this.casillas = new JLabel[50][50];
        this.principal = new JPanel();
        this.cordenadas = new int[2];
        this.boton = new JButton();
        this.YUGADOR = new JButton();
        initTablero();
        init ();
    }
    
    private void initTablero () {
        this.principal.setBounds(0, 0, 1115, 1000);
        this.principal.setBackground(Color.BLACK);
        this.principal.setLayout(null);
        
        this.principal.add(this.YUGADOR);
        
        for (int i=0; i<50; i++){
            for (int j=0; j<50; j++) {
                this.casillas[i][j] = new JLabel();
                System.out.println(" aaa "+(i+1)*13 );
                this.casillas[i][j].setBounds((i+1)*13, (j+1)*13, 10, 10);
                this.casillas[i][j].setBackground(Color.WHITE);
                this.casillas[i][j].setOpaque(true);
                this.principal.add(this.casillas[i][j]);
            }
        }
        this.boton.setBounds(700, 13, 60, 23);
        
        this.boton.addActionListener(this);
        this.YUGADOR.addActionListener(this);
        this.boton.addKeyListener(this);
        this.YUGADOR.addKeyListener(this);
        
        this.principal.add(this.boton);
        this.cordenadas[0] = 24;
        this.cordenadas[1] = 24;
        
        this.YUGADOR.setBounds(663, this.cordenadas[1], 12, 12);
        this.YUGADOR.setBackground(Color.ORANGE);
        this.YUGADOR.setOpaque(true);
        //this.principal.add(this.YUGADOR);
    }
    
    private void init () {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setSize(1110, 1000); 
        this.setResizable(false);
        this.setLayout(null);
        this.add(principal);
        this.setVisible(true);
    }

    private void mover (int x, int y) {
        this.casillas[ this.cordenadas[0] ][ this.cordenadas[1] ].setBackground(Color.WHITE);
        if (this.cordenadas[0]+x != -1 && this.cordenadas[0]+x != 50)
            this.cordenadas[0] = this.cordenadas[0]+x; 
        if (this.cordenadas[1]+y != -1 && this.cordenadas[1]+y != 50)
            this.cordenadas[1] = this.cordenadas[1]+y;
        this.casillas[ this.cordenadas[0] ][ this.cordenadas[1] ].setBackground(Color.RED);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.YUGADOR) {
            VentaInfo info = new VentaInfo ();
        }
        if (e.getSource() == this.boton) { 
            this.casillas[24][24].setBackground(Color.RED);
            this.casillas[0][0].setBackground(Color.BLUE);
            this.casillas[49][49].setBackground(Color.BLUE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {  // 37 a 40 de izq a abajo
        
        switch (e.getKeyCode()) {
            case 37:
                mover(-1, 0);
                this.YUGADOR.setLocation(this.YUGADOR.getX() - 13,this.YUGADOR.getY());
                break;
            case 38:
                mover(0, -1);
                this.YUGADOR.setLocation(this.YUGADOR.getX(),this.YUGADOR.getY() - 13);
                break;
            case 39:
                mover(1, 0);
                this.YUGADOR.setLocation(this.YUGADOR.getX() + 13,this.YUGADOR.getY());
                break;
            case 40:
                mover(0, 1);
                this.YUGADOR.setLocation(this.YUGADOR.getX(),this.YUGADOR.getY()+ 13);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    
}
