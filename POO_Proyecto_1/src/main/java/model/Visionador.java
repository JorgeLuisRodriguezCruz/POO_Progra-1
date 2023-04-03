package model;

import java.util.ArrayList;
import java.util.Random;

public class Visionador extends Organismo {
    
    public Visionador () {
        super ();
    }
    
    @Override
    public int elegirDireccion () { 
        int pos_X = this.coordenadas[0], pos_Y = this.coordenadas[1];
        
        if (pos_X < 468)
            return 2;
        if (pos_X > 780)
            return 0;
        if (pos_Y < 169)
            return 3;
        if (pos_Y > 481)
            return 1;
        
        if (pos_X > 624 && pos_Y < 481) {
            if (pos_Y < 325 && pos_Y < 78)
                return 0;
            if (pos_Y < 325 && pos_Y > 78)
                return 1; 
            if (pos_Y > 325 && pos_X < 715)
                return 2; 
            if (pos_Y > 325 && pos_X > 715)
                return 1;
        } 
        if (pos_X < 624 && pos_Y < 481) {
            if (pos_Y < 325 && pos_X > 377)
                return 0;
            if (pos_Y < 325 && pos_X < 377)
                return 3;
            if (pos_Y < 325 && pos_Y < 416)
                return 3; 
            if (pos_Y < 325 && pos_Y < 416)
                return 2;
        }
        if (pos_X == 624){
            if(pos_Y >= 325)
                return 2;
            return 0;
        }
        if (pos_Y == 325){
            if(pos_X >= 624)
                return 1;
            return 3;
        } 
        return 0;
    }
    
    @Override
    public Alimento alimentosALaVista (ArrayList<Alimento> alimentos) {
        Alimento alimentoElegido = null; 
        int inicialIndx = 0, finalIndx = 0;
        
        for (int tipos = 0; tipos < 3; tipos++) { // Orden de busqueda por tipoAlimento 0 = Energia, 1 = Vision, 2 = Velocidad.
            if (tipos == 0){
                inicialIndx = 0; finalIndx = 6;
            } 
            if (tipos == 1) {
                if (alimentoElegido != null)
                    return alimentoElegido;    
                inicialIndx = 7; finalIndx = 13;
            }
            if (tipos == 2) {
                if (alimentoElegido != null)
                    return alimentoElegido;
                inicialIndx = 14; finalIndx = 20;
            }

            for (int i = inicialIndx; i <= finalIndx; i++) {
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
                    alimentoElegido = alimento; 
            } 
        } 
        return alimentoElegido;
    }
    
    @Override
    public String identificarse (){
        return "Visionador";
    }
    
}
