/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.miagespacelibusager;

import javax.naming.NamingException;

/**
 *
 * @author Quentin
 */
public class Launcher {
    
    public static void main(String[] args) {
        try{
            RMIUsagerServiceManager rmiMgr = new RMIUsagerServiceManager();
            DABStation cons = new DABStation(rmiMgr.getUsagerRemoteSvc());
            cons.run();
        }catch(NamingException ex){
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
        
    }
    
}
