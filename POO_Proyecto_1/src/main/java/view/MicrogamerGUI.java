package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MicrogamerGUI {

    public MicrogamerGUI(){
        // Crear la ventana principal
        JFrame ventanaPrincipal = new JFrame("MicroGamer                                           Versión 1.1");
       ventanaPrincipal.setSize(600, 680);
       ventanaPrincipal.setLocationRelativeTo(null);
       ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ventanaPrincipal.getContentPane().setBackground(Color.lightGray);
       ventanaPrincipal.setResizable(false);



        // Crear la etiqueta de título

        JLabel labelTitulo = new JLabel("MicroGamer", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Forte", Font.BOLD, 48));
        labelTitulo.setForeground(Color.BLACK);
        labelTitulo.setBounds(100, 20, 420, 50);

        // Crear las etiquetas de saludo y escáner
        JLabel labelMaxAtributos = new JLabel("-Máxima capacidad de los atributos", SwingConstants.CENTER);
        labelMaxAtributos.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        labelMaxAtributos.setBounds(1, 80, 400, 30);

        JTextField MaxAtributos = new JTextField();
        MaxAtributos.setBounds(380, 80, 150, 30);

        JLabel labelMinAtributos = new JLabel("-Mínima capacidad de los atributos  ", SwingConstants.CENTER);
        labelMinAtributos.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        labelMinAtributos.setBounds(1, 120, 400, 30);

        JTextField MinAtributos = new JTextField();
        MinAtributos.setBounds(380, 120, 150, 30);

        JLabel labelAumento = new JLabel("-Aumento de los atributos                 ", SwingConstants.CENTER);
        labelAumento.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        labelAumento.setBounds(20, 160, 400, 30);

        JTextField AumentoAtributos = new JTextField();
        AumentoAtributos.setBounds(380, 160, 150, 30);

        JLabel labelDecremento = new JLabel("-Decremento de los atributos            ", SwingConstants.CENTER);
        labelDecremento.setFont(new Font("Comic Sans Ms", Font.BOLD, 18));
        labelDecremento.setBounds(15, 200, 400, 30);

        JTextField DecrementoAtributos = new JTextField();
        DecrementoAtributos.setBounds(380, 200, 150, 30);

        // Crear el botón de jugar partida


        JButton botonJugar = new JButton("JUGAR");
        botonJugar.setFont(new Font("Comic Sans Ms", Font.BOLD, 28));
        botonJugar.setBounds(80, 270, 200, 40);
        botonJugar.setBackground(Color.GREEN);

        botonJugar.addActionListener(e ->{
            try {
                int valorMAX = Integer.parseInt(MaxAtributos.getText());


                int valorMIN = Integer.parseInt(MinAtributos.getText());


                int valorAUMENTO = Integer.parseInt(AumentoAtributos.getText());


                int valorDECREMENTO = Integer.parseInt(DecrementoAtributos.getText());

              } 
              catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ERROR: Los valores digitados deben de ser enteros. Digite nuevamente los valores.");
              }
        }
    );



            JButton botonSalir = new JButton("SALIR");
            botonSalir.setFont(new Font("Comic Sans Ms", Font.BOLD, 28));
            botonSalir.setBounds(320, 270, 200, 40);
            botonSalir.setBackground(Color.RED);
            
            botonSalir.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    // Cerrar la ventana principal
                    ventanaPrincipal.setVisible(false);

                }
            }
            );
            
            // Crear el frame para la imagen

            JPanel imagePanel = new JPanel();
            imagePanel.setBounds(140, 330, 300, 270);
            imagePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            imagePanel.setBackground(Color.lightGray);

            ImageIcon imagen = new ImageIcon("micro.jpg");
            JLabel labelimagen = new JLabel(imagen);
            
            imagePanel.add(labelimagen);


    
            // Agregar los componentes a la ventana
           ventanaPrincipal.setLayout(null);
           ventanaPrincipal.add(labelTitulo);
           ventanaPrincipal.add(labelMaxAtributos);
           ventanaPrincipal.add(MaxAtributos);
           ventanaPrincipal.add(labelMinAtributos);
           ventanaPrincipal.add(MinAtributos);
           ventanaPrincipal.add(labelAumento);
           ventanaPrincipal.add(AumentoAtributos);
           ventanaPrincipal.add(labelDecremento);
           ventanaPrincipal.add(DecrementoAtributos);
            ventanaPrincipal.add(botonJugar);
           ventanaPrincipal.add(imagePanel);
           ventanaPrincipal.add(botonSalir);


    
                //Mostrar la ventana
           ventanaPrincipal.setVisible(true);
        }
    }

