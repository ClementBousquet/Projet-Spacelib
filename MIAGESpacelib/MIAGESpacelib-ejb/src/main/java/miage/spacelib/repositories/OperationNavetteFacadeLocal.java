/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.OperationNavette;

/**
 *
 * @author Quentin
 */
@Local
public interface OperationNavetteFacadeLocal {

    void create(OperationNavette operationNavette);

    void edit(OperationNavette operationNavette);

    void remove(OperationNavette operationNavette);

    OperationNavette find(Object id);

    List<OperationNavette> findAll();

    List<OperationNavette> findRange(int[] range);

    int count();
    
}
