/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Voyage;
import miage.spacelib.entities.Usager;

/**
 *
 * @author Quentin
 */
@Local
public interface VoyageFacadeLocal {

    void create(Voyage voyage);

    void edit(Voyage voyage);

    void remove(Voyage voyage);

    Voyage find(Object id);

    List<Voyage> findByUsager(Usager us);
    
    List<Voyage> findAll();

    List<Voyage> findRange(int[] range);

    int count();
    
}
