/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Station;

/**
 *
 * @author Quentin
 */
@Stateless
public class QuaiFacade extends AbstractFacade<Quai> implements QuaiFacadeLocal {

    @PersistenceContext(unitName = "miage.spacelib_MIAGESpacelib-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuaiFacade() {
        super(Quai.class);
    }

    @Override
    public List<Quai> findDispoByStation(Station st) {
       
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Quai> cq = cb.createQuery(Quai.class);
        Root<Quai> root = cq.from(Quai.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("idStation").as(Station.class), st),
                        cb.equal(root.get("statut").as(String.class), "Dispo")
                )
        );
        return getEntityManager().createQuery(cq).getResultList();
        
    }

    @Override
    public List<Quai> findByStation(Station st) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Quai> cq = cb.createQuery(Quai.class);
        Root<Quai> root = cq.from(Quai.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("idStation").as(Station.class), st)
                )
        );
        return getEntityManager().createQuery(cq).getResultList();
    }
    
}
