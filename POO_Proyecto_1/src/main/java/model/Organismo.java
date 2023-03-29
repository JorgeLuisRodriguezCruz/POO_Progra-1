package model;
import view.Mapa;
import view.Informacion;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class Organismo { 
    protected int[] coordenadas;
    protected int edad;
    protected int vision; 
    protected int energia;
    protected int velocidad;
 
    public Organismo () {
        this.coordenadas = new int[2];
        this.coordenadas[0] = 0;
        this.coordenadas[1] = 0;
        this.edad = 1;
        this.vision = 4; 
        this.energia = 10;
        this.velocidad = 3;
    }
    
    protected int huir () {
        return 1;
    }
    
    protected boolean organismoALaVista (ArrayList<Organismo> organismos, int turno) {
        for (int i = 0; i < organismos.size(); i++) {
            if (i != turno) {
                Organismo organismo = organismos.get(i);
                int xOrgCompar = organismo.getCoordenadas()[0];
                int yOrgCompar = organismo.getCoordenadas()[1]; 
                int diferenciaX, diferenciaY;
                
                if (this.coordenadas[0] > xOrgCompar)
                    diferenciaX = this.coordenadas[0] - xOrgCompar;
                else
                    diferenciaX = xOrgCompar - this.coordenadas[0];
                
                if (this.coordenadas[1] > yOrgCompar)
                    diferenciaY = this.coordenadas[1] - yOrgCompar;
                else
                    diferenciaY = yOrgCompar - this.coordenadas[1];
                
                if (diferenciaX <= this.vision && diferenciaY <= this.vision)
                    return true; 
            } 
        }
        return false;
    }
     
    protected boolean alimentosALaVista (ArrayList<Alimento> alimentos) {
        for (int i = 0; i < alimentos.size(); i++) {
            Alimento alimento = alimentos.get(i);
            int xOrgCompar = alimento.getCoordenadas()[0];
            int yOrgCompar = alimento.getCoordenadas()[1]; 
            int diferenciaX, diferenciaY;

            if ((this.coordenadas[0] - 299) / 13 > xOrgCompar)
                diferenciaX = (this.coordenadas[0] - 299) / 13 - xOrgCompar;
            else
                diferenciaX = xOrgCompar - (this.coordenadas[0] - 299) / 13;

            if (this.coordenadas[1] / 13 > yOrgCompar)
                diferenciaY = (this.coordenadas[1] / 13) - yOrgCompar;
            else
                diferenciaY = yOrgCompar - (this.coordenadas[1] / 13);

            if (diferenciaX <= this.vision && diferenciaY <= this.vision)
                return true; 
            
        }
        return false;
    } 
    
    public int elegirDireccion (ArrayList<Organismo> organismos, ArrayList<Alimento> alimentos, int turno){
        return 0;
    }

    public int[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int x, int y) {
        this.coordenadas [0] = x;
        this.coordenadas [1] = y;
    }

    public int getEdad() {
        return edad;
    }

    public int getVision() {
        return vision;
    }

    public int getEnergia() {
        return energia;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }
    
}
