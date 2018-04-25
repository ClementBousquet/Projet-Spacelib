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
import javax.persistence.OneToOne;

/**
 *
 * @author Quentin
 */
@Entity
public class Navette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Long quai;
    
    private int nbPlaces;
    
    @OneToMany
    private List<Operation> historique;

    protected Navette() {
    }
    
    public Navette(Long quai, int nbPlaces) {
        this.quai = quai;
        this.nbPlaces = nbPlaces;
        this.historique = new ArrayList();
    }
    
    public Long getQuai() {
        return quai;
    }

    public void setQuai(Long quai) {
        this.quai = quai;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public List<Operation> getHistorique() {
        return historique;
    }

    public void setHistorique(List<Operation> historique) {
        this.historique = historique;
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
        if (!(object instanceof Navette)) {
            return false;
        }
        Navette other = (Navette) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.spacelib.entities.Navette[ id=" + id + " ]";
    }
    
}
