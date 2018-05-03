/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import javax.ejb.Remote;
import miage.spacelib.miagespacelibshared.VoyageVoyage;

/**
 *
 * @author Quentin
 */
@Remote
public interface ServiceUsagerRemote {
    
    Long authentifier(String login, String pass);
    void inscrire(String nom, String prenom, String pass);
    String initierVoyage(Long idUsager, int nbPass, String stationArr, String stationDep);
    void finaliserVoyage(Long idUsager, VoyageVoyage v);
    VoyageVoyage afficherVoyage(Long idUsager);
    
}
