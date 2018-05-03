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

/**
 *
 * @author Quentin
 */
@Entity
public class Voyage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Trajet idTrajet;
    private Navette idNavette;
    private Usager idUsager;
    private Date dateDepart;
    private Date dateArrive;
    private int nbPassager;
    private Date dateCreation;
    private String intitule;
    
    protected Voyage() {
    }
    
    public Voyage(Trajet idT, Navette idN, Usager idU, Date date, int nbPass, Date datecrea, String intit) {
        this.idTrajet = idT;
        this.idNavette = idN;
        this.idUsager = idU;
        this.dateDepart = date;
        this.nbPassager = nbPass;
        this.dateCreation = datecrea;
        this.intitule = intit;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }

    public Trajet getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Trajet idTrajet) {
        this.idTrajet = idTrajet;
    }

    public Navette getIdNavette() {
        return idNavette;
    }

    public void setIdNavette(Navette idNavette) {
        this.idNavette = idNavette;
    }

    public Usager getIdUsager() {
        return idUsager;
    }

    public void setIdUsager(Usager idUsager) {
        this.idUsager = idUsager;
    }
    
    

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getNbPassager() {
        return nbPassager;
    }

    public void setNbPassager(int nbPassager) {
        this.nbPassager = nbPassager;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
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
        if (!(object instanceof Voyage)) {
            return false;
        }
        Voyage other = (Voyage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.spacelib.entities.Voyage[ id=" + id + " ]";
    }
    
}
