/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.OperationNavette;
import miage.spacelib.entities.OperationRevisionNavette;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Reservation;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Trajet;
import miage.spacelib.entities.Usager;
import miage.spacelib.entities.Voyage;
import miage.spacelib.repositories.NavetteFacadeLocal;
import miage.spacelib.repositories.OperationNavetteFacadeLocal;
import miage.spacelib.repositories.OperationRevisionNavetteFacadeLocal;
import miage.spacelib.repositories.QuaiFacadeLocal;
import miage.spacelib.repositories.ReservationFacadeLocal;
import miage.spacelib.repositories.StationFacadeLocal;
import miage.spacelib.repositories.TrajetFacadeLocal;
import miage.spacelib.repositories.UsagerFacadeLocal;
import miage.spacelib.repositories.VoyageFacadeLocal;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionVoyage implements GestionVoyageLocal {

    final static Logger log4j = Logger.getLogger(GestionVoyage.class);
    
    @EJB
    ReservationFacadeLocal reservationFacade;
    
    @EJB
    UsagerFacadeLocal usagerFacade;
    
    @EJB
    QuaiFacadeLocal quaiFacade;
    
    @EJB
    StationFacadeLocal stationFacade;
    
    @EJB
    NavetteFacadeLocal navetteFacade;
    
    @EJB
    VoyageFacadeLocal voyageFacade;
    
    @EJB
    TrajetFacadeLocal trajetFacade;
    
    @EJB
    OperationNavetteFacadeLocal operationNavetteFacade;
    
    @EJB
    OperationRevisionNavetteFacadeLocal operationRevNavetteFacade;
    
    // LOGIN : NOM.PRENOM
    @Override
    public Long authentifier(String login, String pass) {
        log4j.debug("authentifier");
        String[] tab = login.split("\\.");
        try {
            Usager us = usagerFacade.findByNameAndFirstname(tab[0], tab[1]);
            if(us != null && us.getMdp().equals(pass)) {
                return us.getId();
            } else {
                return 0L;
        }
        } catch (javax.persistence.NoResultException e) {
            log4j.error("Pas d'usager pour ces identifiants " + e.getMessage());
            return 0L;
        }
    }

    @Override
    public void inscrire(String nom, String prenom, String pass) {
        log4j.debug("inscrire");
        usagerFacade.create(new Usager(nom, prenom, pass));
    }

    @Override
    public String initierVoyage(Long idUsager, int nbPass, String stationArr, String stationDep) {
        log4j.debug("initierVoyage");
        
        Station st = stationFacade.findByName(stationDep);
        List<Quai> quais = quaiFacade.findByStation(st);
        
        List<Navette> navettes = new ArrayList();
        
        for (int i = 0; i < quais.size(); i++) {
            if (quais.get(i).getIdNavette() != null) {
                try {
                    Navette n = navetteFacade.findByQuaiAndStatut(quais.get(i), "Disponible");
                    if (n.getNbPlaces() >= nbPass)
                        navettes.add(navetteFacade.findByQuai(quais.get(i)));
                } catch (NoResultException e) {
                    log4j.error(" Navette " + navetteFacade.findByQuai(quais.get(i)).getId() +" Non disponible " + e.getMessage());
                }
            }
        }
        
        Calendar c = Calendar.getInstance();
        
        Trajet t = trajetFacade.findByStations(stationFacade.findByName(stationDep), stationFacade.findByName(stationArr));
        if(navettes.size() > 0) {
            
            Navette ln = navettes.get(0);
            List<Quai> lq = quaiFacade.findDispoByStation(stationFacade.findByName(stationArr));
            
            if (lq.size() > 0) {
                Quai q = lq.get(0);
                
                if (q != null) {

                    q.setStatut("NonDispo");
                    ln.setStatut("Voyage");
                    Voyage v = new Voyage(
                            t,
                            ln,
                            usagerFacade.find(idUsager), 
                            new Date(), 
                            nbPass, 
                            new Date(), 
                            "Voyage Initie");
                    c.setTime(v.getDateDepart());
                    c.add(Calendar.DATE, t.getDureeVoyage());

                    v.setDateArrive(c.getTime());

                    voyageFacade.create(v);
                    OperationNavette on = new OperationNavette(
                            v.getIdNavette(), 
                            usagerFacade.find(idUsager), 
                            quaiFacade.find(ln.getQuai().getId()), 
                            q, 
                            "Voyage Initie", 
                            new Date(), 
                            c.getTime(), 
                            nbPass, 
                            new Date());

                    operationNavetteFacade.create(on);

                    Quai q2 = quaiFacade.find(ln.getQuai().getId());
                    q2.setStatut("Dispo");
                    q2.setIdNavette(null);



                    Navette n = navetteFacade.find(ln.getId());
                    n.setQuai(q);
                    q.setIdNavette(n);

                    Map<Voyage, OperationNavette> lon = n.getHistorique();
                    lon.put(v, on);

                    quaiFacade.edit(q);
                    quaiFacade.edit(q2);
                    navetteFacade.edit(n);

                    Usager u = usagerFacade.find(idUsager);
                    List<Voyage> lv = u.getResa();
                    lv.add(v);
                    u.setResa(lv);

                    usagerFacade.edit(u);

                    return "Rendez vous au quai "+q2.getId()+", vous arriverez au quai "+q.getId();

                } else {
                    return "Aucun Quais dans la station d'arrivee disponibles";
                }
                
            } else {
                return "Aucun Quai disponible";
            }

        } else {
            return "Aucune Navettes disponibles";
        }
        
    }

    @Override
    public void finaliserVoyage(Long idUsager, Long idVoyage) {
        log4j.debug("finaliserVoyage");
        
        Voyage v = voyageFacade.find(idVoyage);
        
        Navette n = navetteFacade.find(v.getIdNavette().getId());
        
        OperationNavette on = new OperationNavette(
                v.getIdNavette(),
                v.getIdUsager(),
                n.getHistorique().get(v).getQuaiDep(),
                n.getHistorique().get(v).getQuaiArr(),
                "Voyage Acheve",
                v.getDateDepart(),
                v.getDateArrive(),
                v.getNbPassager(),
                new Date());
        
        v.setIntitule("Voyage Acheve");
        v.setDateCreation(new Date());
        voyageFacade.edit(v);
        
        operationNavetteFacade.create(on);
        
        Map<Voyage, OperationNavette> mapvo = n.getHistorique();
        mapvo.put(v, on);
        n.setHistorique(mapvo);
        
        if(mapvo.size()%3 == 0) {
            n.setStatut("BesoinRevision");
            
            OperationRevisionNavette orn = new OperationRevisionNavette(
            on.getIdNavette(),
            quaiFacade.find(on.getQuaiArr().getId()).getIdStation(),
            on.getQuaiArr(),
            null,
            "Revision nécessaire",
            on.getDateArr());
            
            operationRevNavetteFacade.create(orn);
            
            List<OperationRevisionNavette> lorn = n.getHistoriqueRev();
            lorn.add(orn);
            n.setHistoriqueRev(lorn);
            
        } else {
             n.setStatut("Disponible");
        }
        
        navetteFacade.edit(n);
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Voyage afficherVoyage(Long idUsager) {
        log4j.debug("afficherVoyage");
        
        Voyage v = null;
        
        try {
            
            List<Voyage> lv = voyageFacade.findByUsager(usagerFacade.find(idUsager));
            for (int i = 0; i < lv.size(); i++) {
                
                if (v == null) {
                    v = lv.get(i);
                } else {
                    if (lv.get(i).getDateCreation().compareTo(v.getDateCreation()) > 0) {
                        v = lv.get(i);
                    }
                }
            }
            
            if (v != null && "Voyage Initie".equals(v.getIntitule())) {
                return v;
            } else {
                return null;
            }
            
        } catch(javax.persistence.NoResultException e) {
            log4j.error("Pas de voyage pour l'usager " + idUsager + " " + e.getMessage());
            return null;
        }
            
    }

    @Override
    public List<String> recupStations() {
        log4j.debug("recupStations");
        List<String> lst = new ArrayList();
        List<Station> ls = stationFacade.findAll();
        for (int i = 0; i < ls.size(); i++) {
            lst.add(ls.get(i).getNom());
        }
        
        return lst;
        
    }

    @Override
    public String creerReservation(Long idUsager, Date datedep, int nbpass, String st1, String st2) {
        log4j.debug("creerReservation");
        
        Station st = stationFacade.findByName(st1);
        List<Quai> quais = quaiFacade.findByStation(st);
        
        List<Quai> qsa = quaiFacade.findDispoByStation(stationFacade.findByName(st2));
        
        if (qsa.size() > 0) {
            
            Quai qa = qsa.get(0);
            
            if (quais.size() > 0) {
            
                List<Navette> navettes = new ArrayList();

                for (int i = 0; i < quais.size(); i++) {
                    if (quais.get(i).getIdNavette() != null) {
                        try {
                            Navette n = navetteFacade.findByQuaiAndStatut(quais.get(i), "Disponible");
                            if (n.getNbPlaces() >= nbpass && n.getStatutResa().equals("Libre"))
                                navettes.add(navetteFacade.findByQuai(quais.get(i)));
                        } catch (NoResultException e) {
                            log4j.error(" Navette " + navetteFacade.findByQuai(quais.get(i)).getId() +" Non disponible " + e.getMessage());
                        }
                    }
                }

                if (navettes.size() > 0){
                    Navette n = navettes.get(0);
                    Quai qd = n.getQuai();

                    reservationFacade.create(new Reservation(
                        usagerFacade.find(idUsager),
                        datedep,
                        nbpass,
                        qd,
                        qa,
                        n,
                        trajetFacade.findByStations(stationFacade.findByName(st1), stationFacade.findByName(st2))
                    ));

                    qa.setStatut("Reserve");
                    n.setStatutResa("Reserve");

                    quaiFacade.edit(qa);
                    navetteFacade.edit(n);

                    return "Veuillez vous rendre le "+datedep+" au quai numéro "+qd.getId()+", vous arriverez au quai "+qa.getId();

                } else {
                    return "pas de navettes disponible en départ de la station "+st1;
                }

            } else {
                return " pas de quais disponible en arrivée à la station "+st2;
            }
        } else {
            return " Pas de quais disponible dans la station d'arrivée "+st2;
        }
        
 
    }

    @Override
    public Reservation afficherReservation(Long idUsager, String station) {
        log4j.debug("afficherReservation");
        
        List<Reservation> lr = reservationFacade.findByUsager(usagerFacade.find(idUsager));
        
        List<Quai> quais = quaiFacade.findByStation(stationFacade.findByName(station));
        
        Reservation r = null;
        
        for (int i = 0; i < lr.size(); i++) {
            if ("Cloturer".equals(lr.get(i).getStatut()) || lr.get(i).getDateDep().compareTo(new Date()) < 0 || !quais.contains(lr.get(i).getQuaiDep())  ) {
                lr.remove(i);
            }
        }
        
        for (int i = 0; i < lr.size(); i++) {
            if (r == null) 
                r = lr.get(i);
            else
                if (lr.get(i).getDateDep().compareTo(r.getDateDep()) < 0)
                    r = lr.get(i);
        }
        
        if (r != null) 
            return r;
        else
            return null;
    }

    @Override
    public String cloturerReservation(Long idUsager, Long idResa) {
        log4j.debug("cloturerReservation");
        
        Reservation r = reservationFacade.find(idResa);
        r.setStatut("Cloturer");
        reservationFacade.edit(r);
        
        Navette n = r.getNavette();
        n.setStatutResa("Libre");
        
        Calendar c = Calendar.getInstance();
        
        Voyage v = new Voyage(
                r.getTrajet(),
                n,
                usagerFacade.find(idUsager), 
                new Date(), 
                r.getNbPassager(), 
                new Date(), 
                "Voyage Initie");
        c.setTime(v.getDateDepart());
        c.add(Calendar.DATE, r.getTrajet().getDureeVoyage());

        v.setDateArrive(c.getTime());

        voyageFacade.create(v);
        
        OperationNavette on = new OperationNavette(
                v.getIdNavette(), 
                usagerFacade.find(idUsager), 
                r.getQuaiDep(), 
                r.getQuaiArr(), 
                "Voyage Initie", 
                new Date(), 
                c.getTime(), 
                r.getNbPassager(), 
                new Date());

        operationNavetteFacade.create(on);
        
        Quai q = quaiFacade.find(r.getQuaiDep().getId());
        Quai q2 = quaiFacade.find(r.getQuaiArr().getId());
        q.setStatut("Dispo");
        q.setIdNavette(null);

        n.setQuai(q2);
        q2.setIdNavette(n);

        q.setStatut("NonDispo");
        n.setStatut("Voyage");
        
        Map<Voyage, OperationNavette> lon = n.getHistorique();
        lon.put(v, on);

        quaiFacade.edit(q);
        quaiFacade.edit(q2);
        navetteFacade.edit(n);

        Usager u = usagerFacade.find(idUsager);
        List<Voyage> lv = u.getResa();
        lv.add(v);
        u.setResa(lv);

        usagerFacade.edit(u);
        
        return "Rendez-vous au quai "+r.getId()+", votre navette va partir";
    }

    @Override
    public void annulerReservation(Long idUs, Long idResa) {
        log4j.debug("annlerReservation");
        Reservation r = reservationFacade.find(idResa);
        Navette n = r.getNavette();
        n.setStatutResa("Libre");
        navetteFacade.edit(n);
        Quai qA = r.getQuaiArr();
        qA.setStatut("Dispo");
        quaiFacade.edit(qA);
        
        reservationFacade.remove(r);
        
    }

    @Override
    public List<Station> genererCarteSpacelib() {
        log4j.debug("genererCarteSpacelib");
        return stationFacade.findAll();
    }

    @Override
    public List<Reservation> afficherReservations(Long idUsager) {
        log4j.debug("afficherReservations");
        List<Reservation> lr = reservationFacade.findAll();
        
        for (int i = 0; i < lr.size(); i++) {
            if ("Cloturer".equals(lr.get(i).getStatut()) || lr.get(i).getDateDep().compareTo(new Date()) < 0 ) {
                lr.remove(i);
            }
        }
        
        return lr;
        
    }
}
