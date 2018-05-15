package miage.spacelib.miagespacelibusager;

import java.util.List;
import java.util.Scanner;
import miage.spacelib.miagespacelibshared.ReservationUs;
import miage.spacelib.miagespacelibshared.VoyageVoyage;
import miage.spacelib.services.ServiceUsagerV4Remote;

/**
 *
 * @author Quentin
 */
public class DABStation {
    
    private final ServiceUsagerV4Remote services;
    private final Scanner scanner = new Scanner(System.in);
    private String stationActuelle;
    
    private int run = 0;
    
    private Long idUs = null;
    private Boolean isVoyage = false;
    private VoyageVoyage v = new VoyageVoyage(0L, null, null, 0);
    private VoyageVoyage vComp = new VoyageVoyage(0L, null, null, 0);
    private ReservationUs r = new ReservationUs(0L, null, null, null);
    private ReservationUs rComp = new ReservationUs(0L, null, null, null);
    
    public DABStation(ServiceUsagerV4Remote services) {
        this.services = services;
        this.stationActuelle = "Terre";
    }
    
    public void run() { 
        int choix = -1;
        
        choix = (int) CLIUtils.saisirEntier(scanner, "Generer Jeu de Test ? (0 : Non/ 1 : Oui) : ", 0, 1);
        
        if (choix == 1) {
            this.genererJeuTest();
        }
        
        this.setStationActuelle();
        
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
            run = CLIUtils.yesNoQuestion(scanner, "Voulez vous quitter l'application (y|n) ?") ? 1 : 0;
            this.quitter();
        } while (run != 1);
    }
    
     private void launchServCli() {
        int choix2 = -1;
        do {
            v = services.afficherVoyage(idUs);
            r = services.afficherResa(idUs, stationActuelle);
            this.showMenuAuthent();
            choix2 = (int) CLIUtils.saisirEntier(scanner, "Que voulez vous faire : ", 0, 1);
            switch (choix2) {
                case 0 :
                    deconnexion();
                    break;
                case 1 :
                    if (!r.getIdReservation().equals(rComp.getIdReservation())) {
                        cloturerReservation();
                    }
                    else if (v.getIdVoyage().equals(vComp.getIdVoyage())) {
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
        stations.remove(this.stationActuelle);
        int i  = showMenuStation(stations);
        st = (int) CLIUtils.saisirEntier(scanner, "Votre choix : ", 0, i-1);
        String msgQuai = this.services.initierVoyage(idUs, (int) nbPass, stations.get(st), this.stationActuelle);
        System.out.println(msgQuai);
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
            if (r.getIdReservation().equals(rComp.getIdReservation())) {
                System.out.println("\t1. InitierVoyage");
            } else {
                System.out.println("\t1. CloturerReservation");
            }          
        } else {
            CLIUtils.afficherTitreSection("Menu de sélection");
            System.out.println("\t0. Déconnexion");
            System.out.println("\t1. FinaliserVoyage");
        }
           
    }
    
    private int showMenuStation(List<String> stations) {
        CLIUtils.afficherTitreSection("Menu de sélection");
        for (int i = 0; i < stations.size(); i++) {
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
    
    private void cloturerReservation() {
        System.out.println("Vous avez une réservation le "+r.getDate()+ "partant du quai "+r.getQuaidep()+".");
        int choix = CLIUtils.yesNoQuestion(scanner, "Souhaitez-vous confirmer cette réservation (y|n) ?") ? 1 : 0;
        if (choix == 1) {
            services.cloturerReservation(idUs, r.getIdReservation());
        } else {
            services.annulerReservation(idUs, r.getIdReservation());
        }
    }
    
}
