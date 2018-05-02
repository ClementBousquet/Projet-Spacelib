/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.OperationRevisionNavette;

/**
 *
 * @author Quentin
 */
@Local
public interface OperationRevisionNavetteFacadeLocal {

    void create(OperationRevisionNavette operationRevisionNavette);

    void edit(OperationRevisionNavette operationRevisionNavette);

    void remove(OperationRevisionNavette operationRevisionNavette);

    OperationRevisionNavette find(Object id);

    List<OperationRevisionNavette> findAll();

    List<OperationRevisionNavette> findRange(int[] range);

    int count();
    
}
