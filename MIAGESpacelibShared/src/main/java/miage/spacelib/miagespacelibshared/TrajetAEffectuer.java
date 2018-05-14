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
public class TrajetAEffectuer implements Serializable {
    
    int nbTrajet;
    int stationDep;
    int stationArr;
    
    public TrajetAEffectuer() {
        
    }

    public int getNbTrajet() {
        return nbTrajet;
    }

    public void setNbTrajet(int nbTrajet) {
        this.nbTrajet = nbTrajet;
    }

    public int getStationDep() {
        return stationDep;
    }

    public void setStationDep(int stationDep) {
        this.stationDep = stationDep;
    }

    public int getStationArr() {
        return stationArr;
    }

    public void setStationArr(int stationArr) {
        this.stationArr = stationArr;
    }
    
    
    
}
