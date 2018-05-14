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
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Reservation;
import miage.spacelib.entities.Usager;

/**
 *
 * @author Quentin
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> implements ReservationFacadeLocal {

    @PersistenceContext(unitName = "miage.spacelib_MIAGESpacelib-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> findByUsager(Usager us) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
        Root<Reservation> root = cq.from(Reservation.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("usager").as(Usager.class), us)
                )
        );
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public Reservation findByQuaiArrAndLabel(Quai q) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
        Root<Reservation> root = cq.from(Reservation.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("quaiArr").as(Quai.class), q),
                        cb.equal(root.get("statut").as(String.class), "EnCours")
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public Reservation findByQuaiDepAndLabel(Quai q) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
        Root<Reservation> root = cq.from(Reservation.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("quaiDep").as(Quai.class), q),
                        cb.equal(root.get("statut").as(String.class), "EnCours")
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }
    
    
    
    
}
