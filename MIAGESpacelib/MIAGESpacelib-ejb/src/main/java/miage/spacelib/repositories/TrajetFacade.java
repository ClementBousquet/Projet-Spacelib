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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Trajet> cq = cb.createQuery(Trajet.class);
        Root<Trajet> root = cq.from(Trajet.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("stationDep").as(Station.class), st1),
                        cb.equal(root.get("stationArr").as(Station.class), st2)
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }
    
}
