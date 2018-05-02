/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Usager;

/**
 *
 * @author Quentin
 */
@Stateless
public class StationFacade extends AbstractFacade<Station> implements StationFacadeLocal {

    @PersistenceContext(unitName = "miage.spacelib_MIAGESpacelib-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StationFacade() {
        super(Station.class);
    }

    @Override
    public Station findByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Station> cq = cb.createQuery(Station.class);
        Root<Station> root = cq.from(Station.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("nom").as(String.class)), name.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<Navette> findNavetteRevision(Object id) {
        Query qu = em.createQuery("SELECT n FROM QUAI, NAVETTE, STATION WHERE NAVETTE.id = QUAI.idNavette AND STATION.id = QUAI.idStation AND NAVETTE.Statut = 'BesoinRevision' AND STATION.id =:id");
       List<Navette> n = qu.getResultList();
       return n;
    }
    
}
