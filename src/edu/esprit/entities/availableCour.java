/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author hadil
 */
public class availableCour {
    private int id_availableCour;
    private String nomCour;
    private String description;
   

    public availableCour(int id_availableCour, String nomCour, String description) {
        this.id_availableCour = id_availableCour;
        this.nomCour = nomCour;
        this.description = description;
        
    }

    public int getId_availableCour() {
        return id_availableCour;
    }

    public void setId_availableCour(int id_availableCour) {
        this.id_availableCour = id_availableCour;
    }

    public String getNomCour() {
        return nomCour;
    }

    public availableCour(String nomCour, String description) {
        this.nomCour = nomCour;
        this.description = description;
    }

    public void setNomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    

   
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    @Override
    public String toString() {
        return "availableCour{" + "id_availableCour=" + id_availableCour + ", nomCour=" + nomCour + ", description=" + description + '}';
    }
    
}
