/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionSpacelibLocal {
    
    void creerStation(String nom, float coordX, float coordY, List<Integer> nbPassagers);
    
}