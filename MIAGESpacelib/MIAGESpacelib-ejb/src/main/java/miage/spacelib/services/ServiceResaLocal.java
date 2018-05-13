/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Quentin
 */
@Local
public interface ServiceResaLocal {
    
    String creerReservation(Long idUsager, String station1, String station2, int nbPass, Date date);
    Long authentifier(String login, String pass);
    
}
