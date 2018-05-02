/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Navette;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionTechniqueLocal {
    
    List<Navette> afficherRevision(String station);
    void initierRevision(Long idUsager, Long idNavette);
    void finaliserRevision(Long idUsager);
    
}
