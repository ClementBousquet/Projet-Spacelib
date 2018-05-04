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
import miage.spacelib.entities.Quai;

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
    public Navette findByStation(Long idStation, int nbPass) {
       Query qu = em.createQuery("SELECT n FROM NAVETTE WHERE nbPlaces >= "+nbPass);
       List<Navette> n = qu.getResultList();
              
       Query qu2 = em.createQuery("SELECT q FROM QUAI WHERE idStation =:idS");
       qu2.setParameter("idS", idStation);
       List<Quai> q = qu2.getResultList();
       
       Navette nav = null;
       
       boolean trouve = false;
       for (int i = 0; i < q.size(); i++) {
           
           if(!trouve) {
               
               for (int j = 0; j < n.size(); j++) {
                   
                   if (q.get(i).getIdNavette().equals(n.get(j).getId()) && "Disponible".equals(n.get(j).getStatut())) {
                       trouve = true;
                       nav = n.get(j);
                   }
                   
               }
               
           }    
       }
       return nav;
    }
    
}
