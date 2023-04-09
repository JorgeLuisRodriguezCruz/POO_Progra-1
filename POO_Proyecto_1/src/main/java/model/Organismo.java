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
        this.edad = 0;
        this.vision = 5; 
        this.energia = 10;
        this.velocidad = 4;
    }
    
    public Organismo(int x, int y) {
    }
//Métdo para que los organismos (en este caso los NPC) sigan al alimento en el mapa
    protected int seguirAlimento (Alimento alimento) { 
        int posAlimenX = alimento.getCoordenadas()[0]; int posAlimenY = alimento.getCoordenadas()[1];
        int posOrgX = ((this.coordenadas[0] - 299) / 13) - 1; int posOrgY = (this.coordenadas[1] / 13) - 1;
        int diferenciaX = 0, diferenciaY = 0;

        if (posOrgX > posAlimenX)
            diferenciaX = posOrgX - posAlimenX;
        else
            diferenciaX = posAlimenX - posOrgX;

        if (posOrgY > posAlimenY)
            diferenciaY = posOrgY - posAlimenY;
        else
            diferenciaY = posAlimenY - posOrgY;
        
        
        if (posOrgX < posAlimenX && posOrgY > posAlimenY) { //El alim esta Der + Arrb
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
        if (posOrgX < posAlimenX && posOrgY < posAlimenY) { //Der + Abaj
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
        if (posOrgX > posAlimenX && posOrgY > posAlimenY) { //Izq + Arrb
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
        if (posOrgX > posAlimenX && posOrgY < posAlimenY) { //Izq + Abaj
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
        if (diferenciaX == 0) {
            if (posOrgY > posAlimenY)
                return 1;
            return 3;
        }
        if (diferenciaY == 0) {
            if (posOrgX > posAlimenX)
                return 0;
            return 2;
        }
        return -1;
    }
//Método para que un organismo (en este caso los NPC) sigan a los otros organismos, haciendo que estos tengan que huir
    protected int seguirOrganismo (Organismo organismo) {
        int xPosOrg = (this.coordenadas[0] - 299) / 13, yPosOrg = this.coordenadas[1] / 13;
        int xOrgCompar = (organismo.getCoordenadas()[0] - 299) / 13, yOrgCompar = organismo.getCoordenadas()[1] / 13;
        int diferenciaX = 0, diferenciaY = 0; 
        
        if (xPosOrg > xOrgCompar)
            diferenciaX = xPosOrg - xOrgCompar;
        else
            diferenciaX = xOrgCompar - xPosOrg;
        if (yPosOrg > yOrgCompar)
            diferenciaY = yPosOrg - yOrgCompar;
        else  
            diferenciaY = yOrgCompar - yPosOrg; 
        
        if (xPosOrg < xOrgCompar && yPosOrg > yOrgCompar) { //esta Der + Arrb
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
        if (xPosOrg < xOrgCompar && yPosOrg < yOrgCompar) { //Der + Abaj
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
        if (xPosOrg > xOrgCompar && yPosOrg > yOrgCompar) { //Izq + Arrb
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
        if (xPosOrg > xOrgCompar && yPosOrg < yOrgCompar) { //Izq + Abaj
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
        if (diferenciaX == 0) {
            if (yPosOrg > yOrgCompar)
                return 1;
            return 3;
        }
        if (diferenciaY == 0) {
            if (xPosOrg > xOrgCompar)
                return 0;
            return 2;
        }
        return -1;
    }
//Método para que un organismo al verse en peligro, huya de los organismos que le siguen

    protected int huirDeOrganismo (Organismo organismo) { 
        int xPosOrg = (this.coordenadas[0] - 299) / 13, yPosOrg = this.coordenadas[1] / 13;
        int xOrgCompar = (organismo.getCoordenadas()[0] - 299) / 13, yOrgCompar = organismo.getCoordenadas()[1] / 13;
        int diferenciaX = 0, diferenciaY = 0; 
        
        if (xPosOrg > xOrgCompar)
            diferenciaX = xPosOrg - xOrgCompar;
        else
            diferenciaX = xOrgCompar - xPosOrg;
        if (yPosOrg > yOrgCompar)
            diferenciaY = yPosOrg - yOrgCompar;
        else  
            diferenciaY = yOrgCompar - yPosOrg; 
        

        
        if (xPosOrg < xOrgCompar && yPosOrg > yOrgCompar) { //está Derecha + Arriba
            if (diferenciaX == diferenciaY){
                if (new Random().nextInt(2) == 0) // Izquierda o Abajo
                    return 0;
                return 3; 
            }
            if (diferenciaX > diferenciaY) {
                return 0;
            } else { 
                return 3;
            }
        }
        if (xPosOrg < xOrgCompar && yPosOrg < yOrgCompar) { //Derecha + Abajo
            if (diferenciaX == diferenciaY){
                if (new Random().nextInt(2) == 0) // Izquierda o Arriba
                    return 0;
                return 1; 
            } 
            if (diferenciaX > diferenciaY) {
                return 0;
            } else { 
                return 1;
            } 
        }
        if (xPosOrg > xOrgCompar && yPosOrg > yOrgCompar) { //Izquierda + Arriba
            if (diferenciaX == diferenciaY){
                if (new Random().nextInt(2) == 0) // Derecha o Abajo
                    return 2;
                return 3; 
            }
            if (diferenciaX > diferenciaY) {
                return 2;
            } else { 
                return 3;
            }
        } 
        if (xPosOrg > xOrgCompar && yPosOrg < yOrgCompar) { //Izquierda + Abajo
            if (diferenciaX == diferenciaY){
                if (new Random().nextInt(2) == 0) // Derecha o Arriba
                    return 2;
                return 1; 
            }
            if (diferenciaX > diferenciaY) {
                return 2;
            } else { 
                return 1;
            }
        } 
        if (diferenciaX == 0) {
            if (yPosOrg > yOrgCompar)
                return 3;
            return 1;
        }
        if (diferenciaY == 0) {
            if (xPosOrg > xOrgCompar)
                return 2;
            return 0;
        }
        return -1;
    }
//Método para identificar si existe un organismo cercano respecto a los otros organismos

    public Organismo organismoMasSercano (ArrayList<Organismo> organismos, int turno) {
        Organismo organismoSercano = null;
        int distanciaMenor = 0;
        
        for (int i = 0; i < organismos.size(); i++) {
            if (i != turno) {
                Organismo organismo = organismos.get(i);
                
                int posOrgX = (this.coordenadas[0] - 299) / 13, posOrgY = this.coordenadas[1] / 13;
                int posOrgComparX = (organismo.getCoordenadas()[0] - 299) / 13, posOrgComparY = organismo.getCoordenadas()[1] / 13; 
                int diferenciaX, diferenciaY, distancia;
                
                if (posOrgX > posOrgComparX)
                    diferenciaX = posOrgX - posOrgComparX;
                else
                    diferenciaX = posOrgComparX - posOrgX;
                
                if (posOrgY > posOrgComparY)
                    diferenciaY = posOrgY - posOrgComparY;
                else
                    diferenciaY = posOrgComparY - posOrgY;
                
                if (diferenciaX  <= this.vision && diferenciaY <= this.vision){
                    distancia = (diferenciaX + diferenciaY) / 2;
                    if (diferenciaX == 0)
                        distancia = diferenciaY;
                    if (diferenciaY == 0)
                        distancia =  diferenciaX;
                    
                    if (distanciaMenor == 0 || distanciaMenor > distancia){
                        distanciaMenor = distancia;
                        organismoSercano = organismo;
                    }
                }
            }
        }
        return organismoSercano;
    }
    
    public Alimento alimentosALaVista (ArrayList<Alimento> alimentos) {
        return null;
    } 
    
    public int cantidadMovimiento (Organismo organismo, int direccion) {
        int xPosOrg = (this.coordenadas[0] - 299) / 13, yPosOrg = this.coordenadas[1] / 13;
        int xOrgCompar = (organismo.getCoordenadas()[0] - 299) / 13, yOrgCompar = organismo.getCoordenadas()[1] / 13;
        int diferenciaX = 0, diferenciaY = 0; 
        
        if (this.coordenadas[0] > xOrgCompar)
            diferenciaX = this.coordenadas[0] - xOrgCompar;
        else
            diferenciaX = xOrgCompar - this.coordenadas[0];
        if (this.coordenadas[1] > yOrgCompar)
            diferenciaY = this.coordenadas[1] - yOrgCompar;
        else  
            diferenciaY = yOrgCompar - this.coordenadas[1]; 
        
        if (direccion == 0 || direccion == 2){
            if (this.velocidad >= diferenciaX)
                return diferenciaX;
            return this.velocidad;
        }
        if (direccion == 1 || direccion == 3){ 
            if (this.velocidad >= diferenciaY)
                return diferenciaY;
            return this.velocidad;
        }
        return 1;
    }
//Método para saber la cantidad de movimientos que un organismo debe realizar en su correspondiente turno

    public int cantidadMovimiento (Alimento alimento, int direccion) {
        int posAlimenX = alimento.getCoordenadas()[0]; int posAlimenY = alimento.getCoordenadas()[1];
        int posOrgX = ((this.coordenadas[0] - 299) / 13) - 1; int posOrgY = (this.coordenadas[1] / 13) - 1;
        int diferenciaX = 0, diferenciaY = 0;

        if (posOrgX > posAlimenX)
            diferenciaX = posOrgX - posAlimenX;
        else
            diferenciaX = posAlimenX - posOrgX;

        if (posOrgY > posAlimenY)
            diferenciaY = posOrgY - posAlimenY;
        else
            diferenciaY = posAlimenY - posOrgY;
        
        if (direccion == 0 || direccion == 2){
            if (this.velocidad >= diferenciaX)
                return diferenciaX;
            return this.velocidad;
        }
        if (direccion == 1 || direccion == 3){ 
            if (this.velocidad >= diferenciaY)
                return diferenciaY;
            return this.velocidad;
        }
        return 0;
    }
    
    public int elegirDireccion () {
        return -1;
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

    public void reiniciarAtributos () {
        this.edad = 1;
        this.vision = 4;
        this.energia = 10;
        this.velocidad = 3;
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
    
    public void setVision(int vision) {
        this.vision = vision;
    }
    
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
}
