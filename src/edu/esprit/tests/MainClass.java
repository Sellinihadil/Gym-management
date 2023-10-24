/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.cour;
import edu.esprit.services.courCRUD;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;

/**
 *
 * @author hadil
 */
public class MainClass {
    public static void main(String[] args){
        Connection connect = MyConnection.connectDb();

    }
}
