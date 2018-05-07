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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import miage.spacelib.entities.Usager;
import miage.spacelib.entities.Voyage;

/**
 *
 * @author Quentin
 */
@Stateless
public class VoyageFacade extends AbstractFacade<Voyage> implements VoyageFacadeLocal {

    @PersistenceContext(unitName = "miage.spacelib_MIAGESpacelib-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoyageFacade() {
        super(Voyage.class);
    }

    @Override
    public List<Voyage> findByUsager(Usager us) {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
        Root<Voyage> root = cq.from(Voyage.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("idUsager").as(Usager.class), us)
                )
        );
        
        return getEntityManager().createQuery(cq).getResultList();
    }
    
}
