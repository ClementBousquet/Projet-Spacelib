/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Station;

/**
 *
 * @author Quentin
 */
@Local
public interface QuaiFacadeLocal {

    void create(Quai quai);

    void edit(Quai quai);

    void remove(Quai quai);

    Quai find(Object id);
    
    List<Quai> findByStation(Station st);

    List<Quai> findDispoByStation(Station st);
    
    List<Quai> findAll();

    List<Quai> findRange(int[] range);

    int count();
    
}
