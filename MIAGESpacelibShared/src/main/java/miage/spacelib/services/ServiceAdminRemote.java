/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Quentin
 */
@Remote
public interface ServiceAdminRemote {
    /* V1 */
    void creerStation(String nom, float coordX, float coordY, List<Integer> nbPass);
    void ajouterMeca(String nom, String prenom, String pass);
    void creerTrajet(String st1, String st2, int duree);
    List<String[]> getStations();
    List<String[]> getMecas();
    List<String[]> getTrajets();
    List<String[]> getConducts();
    /* V3 */
    void ajouterConduc(String nom, String prenom, String pass);
    /* V4 */
    void nettoyerResa();

    
}
