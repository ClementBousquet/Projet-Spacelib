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

/**
 *
 * @author Quentin
 */
@Entity
public class Trajet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Station stationDep;
    
    private Station stationArr;
    
    //En nombres de jours
    @Column(nullable = false)
    private int dureeVoyage;
    
    protected Trajet() {
    }
    
    public Trajet(Station stDep, Station stArr, int duree) {
        this.stationDep = stDep;
        this.stationArr = stArr;
        this.dureeVoyage = duree;
    }

    public Station getStationDep() {
        return stationDep;
    }

    public void setStationDep(Station stationDep) {
        this.stationDep = stationDep;
    }

    public Station getStationArr() {
        return stationArr;
    }

    public void setStationArr(Station stationArr) {
        this.stationArr = stationArr;
    }

    public int getDureeVoyage() {
        return dureeVoyage;
    }

    public void setDureeVoyage(int dureeVoyage) {
        this.dureeVoyage = dureeVoyage;
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
        if (!(object instanceof Trajet)) {
            return false;
        }
        Trajet other = (Trajet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.spacelib.entities.Trajet[ id=" + id + " ]";
    }
    
}
