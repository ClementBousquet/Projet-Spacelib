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
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dateDep;
    
    private Usager usager;
    private int NbPassager;
    private Quai quaiDep;
    private Quai quaiArr;
    private Navette navette;
    private Trajet trajet;
    
    /*EnCours, Cloturer*/
    private String statut;
    
    protected Reservation() {  
    }
    
    public Reservation (Usager us, Date date, int nbPass, Quai quaiDep, Quai quaiArr, Navette nav, Trajet t) {
        this.dateDep = date;
        this.NbPassager = nbPass;
        this.quaiDep = quaiDep;
        this.quaiArr = quaiArr;
        this.navette = nav;
        this.usager = us;
        this.trajet = t;
        this.statut = "EnCours";
    } 

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    
    
    public Date getDateDep() {
        return dateDep;
    }

    public void setDateDep(Date dateDep) {
        this.dateDep = dateDep;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public int getNbPassager() {
        return NbPassager;
    }

    public void setNbPassager(int NbPassager) {
        this.NbPassager = NbPassager;
    }

    public Quai getQuaiDep() {
        return quaiDep;
    }

    public void setQuaiDep(Quai quaiDep) {
        this.quaiDep = quaiDep;
    }

    public Quai getQuaiArr() {
        return quaiArr;
    }

    public void setQuaiArr(Quai quaiArr) {
        this.quaiArr = quaiArr;
    }

    public Navette getNavette() {
        return navette;
    }

    public void setNavette(Navette navette) {
        this.navette = navette;
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
        return "miage.spacelib.entities.R\u00e9servation[ id=" + id + " ]";
    }
    
}
