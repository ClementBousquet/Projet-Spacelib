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
import miage.spacelib.miagespacelibshared.ReservationUs;
import miage.spacelib.miagespacelibshared.VoyageVoyage;
import miage.spacelib.services.ServiceUsagerV4Local;

/**
 *
 * @author Quentin
 */
@WebService(serviceName = "WSUsagerV4")
@Stateless()
public class WSUsagerV4 {

    @EJB
    private ServiceUsagerV4Local ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "authentifier")
    public Long authentifier(@WebParam(name = "login") String login, @WebParam(name = "pass") String pass) {
        return ejbRef.authentifier(login, pass);
    }

    @WebMethod(operationName = "inscrire")
    @Oneway
    public void inscrire(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "pass") String pass) {
        ejbRef.inscrire(nom, prenom, pass);
    }

    @WebMethod(operationName = "initierVoyage")
    public String initierVoyage(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "nbPass") int nbPass, @WebParam(name = "stationArr") String stationArr, @WebParam(name = "stationDep") String stationDep) {
        return ejbRef.initierVoyage(idUsager, nbPass, stationArr, stationDep);
    }

    @WebMethod(operationName = "afficherResa")
    public ReservationUs afficherResa(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "st") String st) {
        return ejbRef.afficherResa(idUsager, st);
    }

    @WebMethod(operationName = "cloturerReservation")
    public String cloturerReservation(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idResa") Long idResa) {
        return ejbRef.cloturerReservation(idUsager, idResa);
    }

    @WebMethod(operationName = "annulerReservation")
    @Oneway
    public void annulerReservation(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idResa") Long idResa) {
        ejbRef.annulerReservation(idUsager, idResa);
    }

    @WebMethod(operationName = "finaliserVoyage")
    @Oneway
    public void finaliserVoyage(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "v") VoyageVoyage v) {
        ejbRef.finaliserVoyage(idUsager, v);
    }

    @WebMethod(operationName = "afficherVoyage")
    public VoyageVoyage afficherVoyage(@WebParam(name = "idUsager") Long idUsager) {
        return ejbRef.afficherVoyage(idUsager);
    }

    @WebMethod(operationName = "recupStations")
    public List<String> recupStations() {
        return ejbRef.recupStations();
    }
    
}
