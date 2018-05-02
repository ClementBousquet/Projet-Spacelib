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
import miage.spacelib.miagespacelibshared.StatutMeca;
import miage.spacelib.miagespacelibshared.StatutNavette;
import miage.spacelib.repositories.NavetteFacade;
import miage.spacelib.repositories.OperationRevisionNavetteFacade;
import miage.spacelib.repositories.QuaiFacade;
import miage.spacelib.repositories.StationFacade;
import miage.spacelib.repositories.UsagerFacade;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionTechnique implements GestionTechniqueLocal {

    @EJB
    private StationFacade stationFacade;

    @EJB
    private OperationRevisionNavetteFacade ornFacade;

    @EJB
    private NavetteFacade navetteFacade;

    @EJB
    private QuaiFacade quaiFacade;

    @EJB
    private UsagerFacade usagerFacade;

    @Override
    public List<Navette> afficherRevision(String station, Long idUsager) {

        OperationRevisionNavette orn = null;
        List<Navette> ln = new ArrayList();

        if (usagerFacade.find(idUsager).getStatutMeca().equals(StatutMeca.Libre)) {
            return this.stationFacade.findNavetteRevision(station);
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
            ln.add(navetteFacade.find(orn.getIdNavette()));
            return ln;
        }
    }

    @Override
    public Quai initierRevision(Long idUsager, Long idNavette, String station) {
        this.ornFacade.create(new OperationRevisionNavette(idNavette,
                stationFacade.findByName(station).getId(),
                navetteFacade.find(idNavette).getQuai(),
                idUsager,
                "Debut de revision",
                new Date()));

        this.usagerFacade.find(idUsager).setStatutMeca(StatutMeca.Occupe);

        this.navetteFacade.find(idNavette).setStatut(StatutNavette.EnRevision);

        return quaiFacade.find(navetteFacade.find(idNavette).getQuai());
    }

    @Override
    public void finaliserRevision(Long idUsager, Long idNavette, String station) {
        this.ornFacade.create(new OperationRevisionNavette(idNavette,
                stationFacade.findByName(station).getId(),
                navetteFacade.find(idNavette).getQuai(),
                idUsager,
                "Fin de revision",
                new Date()));

        this.usagerFacade.find(idUsager).setStatutMeca(StatutMeca.Libre);

        this.navetteFacade.find(idNavette).setStatut(StatutNavette.Disponible);
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
