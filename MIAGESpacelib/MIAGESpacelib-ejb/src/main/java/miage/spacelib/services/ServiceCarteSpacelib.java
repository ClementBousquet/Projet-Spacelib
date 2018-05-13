/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionVoyageLocal;
import miage.spacelib.entities.Station;
import miage.spacelib.miagespacelibshared.Coordonnee;
import miage.spacelib.miagespacelibshared.StationUs;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceCarteSpacelib implements ServiceCarteSpacelibLocal {

    @EJB
    GestionVoyageLocal gestionVoyage;
    
    @Override
    public List<StationUs> genCarteSpacelib() {
        List<Station> ls = gestionVoyage.genererCarteSpacelib();  
        List<StationUs> lus = new ArrayList();
        
        for (int i = 0; i < ls.size(); i++) 
            lus.add(new StationUs(ls.get(i).getCoordStation(), ls.get(i).getNom()));
        
        
        return lus;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
