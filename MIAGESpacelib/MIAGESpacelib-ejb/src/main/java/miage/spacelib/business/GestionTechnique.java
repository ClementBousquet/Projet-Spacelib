/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.OperationRevisionNavette;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Reservation;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Usager;
import miage.spacelib.miagespacelibshared.TrajetAEffectuer;
import miage.spacelib.repositories.NavetteFacadeLocal;
import miage.spacelib.repositories.OperationRevisionNavetteFacadeLocal;
import miage.spacelib.repositories.QuaiFacadeLocal;
import miage.spacelib.repositories.ReservationFacade;
import miage.spacelib.repositories.ReservationFacadeLocal;
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

    @EJB
    private ReservationFacadeLocal reservationFacade;

    @Override
    public List<Navette> afficherRevision(String station, Long idUsager) {
        log4j.debug("afficherRevision");

        OperationRevisionNavette orn = null;
        List<Navette> ln = new ArrayList();

        if (usagerFacade.find(idUsager).getStatutMeca().equals("Libre")) {

            Station st = stationFacade.findByName(station);
            List<Quai> quais = quaiFacade.findByStation(st);
            List<Navette> navettes = new ArrayList();
            for (int i = 0; i < quais.size(); i++) {
                try {
                    Navette n = navetteFacade.findByQuaiAndStatut(quais.get(i), "BesoinRevision");
                    if (n.getNbPlaces() != 0) {
                        navettes.add(n);
                    }
                } catch (javax.persistence.NoResultException e) {
                    log4j.error("La navette " + navetteFacade.findByQuai(quais.get(i)) + " n'as pas besoin de révision");
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

        if (usagerFacade.find(idUsager).getStatutMeca().equals("Occupe")) {
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
    public Long authentifierMeca(String login, String pass) {
        log4j.debug("authentifierMeca");
        String[] tab = login.split("\\.");
        try {
            Usager us = usagerFacade.findByNameAndFirstname(tab[0], tab[1]);
            if (us != null && us.getMdp().equals(pass) && us.getStatutUsager().equals("Mecanicien")) {
                return us.getId();
            } else {
                return 0L;
            }
        } catch (javax.persistence.NoResultException e) {
            log4j.error("Pas de mécanicien a ces identifiants " + e.getMessage());
            return 0L;
        }
    }

    @Override
    public Long authentifierConduc(String login, String pass) {
        log4j.debug("authentifierConduc");
        String[] tab = login.split("\\.");
        try {
            Usager us = usagerFacade.findByNameAndFirstname(tab[0], tab[1]);
            if (us != null && us.getMdp().equals(pass) && us.getStatutUsager().equals("Conducteur")) {
                return us.getId();
            } else {
                return 0L;
            }
        } catch (javax.persistence.NoResultException e) {
            log4j.error("Pas de conducteur a ces identifiants " + e.getMessage());
            return 0L;
        }
    }

    @Override
    public List<TrajetAEffectuer> transfertNecessaire() {

        List<Station> stations = stationFacade.findAll();

        List<String> besoinNavette = new ArrayList();
        List<String> besoinQuai = new ArrayList();
        List<String> aucunBesoin = new ArrayList();

        Map<String, Float> ratioQuai = new HashMap();
        Map<String, Float> ratioNav = new HashMap();

        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.DATE, 10);

        /*Quais dispo sur une station va devenir inf. 10%*/
        for (int i = 0; i < stations.size(); i++) {
            List<Quai> lq = quaiFacade.findByStation(stations.get(i));

            int sizeQ = lq.size();
            int sizeD = 0;

            for (int j = 0; j < lq.size(); j++) {
                if (lq.get(j).getStatut().equals("Dispo")) {
                    sizeD++;
                }
                if (lq.get(j).getStatut().equals("Reserve")) {
                    Reservation r = reservationFacade.findByQuaiArrAndLabel(lq.get(j));
                    if (r.getDateDep().compareTo(c.getTime()) < 0) {
                        sizeD++;
                    }
                }
            }

            Float rat = (float) (((float) sizeD * 100.0) / ((float) sizeQ * 100.0) * 100.0);
            ratioQuai.put(stations.get(i).getNom(), rat);

        }

        /*Navette dispo < 10% dans les 10 proch. jours */
        for (int i = 0; i < stations.size(); i++) {

            List<Quai> lq = stations.get(i).getQuais();

            int nbQ = lq.size();

            int sizeNav = 0;

            for (int j = 0; j < lq.size(); j++) {
                if (lq.get(j).getStatut().equals("NonDispo")) {
                    sizeNav++;
                }
            }

            Float rat = (float) (((float) sizeNav * 100.0) / ((float) nbQ * 100.0) * 100.0);
            ratioNav.put(stations.get(i).getNom(), rat);

        }


        /*Ordre de ratio*/
        for (int i = 0; i < stations.size(); i++) {

            if (ratioNav.get(stations.get(i).getNom()) <= 10.0) {
                besoinNavette.add(stations.get(i).getNom());
            }

            if (ratioQuai.get(stations.get(i).getNom()) <= 10.0) {
                besoinQuai.add(stations.get(i).getNom());
            }

            if (!besoinQuai.contains(stations.get(i).getNom()) && !besoinNavette.contains(stations.get(i).getNom())) {
                aucunBesoin.add(stations.get(i).getNom());
            }

        }
        
        besoinNavette = tri(besoinNavette, ratioNav);
        besoinQuai = tri(besoinQuai, ratioQuai);

        List<TrajetAEffectuer> ltae = new ArrayList();

        for (int i = 0; i < besoinQuai.size(); i++) {

            Station st = stationFacade.findByName(besoinQuai.get(i));
            Float d = (ratioQuai.get(st.getNom()) / 100) * st.getQuais().size();

            int besoin10 = Math.round(d);

            int besoin20 = st.getQuais().size() / 5;

            int besoin = besoin20 - besoin10 + 1;

            for (int j = 0; j < besoin; j++) {
                TrajetAEffectuer t = new TrajetAEffectuer();
                t.setStationDep(st.getNom());
                ltae.add(t);
            }
        }
        
        for (int i = 0; i < besoinNavette.size(); i++) {

            Station st = stationFacade.findByName(besoinNavette.get(i));

            Float d = (ratioNav.get(st.getNom()) / 100) * st.getQuais().size();

            int besoin10 = Math.round(d);
            int besoin20 = st.getQuais().size() / 5;
            int besoin = besoin20 - besoin10 + 1;
            
            int cpt = besoin;
            for (int j = 0; j < besoin; j++) {
                if (ltae.size() > 0) {
                    boolean found = false;
                    for (int k = 0; k < ltae.size(); k++) {
                        if (ltae.get(k).getStationArr().equals("") && !found) {
                            ltae.get(k).setStationArr(st.getNom());
                            found = true;
                            cpt--;
                        }
                    }
                }
            }
            if (cpt > 0) {
                
                for (int k = 0; k < cpt; k++) {
                    TrajetAEffectuer t = new TrajetAEffectuer();
                    t.setStationArr(st.getNom());
                    ltae.add(t);
                }
            }

        }

        int x = aucunBesoin.size() - 1;

        for (int i = 0; i < ltae.size(); i++) {
            
            if (ltae.get(i).getStationArr().equals("")) {
                ltae.get(i).setStationArr(aucunBesoin.get(x));
                if (x > 0) {
                    x--;
                }
            }
            
            if (ltae.get(i).getStationDep().equals("")) {
                ltae.get(i).setStationDep(aucunBesoin.get(x));
                if (x > 0) {
                    x--;
                }
            }
        }
        return ltae;

    }

    private List<String> tri(List<String> liste, Map<String, Float> map) {
        List<String> listeB = new ArrayList();

        String planete = "";

        for (int i = 0; i < liste.size(); i++) {
            for (int j = 0; j < liste.size(); j++) {

                if ("".equals(planete)) {
                    planete = liste.get(j);
                } else {
                    if (map.get(planete) > map.get(liste.get(j)) && !listeB.contains(liste.get(j))) {
                        planete = liste.get(j);
                    }
                }

            }

            listeB.add(planete);
            //liste.remove(planete);  
            planete = "";
        }

        return listeB;

    }

    @Override
    public String getStatutMeca(Long idUs) {
        return usagerFacade.find(idUs).getStatutMeca();
    }
}
