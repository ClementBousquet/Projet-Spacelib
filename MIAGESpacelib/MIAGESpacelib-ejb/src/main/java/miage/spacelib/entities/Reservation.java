/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Quentin
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Long idUsager;
    
    private String intitule;
    
    private Long idVoyage;
    private int placeReserve;   
    private Date dateDep;
    private Date dateArr;
    
    protected Reservation() {
    }
    
    public Reservation(Long idV, Long idU, String intit, int nbPlaces, Date dateDep, Date dateArr) {
        this.idUsager = idU;
        this.idVoyage = idV;
        this.intitule = intit;
        this.placeReserve = nbPlaces;
        this.dateDep = dateDep;
        this.dateArr = dateArr;
    }

    public Long getIdUsager() {
        return idUsager;
    }

    public void setIdUsager(Long idUsager) {
        this.idUsager = idUsager;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Long getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Long idVoyage) {
        this.idVoyage = idVoyage;
    }

    public int getPlaceReserve() {
        return placeReserve;
    }

    public void setPlaceReserve(int placeReserve) {
        this.placeReserve = placeReserve;
    }

    public Date getDateDep() {
        return dateDep;
    }

    public void setDateDep(Date dateDep) {
        this.dateDep = dateDep;
    }

    public Date getDateArr() {
        return dateArr;
    }

    public void setDateArr(Date dateArr) {
        this.dateArr = dateArr;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.spacelib.entities.Reservation[ id=" + id + " ]";
    }
    
}
