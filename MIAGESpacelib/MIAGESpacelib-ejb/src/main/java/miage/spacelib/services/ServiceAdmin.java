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
import miage.spacelib.business.GestionSpacelibLocal;
import miage.spacelib.entities.Station;
import miage.spacelib.entities.Trajet;
import miage.spacelib.entities.Usager;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceAdmin implements ServiceAdminRemote {

    final static Logger log4j = Logger.getLogger(ServiceAdmin.class);
    
    @EJB
    GestionSpacelibLocal gestionSpacelib;
    
    @Override
    public void creerStation(String nom, float coordX, float coordY, List<Integer> nbPass) {
        log4j.debug("creerStation");
        gestionSpacelib.creerStation(nom, coordX, coordY, nbPass);
    }

    @Override
    public void ajouterMeca(String nom, String prenom, String pass) {
        log4j.debug("ajouterMeca");
        gestionSpacelib.ajouterMeca(nom, prenom, pass);
    }

    @Override
    public void creerTrajet(String st1, String st2, int duree) {
        log4j.debug("creerTrajet");
        gestionSpacelib.creerTrajet(st1, st2, duree);
    }
    
    @Override
    public List<String[]> getStations() {
        log4j.debug("getStations");
        List<Station> ls = gestionSpacelib.getStations();
        List<String[]> stations = new ArrayList<>();
        for (Station st : ls ) {
            String[] maStation = new String []{st.getNom()};
            stations.add(maStation);
        }
        return stations;
    }
    
    @Override
    public List<String[]> getMecas() {
        log4j.debug("getMecas");
        List<Usager> lu = gestionSpacelib.getMecas();
        List<String[]> mecas = new ArrayList<>();
        for (Usager us : lu ) {
            String[] monUsager = new String []{us.getNom(), us.getPrenom(), us.getMdp()};
            mecas.add(monUsager);
        }
        return mecas;
    }
    
    @Override
    public List<String[]> getTrajets() {
        log4j.debug("getTrajets");
        List<Trajet> lt = gestionSpacelib.getTrajets();
        List<String[]> trajets = new ArrayList<>();
        for (Trajet tr : lt ) {
            String[] monTrajet = new String []{tr.getStationArr().getNom(), tr.getStationDep().getNom(), Integer.toString(tr.getDureeVoyage())};
            trajets.add(monTrajet);
        }
        return trajets;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void ajouterConduc(String nom, String prenom, String pass) {
        log4j.debug("ajouterConduc");
        gestionSpacelib.ajouterConducteur(nom, prenom, pass);
    }

    @Override
    public void nettoyerResa() {
        log4j.debug("nettoyerResa");
        gestionSpacelib.nettoyerResa();
    }

    @Override
    public List<String[]> getConducts() {
        log4j.debug("getMecas");
        List<Usager> lu = gestionSpacelib.getConducts();
        List<String[]> conducts = new ArrayList<>();
        for (Usager us : lu ) {
            String[] monUsager = new String []{us.getNom(), us.getPrenom(), us.getMdp()};
            conducts.add(monUsager);
        }
        return conducts;
    }

}
