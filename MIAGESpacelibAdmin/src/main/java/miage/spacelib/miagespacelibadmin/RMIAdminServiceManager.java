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
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.spacelib.services.ServiceAdminRemote;

/**
 *
 * @author Quentin
 */
public class RMIAdminServiceManager {
    
    private final static String GLASSFISH_ORB_HOST = "localhost";
    private final static String GLASSFISH_ORB_PORT = "3700";
    private final static String SERVICES_DAB_EJB_URI = "java:global/MIAGESpacelib-ear/MIAGESpacelib-ejb-1.0-SNAPSHOT/ServiceAdmin!miage.spacelib.services.ServiceAdminRemote";

    private InitialContext namingContext;
    private ServiceAdminRemote remoteSvc;

    public RMIAdminServiceManager() throws NamingException {
        this.initJndi();
        this.retrieveRemoteServicesAdmin();
    }
    
     private void initJndi() throws NamingException {
        Properties jNDIProperties = new Properties();
        jNDIProperties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        jNDIProperties.setProperty("org.omg.CORBA.ORBInitialHost", GLASSFISH_ORB_HOST);
        jNDIProperties.setProperty("org.omg.CORBA.ORBInitialPort", GLASSFISH_ORB_PORT);
        this.namingContext = new InitialContext(jNDIProperties);
    }

    private void retrieveRemoteServicesAdmin() throws NamingException {
        this.remoteSvc = (ServiceAdminRemote) this.namingContext.lookup(SERVICES_DAB_EJB_URI);
    }

    public ServiceAdminRemote getAdminRemoteSvc() {
        return remoteSvc;
    }
    
}
