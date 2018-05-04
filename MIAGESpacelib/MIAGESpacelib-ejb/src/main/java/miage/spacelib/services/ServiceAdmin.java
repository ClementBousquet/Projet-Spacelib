/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionSpacelib;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceAdmin implements ServiceAdminRemote {

    @EJB
    GestionSpacelib gestionSpacelib;
    
    @Override
    public void creerStation(String nom, float coordX, float coordY, List<Integer> nbPass) {
        gestionSpacelib.creerStation(nom, coordX, coordY, nbPass);
    }

    @Override
    public void ajouterMeca(String nom, String prenom, String pass) {
        gestionSpacelib.ajouterMeca(nom, prenom, pass);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void creerTrajet(String st1, String st2, int duree) {
        gestionSpacelib.creerTrajet(st1, st2, duree);
    }
}
