/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionSpacelibLocal;
import miage.spacelib.entities.Station;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceAdmin implements ServiceAdminRemote {

    final static Logger log4j = Logger.getLogger(ServiceAdmin.class);
    
    @EJB
    GestionSpacelibLocal gestionSpacelib;
    
    @Override
    public void creerStation(String nom, float coordX, float coordY, List<Integer> nbPass) {
        log4j.debug("ServiceAdmin - creerStation " + nom );
        gestionSpacelib.creerStation(nom, coordX, coordY, nbPass);
    }

    @Override
    public void ajouterMeca(String nom, String prenom, String pass) {
        log4j.debug("ServiceAdmin - ajouterMeca " + nom +" "+prenom+" "+pass);
        gestionSpacelib.ajouterMeca(nom, prenom, pass);
    }

    @Override
    public void creerTrajet(String st1, String st2, int duree) {
        log4j.debug("ServiceAdmin - creerTrajet " + st1 +" "+st2+" "+duree );
        gestionSpacelib.creerTrajet(st1, st2, duree);
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<String> getStations() {
        List<Station> ls = gestionSpacelib.getStations();
        List<String> stations = new ArrayList<>();
        for (Station st : ls ) {
            stations.add(st.getNom());
        }
        return stations;
    }

}
