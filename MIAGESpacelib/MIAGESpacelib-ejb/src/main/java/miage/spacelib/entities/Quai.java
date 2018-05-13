/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Quentin
 */
@Entity
public class Quai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn
    private Station idStation;

    @OneToOne
    private Navette idNavette;
    
    
    /* Dispo, NonDispo, Reserve */
    @Column(nullable = false)
    private String statut;
    
    protected Quai() {       
    };
    
    public Quai(Station station) {
        this.idStation = station;
        this.statut = "Dispo";
    }
    
    public Quai(Station station, Navette navette) {
        this.idStation = station;
        this.idNavette = navette;
        this.statut = "NonDispo";
    }


    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getIdStation() {
        return idStation;
    }

    public void setIdStation(Station idStation) {
        this.idStation = idStation;
    }

    public Navette getIdNavette() {
        return idNavette;
    }

    public void setIdNavette(Navette idNavette) {
        this.idNavette = idNavette;
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
        if (!(object instanceof Quai)) {
            return false;
        }
        Quai other = (Quai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.spacelib.entities.Quai[ id=" + id + " ]";
    }
    
}
