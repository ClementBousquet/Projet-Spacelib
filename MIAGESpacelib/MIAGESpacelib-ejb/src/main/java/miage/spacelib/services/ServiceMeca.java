/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionTechniqueLocal;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceMeca implements ServiceMecaLocal {

    @EJB
    private GestionTechniqueLocal gestionTech;
    
    @Override
    public void authentifier(String login, String pass) {
        gestionTech.authentifier(login, pass);
    }

    @Override
    public Quai initierRevision(Long idUsager, Long idNavette, String station) {
        return gestionTech.initierRevision(idUsager, idNavette, station);
    }

    @Override
    public void finaliserRevision(Long idUsager, Long idNavette, String station) {
        gestionTech.finaliserRevision(idUsager, idNavette, station);
    }

    @Override
    public List<Navette> afficherRevision(String station, Long idUsager) {
        return gestionTech.afficherRevision(station, idUsager);
    }

    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
