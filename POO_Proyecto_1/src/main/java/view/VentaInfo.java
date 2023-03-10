package view;

import java.awt.Color;
import javax.swing.JFrame;

public class VentaInfo extends JFrame {

    public VentaInfo() {
        super("Informacion");
        this.init();
    }
    
    private void init() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setSize(720, 480); 
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }
    
}
