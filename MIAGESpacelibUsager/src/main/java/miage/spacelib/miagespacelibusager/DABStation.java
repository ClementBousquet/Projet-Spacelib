package miage.spacelib.miagespacelibusager;

import java.util.List;
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
    private String stationActuelle;
    
    private Long idUs = null;
    private Boolean isVoyage = false;
    private VoyageVoyage v = new VoyageVoyage(0L, null, null, 0);
    private VoyageVoyage vComp = new VoyageVoyage(0L, null, null, 0);
    
    public DABStation(ServiceUsagerRemote services) {
        this.services = services;
        this.stationActuelle = "Terre";
    }
    
    public void run() { 
        int choix = -1;
        
        choix = (int) CLIUtils.saisirEntier(scanner, "Generer Jeu de Test ? (0 : Non/ 1 : Oui) : ", 0, 1);
        
        this.setStationActuelle();
        
        if (choix == 1) {
            this.genererJeuTest();
        }
        
        do {
            do {
                showMenu();
                choix = (int) CLIUtils.saisirEntier(scanner, "Votre choix : ", 0, 3);
                switch (choix) {
                    case 1:
                        if (this.authentifier()) {
                            launchServCli();
                        } else {
                            System.out.println("Erreur lors de l'authentification");
                            System.out.println("Rappel identifiant : NOM.PRENOM");

                        }
                        choix = this.askNext();
                        break;
                    case 2:
                        inscrire();
                        choix = this.askNext();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Erreur de choix");
                }
            } while (choix != 0);
            this.quitter();
        } while (true);
    }
    
     private void launchServCli() {
        int choix2 = -1;
        do {
            v = services.afficherVoyage(idUs);
            this.showMenuAuthent();
            choix2 = (int) CLIUtils.saisirEntier(scanner, "Que voulez vous faire : ", 0, 1);
            switch (choix2) {
                case 0 :
                    deconnexion();
                    break;
                case 1 :
                    if(v.equals(vComp)) {
                        initierVoyage();
                    } else {
                        finaliserVoyage();
                    }
                    choix2 = this.askNext();
                    break;
                default:
                    System.out.println("Erreur de choix");
            }
            
        } while(choix2 != 0);
    }
    
    private void deconnexion() {
        idUs = 0L;
    } 
     
    private void initierVoyage() {
        int st;
        long nbPass = CLIUtils.saisirEntier(scanner, "Combien de passagers ? : ");
        List<String> stations = this.services.recupStations();
        int i  = showMenuStation(stations);
        st = (int) CLIUtils.saisirEntier(scanner, "Votre choix : ", 0, i-1);
        String quai = this.services.initierVoyage(idUs, (int) nbPass, stations.get(st), this.stationActuelle);
        System.out.println("Voyage Initiée, veuillez vous rendre au quai "+quai);
    } 
    
    private void finaliserVoyage() {
        this.services.finaliserVoyage(idUs, v);
        v = vComp;
        System.out.println("Voyage Achevée");
    }
    
    private void inscrire() {
        String nom = CLIUtils.saisirChaine(scanner, "Rentrer votre nom : ");
        String prenom = CLIUtils.saisirChaine(scanner, "Rentrer votre prenom : ");
        String mdp = CLIUtils.saisirChaine(scanner, "Rentrer un nouveau mot de passe : ");
        
        this.services.inscrire(nom, prenom, mdp);
        
    } 
     
    private void showMenu() {
        CLIUtils.afficherTitreSection("Menu de sélection");
        System.out.println("\t0. Quitter");
        System.out.println("\t1. Authentifier");
        System.out.println("\t2. S'inscrire"); 
    }
    
    private void showMenuAuthent () {
        if (v.getIdVoyage().equals(vComp.getIdVoyage())) {
            CLIUtils.afficherTitreSection("Menu de sélection");
            System.out.println("\t0. Déconnexion");
            System.out.println("\t1. InitierVoyage");
        } else {
            CLIUtils.afficherTitreSection("Menu de sélection");
            System.out.println("\t0. Déconnexion");
            System.out.println("\t1. FinaliserVoyage");
        }
           
    }
    
    private int showMenuStation(List<String> stations) {
        CLIUtils.afficherTitreSection("Menu de sélection");
        for (int i = 0; i < stations.size(); i++) {
            if(!stations.get(i).equals(stationActuelle))
                System.out.println("\t"+i+". "+stations.get(i));
        }
        return stations.size();
    }
    
    private void genererJeuTest() {
        
        this.services.inscrire("Ate", "Tom", "mdp");
        this.services.inscrire("Alain", "Terieur", "mdp");
        this.services.inscrire("Haile", "Leau", "mdp");
        
        System.out.println("Jeu de Test initialisé");
        
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
        
        if (login.contains("."))
            idUs = services.authentifier(login, passw);
        else
            return false;
            
        if (!idUs.equals(0L)){
            return true;
        }       
        return false;
    }

    private void setStationActuelle() {
        List<String> stations = this.services.recupStations();
        int nbStations  = showMenuStation(stations);
        int station = (int) CLIUtils.saisirEntier(scanner, "Votre choix : ", 0, nbStations-1);
        this.stationActuelle = stations.get(station);
    }
    
}
