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
import miage.spacelib.entities.Navette;
import miage.spacelib.entities.Quai;
import miage.spacelib.services.ServiceMecaLocal;

/**
 *
 * @author Quentin
 */
@WebService(serviceName = "WSmeca")
@Stateless()
public class WSmeca {

    @EJB
    private ServiceMecaLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "authentifier")
    @Oneway
    public void authentifier(@WebParam(name = "login") String login, @WebParam(name = "pass") String pass) {
        ejbRef.authentifier(login, pass);
    }

    @WebMethod(operationName = "initierRevision")
    public Quai initierRevision(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idNavette") Long idNavette, @WebParam(name = "station") String station) {
        return ejbRef.initierRevision(idUsager, idNavette, station);
    }

    @WebMethod(operationName = "finaliserRevision")
    @Oneway
    public void finaliserRevision(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idNavette") Long idNavette, @WebParam(name = "station") String station) {
        ejbRef.finaliserRevision(idUsager, idNavette, station);
    }

    @WebMethod(operationName = "afficherRevision")
    public List<Navette> afficherRevision(@WebParam(name = "station") String station, @WebParam(name = "idUsager") Long idUsager) {
        return ejbRef.afficherRevision(station, idUsager);
    }
    
}
