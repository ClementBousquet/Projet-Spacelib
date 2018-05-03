/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Quentin
 */
@Remote
public interface ServiceAdminRemote {
    
    void creerStation(String nom, float coordX, float coordY, List<Integer> nbPass);
    void ajouterMeca(String nom, String prenom, String pass);
    
}
