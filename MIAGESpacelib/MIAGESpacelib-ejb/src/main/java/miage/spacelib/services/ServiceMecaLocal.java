/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;

/**
 *
 * @author Quentin
 */
@Local
public interface ServiceMecaLocal {
    
    Long authentifier(String login, String pass);
    String initierRevision(Long idUsager, Long idNavette, String station);
    void finaliserRevision(Long idUsager, Long idNavette, String station);
    List<String> afficherRevision(String station, Long idUsager);
    
}
