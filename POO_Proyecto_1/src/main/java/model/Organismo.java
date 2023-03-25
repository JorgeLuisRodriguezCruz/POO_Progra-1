package model;
import view.Mapa;
import view.Informacion;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

public class Organismo extends JButton implements KeyListener {
 
    private int[] coordenadas;
    private int edad;
    private int vision;
    private int energia;
    private int velocidad;
    public void crearOrganismos;


    public void crearOrganismos() {
        for (int i = 0; i < 10; i++) {
            JButton organismo = new JButton();
            organismo.setBounds(0, 0, 13, 13);
            organismo.setBackground(Color.GREEN);
            organismo.setBorderPainted(false);
            organismo.setFocusPainted(false);
            organismo.setVisible(false);
            organismo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Informacion info = new Informacion();
                    info.mostrarInformacion();
                }
            });
            organismos.add(organismo);
            add(organismo);
        }
    }
}