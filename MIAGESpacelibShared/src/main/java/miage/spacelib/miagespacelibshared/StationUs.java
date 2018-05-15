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
public class StationUs implements Serializable {
    
        private static final long serialVersionUID = 1L;
    
        private Coordonnee coord;
        private String station;
        
        public StationUs(Coordonnee c, String st) {
            this.coord = c;
            this.station = st;
        }
        
        public StationUs(){
            
        }

    public Coordonnee getCoord() {
        return coord;
    }

    public void setCoord(Coordonnee coord) {
        this.coord = coord;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
        
        
}
