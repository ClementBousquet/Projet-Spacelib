/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.entities.Usager;
import miage.spacelib.repositories.UsagerFacade;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionVoyage implements GestionVoyageLocal {

    @EJB
    UsagerFacade usagerFacade;
    
    
    // LOGIN : NOM.PRENOM
    @Override
    public boolean authentifier(String login, String pass) {
        String[] tab = login.split(".");
        Usager us = usagerFacade.findByNameAndFirstname(tab[0], tab[1]);
        if(us.getMdp().equals(pass)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void inscrire(String nom, String prenom, String pass) {
        usagerFacade.create(new Usager(nom, prenom, pass));
    }

    @Override
    public void initierVoyage(Long idUsager, int nbPass, String nameSt) {
        //TODO
    }

    @Override
    public void finaliserVoyage(Long idUsager) {
        //TODO
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
