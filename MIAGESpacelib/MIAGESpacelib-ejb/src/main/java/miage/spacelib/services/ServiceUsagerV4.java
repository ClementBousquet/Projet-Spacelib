/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionVoyageLocal;
import miage.spacelib.entities.Reservation;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Voyage;
import miage.spacelib.miagespacelibshared.ReservationUs;
import miage.spacelib.miagespacelibshared.StationUs;
import miage.spacelib.miagespacelibshared.VoyageVoyage;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceUsagerV4 implements ServiceUsagerV4Remote, ServiceUsagerV4Local {

    final static Logger log4j = Logger.getLogger(ServiceUsagerV4.class);

    
    @EJB
    GestionVoyageLocal gestionVoyage;
    
    @Override
    public Long authentifier(String login, String pass) {
        log4j.debug("authentifier");
        return gestionVoyage.authentifier(login, pass);
    }

    @Override
    public void inscrire(String nom, String prenom, String pass) {
        log4j.debug("inscrire ");
        gestionVoyage.inscrire(nom, prenom, pass);
    }

    @Override
    public String initierVoyage(Long idUsager, int nbPass, String stationArr, String stationDep) {
        log4j.debug("initierVoyage");
        return gestionVoyage.initierVoyage(idUsager, nbPass, stationArr, stationDep);
    }

    @Override
    public void finaliserVoyage(Long idUsager, VoyageVoyage v) {
        log4j.debug("finaliserVoyage");
        gestionVoyage.finaliserVoyage(idUsager, v.getIdVoyage());
    }

    @Override
    public VoyageVoyage afficherVoyage(Long idUsager) {
        log4j.debug("afficherVoyage");
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
        log4j.debug("recupStations");
        return gestionVoyage.recupStations();
    }

    @Override
    public ReservationUs afficherResa(Long idUsager, String st) {
        log4j.debug("afficherResa");
        Reservation v = gestionVoyage.afficherReservation(idUsager, st);
        if (v != null) {
            return new ReservationUs(v.getId(), 
                v.getQuaiDep().getId().toString(), 
                v.getQuaiArr().getId().toString(),
                v.getDateDep()
            );
        } else {
            return new ReservationUs(0L, null, null, new Date());
        }
    }

    @Override
    public String cloturerReservation(Long idUsager, Long idResa) {
        log4j.debug("cloturerReservation");
        return gestionVoyage.cloturerReservation(idUsager, idResa);
    }

    @Override
    public void annulerReservation(Long idUsager, Long idResa) {
        log4j.debug("annulerReservation");
        gestionVoyage.annulerReservation(idUsager, idResa);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String creerReservation(Long idUsager, String station1, String station2, int nbPass, Date date) {
        log4j.debug("creerReservation");
        return gestionVoyage.creerReservation(idUsager, date, nbPass, station1, station2);
    }

    @Override
    public List<ReservationUs> afficherReservations(Long idUsager) {
        log4j.debug("afficherReservations");
        List<ReservationUs> list = new ArrayList();
        List<Reservation> ls = gestionVoyage.afficherReservations(idUsager);
        for (int i = 0; i < ls.size(); i++) {
            list.add(new ReservationUs(ls.get(i).getId(), ls.get(i).getTrajet().getStationDep().getNom(), ls.get(i).getTrajet().getStationArr().getNom(), null));
        }
        
        return list;
    }

    @Override
    public List<StationUs> genCarteSpacelib() {
        log4j.debug("genCarteSpacelib");
        List<Station> ls = gestionVoyage.genererCarteSpacelib();  
        List<StationUs> lus = new ArrayList();
        
        for (int i = 0; i < ls.size(); i++) 
            lus.add(new StationUs(ls.get(i).getCoordStation(), ls.get(i).getNom()));
        
        
        return lus;
    }
}
