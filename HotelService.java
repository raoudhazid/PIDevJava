/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import Entities.Event;
import Entities.Hotel;
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
public class HotelService {
     Connection cnx= ConnexionBD.getInstance().getCnx();
    public void ajouterFeed(Hotel f){
           try {
            String req = "INSERT INTO hotels (nom_h,adresse_h,etoiles_h,numtlf_h,prix_h) VALUES ('" +f.getNom_h()+ "', '" + f.getAdresse_h()+ "', '" + f.getEtoiles_h()+ "', '"  + f.getNumtlf_h()+ "', '"  + f.getPrix_h() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Successful insertion!");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public List<Hotel> RechercherFeed(int id) throws SQLException {

        List<Hotel> listrecherche = new ArrayList<>();
        try {
            String req = "SELECT * FROM hotels WHERE id='" +id + "'";
            Statement stt = cnx.createStatement();
            ResultSet rs = stt.executeQuery(req);
            while (rs.next()) {
               Hotel p = new Hotel();
               p.setId(rs.getInt(1));
               p.setNom_h(rs.getString(2));
               p.setAdresse_h(rs.getString(3));
               p.setEtoiles_h(rs.getInt(4));
               p.setNumtlf_h(rs.getString(5));
               p.setPrix_h(rs.getString(6));
                 p.setUsers_id(rs.getInt(7));
               listrecherche.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }


    public int modifierFeedback(Hotel f) {
        int executeUpdate = 0;
      Connection cnx= ConnexionBD.getInstance().getCnx();
        try {
            PreparedStatement ps;
            String query = "UPDATE hotels SET nom_h=?,adresse_h=?,etoiles_h=?,numtlf_h=?,prix_h=? WHERE id=?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1,f.getNom_h());
                  ps.setString(2,f.getAdresse_h());
               ps.setInt(3,f.getEtoiles_h());
                   ps.setString(4,f.getNumtlf_h());
                  ps.setString(5,f.getPrix_h());
        
               ps.setInt(6,f.getId());

            executeUpdate = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeUpdate;
    }

    
    public void supprimerFeedback(int id) {
      Connection cnx= ConnexionBD.getInstance().getCnx();;
        try {
            PreparedStatement ps;
            String query = "DELETE FROM hotels WHERE id = ?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
       public List<Hotel> afficherFeedback() throws SQLException {
           List<Hotel> listEvent = new ArrayList<>();

        String requete = "select * from hotels "; 
                
        try {
            Statement statement = cnx.createStatement();
            ResultSet r = statement.executeQuery(requete);
        
            while (r.next()) {
               Hotel f = new Hotel();
               f.setId(r.getInt(1));
               f.setNom_h(r.getString(2));
               f.setAdresse_h(r.getString(3));
               f.setEtoiles_h(r.getInt(4));
               f.setNumtlf_h(r.getString(5));
             f.setPrix_h(r.getString(6));
             f.setUsers_id(r.getInt(7));
             
              listEvent.add(f);
               
            }
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            
        }
        return listEvent;
       
       
    }
      
       public void modifierFeed1(int id,String nom,String adresse,int etoiles,String num_tlf,String prix) {

        try {

            String query = "UPDATE hotels SET  nom_h= '" + nom+ "',adresse_h= '" + adresse+ "',etoiles_h= '" + etoiles+ "',numtlf_h= '" + num_tlf+ "',prix_h= '" + prix+ "' WHERE id='" + id + "'";
            Statement ps = cnx.createStatement();
            ps.executeUpdate(query);
            System.out.println("hotel has been modified");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
  

    public void supprimerparID(int id) {

        try {
            String query = "DELETE FROM hotels WHERE id= '" + id + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("hotel with id = " + id + " was deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }


    
}
