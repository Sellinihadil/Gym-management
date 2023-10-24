/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.cour;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hadil
 */
public class courCRUD {
    Connection connect;
   public courCRUD(){
   connect = MyConnection.connectDb();
   }
    
    public void ajouterCour(){
        try {
            String requete = "INSERT INTO cour (nom,description,duree) "
                    + "VALUES ('cour','bbbdhdg','heure')";
            Statement st= connect.createStatement();
            st.executeUpdate(requete);
            System.out.println("cour ajouté avec succes");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
                
        }
}
    public void ajouterCour2(cour c){
        try {
            String requete2 = "INSERT INTO cour (nom,description,duree) VALUES (?,?,?)";
            PreparedStatement ccd = connect.prepareStatement(requete2);
                   ccd.setString(1,c.getNomcour());
                   ccd.setString(2,c.getDescriptioncour());
                   ccd.setString(3,c.getDureecour());

                   ccd.executeUpdate();
                   System.out.println("votre cour est ajouteé");
       
                    
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
        }
    }
    public List<cour> afficherCour(){
        List<cour> myList = new ArrayList<>() ;

        try {
            String requete3 = "SELECT * FROM cour";
            Statement st = connect.createStatement();
            ResultSet rs= st.executeQuery(requete3);
            while(rs.next()){
            cour c = new cour ();
            c.setIdcour(rs.getInt(1));
            c.setNomcour(rs.getString("nom"));
            c.setDescriptioncour(rs.getString("Description"));
            c.setDureecour(rs.getString("Duree"));

            myList.add(c);

            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    
    
    
    
}
