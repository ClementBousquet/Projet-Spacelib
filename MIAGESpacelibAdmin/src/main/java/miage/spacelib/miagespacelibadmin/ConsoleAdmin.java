/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.miagespacelibadmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import miage.spacelib.services.ServiceAdminRemote;

/**
 *
 * @author Quentin
 */
public class ConsoleAdmin {
    
    private final ServiceAdminRemote services;
    private final Scanner scanner = new Scanner(System.in);
    
    ConsoleAdmin(ServiceAdminRemote sv) {
        this.services = sv;
    }
    
    public void run() {
        
        int choix = -1;
        
        choix = (int) CLIUtils.saisirEntier(scanner, "Generer Jeu de Test ? (0 : Non/ 1 : Oui) : ", 0, 1);
        
        if (choix == 1) {
            this.genererJeuTest();
        }
        
        do {
            do {
                showMenu();
                choix = (int) CLIUtils.saisirEntier(scanner, "Votre choix : ", 0, 3);
                    switch (choix) {
                        case 0:
                            break;
                        case 1:
                            AjouterStation();
                            choix = this.askNext();
                            break;
                        case 2:
                            AjouterTrajet();
                            choix = this.askNext();
                            break;
                        case 3:
                            AjouterMeca();
                            choix = this.askNext();
                            break;
                        default:
                            System.out.println("Erreur de choix");
                    }
            } while(choix != 0); 
            this.quitter();
        } while(true);
        
    }
    
    private void showMenu() {
        CLIUtils.afficherTitreSection("Menu de sélection");
        System.out.println("\t0. Quitter");
        System.out.println("\t1. Ajouter une nouvelle Station");
        System.out.println("\t2. Ajouter un nouveau Trajet");
        System.out.println("\t3. Ajouter un nouveau Mécanicien");
    }
    
    private void AjouterMeca() {
        String nom = CLIUtils.saisirChaine(scanner, "Entrer le nom : ");
        String prenom = CLIUtils.saisirChaine(scanner, "Entrer le prenom : ");
        String pass = CLIUtils.saisirChaine(scanner, "Entrer le mot de passe : ");
        this.services.ajouterMeca(nom, prenom, pass);
    }
    
    private void AjouterTrajet() {
        String st1 = CLIUtils.saisirChaine(scanner, "Entrer la station de départ : ");
        String st2 = CLIUtils.saisirChaine(scanner, "Entrer la station d'arrivée : ");
        long duree = CLIUtils.saisirEntier(scanner, "Entrer la durée du voyage : ");
        this.services.creerTrajet(st1, st2, (int) duree);
    }
    
    private void AjouterStation() {
        Long nbPass;
        List<Integer> lint = new ArrayList();
        String st = CLIUtils.saisirChaine(scanner, "Entrer le nom de la station : ");
        Long coordX = CLIUtils.saisirEntier(scanner, "Entrer la latitude : ");
        Long coordY = CLIUtils.saisirEntier(scanner, "Entrer la longitude : ");
        boolean choix = true;
        while(choix) {
            nbPass = CLIUtils.saisirEntier(scanner, "Entrer le nombre de place de la navette : ");
            lint.add((int) (long) nbPass);
            choix = CLIUtils.yesNoQuestion(scanner, "Souhaitez-vous ajouter une nouvelle navette (y|n) ?");
        }

        this.services.creerStation(st, coordX, coordY, lint);
        
    }
    
    private int askNext() {
        return CLIUtils.yesNoQuestion(scanner, "Souhaitez-vous effectuer une autre action (y|n) ?") ? 1 : 0;
    }
    
    private void quitter() {
        CLIUtils.afficherTitreSection("Au revoir");
    }
    
    private void genererJeuTest() {
        
        List<Integer> navettes = new ArrayList();
        navettes.add(2);
        navettes.add(5);
        navettes.add(5);
        this.services.creerStation("Terre", 1, 1, navettes);
        navettes.add(15);
        this.services.creerStation("Dimidium", 2, 2, navettes);
        this.services.creerStation("Arion", 3, 3, navettes);
        this.services.creerStation("Brahe", 4, 4, navettes);
        navettes.add(10);
        navettes.add(10);
        this.services.creerStation("Amateru", 5, 5, navettes);
        this.services.creerStation("Tadmor", 6, 6, navettes);
        
        this.services.ajouterMeca("Guillet", "Quentin", "toulouse");
        this.services.ajouterMeca("Bousquet", "Clement", "canard");
        this.services.ajouterMeca("Pasero", "Hugo", "toulouse");
        
        this.services.creerTrajet("Terre", "Dimidium", 2);
        this.services.creerTrajet("Terre", "Arion", 6);
        this.services.creerTrajet("Terre", "Brahe", 2);
        this.services.creerTrajet("Terre", "Amateru", 4);
        this.services.creerTrajet("Terre", "Tadmor", 2);
        
        this.services.creerTrajet("Dimidium", "Terre", 2);
        this.services.creerTrajet("Dimidium", "Arion", 6);
        this.services.creerTrajet("Dimidium", "Brahe", 4);
        this.services.creerTrajet("Dimidium", "Amateru", 6);
        this.services.creerTrajet("Dimidium", "Tadmor", 4);
        
        this.services.creerTrajet("Arion", "Terre", 6);
        this.services.creerTrajet("Arion", "Dimidium", 6);
        this.services.creerTrajet("Arion", "Brahe", 6);
        this.services.creerTrajet("Arion", "Amateru", 8);
        this.services.creerTrajet("Arion", "Tadmor", 6);
        
        this.services.creerTrajet("Brahe", "Terre", 2);
        this.services.creerTrajet("Brahe", "Dimidium", 4);
        this.services.creerTrajet("Brahe", "Arion", 6);
        this.services.creerTrajet("Brahe", "Amateru", 4);
        this.services.creerTrajet("Brahe", "Tadmor", 2);
        
        this.services.creerTrajet("Amateru", "Terre", 4);
        this.services.creerTrajet("Amateru", "Dimidium", 6);
        this.services.creerTrajet("Amateru", "Arion", 8);
        this.services.creerTrajet("Amateru", "Brahe", 4);
        this.services.creerTrajet("Amateru", "Tadmor", 2);
        
        this.services.creerTrajet("Tadmor", "Terre", 2);
        this.services.creerTrajet("Tadmor", "Dimidium", 4);
        this.services.creerTrajet("Tadmor", "Arion", 6);
        this.services.creerTrajet("Tadmor", "Brahe", 2);
        this.services.creerTrajet("Tadmor", "Amateru", 2);
        
        System.out.println("Jeu de Test initialisé");
        
    }
    
}
