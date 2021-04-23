/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;




import Entities.Reclamation;
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
public class RecService {
     Connection cnx= ConnexionBD.getInstance().getCnx();
    public void ajouterFeed(Reclamation f){
           try {
            String req = "INSERT INTO reclamation (type,message,date) VALUES ('" +f.getType()+ "', '" + f.getMessage()+ "', '"  + f.getDate() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Successful insertion!");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(RecService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public List<Reclamation> RechercherFeed(String type) throws SQLException {

        List<Reclamation> listrecherche = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE type='" +type+ "'";
            Statement stt = cnx.createStatement();
            ResultSet rs = stt.executeQuery(req);
            while (rs.next()) {
                Reclamation p = new Reclamation();
               p.setId(rs.getInt(1));
             
               p.setType(rs.getString(2));
               p.setMessage(rs.getString(3));
              p.setUsers(rs.getInt(4));
                listrecherche.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RecService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }


    public int modifierFeedback(Reclamation f) {
        int executeUpdate = 0;
      Connection cnx= ConnexionBD.getInstance().getCnx();
        try {
            PreparedStatement ps;
            String query = "UPDATE reclamation SET type=?,message=?,date=? WHERE id=?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1,f.getType());
                  ps.setString(2,f.getMessage());
            
              
               
               
               String x = String.valueOf(f.getDate());
               ps.setDate(3,java.sql.Date.valueOf(x));
               ps.setInt(5,f.getId());

            executeUpdate = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RecService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeUpdate;
    }

    
    public void supprimerFeedback(int id) {
      Connection cnx= ConnexionBD.getInstance().getCnx();;
        try {
            PreparedStatement ps;
            String query = "DELETE FROM reclamation WHERE id = ?";
            ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RecService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
       public List<Reclamation> afficherFeedback() throws SQLException {
           List<Reclamation> listReclamation = new ArrayList<>();

        String requete = "select * from reclamation "; 
                
        try {
            Statement statement = cnx.createStatement();
            ResultSet r = statement.executeQuery(requete);
        
            while (r.next()) {
                 Reclamation f = new Reclamation();
               f.setId(r.getInt(1));
              
               f.setType(r.getString(2));
               f.setMessage(r.getString(3));
              
             ;
                          
               f.setDate((r.getDate(4).toLocalDate()));
              
                f.setUsers(r.getInt(5));
                listReclamation.add(f);
            }
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            
        }
        return listReclamation;
       
       
    }
      
       public void modifierFeed1(int id,String type,String message,LocalDate date) {

        try {

            String query = "UPDATE reclamation SET  type= '" + type+ "',message= '" + message+ "',date= '" + date+ "' WHERE id='" + id + "'";
            Statement ps = cnx.createStatement();
            ps.executeUpdate(query);
            System.out.println("reclamation has been modified");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
  

    public void supprimerparID(int id) {

        try {
            String query = "DELETE FROM reclamation WHERE id= '" + id + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("reclamations with id = " + id + " was deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }


    
}
