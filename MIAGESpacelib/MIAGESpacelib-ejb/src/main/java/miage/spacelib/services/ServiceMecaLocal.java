/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.List;
import javax.ejb.Local;
import miage.spacelib.miagespacelibshared.TrajetAEffectuer;

/**
 *
 * @author Quentin
 */
@Local
public interface ServiceMecaLocal {
    
    Long authentifierMeca(String login, String pass);
    String initierRevision(Long idUsager, Long idNavette, String station);
    void finaliserRevision(Long idUsager, Long idNavette, String station);
    List<String> afficherRevision(String station, Long idUsager);
    
    Long authentifierConduc(String login, String pass);
    List<TrajetAEffectuer> transfertNecessaire();
    
    List<String[]> getStations();
    
}
