        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.OperationRevisionNavette;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Usager;
import miage.spacelib.repositories.NavetteFacadeLocal;
import miage.spacelib.repositories.OperationRevisionNavetteFacadeLocal;
import miage.spacelib.repositories.QuaiFacadeLocal;
import miage.spacelib.repositories.StationFacadeLocal;
import miage.spacelib.repositories.UsagerFacadeLocal;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionTechnique implements GestionTechniqueLocal {

    final static Logger log4j = Logger.getLogger(GestionTechnique.class);
    
    @EJB
    private StationFacadeLocal stationFacade;

    @EJB
    private OperationRevisionNavetteFacadeLocal ornFacade;

    @EJB
    private NavetteFacadeLocal navetteFacade;

    @EJB
    private QuaiFacadeLocal quaiFacade;

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @Override
    public List<Navette> afficherRevision(String station, Long idUsager) {
        log4j.debug("afficherRevision");
        
        OperationRevisionNavette orn = null;
        List<Navette> ln = new ArrayList();

        if (usagerFacade.find(idUsager).getStatutMeca().equals("Libre")) {
            
            Station st = stationFacade.findByName(station);
            List<Quai> quais = quaiFacade.findByStation(st);
            List<Navette> navettes = new ArrayList();
            System.out.println("On cherche les navettes en révisions");
            System.out.println(quais.size());
            for (int i = 0; i < quais.size(); i++) {
               try {
                    System.out.println("la navette " + navetteFacade.findByQuai(quais.get(i)) + " n'as pas besoin de révision");
                    Navette n = navetteFacade.findByQuaiAndStatut(quais.get(i), "BesoinRevision");
                    if (n != null) {
                        navettes.add(n);
                    }
                } catch (javax.persistence.NoResultException e) {
                    log4j.error("La navette "+ navetteFacade.findByQuai(quais.get(i)) +" n'as pas besoin de révision");
                    log4j.error(e.getMessage());
                }
            }
            
            return navettes;
            
        } else {
            List<OperationRevisionNavette> lrn = ornFacade.findAll();
            for (int i = 0; i < lrn.size(); i++) {
                if (orn == null) {
                    orn = lrn.get(i);
                } else {
                    if (lrn.get(i).getDateCreation().compareTo(orn.getDateCreation()) > 0) {
                        orn = lrn.get(i);
                    }
                }
            }
            ln.add(navetteFacade.find(orn.getIdNavette().getId()));
            return ln;
        }
    }

    @Override
    public Quai initierRevision(Long idUsager, Long idNavette, String station) {
        log4j.debug("initierRevision");
        
        if(usagerFacade.find(idUsager).getStatutMeca().equals("Occupe")) {
            return null;
        }
        
        this.ornFacade.create(new OperationRevisionNavette(
                navetteFacade.find(idNavette),
                stationFacade.findByName(station),
                navetteFacade.find(idNavette).getQuai(),
                usagerFacade.find(idUsager),
                "Debut de revision",
                new Date()));

        Usager u = this.usagerFacade.find(idUsager);
        u.setStatutMeca("Occupe");
        usagerFacade.edit(u);
        
        Navette n = this.navetteFacade.find(idNavette);
        n.setStatut("EnRevision");
        navetteFacade.edit(n);
        
        return quaiFacade.find(navetteFacade.find(idNavette).getQuai().getId());
    }

    @Override
    public void finaliserRevision(Long idUsager, Long idNavette, String station) {
        log4j.debug("finaliserRévision");
        this.ornFacade.create(new OperationRevisionNavette(
                navetteFacade.find(idNavette),
                stationFacade.findByName(station),
                navetteFacade.find(idNavette).getQuai(),
                usagerFacade.find(idUsager),
                "Fin de revision",
                new Date()));

        Usager u = this.usagerFacade.find(idUsager);
        u.setStatutMeca("Libre");
        usagerFacade.edit(u);
        
        Navette n = this.navetteFacade.find(idNavette);
        n.setStatut("Disponible");
        navetteFacade.edit(n);
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Long authentifier(String login, String pass) {
        log4j.debug("authentifier");
        String[] tab = login.split("\\.");
        try {
            Usager us = usagerFacade.findByNameAndFirstname(tab[0], tab[1]);
            if(us != null && us.getMdp().equals(pass) && us.getStatutUsager().equals("Mecanicien")) {
                return us.getId();
            } else {
                return 0L;
        }
        } catch (javax.persistence.NoResultException e) {
            log4j.error("Pas de mécanicien a ces identifiants " + e.getMessage());
            return 0L;
        }
    }
}
