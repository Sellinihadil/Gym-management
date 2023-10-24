/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hadil
 */
public class MyConnection {
    
     public static Connection connectDb(){
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "");
            return connect;
        }catch(Exception e){ 
            System.err.println(e.getMessage());}
        return null;
    }

   
}
    
    
    
    

