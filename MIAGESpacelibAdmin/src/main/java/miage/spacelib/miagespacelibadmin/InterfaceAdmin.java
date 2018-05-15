/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.miagespacelibadmin;

import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import miage.spacelib.services.ServiceAdminRemote;

/**
 *
 * @author Clem
 */
public class InterfaceAdmin extends javax.swing.JFrame {
    
    private final ServiceAdminRemote services;
    private List<String[]> stations = new ArrayList<>();
    private List<String[]> trajets = new ArrayList<>();
    private List<String[]> mecas = new ArrayList<>();
    private List<String[]> conducts = new ArrayList<>();
    Etat Etat;
    
    protected enum Etat {
        Etat_initial,
        Etat_SaisieNavette
    }

    /**
     * Creates new form InterfaceAdmin
     */
    public InterfaceAdmin(ServiceAdminRemote sv) {
        initComponents();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) this.jSpinnerNbNavette.getEditor()).getTextField();
        tf.setEditable(false);
        JFormattedTextField tf2 = ((JSpinner.DefaultEditor) this.jSpinnerDureeTrajet.getEditor()).getTextField();
        tf2.setEditable(false);
        this.services = sv;
        this.Etat = Etat.Etat_initial;
        resetOngletStation();
        resetOngletTrajet();
        resetOngletMeca();
        resetOngletConducteur();
    }
    
    public void transitionEtat_SaisieNavette(){
        this.jButtonAjouterStation.setEnabled(false);
        this.jTabbedPane.setEnabled(false);
        this.jTextFieldCoordonneesX.setEnabled(false);
        this.jTextFieldCoordonneesY.setEnabled(false);
        this.jTextFieldNomStation.setEnabled(false);
        this.jSpinnerNbNavette.setEnabled(false);
    }
    
    public void transitionEtat_initial(){
        this.jButtonAjouterStation.setEnabled(true);
        this.jTabbedPane.setEnabled(true);
        this.jTextFieldCoordonneesX.setEnabled(true);
        this.jTextFieldCoordonneesY.setEnabled(true);
        this.jTextFieldNomStation.setEnabled(true);
        this.jSpinnerNbNavette.setEnabled(true);
        this.jTextFieldCoordonneesX.setText("");
        this.jTextFieldCoordonneesY.setText("");
        this.jTextFieldNomStation.setText("");
        this.jSpinnerNbNavette.setValue(1);
        this.jSpinnerDureeTrajet.setValue(1);
        this.jTextFieldMecaNom.setText("");
        this.jTextFieldMecaPrenom.setText("");
        this.jTextFieldMecaMdp.setText("");
        this.jTextFieldConducteurNom.setText("");
        this.jTextFieldConducteurPrenom.setText("");
        this.jTextFieldConducteurMdp.setText("");
    }
    
    public void resetOngletStation () {
        DefaultTableModel model = (DefaultTableModel) jTableStation.getModel();
        model.setRowCount(0);
        this.stations = this.services.getStations();
        for(int i = 0; i < stations.size(); i++) {
            String rowData[] = { "" };
            rowData[0] = stations.get(i)[0];
            model.addRow(rowData);
        }
        this.jTableStation.repaint();
    }
    
    public void resetOngletTrajet () {
        DefaultTableModel model = (DefaultTableModel) jTableTrajet.getModel();
        model.setRowCount(0);
        this.stations = this.services.getStations();
        this.trajets = this.services.getTrajets();
        for(int i = 0; i < trajets.size(); i++) {
            String rowData[] = { "", "", "" };
            rowData[0] = trajets.get(i)[0]; //Station depart
            rowData[1] = trajets.get(i)[1]; //Station arrivée
            rowData[2] = trajets.get(i)[2]; //Duréé
            model.addRow(rowData);
        }
        this.jTableStation.repaint();
        String [] nomStation = new String [stations.size()];
        for(int i = 0; i < stations.size(); i++) {
            nomStation [i] = stations.get(i)[0];
        }
        DefaultComboBoxModel comboModelDepart = new DefaultComboBoxModel(nomStation);
        DefaultComboBoxModel comboModelArrivee = new DefaultComboBoxModel(nomStation);
        jComboBoxStationDepart.setModel(comboModelDepart);
        jComboBoxStationArrivee.setModel(comboModelArrivee);
    }
    
    public void resetOngletMeca () {
        DefaultTableModel model = (DefaultTableModel) jTableMeca.getModel();
        model.setRowCount(0);
        this.mecas = this.services.getMecas();
        for(int i = 0; i < mecas.size(); i++) {
            String rowData[] = { "", "", ""};
            rowData[0] = mecas.get(i)[0]; //Nom
            rowData[1] = mecas.get(i)[1]; //Prenom
            rowData[2] = mecas.get(i)[2]; //Mdp
            model.addRow(rowData);
        }
        this.jTableMeca.repaint();
    }
    
    public void resetOngletConducteur () {
        DefaultTableModel model = (DefaultTableModel) jTableConducteur.getModel();
        model.setRowCount(0);
        this.conducts = this.services.getConducts();
        for(int i = 0; i < conducts.size(); i++) {
            String rowData[] = { "", "", ""};
            rowData[0] = conducts.get(i)[0]; //Nom
            rowData[1] = conducts.get(i)[1]; //Prenom
            rowData[2] = conducts.get(i)[2]; //Mdp
            model.addRow(rowData);
        }
        this.jTableConducteur.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelStation = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTableStation = new javax.swing.JTable();
        jLabelTableStation = new javax.swing.JLabel();
        jPanelAjoutStation = new javax.swing.JPanel();
        jLabelNomStation = new javax.swing.JLabel();
        jTextFieldNomStation = new javax.swing.JTextField();
        jLabelCoordonneesStation = new javax.swing.JLabel();
        jTextFieldCoordonneesX = new javax.swing.JTextField();
        jTextFieldCoordonneesY = new javax.swing.JTextField();
        jLabelCoordonneesStation2 = new javax.swing.JLabel();
        jButtonAjouterStation = new javax.swing.JButton();
        jLabelNbNavettes = new javax.swing.JLabel();
        jSpinnerNbNavette = new javax.swing.JSpinner();
        jPanelTrajet = new javax.swing.JPanel();
        jLabelTableTrajet = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTrajet = new javax.swing.JTable();
        jPanelAjoutTrajet = new javax.swing.JPanel();
        jLabelStationDepart = new javax.swing.JLabel();
        jLabelStationArrivee = new javax.swing.JLabel();
        jButtonAjouterTrajet = new javax.swing.JButton();
        jLabelDureeTrajet = new javax.swing.JLabel();
        jSpinnerDureeTrajet = new javax.swing.JSpinner();
        jComboBoxStationDepart = new javax.swing.JComboBox<>();
        jComboBoxStationArrivee = new javax.swing.JComboBox<>();
        jPanelMeca = new javax.swing.JPanel();
        jLabelTableMeca = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableMeca = new javax.swing.JTable();
        jPanelAjoutMeca = new javax.swing.JPanel();
        jLabelMecaNom = new javax.swing.JLabel();
        jLabelMecaPrenom = new javax.swing.JLabel();
        jButtonAjouterMeca = new javax.swing.JButton();
        jLabelMecaMdp = new javax.swing.JLabel();
        jTextFieldMecaNom = new javax.swing.JTextField();
        jTextFieldMecaPrenom = new javax.swing.JTextField();
        jTextFieldMecaMdp = new javax.swing.JTextField();
        jPanelConducteur = new javax.swing.JPanel();
        jLabelTableConducteur = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConducteur = new javax.swing.JTable();
        jPanelAjoutConducteur = new javax.swing.JPanel();
        jLabelConducteurNom = new javax.swing.JLabel();
        jLabelConducteurPrenom = new javax.swing.JLabel();
        jButtonAjouterConducteur = new javax.swing.JButton();
        jLabelConducteurMdp = new javax.swing.JLabel();
        jTextFieldConducteurNom = new javax.swing.JTextField();
        jTextFieldConducteurPrenom = new javax.swing.JTextField();
        jTextFieldConducteurMdp = new javax.swing.JTextField();
        jPanelResa = new javax.swing.JPanel();
        jButtonNettoyerReservations = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPaneMouseClicked(evt);
            }
        });

        jTableStation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableStation.setToolTipText("");
        jScrollPane.setViewportView(jTableStation);

        jLabelTableStation.setText("Liste des Stations :");

        jPanelAjoutStation.setBorder(javax.swing.BorderFactory.createTitledBorder("Ajout de Station"));

        jLabelNomStation.setText("Nom de la station :");

        jLabelCoordonneesStation.setText("Coordonnées X , Y :");

        jLabelCoordonneesStation2.setText(",");

        jButtonAjouterStation.setText("Ajouter Station");
        jButtonAjouterStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterStationActionPerformed(evt);
            }
        });

        jLabelNbNavettes.setText("Nombre de navette :");

        jSpinnerNbNavette.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinnerNbNavette.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinnerNbNavette, ""));
        jSpinnerNbNavette.setFocusable(false);

        javax.swing.GroupLayout jPanelAjoutStationLayout = new javax.swing.GroupLayout(jPanelAjoutStation);
        jPanelAjoutStation.setLayout(jPanelAjoutStationLayout);
        jPanelAjoutStationLayout.setHorizontalGroup(
            jPanelAjoutStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjoutStationLayout.createSequentialGroup()
                .addGroup(jPanelAjoutStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAjoutStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAjoutStationLayout.createSequentialGroup()
                            .addGroup(jPanelAjoutStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelNomStation)
                                .addComponent(jLabelCoordonneesStation))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelAjoutStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldNomStation, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelAjoutStationLayout.createSequentialGroup()
                                    .addComponent(jTextFieldCoordonneesX, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabelCoordonneesStation2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCoordonneesY, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanelAjoutStationLayout.createSequentialGroup()
                            .addComponent(jLabelNbNavettes)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinnerNbNavette, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonAjouterStation, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanelAjoutStationLayout.setVerticalGroup(
            jPanelAjoutStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjoutStationLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelAjoutStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomStation)
                    .addComponent(jTextFieldNomStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelAjoutStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCoordonneesStation)
                    .addComponent(jTextFieldCoordonneesX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCoordonneesY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCoordonneesStation2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelAjoutStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNbNavettes)
                    .addComponent(jSpinnerNbNavette, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonAjouterStation)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanelStationLayout = new javax.swing.GroupLayout(jPanelStation);
        jPanelStation.setLayout(jPanelStationLayout);
        jPanelStationLayout.setHorizontalGroup(
            jPanelStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStationLayout.createSequentialGroup()
                .addGroup(jPanelStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTableStation, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelAjoutStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelStationLayout.setVerticalGroup(
            jPanelStationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStationLayout.createSequentialGroup()
                .addComponent(jLabelTableStation)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(jPanelAjoutStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("Gestion Station", jPanelStation);

        jLabelTableTrajet.setText("Liste des Trajets :");

        jTableTrajet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Station de départ", "Station d'arrivée", "Durée (en jour)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTrajet.setToolTipText("");
        jScrollPane1.setViewportView(jTableTrajet);

        jPanelAjoutTrajet.setBorder(javax.swing.BorderFactory.createTitledBorder("Ajout de Trajet"));

        jLabelStationDepart.setText("Station de départ :");

        jLabelStationArrivee.setText("Station d'arrivée :");

        jButtonAjouterTrajet.setText("Ajouter Trajet");
        jButtonAjouterTrajet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterTrajetActionPerformed(evt);
            }
        });

        jLabelDureeTrajet.setText("Durée (en jour) :");

        jSpinnerDureeTrajet.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinnerDureeTrajet.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinnerDureeTrajet, ""));
        jSpinnerDureeTrajet.setFocusable(false);

        javax.swing.GroupLayout jPanelAjoutTrajetLayout = new javax.swing.GroupLayout(jPanelAjoutTrajet);
        jPanelAjoutTrajet.setLayout(jPanelAjoutTrajetLayout);
        jPanelAjoutTrajetLayout.setHorizontalGroup(
            jPanelAjoutTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjoutTrajetLayout.createSequentialGroup()
                .addGroup(jPanelAjoutTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAjoutTrajetLayout.createSequentialGroup()
                        .addComponent(jLabelStationDepart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxStationDepart, 0, 132, Short.MAX_VALUE))
                    .addGroup(jPanelAjoutTrajetLayout.createSequentialGroup()
                        .addComponent(jLabelStationArrivee)
                        .addGap(12, 12, 12)
                        .addComponent(jComboBoxStationArrivee, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelAjoutTrajetLayout.createSequentialGroup()
                        .addComponent(jLabelDureeTrajet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerDureeTrajet))
                    .addComponent(jButtonAjouterTrajet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelAjoutTrajetLayout.setVerticalGroup(
            jPanelAjoutTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjoutTrajetLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelAjoutTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStationDepart)
                    .addComponent(jComboBoxStationDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanelAjoutTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStationArrivee)
                    .addComponent(jComboBoxStationArrivee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelAjoutTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDureeTrajet)
                    .addComponent(jSpinnerDureeTrajet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonAjouterTrajet)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanelTrajetLayout = new javax.swing.GroupLayout(jPanelTrajet);
        jPanelTrajet.setLayout(jPanelTrajetLayout);
        jPanelTrajetLayout.setHorizontalGroup(
            jPanelTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTrajetLayout.createSequentialGroup()
                .addGroup(jPanelTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTableTrajet, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(284, Short.MAX_VALUE))
            .addGroup(jPanelTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTrajetLayout.createSequentialGroup()
                    .addContainerGap(341, Short.MAX_VALUE)
                    .addComponent(jPanelAjoutTrajet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanelTrajetLayout.setVerticalGroup(
            jPanelTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTrajetLayout.createSequentialGroup()
                .addComponent(jLabelTableTrajet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTrajetLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelAjoutTrajet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane.addTab("Gestion Trajet", jPanelTrajet);

        jLabelTableMeca.setText("Liste des Mecaniciens :");

        jTableMeca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prénom", "Mot de passe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMeca.setToolTipText("");
        jScrollPane2.setViewportView(jTableMeca);

        jPanelAjoutMeca.setBorder(javax.swing.BorderFactory.createTitledBorder("Ajout de Mecanicien"));

        jLabelMecaNom.setText("Nom :");

        jLabelMecaPrenom.setText("Prénom :");

        jButtonAjouterMeca.setText("Ajouter Mécanicien");
        jButtonAjouterMeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterMecaActionPerformed(evt);
            }
        });

        jLabelMecaMdp.setText("Mot de passe :");

        jTextFieldMecaPrenom.setToolTipText("");

        javax.swing.GroupLayout jPanelAjoutMecaLayout = new javax.swing.GroupLayout(jPanelAjoutMeca);
        jPanelAjoutMeca.setLayout(jPanelAjoutMecaLayout);
        jPanelAjoutMecaLayout.setHorizontalGroup(
            jPanelAjoutMecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjoutMecaLayout.createSequentialGroup()
                .addGroup(jPanelAjoutMecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAjoutMecaLayout.createSequentialGroup()
                        .addComponent(jLabelMecaNom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldMecaNom))
                    .addGroup(jPanelAjoutMecaLayout.createSequentialGroup()
                        .addComponent(jLabelMecaPrenom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMecaPrenom))
                    .addGroup(jPanelAjoutMecaLayout.createSequentialGroup()
                        .addComponent(jLabelMecaMdp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMecaMdp))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAjoutMecaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAjouterMeca, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelAjoutMecaLayout.setVerticalGroup(
            jPanelAjoutMecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjoutMecaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelAjoutMecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMecaNom)
                    .addComponent(jTextFieldMecaNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelAjoutMecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMecaPrenom)
                    .addComponent(jTextFieldMecaPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelAjoutMecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMecaMdp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMecaMdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonAjouterMeca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMecaLayout = new javax.swing.GroupLayout(jPanelMeca);
        jPanelMeca.setLayout(jPanelMecaLayout);
        jPanelMecaLayout.setHorizontalGroup(
            jPanelMecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTableMeca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelMecaLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAjoutMeca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMecaLayout.setVerticalGroup(
            jPanelMecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMecaLayout.createSequentialGroup()
                .addComponent(jLabelTableMeca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMecaLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanelAjoutMeca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane.addTab("Gestion Mecanicien", jPanelMeca);

        jLabelTableConducteur.setText("Liste des Conducteurs :");

        jTableConducteur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prénom", "Mot de passe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConducteur.setToolTipText("");
        jScrollPane3.setViewportView(jTableConducteur);

        jPanelAjoutConducteur.setBorder(javax.swing.BorderFactory.createTitledBorder("Ajout de Conducteur"));

        jLabelConducteurNom.setText("Nom :");

        jLabelConducteurPrenom.setText("Prénom :");

        jButtonAjouterConducteur.setText("Ajouter Conducteur");
        jButtonAjouterConducteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterConducteurActionPerformed(evt);
            }
        });

        jLabelConducteurMdp.setText("Mot de passe :");

        jTextFieldConducteurPrenom.setToolTipText("");

        javax.swing.GroupLayout jPanelAjoutConducteurLayout = new javax.swing.GroupLayout(jPanelAjoutConducteur);
        jPanelAjoutConducteur.setLayout(jPanelAjoutConducteurLayout);
        jPanelAjoutConducteurLayout.setHorizontalGroup(
            jPanelAjoutConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjoutConducteurLayout.createSequentialGroup()
                .addGroup(jPanelAjoutConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAjoutConducteurLayout.createSequentialGroup()
                        .addComponent(jLabelConducteurNom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldConducteurNom))
                    .addGroup(jPanelAjoutConducteurLayout.createSequentialGroup()
                        .addComponent(jLabelConducteurPrenom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldConducteurPrenom))
                    .addGroup(jPanelAjoutConducteurLayout.createSequentialGroup()
                        .addComponent(jLabelConducteurMdp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldConducteurMdp))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAjoutConducteurLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAjouterConducteur, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelAjoutConducteurLayout.setVerticalGroup(
            jPanelAjoutConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjoutConducteurLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelAjoutConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConducteurNom)
                    .addComponent(jTextFieldConducteurNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelAjoutConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConducteurPrenom)
                    .addComponent(jTextFieldConducteurPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelAjoutConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConducteurMdp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldConducteurMdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonAjouterConducteur)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelConducteurLayout = new javax.swing.GroupLayout(jPanelConducteur);
        jPanelConducteur.setLayout(jPanelConducteurLayout);
        jPanelConducteurLayout.setHorizontalGroup(
            jPanelConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConducteurLayout.createSequentialGroup()
                .addGroup(jPanelConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConducteurLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelTableConducteur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelConducteurLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelAjoutConducteur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelConducteurLayout.setVerticalGroup(
            jPanelConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConducteurLayout.createSequentialGroup()
                .addComponent(jLabelTableConducteur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConducteurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanelAjoutConducteur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Gestion conducteur", jPanelConducteur);

        jButtonNettoyerReservations.setText("Nettoyer Reservations");
        jButtonNettoyerReservations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNettoyerReservationsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelResaLayout = new javax.swing.GroupLayout(jPanelResa);
        jPanelResa.setLayout(jPanelResaLayout);
        jPanelResaLayout.setHorizontalGroup(
            jPanelResaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelResaLayout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(jButtonNettoyerReservations)
                .addGap(209, 209, 209))
        );
        jPanelResaLayout.setVerticalGroup(
            jPanelResaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResaLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jButtonNettoyerReservations)
                .addContainerGap(171, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Gestion Reservation", jPanelResa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAjouterStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterStationActionPerformed
        String nom = this.jTextFieldNomStation.getText();
        if (nom.matches(".*\\d+.*") || nom == "") {
            JOptionPane.showMessageDialog(this, "Le nom de la station ne doit pas etre vide ou contenir de caractères spéciaux.");
            this.jTextFieldNomStation.setText("");
        }
        float x = 0;
        float y = 0;
        try {
            x = Float.parseFloat(this.jTextFieldCoordonneesX.getText());
            y = Float.parseFloat(this.jTextFieldCoordonneesY.getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Les coordonnées doivent être des entiers.");
            this.jTextFieldCoordonneesX.setText("");
            this.jTextFieldCoordonneesY.setText("");
            return;
        }
        int nbNav = (int) this.jSpinnerNbNavette.getValue();
        ArrayList<JPanel> panels = new ArrayList<>();
        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<JComboBox> combos = new ArrayList<>();
        String [] nbPlaces = new String [] {"2", "5", "10", "15"};
        for (int i=0; i<nbNav; i++) {
            panels.add(new JPanel());
            labels.add(new JLabel("Navette n°"+(i+1)+" :"));
            combos.add(new JComboBox<>(nbPlaces));
        }
        RMIAdminServiceManager rmiMgr;
        try {
            rmiMgr = new RMIAdminServiceManager();
            new SaisieNavette(rmiMgr.getAdminRemoteSvc(),this, nom, x, y,  panels, labels, combos).setVisible(true);
        } catch (NamingException ex) {
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
        this.Etat = Etat.Etat_SaisieNavette;
        transitionEtat_SaisieNavette();
    }//GEN-LAST:event_jButtonAjouterStationActionPerformed

    private void jButtonAjouterTrajetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterTrajetActionPerformed
        String depart = (String) jComboBoxStationDepart.getSelectedItem();
        String arrivee = (String) jComboBoxStationArrivee.getSelectedItem();
        if (depart.equals(arrivee)) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir 2 stations différentes.");
        }
        int duree = (int) this.jSpinnerDureeTrajet.getValue();
        services.creerTrajet(depart, arrivee, duree);
        resetOngletTrajet();
        this.Etat = Etat.Etat_initial;
        transitionEtat_initial();
    }//GEN-LAST:event_jButtonAjouterTrajetActionPerformed

    private void jButtonAjouterMecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterMecaActionPerformed
        String nom = jTextFieldMecaNom.getText();
        String prenom = jTextFieldMecaPrenom.getText();
        String mdp = jTextFieldMecaMdp.getText();
        services.ajouterMeca(nom, prenom, mdp);
        resetOngletMeca();
        this.Etat = Etat.Etat_initial;
        transitionEtat_initial();
    }//GEN-LAST:event_jButtonAjouterMecaActionPerformed

    private void jTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPaneMouseClicked
        resetOngletTrajet();
    }//GEN-LAST:event_jTabbedPaneMouseClicked

    private void jButtonAjouterConducteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterConducteurActionPerformed
        String nom = jTextFieldConducteurNom.getText();
        String prenom = jTextFieldConducteurPrenom.getText();
        String mdp = jTextFieldConducteurMdp.getText();
        services.ajouterConduc(nom, prenom, mdp);
        resetOngletConducteur();
        this.Etat = Etat.Etat_initial;
        transitionEtat_initial();
    }//GEN-LAST:event_jButtonAjouterConducteurActionPerformed

    private void jButtonNettoyerReservationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNettoyerReservationsActionPerformed
        services.nettoyerResa();
        JOptionPane.showMessageDialog(this, "Réservations nettoyées avec succès.");
    }//GEN-LAST:event_jButtonNettoyerReservationsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    RMIAdminServiceManager rmiMgr = new RMIAdminServiceManager();
                    new InterfaceAdmin(rmiMgr.getAdminRemoteSvc()).setVisible(true);
                }catch(NamingException ex){
                    System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
                    System.err.println(ex.getExplanation());
                }              
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouterConducteur;
    private javax.swing.JButton jButtonAjouterMeca;
    private javax.swing.JButton jButtonAjouterStation;
    private javax.swing.JButton jButtonAjouterTrajet;
    private javax.swing.JButton jButtonNettoyerReservations;
    private javax.swing.JComboBox<String> jComboBoxStationArrivee;
    private javax.swing.JComboBox<String> jComboBoxStationDepart;
    private javax.swing.JLabel jLabelConducteurMdp;
    private javax.swing.JLabel jLabelConducteurNom;
    private javax.swing.JLabel jLabelConducteurPrenom;
    private javax.swing.JLabel jLabelCoordonneesStation;
    private javax.swing.JLabel jLabelCoordonneesStation2;
    private javax.swing.JLabel jLabelDureeTrajet;
    private javax.swing.JLabel jLabelMecaMdp;
    private javax.swing.JLabel jLabelMecaNom;
    private javax.swing.JLabel jLabelMecaPrenom;
    private javax.swing.JLabel jLabelNbNavettes;
    private javax.swing.JLabel jLabelNomStation;
    private javax.swing.JLabel jLabelStationArrivee;
    private javax.swing.JLabel jLabelStationDepart;
    private javax.swing.JLabel jLabelTableConducteur;
    private javax.swing.JLabel jLabelTableMeca;
    private javax.swing.JLabel jLabelTableStation;
    private javax.swing.JLabel jLabelTableTrajet;
    private javax.swing.JPanel jPanelAjoutConducteur;
    private javax.swing.JPanel jPanelAjoutMeca;
    private javax.swing.JPanel jPanelAjoutStation;
    private javax.swing.JPanel jPanelAjoutTrajet;
    private javax.swing.JPanel jPanelConducteur;
    private javax.swing.JPanel jPanelMeca;
    private javax.swing.JPanel jPanelResa;
    private javax.swing.JPanel jPanelStation;
    private javax.swing.JPanel jPanelTrajet;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinnerDureeTrajet;
    private javax.swing.JSpinner jSpinnerNbNavette;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableConducteur;
    private javax.swing.JTable jTableMeca;
    private javax.swing.JTable jTableStation;
    private javax.swing.JTable jTableTrajet;
    private javax.swing.JTextField jTextFieldConducteurMdp;
    private javax.swing.JTextField jTextFieldConducteurNom;
    private javax.swing.JTextField jTextFieldConducteurPrenom;
    private javax.swing.JTextField jTextFieldCoordonneesX;
    private javax.swing.JTextField jTextFieldCoordonneesY;
    private javax.swing.JTextField jTextFieldMecaMdp;
    private javax.swing.JTextField jTextFieldMecaNom;
    private javax.swing.JTextField jTextFieldMecaPrenom;
    private javax.swing.JTextField jTextFieldNomStation;
    // End of variables declaration//GEN-END:variables
}
