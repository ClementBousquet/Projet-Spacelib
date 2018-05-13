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
import javax.jws.WebMethod;
import miage.spacelib.miagespacelibshared.StationUs;
import miage.spacelib.services.ServiceCarteSpacelibLocal;

/**
 *
 * @author Quentin
 */
@WebService(serviceName = "WSCarte")
@Stateless()
public class WSCarte {

    @EJB
    private ServiceCarteSpacelibLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "genCarteSpacelib")
    public List<StationUs> genCarteSpacelib() {
        return ejbRef.genCarteSpacelib();
    }
    
}
