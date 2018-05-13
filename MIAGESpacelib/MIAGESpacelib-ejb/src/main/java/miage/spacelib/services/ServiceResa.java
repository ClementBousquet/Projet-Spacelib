/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionVoyageLocal;
import miage.spacelib.entities.Reservation;
import miage.spacelib.miagespacelibshared.ReservationUs;
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
        log4j.debug("creerReservation");
        return gestionVoyage.creerReservation(idUsager, date, nbPass, station1, station2);
    }
    
    @Override
    public Long authentifier(String login, String pass) {
        log4j.debug("Authentifier");
        return gestionVoyage.authentifier(login, pass);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void annulerReservation(Long idUsager, Long idResa) {
        log4j.debug("annulerReservation");
        gestionVoyage.annulerReservation(idUsager, idResa);
    }

    @Override
    public List<ReservationUs> afficherReservations(Long idUsager) {
        log4j.debug("annulerReservation");
        List<ReservationUs> list = new ArrayList();
        List<Reservation> ls = gestionVoyage.afficherReservations(idUsager);
        for (int i = 0; i < ls.size(); i++) {
            list.add(new ReservationUs(ls.get(i).getId(), ls.get(i).getTrajet().getStationDep().getNom(), ls.get(i).getTrajet().getStationArr().getNom(), null));
        }
        
        return list;
        
    }
}
