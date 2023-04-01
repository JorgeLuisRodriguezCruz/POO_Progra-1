package model;
import view.Mapa;
import view.Informacion;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
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
    
    protected int seguirAlimento (Alimento alimento) { 
        int posAlimenX = alimento.getCoordenadas()[0]; int posAlimenY = alimento.getCoordenadas()[1];
        int posOrgX = ((this.coordenadas[0] - 299) / 13) - 1; int posOrgY = (this.coordenadas[1] / 13) - 1;
        int diferenciaX, diferenciaY; 

        if (posOrgX > posAlimenX)
            diferenciaX = posOrgX - posAlimenX;
        else
            diferenciaX = posAlimenX - posOrgX;

        if (posOrgY > posAlimenY)
            diferenciaY = posOrgY - posAlimenY;
        else
            diferenciaY = posAlimenY - posOrgY;
        
        if (posOrgX < posAlimenX && posOrgY < posAlimenY) { //Der + Arrb
            if (diferenciaX == diferenciaY){
                if (new Random().nextInt(2) == 0) // Der o Arrb
                    return 2;
                return 1; 
            }
            if (diferenciaX > diferenciaY) {
                return 2;
            } else { 
                return 1;
            }
        }  
        if (posOrgX < posAlimenX && posOrgY > posAlimenY) { //Der + Abaj
            if (diferenciaX == diferenciaY){
                if (new Random().nextInt(2) == 0) // Der o Abaj
                    return 2;
                return 3; 
            }
            if (diferenciaX > diferenciaY) {
                return 2;
            } else { 
                return 3;
            }
        }  
        if (posOrgX > posAlimenX && posOrgY < posAlimenY) { //Izq + Arrb
            if (diferenciaX == diferenciaY){
                if (new Random().nextInt(2) == 0) // Izq o Arrb
                    return 0;
                return 1; 
            }
            if (diferenciaX > diferenciaY) {
                return 0;
            } else { 
                return 1;
            }
        }  
        if (posOrgX > posAlimenX && posOrgY > posAlimenY) { //Izq + Abaj
            if (diferenciaX == diferenciaY){
                if (new Random().nextInt(2) == 0) // Izq o Abaj
                    return 0;
                return 3; 
            }
            if (diferenciaX > diferenciaY) {
                return 0;
            } else { 
                return 3;
            }
        }
        return -1;
    }
    
    protected int seguirOrganismo (Organismo organismo) {
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
        
        if (diferenciaX == diferenciaY) {
            if (this.coordenadas[0] < xOrgCompar) {     // El org esta por derecha
                if (this.coordenadas[1] > yOrgCompar) { // El org esta por arriba
                    if (new Random().nextInt(2) == 0) // Der o arrib
                        return 2;
                    return 1;
                } else {
                    if (new Random().nextInt(2) == 0) // Der o Abaj
                        return 2;
                    return 3;
                }
            } else {                                    // El org esta por Izquierda
                if (this.coordenadas[1] > yOrgCompar) { // El org esta por arriba
                    if (new Random().nextInt(2) == 0) // Izq o arrib
                        return 0;
                    return 1; 
                } else { 
                    if (new Random().nextInt(2) == 0) // Izq o Abaj
                        return 0;
                    return 3;
                }
            }
        } 
        if (diferenciaX > diferenciaY) {
            if (this.coordenadas[0] > xOrgCompar) {  // El org esta por la izquierda
                return 0; 
            } else {
                return 2;
            }
        }
        if (diferenciaX < diferenciaY) {
            if (this.coordenadas[1] > yOrgCompar) {  // El org esta por la arriba
                return 1;
            } else {
                return 3;
            }
        }
        return -1;
    }
    
    protected int huirDeOrganismo (Organismo organismo) {
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
        
        if (diferenciaX == diferenciaY) {
            if (this.coordenadas[0] < xOrgCompar) {
                if (this.coordenadas[1] > yOrgCompar) { // 0 - 3 / Izq - Abaj
                    if (this.coordenadas[0] >= 624)
                        return 0; 
                    if (this.coordenadas[1] <= 325)
                        return 3;
                    if (new Random().nextInt(2) == 0)
                        return 0;
                    return 3;
                } else { // 0  - 1 / Izq - Arrib
                    if (this.coordenadas[0] >= 624)
                        return 0; 
                    if (this.coordenadas[1] >= 325)
                        return 1;
                    if (new Random().nextInt(2) == 0)
                        return 0;
                    return 1; 
                }
            }
            if (this.coordenadas[0] > xOrgCompar) { 
                
                if (this.coordenadas[1] > yOrgCompar) { // 2 - 3 / Der - Abaj
                    if (this.coordenadas[0] <= 624)
                        return 2; 
                    if (this.coordenadas[1] <= 325)
                        return 3;
                    if (new Random().nextInt(2) == 0)
                        return 2;
                    return 3;
                } else {   // 1 - 2 / Arrib - Der
                    if (this.coordenadas[0] <= 624)
                        return 2; 
                    if (this.coordenadas[1] >= 325)
                        return 1;
                    if (new Random().nextInt(2) == 0)
                        return 2;
                    return 1;
                }
            }
        } 
        if (diferenciaX > diferenciaY) {
            if (this.coordenadas[0] > xOrgCompar) {
                if (this.coordenadas[0] <= 624) // Mover derecha
                    return 2;
                if (new Random().nextInt(2) == 0) // Mueve arriba o abajo
                    return 1;       //Arrib
                return 3;           //Abaj
            } else {
                if (this.coordenadas[0] >= 624) // Mover izq
                    return 0;
                if (new Random().nextInt(2) == 0) // Mueve arriba o abajo
                    return 1;       //Arrib
                return 3;           //Abaj 
            }
        }
        if (diferenciaX < diferenciaY) {
            if (this.coordenadas[1] > yOrgCompar) {
                if (this.coordenadas[1] <=  325) // Mover abajo
                    return 3;
                if (new Random().nextInt(2) == 0) // Mueve der o izq
                    return 2;       //der
                return 0;           //izq
            } else {
                if (this.coordenadas[1] >=  325) // Mover arrib
                    return 1;
                if (new Random().nextInt(2) == 0) // Mueve der o izq
                    return 2;       //der
                return 0;           //izq
            }
        }
        return -1;
    }
    
    protected Organismo organismoMasSercano (ArrayList<Organismo> organismos, int turno) {
        Organismo organismoSercano = null;
        int distanciaMenor = 0;
        
        for (int i = 0; i < organismos.size(); i++) {
            if (i != turno) {
                Organismo organismo = organismos.get(i);
                int xOrgCompar = organismo.getCoordenadas()[0];
                int yOrgCompar = organismo.getCoordenadas()[1]; 
                int diferenciaX, diferenciaY, distancia;
                
                if (this.coordenadas[0] > xOrgCompar)
                    diferenciaX = this.coordenadas[0] - xOrgCompar;
                else
                    diferenciaX = xOrgCompar - this.coordenadas[0];
                
                if (this.coordenadas[1] > yOrgCompar)
                    diferenciaY = this.coordenadas[1] - yOrgCompar;
                else
                    diferenciaY = yOrgCompar - this.coordenadas[1];
                
                if (diferenciaX <= this.vision && diferenciaY <= this.vision){
                    distancia = (diferenciaX + diferenciaY) / 2;
                    if (distanciaMenor == 0 || distanciaMenor > distancia){
                        distanciaMenor = distancia;
                        organismoSercano = organismo;
                    }
                }
            }
        }
        return organismoSercano;
    }
         
    protected boolean alimentosALaVista (ArrayList<Alimento> alimentos) {
        for (int i = 0; i < alimentos.size(); i++) {
            Alimento alimento = alimentos.get(i);
            int xAlimenCompar = alimento.getCoordenadas()[0]; int yAlimenCompar = alimento.getCoordenadas()[1]; 
            int posOrgX = ((this.coordenadas[0] - 299) / 13) - 1; int posOrgY = (this.coordenadas[1] / 13) - 1;
            int diferenciaX, diferenciaY;

            if (posOrgX > xAlimenCompar)
                diferenciaX = posOrgX - xAlimenCompar;
            else
                diferenciaX = xAlimenCompar - posOrgX;

            if (posOrgY > yAlimenCompar)
                diferenciaY = posOrgY - yAlimenCompar;
            else
                diferenciaY = yAlimenCompar - posOrgY;

            if (diferenciaX <= this.vision && diferenciaY <= this.vision)
                return true; 
            
        }
        return false;
    } 
    
    public int elegirDireccion (ArrayList<Organismo> organismos, ArrayList<Alimento> alimentos, int turno){
        return 0;
    }

    public String identificarse (){
        return "Indefinido";
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
