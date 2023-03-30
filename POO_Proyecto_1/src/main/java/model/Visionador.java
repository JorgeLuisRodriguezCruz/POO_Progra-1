package model;

import java.util.ArrayList;
import java.util.Random;

public class Visionador extends Organismo {
    
    public Visionador () {
        super ();
    }
    
    @Override
    public int elegirDireccion (ArrayList<Organismo> organismos, ArrayList<Alimento> alimentos, int turno) {        
        Random rand = new Random();  
        int ramdom = rand.nextInt(4);
        Organismo organismoEncontrado = this.organismoMasSercano(organismos, turno);
        
        if (organismoEncontrado != null){ 
            if (organismoEncontrado.getEnergia() >= this.energia)
                return this.huirDeOrganismo(organismoEncontrado); 
            return this.seguirOrganismo(organismoEncontrado);
        }
        if (this.alimentosALaVista(alimentos)){
            Alimento alimentoElegido = null;
            
            int inicialIndx = 0, finalIndx = 0; 
            for (int tipos = 0; tipos < 3; tipos++) { // Orden de busqueda por tipoAlimento 0 = Energia, 1 = Vision, 2 = Velocidad.
                if (tipos == 0)    
                    inicialIndx = 0; finalIndx = 6;
                if (tipos == 1) {
                    if (alimentoElegido != null)
                        return this.seguirAlimento(alimentoElegido);
                    
                    inicialIndx = 7; finalIndx = 13;
                }
                if (tipos == 2) {
                    if (alimentoElegido != null)
                        return this.seguirAlimento(alimentoElegido);
                    
                    inicialIndx = 14; finalIndx = 20;
                }
                    
                for (int i = inicialIndx; i <= finalIndx; i++) {
                    Alimento alimento = alimentos.get(i);
                    int xAlimenCompar = alimento.getCoordenadas()[0]; int yAlimenCompar = alimento.getCoordenadas()[1]; 
                    int posOrgX = (this.coordenadas[0] - 299) / 13; int posOrgY = (this.coordenadas[1] / 13);
                    int diferenciaX, diferenciaY;

                    if (posOrgX > xAlimenCompar)
                        diferenciaX = posOrgX - xAlimenCompar;
                    else
                        diferenciaX = xAlimenCompar - posOrgX;
                    if (posOrgY > yAlimenCompar)
                        diferenciaY = posOrgY - yAlimenCompar;
                    else
                        diferenciaY = yAlimenCompar - posOrgY;

                    if (diferenciaX <= this.vision && diferenciaY <= this.vision) {
                        if (this.velocidad % 2 == 0){
                            if (posOrgX % 2 == xAlimenCompar % 2 && posOrgY % 2 == yAlimenCompar % 2)
                                alimentoElegido = alimento;
                        } else {
                            if (posOrgX % 2 == xAlimenCompar % 2 && posOrgY % 2 == yAlimenCompar % 2)
                                alimentoElegido = alimento;
                        } 
                    }
                } 
            } 
            return ramdom;
        }
        return ramdom;
    }
}
