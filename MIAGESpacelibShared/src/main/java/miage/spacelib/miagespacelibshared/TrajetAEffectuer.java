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
    
    private static final long serialVersionUID = 1L;
    
    String stationDep;
    String stationArr;
    
    public TrajetAEffectuer() {
        stationDep = "";
        stationArr = "";
    }

    public String getStationDep() {
        return stationDep;
    }

    public void setStationDep(String stationDep) {
        this.stationDep = stationDep;
    }

    public String getStationArr() {
        return stationArr;
    }

    public void setStationArr(String stationArr) {
        this.stationArr = stationArr;
    }
    
    
    
}
