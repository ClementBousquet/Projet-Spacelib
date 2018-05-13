/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import miage.spacelib.entities.Reservation;
import miage.spacelib.miagespacelibshared.ReservationUs;

/**
 *
 * @author Quentin
 */
@Local
public interface ServiceResaLocal {
    
    String creerReservation(Long idUsager, String station1, String station2, int nbPass, Date date);
    void annulerReservation(Long idUsager, Long idResa);
    List<ReservationUs> afficherReservations(Long idUsager);
    Long authentifier(String login, String pass);
    
}
