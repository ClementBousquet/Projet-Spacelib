/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import javax.ejb.Local;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionVoyageLocal {
    
    void authentifier(String login, String pass);
    void inscrire(String nom, String prenom);
    void initierVoyage(Long idUsager, int nbPass, String nameSt);
    void finaliserVoyage(Long idUsager);
    
}
