/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionVoyageLocal;
import miage.spacelib.entities.Voyage;
import miage.spacelib.miagespacelibshared.VoyageVoyage;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceUsager implements ServiceUsagerRemote {
    
        final static Logger log4j = Logger.getLogger(ServiceUsager.class);

    
    @EJB
    GestionVoyageLocal gestionVoyage;
    
    @Override
    public Long authentifier(String login, String pass) {
        log4j.debug("ServiceUsager - Authentifier " + login + " " + pass);
        return gestionVoyage.authentifier(login, pass);
    }

    @Override
    public void inscrire(String nom, String prenom, String pass) {
        log4j.debug("ServiceUsager - Inscrire " + nom + " " + prenom + " " + pass);
        gestionVoyage.inscrire(nom, prenom, pass);
    }

    @Override
    public String initierVoyage(Long idUsager, int nbPass, String stationArr, String stationDep) {
        log4j.debug("ServiceUsager - initierVoyage " + idUsager + " " + nbPass + " " + stationArr + " " + stationDep);
        return gestionVoyage.initierVoyage(idUsager, nbPass, stationArr, stationDep);
    }

    @Override
    public void finaliserVoyage(Long idUsager, VoyageVoyage v) {
        log4j.debug("ServiceUsager - finaliserVoyage " + idUsager + " " + v.toString());
        gestionVoyage.finaliserVoyage(idUsager, v.getIdVoyage());
    }

    @Override
    public VoyageVoyage afficherVoyage(Long idUsager) {
        log4j.debug("ServiceUsager - afficherVoyage "+ idUsager);
        Voyage v = gestionVoyage.afficherVoyage(idUsager);
        if (v != null) {
            return new VoyageVoyage(v.getId(), v.getDateDepart(), v.getDateArrive(), v.getNbPassager());
        } else {
            return new VoyageVoyage(0L, null, null, 0);
        }
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<String> recupStations() {
        log4j.debug("ServiceUsager - recupStations");
        return gestionVoyage.recupStations();
    }
}
