/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionVoyageLocal;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceResa implements ServiceResaLocal {

    final static Logger log4j = Logger.getLogger(ServiceResa.class);
    
    @EJB
    GestionVoyageLocal gestionVoyage;
    
    @Override
    public String creerReservation(Long idUsager, String station1, String station2, int nbPass, Date date) {
        log4j.debug("ServiceResa - creerReservation");
        return gestionVoyage.creerReservation(idUsager, date, nbPass, station1, station2);
    }
    
    @Override
    public Long authentifier(String login, String pass) {
        log4j.debug("ServiceResa - Authentifier " + login + " " + pass);
        return gestionVoyage.authentifier(login, pass);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
