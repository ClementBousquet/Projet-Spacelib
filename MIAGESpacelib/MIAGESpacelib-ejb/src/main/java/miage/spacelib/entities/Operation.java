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
import javax.persistence.OneToOne;

/**
 *
 * @author Quentin
 */
@Entity
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Long idNavette;
    
    private Quai quaiDep;
    private Quai quaiArr;
    private Date dateDep;
    private Date dateArr;
    private int nbPassager;
    private Date dateOperation;
    
    protected Operation() {
    }
    
    public Operation(Long idNavette, Quai quaidep, Quai quaiarr, Date datedep, Date datearr, int nb, Date dateope) {
        this.idNavette = idNavette;
        this.quaiDep = quaidep;
        this.quaiArr = quaiarr;
        this.dateDep = datedep;
        this.dateArr = datearr;
        this.nbPassager = nb;
        this.dateOperation = dateope;
    }

    public Long getIdNavette() {
        return idNavette;
    }

    public Quai getQuaiDep() {
        return quaiDep;
    }

    public Quai getQuaiArr() {
        return quaiArr;
    }

    public Date getDateDep() {
        return dateDep;
    }

    public Date getDateArr() {
        return dateArr;
    }

    public int getNbPassager() {
        return nbPassager;
    }

    public Date getDateOperation() {
        return dateOperation;
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
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.spacelib.entities.HistoNavette[ id=" + id + " ]";
    }
    
}
