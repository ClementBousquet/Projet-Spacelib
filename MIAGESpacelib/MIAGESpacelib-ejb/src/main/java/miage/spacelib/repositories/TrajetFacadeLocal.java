/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Trajet;

/**
 *
 * @author Quentin
 */
@Local
public interface TrajetFacadeLocal {

    void create(Trajet trajet);

    void edit(Trajet trajet);

    void remove(Trajet trajet);

    Trajet find(Object id);

    Trajet findByStations(Station st1, Station st2);
    
    List<Trajet> findAll();

    List<Trajet> findRange(int[] range);

    int count();
    
}
