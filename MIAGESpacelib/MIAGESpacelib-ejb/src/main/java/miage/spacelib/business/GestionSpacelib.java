/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Usager;
import miage.spacelib.miagespacelibshared.StatutUsager;
import miage.spacelib.repositories.NavetteFacade;
import miage.spacelib.repositories.QuaiFacade;
import miage.spacelib.repositories.StationFacade;
import miage.spacelib.repositories.UsagerFacade;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionSpacelib implements GestionSpacelibLocal {

     @EJB
    private StationFacade stationFacade;

    @EJB
    private NavetteFacade navetteFacade;

    @EJB
    private QuaiFacade quaiFacade;

    @EJB
    private UsagerFacade usagerFacade;
    
    @Override
    public void creerStation(String nom, float coordX, float coordY, List<Integer> nbPassagers) {
        
        Navette nav;
        Station st = new Station(nom, coordX, coordY);
        stationFacade.create(st);
        
        for(int i = 0; i < nbPassagers.size(); i++) {
            nav = new Navette(nbPassagers.get(i));
            navetteFacade.create(nav);
            quaiFacade.create(new Quai(st, nav));
            quaiFacade.create(new Quai(st));
        }
            
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void ajouterMeca(String nom, String prenom, String pass) {
        usagerFacade.create(new Usager(nom, prenom, pass, StatutUsager.Mecanicien));
    }
}
