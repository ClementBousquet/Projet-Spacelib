/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import miage.spacelib.miagespacelibshared.ReservationUs;
import miage.spacelib.miagespacelibshared.StationUs;
import miage.spacelib.miagespacelibshared.VoyageVoyage;

/**
 *
 * @author Quentin
 */
@Local
public interface ServiceUsagerV4Local {
    
    Long authentifier(String login, String pass);
    void inscrire(String nom, String prenom, String pass);
    String initierVoyage(Long idUsager, int nbPass, String stationArr, String stationDep);
    ReservationUs afficherResa(Long idUsager, String st);
    String cloturerReservation(Long idUsager, Long idResa);
    void finaliserVoyage(Long idUsager, VoyageVoyage v);
    VoyageVoyage afficherVoyage(Long idUsager);
    List<String> recupStations();
    /* RESA */
    String creerReservation(Long idUsager, String station1, String station2, int nbPass, Date date);
    void annulerReservation(Long idUsager, Long idResa);
    List<ReservationUs> afficherReservations(Long idUsager);
    /*CARTE */
    List<StationUs> genCarteSpacelib();
    
}
