/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import miage.spacelib.miagespacelibshared.Coordonnee;

/**
 *
 * @author Quentin
 */
@Entity
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Coordonnee coordStation;
    
    private String nom;
    
    @OneToMany
    private List<Quai> quais;
    
    protected Station() {
    }
    
    public Station(String nom, float coordX, float coordY) {
        this.nom = nom;
        this.coordStation = new Coordonnee(coordX, coordY);
        this.quais = new ArrayList();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coordonnee getCoordStation() {
        return coordStation;
    }

    public void setCoordStation(Coordonnee coordStation) {
        this.coordStation = coordStation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Quai> getQuais() {
        return quais;
    }

    public void setQuais(List<Quai> quais) {
        this.quais = quais;
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
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.spacelib.entities.Station[ id=" + id + " ]";
    }
    
}
