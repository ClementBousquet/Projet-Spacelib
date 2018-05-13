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
public class ReservationUs implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long idReservation;
    private String quaidep;
    private String quaiarr;
    private Date date;

    public ReservationUs(Long idReservation, String quaidep, String quaiarr, Date date) {
        this.idReservation = idReservation;
        this.quaidep = quaidep;
        this.quaiarr = quaiarr;
        this.date = date;
    }

    public ReservationUs() {
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public String getQuaidep() {
        return quaidep;
    }

    public void setQuaidep(String quaidep) {
        this.quaidep = quaidep;
    }

    public String getQuaiarr() {
        return quaiarr;
    }

    public void setQuaiarr(String quaiarr) {
        this.quaiarr = quaiarr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
