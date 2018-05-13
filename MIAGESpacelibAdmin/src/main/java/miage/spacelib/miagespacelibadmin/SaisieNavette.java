/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.miagespacelibadmin;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import miage.spacelib.miagespacelibadmin.InterfaceAdmin.Etat;
import miage.spacelib.services.ServiceAdminRemote;

/**
 *
 * @author clem
 */
public class SaisieNavette extends javax.swing.JFrame {
    
    private final ServiceAdminRemote services;
    private InterfaceAdmin intAdmin;

    public SaisieNavette(ServiceAdminRemote adminRemoteSvc, InterfaceAdmin intAdmin, String nom, float x, float y, ArrayList<JPanel> panels, ArrayList<JLabel> labels, ArrayList<JComboBox> combos) {
        this.services = adminRemoteSvc;
        this.intAdmin = intAdmin;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                intAdmin.Etat = Etat.Etat_initial;
                intAdmin.transitionEtat_initial();
            }
        });
        this.setLayout(new GridLayout(panels.size()+2,1));
        JPanel header = new JPanel();
        JLabel labelHeader = new JLabel("Capacité des navettes de la station "+nom+" :");
        header.add(labelHeader);
        this.add(header);
        for (int i=0; i<panels.size();i++) {
            panels.get(i).add(labels.get(i));
            panels.get(i).add(combos.get(i));
            this.add(panels.get(i));
        }
        JButton jButtonAjouterNavette = new JButton("Valider");
        jButtonAjouterNavette.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterNavetteActionPerformed(evt);
            }

            private void jButtonAjouterNavetteActionPerformed(ActionEvent evt) {
                List<Integer> nbPass = new ArrayList<>();
                for (int i=0; i<combos.size();i++){
                    nbPass.add(Integer.parseInt((String) combos.get(i).getSelectedItem()));
                }
                services.creerStation(nom, x, y, nbPass);
                intAdmin.resetOngletStation();
                intAdmin.Etat = Etat.Etat_initial;
                intAdmin.transitionEtat_initial();
                dispose();
            }
        });
        JPanel footer = new JPanel();
        footer.add(jButtonAjouterNavette);
        this.add(footer);
        this.pack();
    }
    
}
