/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.repositories;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Reservation;
import miage.spacelib.entities.Usager;

/**
 *
 * @author Quentin
 */
@Local
public interface ReservationFacadeLocal {

    void create(Reservation reservation);

    void edit(Reservation reservation);

    void remove(Reservation reservation);

    Reservation find(Object id);
    
    List<Reservation> findByUsager(Usager us);

    Reservation findByQuaiArrAndLabel(Quai q);
    
    Reservation findByQuaiDepAndLabel(Quai q);
    
    List<Reservation> findAll();

    List<Reservation> findRange(int[] range);

    int count();
    
}
