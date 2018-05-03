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
import miage.spacelib.entities.Navette;

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
    public List<Navette> findByStation(Long idStation, int nbPass) {
        Query qu = em.createQuery("SELECT n FROM QUAI, NAVETTE, STATION WHERE NAVETTE.id = QUAI.idNavette AND STATION.id = QUAI.idStation AND NAVETTE.Statut = 'Disponible' AND STATION.id =:idStation AND NAVETTE.nbPlaces >=:nbPass");
       List<Navette> n = qu.getResultList();
       return n;
    }
    
}
