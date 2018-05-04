/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Trajet;
import miage.spacelib.entities.Usager;
import miage.spacelib.miagespacelibshared.StatutUsager;
import miage.spacelib.repositories.NavetteFacadeLocal;
import miage.spacelib.repositories.QuaiFacadeLocal;
import miage.spacelib.repositories.StationFacadeLocal;
import miage.spacelib.repositories.TrajetFacadeLocal;
import miage.spacelib.repositories.UsagerFacadeLocal;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionSpacelib implements GestionSpacelibLocal {

    @EJB
    private StationFacadeLocal stationFacade;

    @EJB
    private NavetteFacadeLocal navetteFacade;

    @EJB
    private QuaiFacadeLocal quaiFacade;

    @EJB
    private UsagerFacadeLocal usagerFacade;
    
    @EJB
    private TrajetFacadeLocal trajetFacade;
    
    @Override
    public void creerStation(String nom, float coordX, float coordY, List<Integer> nbPassagers) {
        
        Navette nav;
        Quai q1, q2;
        
        Station st = new Station(nom, coordX, coordY);
        List<Quai> listq = st.getQuais();
        
        stationFacade.create(st);
        
        for(int i = 0; i < nbPassagers.size(); i++) {
            nav = new Navette(nbPassagers.get(i));
            navetteFacade.create(nav);
            q1 = new Quai(st, nav);
            q2 = new Quai(st);
            quaiFacade.create(q1);
            quaiFacade.create(q2);
            listq.add(q1);
            listq.add(q2);
            nav.setQuai(q1);
            navetteFacade.edit(nav);
        }
        
        st.setQuais(listq);
        stationFacade.edit(st);
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void ajouterMeca(String nom, String prenom, String pass) {
        usagerFacade.create(new Usager(nom, prenom, pass, StatutUsager.Mecanicien));
    }

    @Override
    public void creerTrajet(String nomSt1, String nomSt2, int duree) {
        trajetFacade.create(new Trajet(stationFacade.findByName(nomSt1),stationFacade.findByName(nomSt2), duree));
    }
}
