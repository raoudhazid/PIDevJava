/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import Entities.Event;
import Entities.Users;
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
 * @author mon
 */
public class ParticipationService {
     PreparedStatement ps;
     Connection cnx= ConnexionBD.getInstance().getCnx();
    public void ajouterP(int u,int e) throws MessagingException{
         try {
            String req = "INSERT INTO participation (users_id,event_id) VALUES ('" +u+ "','" +e+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Successful insertion!");
              EmailAttachmentSender.sendEmailWithAttachments("rim.mabrouki@esprit.tn", "Confirmation du Compte WinIT", "<h1> Cher utilisateur,</h1></br> <p>Nous avons l'honneur de vous accueiller parmi notre communauté. Vous devez confirmer votre compte en copiant le code ci-dessous lors de votre prochaine authentification.</p> </br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de WinIT</h3>");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
           
    
    }
    public List<Event> RechercherFeed(int id) throws SQLException {

        List<Event> listrecherche = new ArrayList<>();
        try {
            String req = "SELECT * FROM event WHERE id='" +id + "'";
            Statement stt = cnx.createStatement();
            ResultSet rs = stt.executeQuery(req);
            while (rs.next()) {
                Event p = new Event();
               p.setId(rs.getInt(1));
               p.setUsers(rs.getInt(2));
               p.setType(rs.getString(3));
               p.setTitre(rs.getString(4));
               p.setDescription(rs.getString(5));
                listrecherche.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }


    public int modifierFeedback(Event f) {
        int executeUpdate = 0;
      Connection cnx= ConnexionBD.getInstance().getCnx();
        try {
            PreparedStatement ps;
            String query = "UPDATE event SET type=?,titre=?,description=?,date_event=? WHERE id=?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1,f.getType());
                  ps.setString(2,f.getTitre());
               ps.setString(3,f.getDescription());
              
               
               
               String x = String.valueOf(f.getDate_event());
               ps.setDate(4,java.sql.Date.valueOf(x));
               ps.setInt(5,f.getId());

            executeUpdate = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeUpdate;
    }

    
    public void supprimerFeedback(int id) {
      Connection cnx= ConnexionBD.getInstance().getCnx();;
        try {
            PreparedStatement ps;
            String query = "DELETE FROM event WHERE id = ?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
       public List<Event> afficherFeedback() throws SQLException {
           List<Event> listEvent = new ArrayList<>();

        String requete = "select * from event "; 
                
        try {
            Statement statement = cnx.createStatement();
            ResultSet r = statement.executeQuery(requete);
        
            while (r.next()) {
                 Event f = new Event();
               f.setId(r.getInt(1));
               f.setUsers(r.getInt(2));
               f.setType(r.getString(3));
               f.setTitre(r.getString(4));
               f.setDescription(r.getString(5));
             ;
                          
               f.setDate_event((r.getDate(6).toLocalDate()));
              listEvent.add(f);
               
            }
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            
        }
        return listEvent;
       
       
    }
      
       public void modifierFeed1(int id,String type,String titre,String description,LocalDate date) {

        try {

            String query = "UPDATE event SET  type= '" + type+ "',titre= '" + titre+ "',description= '" + description+ "',date_event= '" + date+ "' WHERE id='" + id + "'";
            Statement ps = cnx.createStatement();
            ps.executeUpdate(query);
            System.out.println("Event has been modified");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
  

    public void supprimerparID(int id) {

        try {
            String query = "DELETE FROM event WHERE id= '" + id + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("events with id = " + id + " was deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }


    
}
