/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import Entities.Restaurant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import java.time.LocalDate;
import java.util.Date;
import static javax.swing.UIManager.getString;

/**
 *
 * @author DELL
 */
public class RestaurantService {
    Connection cnx= ConnexionBD.getInstance().getCnx();
    
    public void ajouterFeed(Restaurant f){
           try {
            String req = "INSERT INTO Restaurants (nom_r,adresse_r,specialites) VALUES ('" +f.getNom_r()+ "', '" + f.getAdresse_r()+ "', '"  + f.getSpecialites() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Successful insertion!");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

public int modifierFeedback(Restaurant f) {
        int executeUpdate = 0;
      Connection cnx= ConnexionBD.getInstance().getCnx();
        try {
            PreparedStatement ps;
            String query = "UPDATE Restaurants SET nom_r=?,adresse_r=?,specialites=?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1,f.getNom_r());
                  ps.setString(2,f.getAdresse_r());
               
                   ps.setString(3,f.getSpecialites());
                  
        
               ps.setInt(4,f.getId());

            executeUpdate = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeUpdate;
    }

    public void supprimerFeedback(int id) {
      Connection cnx= ConnexionBD.getInstance().getCnx();;
        try {
            PreparedStatement ps;
            String query = "DELETE FROM restaurants WHERE id = ?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      public List<Restaurant> afficherFeedback() throws SQLException {
           List<Restaurant> listEvent = new ArrayList<>();

        String requete = "select * from restaurants "; 
                
        try {
            Statement statement = cnx.createStatement();
            ResultSet r = statement.executeQuery(requete);
        
            while (r.next()) 
            {
               Restaurant f = new Restaurant();
               f.setId(r.getInt(1));
               f.setNom_r(r.getString(3));
               f.setAdresse_r(r.getString(4));
               f.setSpecialites(r.getString(5));
               f.setUsers_id(r.getInt(2)); 
            
                    listEvent.add(f);
               
            }
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            
        }
        return listEvent;
       
       
    }

       public void modifierFeed1(int id,String nom,String adresse,String specialites) {

        try {

            String query = "UPDATE restaurants SET  nom_r= '" + nom+ "',adresse_r= '" + adresse+ "',specialites= '" + specialites+ "' WHERE id='" + id + "'";
            Statement ps = cnx.createStatement();
            ps.executeUpdate(query);
            System.out.println("restaurant has been modified");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   public void supprimerparID(int id) {

        try {
            String query = "DELETE FROM restaurants WHERE id= '" + id + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("restaurant with id = " + id + " was deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
       
 public List<Restaurant> RechercherFeed(int id) throws SQLException {

        List<Restaurant> listrecherche = new ArrayList<>();
        try {
            String req = "SELECT * FROM restaurants WHERE id='" +id + "'";
            Statement stt = cnx.createStatement();
            ResultSet rs = stt.executeQuery(req);
            while (rs.next()) {
              Restaurant p = new Restaurant();
               p.setId(rs.getInt(1));
               p.setNom_r(rs.getString(3));
               p.setAdresse_r(rs.getString(4));
               p.setSpecialites(rs.getString(5));
         
                 p.setUsers_id(rs.getInt(2));
               listrecherche.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }



}


    