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

/**
 *
 * @author Quentin
 */
@Stateless
public class UsagerFacade extends AbstractFacade<Usager> implements UsagerFacadeLocal {

    @PersistenceContext(unitName = "miage.spacelib_MIAGESpacelib-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsagerFacade() {
        super(Usager.class);
    }

    @Override
    public Usager findByNameAndFirstname(String name, String firstName) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usager> cq = cb.createQuery(Usager.class);
        Root<Usager> root = cq.from(Usager.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("prenom").as(String.class)), firstName.toUpperCase()),
                        cb.equal(cb.upper(root.get("nom").as(String.class)), name.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<Usager> findMecas() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usager> cq = cb.createQuery(Usager.class);
        Root<Usager> root = cq.from(Usager.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("statutUsager").as(Usager.class), "Mecanicien")
                )
        );
        
        return getEntityManager().createQuery(cq).getResultList();
    }
    
}
