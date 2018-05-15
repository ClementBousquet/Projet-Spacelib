/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.miagespacelibshared;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Quentin
 */
public class VoyageVoyage implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long idVoyage;
    private Date dateDepart;
    private Date dateArrive;
    private int nbPassager;
    
    public VoyageVoyage(){
        
    }
    
    public VoyageVoyage(Long idVoyage, Date dateDepart, Date dateArrive, int nbPassager) {
        this.idVoyage = idVoyage;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
        this.nbPassager = nbPassager;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }

    public int getNbPassager() {
        return nbPassager;
    }

    public void setNbPassager(int nbPassager) {
        this.nbPassager = nbPassager;
    }

    public Long getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Long idVoyage) {
        this.idVoyage = idVoyage;
    }
    
    @Override
    public String toString() {
        return "Le voyage dateDepart=" + dateDepart + ", dateArrive=" + dateArrive + ", nbPassager=" + nbPassager;
    }
}
