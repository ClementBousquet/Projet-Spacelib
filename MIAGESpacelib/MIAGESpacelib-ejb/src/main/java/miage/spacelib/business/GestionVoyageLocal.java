/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Reservation;
import miage.spacelib.entities.Voyage;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionVoyageLocal {
    
    Long authentifier(String login, String pass);
    void inscrire(String nom, String prenom, String pass);
    String initierVoyage(Long idUsager, int nbPass, String stationArr, String stationDep);
    void finaliserVoyage(Long idUsager, Long idVoyage);
    Voyage afficherVoyage(Long idUsager);
    String creerReservation(Long idUsager, Date datedep, int nbpass, String st1, String st2);
    Reservation afficherReservation(Long idUsager, String station);
    String cloturerReservation(Long idUsager, Long idResa);
    void annulerReservation(Long idUs, Long idResa);
    List<String> recupStations();
}
