/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    @JoinColumn
    private Navette idNavette;
    
    private Station idStation;
    
    private Quai idQuai;
    
    @JoinColumn
    private Usager idMecanicien;
    
    @Column(nullable = false)
    private String intitule;
    
    @Column(nullable = false)
    private Date dateCreation;

    protected OperationRevisionNavette() {
    }
    
    public OperationRevisionNavette (Navette idN, Station idS, Quai idQ, Usager idM, String intit, Date date) {
        this.idNavette = idN;
        this.idStation = idS;
        this.idQuai = idQ;
        this.idMecanicien = idM;
        this.intitule = intit;
        this.dateCreation = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Navette getIdNavette() {
        return idNavette;
    }

    public void setIdNavette(Navette idNavette) {
        this.idNavette = idNavette;
    }

    public Station getIdStation() {
        return idStation;
    }

    public void setIdStation(Station idStation) {
        this.idStation = idStation;
    }

    public Quai getIdQuai() {
        return idQuai;
    }

    public void setIdQuai(Quai idQuai) {
        this.idQuai = idQuai;
    }

    public Usager getIdMecanicien() {
        return idMecanicien;
    }

    public void setIdMecanicien(Usager idMecanicien) {
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
