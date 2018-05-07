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
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.OperationNavette;
import miage.spacelib.entities.OperationRevisionNavette;
import miage.spacelib.entities.Quai;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Trajet;
import miage.spacelib.entities.Usager;
import miage.spacelib.entities.Voyage;
import miage.spacelib.repositories.NavetteFacadeLocal;
import miage.spacelib.repositories.OperationNavetteFacadeLocal;
import miage.spacelib.repositories.OperationRevisionNavetteFacadeLocal;
import miage.spacelib.repositories.QuaiFacadeLocal;
import miage.spacelib.repositories.StationFacadeLocal;
import miage.spacelib.repositories.TrajetFacadeLocal;
import miage.spacelib.repositories.UsagerFacadeLocal;
import miage.spacelib.repositories.VoyageFacadeLocal;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionVoyage implements GestionVoyageLocal {

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
        String[] tab = login.split("\\.");
        try {
            Usager us = usagerFacade.findByNameAndFirstname(tab[0], tab[1]);
            if(us != null && us.getMdp().equals(pass)) {
                return us.getId();
            } else {
                return 0L;
        }
        } catch (javax.persistence.NoResultException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override
    public void inscrire(String nom, String prenom, String pass) {
        usagerFacade.create(new Usager(nom, prenom, pass));
    }

    @Override
    public String initierVoyage(Long idUsager, int nbPass, String stationArr, String stationDep) {
        System.out.println("initierVoyage....");
        Station st = stationFacade.findByName(stationDep);
        System.out.println("recupération de la station de départ ...");
        List<Quai> quais = quaiFacade.findByStation(st);
        System.out.println("recuperation des quais ..."+quais.size());
        List<Navette> navettes = new ArrayList();
        
        for (int i = 0; i < quais.size(); i++) {
            if (quais.get(i).getIdNavette() != null) {
                navettes.add(navetteFacade.findByQuai(quais.get(i)));
            }
        }
        System.out.println("Récupération des navettes...."+navettes.size());

        Calendar c = Calendar.getInstance();
        
        Trajet t = trajetFacade.findByStations(stationFacade.findByName(stationDep), stationFacade.findByName(stationArr));
        System.out.println("Récupération du trajet...."+t.getDureeVoyage()+" "+t.getStationDep()+" "+t.getStationArr());
        if(navettes.size() > 0) {
            
            Navette ln = navettes.get(0);
            
            Quai q = quaiFacade.findDispoByStation(stationFacade.findByName(stationArr));
            if (q != null) {
                
                q.setStatut("NonDispo");
                ln.setStatut("Voyage");
                System.out.println("Creation du voyage...");
                Voyage v = new Voyage(t,
                        ln,
                        usagerFacade.find(idUsager), 
                        new Date(), 
                        nbPass, 
                        new Date(), 
                        "Voyage Initie");
                System.out.println("Voyage crée...");
                c.setTime(v.getDateDepart());
                c.add(Calendar.DATE, t.getDureeVoyage());
                
                v.setDateArrive(c.getTime());
                
                voyageFacade.create(v);
                System.out.println("OperationNavette ...");
                OperationNavette on = new OperationNavette(ln, 
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
                
                Navette n = navetteFacade.find(ln.getId());
                n.setQuai(q);
                
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
                
                return "Rendez vous au quai "+ln.getId()+", vous arriverez au quai "+q.getId();
                
            } else {
                return "Aucun Quais dans la station d'arrivee disponibles";
            }
            
        } else {
            return "Aucune Navettes disponibles";
        }
        
    }

    @Override
    public void finaliserVoyage(Long idUsager, Long idVoyage) {
        
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
        v.setDateCreation(v.getDateArrive());
        voyageFacade.edit(v);
        
        operationNavetteFacade.create(on);
        
        Map<Voyage, OperationNavette> mapvo = n.getHistorique();
        mapvo.put(v, on);
        n.setHistorique(mapvo);
        
        if(mapvo.size()%6 == 0) {
            n.setStatut("BesoinRevision");
            
            OperationRevisionNavette orn = new OperationRevisionNavette(
            on.getIdNavette(),
            quaiFacade.find(on.getQuaiArr()).getIdStation(),
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
        
        Voyage v = null;

        System.out.println("Creation voyage null");
        
        List<Voyage> lv = voyageFacade.findByUsager(usagerFacade.find(idUsager));
        
        System.out.println("Recup' liste voyage "+lv.size());
        
            for (int i = 0; i < lv.size(); i++) {
                if (v == null) {
                    v = lv.get(i);
                } else {
                    if (lv.get(i).getDateCreation().compareTo(v.getDateCreation()) > 0) {
                        v = lv.get(i);
                    }
                }
            }
            
            System.out.println(v.getId());
            System.out.println(v.getIntitule());
            
            if ("Voyage Initie".equals(v.getIntitule())) {
                System.out.println("On a un voyage");
                return v;
            } else {
                return null;
            }
            
    }

    @Override
    public List<String> recupStations() {
        List<String> lst = new ArrayList();
        List<Station> ls = stationFacade.findAll();
        for (int i = 0; i < ls.size(); i++) {
            lst.add(ls.get(i).getNom());
        }
        
        return lst;
        
    }
}
