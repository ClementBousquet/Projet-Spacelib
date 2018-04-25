/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.miagespacelibshared;

import java.io.Serializable;

/**
 *
 * @author Quentin
 */
public class Coordonnee implements Serializable {
    
        private static final long serialVersionUID = 1L;

        private float coordX;
        private float coordY;
        
        public Coordonnee(float X, float Y){
            
            this.coordX = X;
            this.coordY = Y;
            
        }

    public float getCoordX() {
        return coordX;
    }

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public float getCoordY() {
        return coordY;
    }

    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }
        
        
    
}
