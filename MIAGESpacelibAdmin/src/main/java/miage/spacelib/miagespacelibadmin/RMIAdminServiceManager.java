/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.miagespacelibadmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.spacelib.services.ServiceAdminRemote;

/**
 *
 * @author Quentin
 */
public class RMIAdminServiceManager {
    
    private final static String SERVICES_DAB_EJB_URI = "java:global/MIAGESpacelib-ear/MIAGESpacelib-ejb-1.0-SNAPSHOT/ServiceAdmin!miage.spacelib.services.ServiceAdminRemote";

    private ServiceAdminRemote remoteSvc;

    public RMIAdminServiceManager() throws NamingException, NotBoundException, RemoteException, MalformedURLException {
        this.retrieveRemoteServicesAdmin();
    }

    private void retrieveRemoteServicesAdmin() throws NamingException, NotBoundException, RemoteException, MalformedURLException {
        this.remoteSvc = (ServiceAdminRemote) Naming.lookup(SERVICES_DAB_EJB_URI);
    }

    public ServiceAdminRemote getAdminRemoteSvc() {
        return remoteSvc;
    }
    
}
