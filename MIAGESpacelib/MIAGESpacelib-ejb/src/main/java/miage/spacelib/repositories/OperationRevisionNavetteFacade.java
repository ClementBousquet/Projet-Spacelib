/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.spacelib.entities.OperationRevisionNavette;

/**
 *
 * @author Quentin
 */
@Stateless
public class OperationRevisionNavetteFacade extends AbstractFacade<OperationRevisionNavette> implements OperationRevisionNavetteFacadeLocal {

    @PersistenceContext(unitName = "miage.spacelib_MIAGESpacelib-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationRevisionNavetteFacade() {
        super(OperationRevisionNavette.class);
    }
    
}
