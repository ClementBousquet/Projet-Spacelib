/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;

/**
 *
 * @author Quentin
 */
@Local
public interface NavetteFacadeLocal {

    void create(Navette navette);

    void edit(Navette navette);

    void remove(Navette navette);

    Navette find(Object id);
    
    Navette findByQuai(Quai q);
    
    List<Navette> findAll();

    List<Navette> findRange(int[] range);

    int count();
    
}
