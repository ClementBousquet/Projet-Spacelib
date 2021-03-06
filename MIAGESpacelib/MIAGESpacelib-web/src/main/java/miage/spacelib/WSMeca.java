/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import miage.spacelib.miagespacelibshared.TrajetAEffectuer;
import miage.spacelib.services.ServiceMecaLocal;

/**
 *
 * @author Quentin
 */
@WebService(serviceName = "WSMeca")
@Stateless()
public class WSMeca {

    @EJB
    private ServiceMecaLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "authentifierMeca")
    public Long authentifierMeca(@WebParam(name = "login") String login, @WebParam(name = "pass") String pass) {
        return ejbRef.authentifierMeca(login, pass);
    }

    @WebMethod(operationName = "initierRevision")
    public String initierRevision(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idNavette") Long idNavette, @WebParam(name = "station") String station) {
        return ejbRef.initierRevision(idUsager, idNavette, station);
    }

    @WebMethod(operationName = "finaliserRevision")
    @Oneway
    public void finaliserRevision(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idNavette") Long idNavette, @WebParam(name = "station") String station) {
        ejbRef.finaliserRevision(idUsager, idNavette, station);
    }

    @WebMethod(operationName = "afficherRevision")
    public List<String> afficherRevision(@WebParam(name = "station") String station, @WebParam(name = "idUsager") Long idUsager) {
        return ejbRef.afficherRevision(station, idUsager);
    }

    @WebMethod(operationName = "authentifierConduc")
    public Long authentifierConduc(@WebParam(name = "login") String login, @WebParam(name = "pass") String pass) {
        return ejbRef.authentifierConduc(login, pass);
    }

    @WebMethod(operationName = "transfertNecessaire")
    public List<TrajetAEffectuer> transfertNecessaire() {
        return ejbRef.transfertNecessaire();
    }

    @WebMethod(operationName = "getStations")
    public List<String[]> getStations() {
        return ejbRef.getStations();
    }

    @WebMethod(operationName = "getStatutMeca")
    public String getStatutMeca(@WebParam(name = "idUs") Long idUs) {
        return ejbRef.getStatutMeca(idUs);
    }
    
}
