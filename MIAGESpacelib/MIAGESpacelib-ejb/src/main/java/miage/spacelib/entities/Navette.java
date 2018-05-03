/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import miage.spacelib.miagespacelibshared.StatutNavette;

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
    private Quai quai;
    
    private StatutNavette statut;
    
    private int nbPlaces;
    
    @OneToMany
    private Map<Voyage, OperationNavette> historique;
    @OneToMany
    private List<OperationRevisionNavette> historiqueRev;

    protected Navette() {
    }
    
    public Navette(int nbPlaces) {
        this.nbPlaces = nbPlaces;
        this.statut = StatutNavette.Disponible;
        this.historique = new HashMap();
        this.historiqueRev = new ArrayList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quai getQuai() {
        return quai;
    }

    public void setQuai(Quai quai) {
        this.quai = quai;
    }

    public StatutNavette getStatut() {
        return statut;
    }

    public void setStatut(StatutNavette statut) {
        this.statut = statut;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public Map<Voyage, OperationNavette> getHistorique() {
        return historique;
    }

    public void setHistorique(Map<Voyage, OperationNavette> historique) {
        this.historique = historique;
    }

    public List<OperationRevisionNavette> getHistoriqueRev() {
        return historiqueRev;
    }

    public void setHistoriqueRev(List<OperationRevisionNavette> historiqueRev) {
        this.historiqueRev = historiqueRev;
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
