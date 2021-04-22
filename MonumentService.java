/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entities.Event;
import Entities.Monument;
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
import javax.mail.MessagingException;
import static javax.swing.UIManager.getString;
import utils.ConnexionBD;
import utils.EmailAttachmentSender;

/**
 *
 * @author Lenovo
 */
public class MonumentService {
     Connection cnx= ConnexionBD.getInstance().getCnx();
      public void ajouterFeed(Monument f){
           try {
            String req = "INSERT INTO monuments (name,destination,adresse,description,prixentrer,img) VALUES ('" +f.getName()+ "', '" + f.getDestination()+ "', '" + f.getAdresse()+ "', '"  + f.getDescription()+ "','"  + f.getPrixentrer()+ "', '"  + f.getImg() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Successful insertion!");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ShopService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
      
      public List<Monument> RechercherFeed(int id) throws SQLException {

        List<Monument> listrecherche = new ArrayList<>();
        try {
            String req = "SELECT * FROM monuments WHERE id='" +id + "'";
            Statement stt = cnx.createStatement();
            ResultSet rs = stt.executeQuery(req);
            while (rs.next()) {
               Monument p = new Monument();
               p.setId(rs.getInt(1));
               p.setName(rs.getString(2));
               p.setAdresse(rs.getString(4));
               p.setDestination(rs.getString(3));
               p.setDescription(rs.getString(5));
               p.setPrixentrer(rs.getInt(6));
                p.setImg(rs.getString(7));
                 p.setUsers_id(rs.getInt(8));
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
            String query = "DELETE FROM monuments WHERE id = ?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShopService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public List<Monument> afficherFeedback() throws SQLException {
           List<Monument> listEvent = new ArrayList<>();

        String requete = "select * from monuments "; 
                
        try {
            Statement statement = cnx.createStatement();
            ResultSet r = statement.executeQuery(requete);
        
            while (r.next()) {
               Monument f = new Monument();
               f.setId(r.getInt(1));
               f.setName(r.getString(2));
               f.setAdresse(r.getString(4));
                f.setDestination(r.getString(3));
               f.setDescription(r.getString(5));
               f.setPrixentrer(r.getInt(6));
             f.setImg(r.getString(7));
             f.setUsers_id(r.getInt(8));
             
              listEvent.add(f);
               
            }} catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            
        }
        return listEvent;
       
       
    }
       
       public void modifierFeed1(int id,String nom,String destination,String adresse,String description,int prixentrer/*,String img*/) throws MessagingException {

        try {

            String query = "UPDATE monuments SET  name= '" + nom+ "',destination= '" + destination+ "',adresse= '" + adresse+ "',description= '" + description+ "',prixentrer= '" + prixentrer/*+ "'img= '" + img*/+ "' WHERE id='" + id + "'";
            Statement ps = cnx.createStatement();
            ps.executeUpdate(query);
            System.out.println("Monument has been modified");
            EmailAttachmentSender.sendEmailWithAttachments("rayen.tajouri@esprit.tn", "UPDATE du table Monuments", "<h1> Cher utilisateur,</h1></br> <p>Nous vous informe qu'une mise a jour a été mise sur votre base de donnees du table Monuments.</p> </br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de WinIT</h3>");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
       
       public void supprimerparID(int id) {

        try {
            String query = "DELETE FROM monuments WHERE id= '" + id + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("monum with id = " + id + " was deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
      
      
      
      
      
      
      
    
}
