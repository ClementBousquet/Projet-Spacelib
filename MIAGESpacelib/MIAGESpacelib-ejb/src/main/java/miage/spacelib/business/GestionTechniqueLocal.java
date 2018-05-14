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
import miage.spacelib.entities.Trajet;
import miage.spacelib.miagespacelibshared.TrajetAEffectuer;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionTechniqueLocal {
    /* V1 */
    List<Navette> afficherRevision(String station, Long idUsager);
    Quai initierRevision(Long idUsager, Long idNavette, String station);
    void finaliserRevision(Long idUsager, Long idNavette, String station);
    Long authentifierMeca(String login, String pass);
    /* V3 */
    Long authentifierConduc(String login, String pass);
    List<TrajetAEffectuer> transfertNecessaire();
}
