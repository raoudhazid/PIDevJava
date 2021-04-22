/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import Entities.Event;
import Entities.Hotel;
import Entities.Monum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.UIManager.getString;
import utils.ConnexionBD;

/**
 *
 * @author mon
 */
public class ShopService {
     Connection cnx= ConnexionBD.getInstance().getCnx();
    public void ajouterFeed(Monum f){
           try {
            String req = "INSERT INTO shoppingmall (name,adresse,destination,description,img) VALUES ('" +f.getName()+ "', '" + f.getAdresse()+ "', '" + f.getDestination()+ "', '"  + f.getDescription()+ "', '"  + f.getImg() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Successful insertion!");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ShopService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public List<Monum> RechercherFeed(int id) throws SQLException {

        List<Monum> listrecherche = new ArrayList<>();
        try {
            String req = "SELECT * FROM shoppingmall WHERE id='" +id + "'";
            Statement stt = cnx.createStatement();
            ResultSet rs = stt.executeQuery(req);
            while (rs.next()) {
               Monum p = new Monum();
               p.setId(rs.getInt(1));
               p.setName(rs.getString(2));
               p.setAdresse(rs.getString(3));
               p.setDestination(rs.getString(4));
               p.setDescription(rs.getString(5));
                p.setImg(rs.getString(6));
                 p.setUsers_id(rs.getInt(7));
               listrecherche.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }


    
    
    public void supprimerFeedback(int id) {
      Connection cnx= ConnexionBD.getInstance().getCnx();;
        try {
            PreparedStatement ps;
            String query = "DELETE FROM shoppingmall WHERE id = ?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShopService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
       public List<Monum> afficherFeedback() throws SQLException {
           List<Monum> listEvent = new ArrayList<>();

        String requete = "select * from shoppingmall "; 
                
        try {
            Statement statement = cnx.createStatement();
            ResultSet r = statement.executeQuery(requete);
        
            while (r.next()) {
               Monum f = new Monum();
               f.setId(r.getInt(1));
               f.setName(r.getString(2));
               f.setAdresse(r.getString(3));
                f.setDestination(r.getString(4));
               f.setDescription(r.getString(5));
             f.setImg(r.getString(6));
             f.setUsers_id(r.getInt(7));
             
              listEvent.add(f);
               
            }
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            
        }
        return listEvent;
       
       
    }
      
       public void modifierFeed1(int id,String nom,String adresse,String destination,String description,String img) {

        try {

            String query = "UPDATE shoppingmall SET  name= '" + nom+ "',adresse= '" + adresse+ "',destination= '" + destination+ "',description= '" + description+ "',img= '" + img+ "' WHERE id='" + id + "'";
            Statement ps = cnx.createStatement();
            ps.executeUpdate(query);
            System.out.println("shoppingMall has been modified");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
  

    public void supprimerparID(int id) {

        try {
            String query = "DELETE FROM shoppingmall WHERE id= '" + id + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("shop with id = " + id + " was deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }


    
}
