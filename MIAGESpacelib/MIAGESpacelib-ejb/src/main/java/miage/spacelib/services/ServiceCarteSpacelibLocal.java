/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.services;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import miage.spacelib.entities.Station;
import miage.spacelib.miagespacelibshared.Coordonnee;
import miage.spacelib.miagespacelibshared.StationUs;

/**
 *
 * @author Quentin
 */
@Local
public interface ServiceCarteSpacelibLocal {
    
    List<StationUs> genCarteSpacelib();
    
}
