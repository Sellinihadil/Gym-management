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
public class cour {
    private int idcour;
    private String nomcour;
    private String descriptioncour;
    private String dureecour;
    private String status;
    private String salle;
    private Date datee;
    private String nomcoach;

    public cour(int idcour, String nomcour, String dureecour, String status, String salle, Date datee) {
        this.idcour = idcour;
        this.nomcour = nomcour;
        this.dureecour = dureecour;
        this.status = status;
        this.salle = salle;
        
        this.datee = datee;
    }

    public cour(int idcour, String nomcour, String descriptioncour, String dureecour, String status, String salle, Date datee, String nomcoach) {
        this.idcour = idcour;
        this.nomcour = nomcour;
        this.descriptioncour = descriptioncour;
        this.dureecour = dureecour;
        this.status = status;
        this.salle = salle;
        this.datee = datee;
        this.nomcoach = nomcoach;
    }

    public Date getDatee() {
        return datee;
    }

    public void setDatee(Date datee) {
        this.datee = datee;
    }

    public String getNomcoach() {
        return nomcoach;
    }

    public void setNomcoach(String nomcoach) {
        this.nomcoach = nomcoach;
    }

    

   


    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

   

   
   

    public cour(String nomcour, String descriptioncour, String dureecour, String status, String salle, String duree, Date datee) {
        this.nomcour = nomcour;
        this.descriptioncour = descriptioncour;
        this.dureecour = dureecour;
        this.status = status;
        this.salle = salle;
        
        this.datee = datee;
    }

    public cour(){
    }
    

    



  

    @Override
    public String toString() {
        return "cour" + "id=" + idcour + ", nom=" + nomcour + ", description=" + descriptioncour + ", duree=" + dureecour + '}'+",salle"+salle+",date"+datee;
    }

    public int getIdcour() {
        return idcour;
    }

    public void setIdcour(int idcour) {
        this.idcour = idcour;
    }

    public String getNomcour() {
        return nomcour;
    }

    public cour(int idcour, String nomcour, String descriptioncour, String dureecour) {
        this.idcour = idcour;
        this.nomcour = nomcour;
        this.descriptioncour = descriptioncour;
        this.dureecour = dureecour;
    }

    public void setNomcour(String nomcour) {
        this.nomcour = nomcour;
    }

    public String getDescriptioncour() {
        return descriptioncour;
    }

    public void setDescriptioncour(String descriptioncour) {
        this.descriptioncour = descriptioncour;
    }

    public String getDureecour() {
        return dureecour;
    }

    public void setDureecour(String dureecour) {
        this.dureecour = dureecour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    
    
    
}
