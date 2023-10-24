/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.Date;

/**
 *
 * @author hadil
 */
public class reservation {
    private int idresv;
    private String nomcour;
    private String coach;
    private String status;
    private String salle;
    private Date date;
    private String description;

    @Override
    public String toString() {
        return "reservation{" + "idresv=" + idresv + ", nomcour=" + nomcour + ", coach=" + coach + ", status=" + status + ", salle=" + salle + ", date=" + date + ", description=" + description + '}';
    }

    public reservation(String nomcour, String coach, String status, String salle, Date date, String description) {
        this.nomcour = nomcour;
        this.coach = coach;
        this.status = status;
        this.salle = salle;
        this.date = date;
        this.description = description;
    }

    public int getIdresv() {
        return idresv;
    }

    public void setIdresv(int idresv) {
        this.idresv = idresv;
    }

    public String getNomcour() {
        return nomcour;
    }

    public void setNomcour(String nomcour) {
        this.nomcour = nomcour;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}
