/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import miage.spacelib.miagespacelibshared.StatutMeca;
import miage.spacelib.miagespacelibshared.StatutUsager;

/**
 *
 * @author Quentin
 */
@Entity
public class Usager implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    @Column(nullable = false)
    private String mdp;
    
    /* Mecanicien, Admin, Usager */
    private String statutUsager;
    
    /* Libre, Occupe */
    private String statutMeca;
    
    @JoinColumn
    private List<Voyage> resa;
    
    protected Usager() {       
    }
    
    public Usager(String nom, String prenom, String motpass) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = motpass;
        this.resa = new ArrayList();
        this.statutUsager = "Usager";
    }
    
    public Usager(String nom, String prenom, String motpass, String statutUsager) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = motpass;
        this.statutUsager = statutUsager;
        this.resa = new ArrayList();
        
        if("Mecanicien".equals(statutUsager)) {
            this.statutMeca = "Libre";
        }
        
    }

    public String getStatutMeca() {
        return statutMeca;
    }

    public void setStatutMeca(String statutMeca) {
        this.statutMeca = statutMeca;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getStatutUsager() {
        return statutUsager;
    }

    public void setStatutUsager(String st) {
        this.statutUsager = st;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Voyage> getResa() {
        return resa;
    }

    public void setResa(List<Voyage> resa) {
        this.resa = resa;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usager)) {
            return false;
        }
        Usager other = (Usager) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.spacelib.entities.Usager[ id=" + id + " ]";
    }
    
}
