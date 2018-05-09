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

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceAdmin implements ServiceAdminRemote {

    @EJB
    GestionSpacelibLocal gestionSpacelib;
    
    @Override
    public void creerStation(String nom, float coordX, float coordY, List<Integer> nbPass) {
        gestionSpacelib.creerStation(nom, coordX, coordY, nbPass);
    }

    @Override
    public void ajouterMeca(String nom, String prenom, String pass) {
        gestionSpacelib.ajouterMeca(nom, prenom, pass);
    }

    @Override
    public void creerTrajet(String st1, String st2, int duree) {
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
