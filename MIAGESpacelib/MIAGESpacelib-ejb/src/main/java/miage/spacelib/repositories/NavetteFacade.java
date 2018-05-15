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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Station;

/**
 *
 * @author Quentin
 */
@Stateless
public class NavetteFacade extends AbstractFacade<Navette> implements NavetteFacadeLocal {

    @PersistenceContext(unitName = "miage.spacelib_MIAGESpacelib-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NavetteFacade() {
        super(Navette.class);
    }

    @Override
    public Navette findByQuai(Quai q) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Navette> cq = cb.createQuery(Navette.class);
        Root<Navette> root = cq.from(Navette.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("quai").as(Quai.class), q)
                )
        );
        try {
            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public Navette findByQuaiAndStatut(Quai q, String statut) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Navette> cq = cb.createQuery(Navette.class);
        Root<Navette> root = cq.from(Navette.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("quai").as(Quai.class), q),
                        cb.equal(cb.upper(root.get("statut").as(String.class)), statut.toUpperCase())
                )
        );
        try {
            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return new Navette(0);
        }
    }
    
}
