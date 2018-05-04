/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.miagespacelibadmin;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.naming.NamingException;

/**
 *
 * @author Quentin
 */
public class Launcher {
    
    public static void main(String[] args) throws NotBoundException, RemoteException, MalformedURLException{
        try{
            RMIAdminServiceManager rmiMgr = new RMIAdminServiceManager();
            ConsoleAdmin cons = new ConsoleAdmin(rmiMgr.getAdminRemoteSvc());
            cons.run();
        }catch(NamingException ex){
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
        
    }
    
}