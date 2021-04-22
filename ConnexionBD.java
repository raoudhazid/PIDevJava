/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mon
 */
public class ConnexionBD {
     private static final String url="jdbc:mysql://localhost:3306/pidevv" ; //jdbc est un framework // 3306:port d'ecoute
    private static final String usr="root";
    private static final String pwd="";
    private static Connection cnx ;
    private static ConnexionBD mycon;
    
    public static Connection getCnx(){
        return cnx;
    }
    
    private ConnexionBD(){
        try{
            // 1. Get a connection to database
        cnx=DriverManager.getConnection(url,usr,pwd); // creer une instance
           System.out.println("Connexion etablie!") ; 
    }
    catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
    }
        public static ConnexionBD getInstance(){
            if(mycon==null)
                mycon=new ConnexionBD() ;
            return mycon ;
        }
}
