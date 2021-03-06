/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Usager;

/**
 *
 * @author Quentin
 */
@Local
public interface UsagerFacadeLocal {

    void create(Usager usager);

    void edit(Usager usager);

    void remove(Usager usager);

    Usager find(Object id);

    Usager findByNameAndFirstname(String name, String firstName);
    
    List<Usager> findAll();

    List<Usager> findRange(int[] range);

    int count();
    
    List<Usager> findMecas();

    List<Usager> findConducts();
    
}
