/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.business;

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
import miage.spacelib.entities.Trajet;
import miage.spacelib.entities.Usager;
import miage.spacelib.entities.Voyage;
import miage.spacelib.miagespacelibshared.StatutNavette;
import miage.spacelib.miagespacelibshared.StatutQuai;
import miage.spacelib.repositories.NavetteFacade;
import miage.spacelib.repositories.OperationNavetteFacade;
import miage.spacelib.repositories.OperationRevisionNavetteFacade;
import miage.spacelib.repositories.QuaiFacade;
import miage.spacelib.repositories.StationFacade;
import miage.spacelib.repositories.TrajetFacade;
import miage.spacelib.repositories.UsagerFacade;
import miage.spacelib.repositories.VoyageFacade;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionVoyage implements GestionVoyageLocal {

    @EJB
    UsagerFacade usagerFacade;
    
    @EJB
    QuaiFacade quaiFacade;
    
    @EJB
    StationFacade stationFacade;
    
    @EJB
    NavetteFacade navetteFacade;
    
    @EJB
    VoyageFacade voyageFacade;
    
    @EJB
    TrajetFacade trajetFacade;
    
    @EJB
    OperationNavetteFacade operationNavetteFacade;
    
    @EJB
    OperationRevisionNavetteFacade operationRevNavetteFacade;
    
    // LOGIN : NOM.PRENOM
    @Override
    public Long authentifier(String login, String pass) {
        String[] tab = login.split(".");
        Usager us = usagerFacade.findByNameAndFirstname(tab[0], tab[1]);
        if(us.getMdp().equals(pass)) {
            return us.getId();
        } else {
            return null;
        }
    }

    @Override
    public void inscrire(String nom, String prenom, String pass) {
        usagerFacade.create(new Usager(nom, prenom, pass));
    }

    @Override
    public String initierVoyage(Long idUsager, int nbPass, String stationArr, String stationDep) {
        
        List<Navette> ln = navetteFacade.findByStation(stationFacade.findByName(stationArr).getId(), nbPass);
        Calendar c = Calendar.getInstance();
        Trajet t = trajetFacade.findByStations(stationFacade.findByName(stationDep), stationFacade.findByName(stationArr));
        
        if(ln.size() > 0) {
            
            Quai q = quaiFacade.findDispoByStation(stationFacade.findByName(stationArr).getId());
            if (!q.equals(null)) {
                
                q.setStatut(StatutQuai.NonDispo);
                ln.get(0).setStatut(StatutNavette.Voyage);
                
                Voyage v = new Voyage(t.getId() ,ln.get(0).getId(), idUsager, new Date(), nbPass, new Date(), "Voyage Initie");
                
                c.setTime(v.getDateDepart());
                c.add(Calendar.DATE, t.getDureeVoyage());
                
                v.setDateArrive(c.getTime());
                
                voyageFacade.create(v);
                
                OperationNavette on = new OperationNavette(ln.get(0).getId(), 
                        idUsager, 
                        quaiFacade.find(ln.get(0).getQuai()), 
                        q, 
                        "Voyage Initie", 
                        new Date(), 
                        c.getTime(), 
                        nbPass, 
                        new Date());
                
                operationNavetteFacade.create(on);
                
                Quai q2 = quaiFacade.find(ln.get(0).getQuai());
                q2.setStatut(StatutQuai.Dispo);
                
                Navette n = navetteFacade.find(ln.get(0));
                n.setQuai(q.getId());
                
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
                
                return "Rendez vous au quai "+ln.get(0).getId()+", vous arriverez au quai "+q.getId();
                
            } else {
                return "Aucun Quais dans la station d'arrivee disponibles";
            }
            
        } else {
            return "Aucune Navettes disponibles";
        }
        
    }

    @Override
    public void finaliserVoyage(Long idUsager, Voyage v) {
        
        Navette n = navetteFacade.find(v.getIdNavette());
        
        OperationNavette on = new OperationNavette(
                v.getIdNavette(),
                v.getIdUsager(),
                n.getHistorique().get(v).getQuaiDep(),
                n.getHistorique().get(v).getQuaiArr(),
                "Voyage Achevé",
                v.getDateDepart(),
                v.getDateArrive(),
                v.getNbPassager(),
                v.getDateCreation());
        
        operationNavetteFacade.create(on);
        
        v.setIntitule("Voyage Achevé");
        v.setDateCreation(v.getDateArrive());
        voyageFacade.edit(v);
        
        Map<Voyage, OperationNavette> mapvo = n.getHistorique();
        mapvo.put(v, on);
        n.setHistorique(mapvo);
        
        if(mapvo.size()%6 == 0) {
            n.setStatut(StatutNavette.BesoinRevision);
            
            OperationRevisionNavette orn = new OperationRevisionNavette(
            on.getIdNavette(),
            quaiFacade.find(on.getQuaiArr()).getIdStation(),
            on.getQuaiArr().getId(),
            null,
            "Revision nécessaire",
            on.getDateArr());
            
            operationRevNavetteFacade.create(orn);
            
            List<OperationRevisionNavette> lorn = n.getHistoriqueRev();
            lorn.add(orn);
            n.setHistoriqueRev(lorn);
            
        } else {
             n.setStatut(StatutNavette.Disponible);
        }
        
        navetteFacade.edit(n);
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Voyage afficherVoyage(Long idUsager) {
        
        Voyage v = null;

        List<Voyage> lv = voyageFacade.findAll();
            for (int i = 0; i < lv.size(); i++) {
                if (v == null) {
                    v = lv.get(i);
                } else {
                    if (lv.get(i).getDateCreation().compareTo(v.getDateCreation()) > 0) {
                        v = lv.get(i);
                    }
                }
            }
            
            return v;
    }
}
