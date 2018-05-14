/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.business.GestionTechniqueLocal;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceMeca implements ServiceMecaLocal {

    final static Logger log4j = Logger.getLogger(ServiceMeca.class);
    
    @EJB
    private GestionTechniqueLocal gestionTech;
    
    @Override
    public Long authentifierMeca(String login, String pass) {
        log4j.debug("authentifierMeca");
        return gestionTech.authentifierMeca(login, pass);
    }

    @Override
    public String initierRevision(Long idUsager, Long idNavette, String station) {
        log4j.debug("initierRevision ");
        try {
            Quai q = gestionTech.initierRevision(idUsager, idNavette, station);
            System.out.println("initierRevision4");
            System.out.println("quai : " + q.getId());
            return q.getId().toString();
        } catch (NullPointerException e) {
            return "Le mécanicien est déja occupé";
        }
    }

    @Override
    public void finaliserRevision(Long idUsager, Long idNavette, String station) {
        log4j.debug("finaliserRevision ");
        gestionTech.finaliserRevision(idUsager, idNavette, station);
    }

    @Override
    public List<String> afficherRevision(String station, Long idUsager) {
        log4j.debug("afficherRevision ");
        List<String> ls = new ArrayList();
        List<Navette> ln = gestionTech.afficherRevision(station, idUsager);

        for (int i = 0; i < ln.size(); i++) {
            ls.add(ln.get(i).getId().toString());
        }
        
        return ls;
        
    }

    @Override
    public Long authentifierConduc(String login, String pass) {
        log4j.debug("authentifierConduc");
        return gestionTech.authentifierConduc(login, pass);
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
