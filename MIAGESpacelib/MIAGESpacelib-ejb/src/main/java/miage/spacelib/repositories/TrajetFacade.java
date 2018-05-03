/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Trajet;

/**
 *
 * @author Quentin
 */
@Stateless
public class TrajetFacade extends AbstractFacade<Trajet> implements TrajetFacadeLocal {

    @PersistenceContext(unitName = "miage.spacelib_MIAGESpacelib-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrajetFacade() {
        super(Trajet.class);
    }

    @Override
    public Trajet findByStations(Station st1, Station st2) {
        Query qu = em.createQuery("SELECT t FROM TRAJET WHERE stationDep =:st1 AND stationArr =:st2");
       qu.setParameter("st1", st1);
       qu.setParameter("st2", st2);
       Trajet t = (Trajet) qu.getSingleResult();
       return t;
    }
    
}
