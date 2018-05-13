/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import miage.spacelib.miagespacelibshared.ReservationUs;
import miage.spacelib.services.ServiceResaLocal;

/**
 *
 * @author Quentin
 */
@WebService(serviceName = "WSResa")
@Stateless()
public class WSResa {

    @EJB
    private ServiceResaLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerReservation")
    public String creerReservation(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "station1") String station1, @WebParam(name = "station2") String station2, @WebParam(name = "nbPass") int nbPass, @WebParam(name = "date") Date date) {
        return ejbRef.creerReservation(idUsager, station1, station2, nbPass, date);
    }

    @WebMethod(operationName = "annulerReservation")
    @Oneway
    public void annulerReservation(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idResa") Long idResa) {
        ejbRef.annulerReservation(idUsager, idResa);
    }

    @WebMethod(operationName = "afficherReservations")
    public List<ReservationUs> afficherReservations(@WebParam(name = "idUsager") Long idUsager) {
        return ejbRef.afficherReservations(idUsager);
    }

    @WebMethod(operationName = "authentifier")
    public Long authentifier(@WebParam(name = "login") String login, @WebParam(name = "pass") String pass) {
        return ejbRef.authentifier(login, pass);
    }
    
}
