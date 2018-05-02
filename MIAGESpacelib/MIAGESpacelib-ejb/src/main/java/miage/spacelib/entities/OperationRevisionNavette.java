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
public class OperationRevisionNavette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idNavette;
    private Long idStation;
    private Long idQuai;
    private Long idMecanicien;
    
    private String intitule;
    private Date dateCreation;

    protected OperationRevisionNavette() {
    }
    
    public OperationRevisionNavette (Long idN, Long idS, Long idQ, Long idM, String intit, Date date) {
        this.idNavette = idN;
        this.idStation = idS;
        this.idQuai = idQ;
        this.idMecanicien = idM;
        this.intitule = intit;
        this.dateCreation = date;
    }
    
    public Long getIdNavette() {
        return idNavette;
    }

    public void setIdNavette(Long idNavette) {
        this.idNavette = idNavette;
    }

    public Long getIdStation() {
        return idStation;
    }

    public void setIdStation(Long idStation) {
        this.idStation = idStation;
    }

    public Long getIdQuai() {
        return idQuai;
    }

    public void setIdQuai(Long idQuai) {
        this.idQuai = idQuai;
    }

    public Long getIdMecanicien() {
        return idMecanicien;
    }

    public void setIdMecanicien(Long idMecanicien) {
        this.idMecanicien = idMecanicien;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
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
        if (!(object instanceof OperationRevisionNavette)) {
            return false;
        }
        OperationRevisionNavette other = (OperationRevisionNavette) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.spacelib.entities.OperationRevisionNavette[ id=" + id + " ]";
    }
    
}
