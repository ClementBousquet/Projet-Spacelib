/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.Date;
import javax.ejb.Local;
import miage.spacelib.entities.OperationNavette;
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
}
