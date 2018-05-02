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
    public Quai findDispoByStation(Long idStation) {
       Query qu = em.createQuery("SELECT TOP q FROM QUAI WHERE idStation =:idS AND statut = 'Dispo'");
       qu.setParameter("idS", idStation);
       Quai q = (Quai) qu.getSingleResult();
       return q;
       }
    
}
