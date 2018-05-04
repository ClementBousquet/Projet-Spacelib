package miage.spacelib.miagespacelibusager;

import java.util.Scanner;
import miage.spacelib.miagespacelibshared.VoyageVoyage;
import miage.spacelib.services.ServiceUsagerRemote;

/**
 *
 * @author Quentin
 */
public class DABStation {
    
    private final ServiceUsagerRemote services;
    private final Scanner scanner = new Scanner(System.in);
    
    private Long idUs = null;
    private Boolean isVoyage = false;
    private VoyageVoyage v = null;
    
    public DABStation(ServiceUsagerRemote services) {
        this.services = services;
    }
    
    public void run() {
        genererJeuTest(); 
        int choix = -1;
        do {
            do {
                try {
                    showMenu();
                    choix = (int) CLIUtils.saisirEntier(scanner, "Votre choix : ", 0, 3);
                    switch (choix) {
                        case 1:
                            if(this.authentifier()) {
                                launchServCli();
                            };
                            choix = this.askNext();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Erreur de choix");
                    }
                } catch(Exception ex){
                    System.out.println("Votre compte est inconnu.");
                    choix = 0;
                }
            } while (choix != 0);
            this.quitter();
        } while (true);
    }
    
     private void launchServCli() {
        int choix = -1;
        do {
            v = services.afficherVoyage(idUs);
            
            this.showMenuAuthent();
            choix = (int) CLIUtils.saisirEntier(scanner, "Que voulez vous faire : ", 0, 1);
            switch (choix) {
                case 0 :
                    break;
                case 1 :
                    if(v.equals(null)) {
                        //A COMPLETER
                    } else {
                        //A COMPLETER
                    }
                    break;
                default:
                    System.out.println("Erreur de choix");
            }
            
        } while(choix != 0);
    }
    
    private void showMenu() {
        CLIUtils.afficherTitreSection("Menu de sélection");
        System.out.println("\t0. Quitter");
        System.out.println("\t1. Authentifier");
        System.out.println("\t2. S'inscrire"); 
    }
    
    private void showMenuAuthent () {
        if (v.equals(null)) {
            CLIUtils.afficherTitreSection("Menu de sélection");
            System.out.println("\t0. Déconnexion");
            System.out.println("\t1. InitierVoyage");
        } else {
            CLIUtils.afficherTitreSection("Menu de sélection");
            System.out.println("\t0. Déconnexion");
            System.out.println("\t1. FinaliserVoyage");
        }
           
    }
    
    private void genererJeuTest() {
        
        this.services.inscrire("Ate", "Tom", "mdp");
        this.services.inscrire("Alain", "Terieur", "mdp");
        this.services.inscrire("Haile", "Leau", "mdp");
        
    }
    
    private int askNext() {
        return CLIUtils.yesNoQuestion(scanner, "Souhaitez-vous effectuer une autre action (y|n) ?") ? 1 : 0;
    }
    
    private void quitter() {
        CLIUtils.afficherTitreSection("Au revoir");
    }
    
    private boolean authentifier() {
        String login = CLIUtils.saisirChaine(scanner, "Rentrer votre login : ");
        String passw = CLIUtils.saisirChaine(scanner, "Rentrer votre mot de passe : ");
        
        idUs = services.authentifier(login, passw);
        
        if ( !idUs.equals(null) ){
            return true;
        }       
        return false;
    }
    
}
