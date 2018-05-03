/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionVoyage;
import miage.spacelib.entities.Voyage;
import miage.spacelib.miagespacelibshared.VoyageVoyage;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceUsager implements ServiceUsagerRemote {

    @EJB
    GestionVoyage gestionVoyage;
    
    @Override
    public Long authentifier(String login, String pass) {
        return gestionVoyage.authentifier(login, pass);
    }

    @Override
    public void inscrire(String nom, String prenom, String pass) {
        gestionVoyage.inscrire(nom, prenom, pass);
    }

    @Override
    public String initierVoyage(Long idUsager, int nbPass, String stationArr, String stationDep) {
        return gestionVoyage.initierVoyage(idUsager, nbPass, stationArr, stationDep);
    }

    @Override
    public void finaliserVoyage(Long idUsager, VoyageVoyage v) {
        gestionVoyage.finaliserVoyage(idUsager, v.getIdVoyage());
    }

    @Override
    public VoyageVoyage afficherVoyage(Long idUsager) {
        Voyage v = gestionVoyage.afficherVoyage(idUsager);
        return new VoyageVoyage(v.getId(), v.getDateDepart(), v.getDateArrive(), v.getNbPassager());
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
