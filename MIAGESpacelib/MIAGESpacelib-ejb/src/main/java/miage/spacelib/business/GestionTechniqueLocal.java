/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionTechniqueLocal {
    
    List<Navette> afficherRevision(String station, Long idUsager);
    Quai initierRevision(Long idUsager, Long idNavette, String station);
    void finaliserRevision(Long idUsager, Long idNavette, String station);
    
}
