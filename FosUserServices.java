/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.ConnexionBD;
import Entities.FosUser;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import utils.EmailAttachmentSender;
import java.sql.Timestamp;
import utils.BCrypt;
import utils.SessionUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.MessagingException;

/**
 *
 * @author hatem
 */
public class FosUserServices implements IFosUserServices {

//    @PersistenceUnit(unitName = "RawdaPU")
//    static private EntityManagerFactory emf;
//    static EntityManager em;
//
//    static {
//        try {
//            emf = Persistence.createEntityManagerFactory("RawdaPU");
//            em = emf.createEntityManager();
//        } catch (Exception e) {
//            System.out.println("Fatal: Unable to create entity manager factory");
//            e.printStackTrace();
//        }
//    }
    Connection conn = ConnexionBD.getInstance().getCnx();
    Statement stmt;
    // public int logUser;

    @Override
    public boolean Authentification(FosUser u) {
        boolean status = false;
        try {
            String req = "select * from users where nom=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, u.getNom());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (BCrypt.checkpw(u.getPassword(), rs.getString("password")) == true) {
                    
                        
                    status = true;
                    u = this.findById(rs.getInt("id"));
                    SessionUser.setUser(u);
                    System.out.println(u.getId());

                    
                    
                    // logUser=rs.getInt(1);  

                } else {
                    status = false;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
    

    @Override
    public FosUser getUserbyId(int id) {
        FosUser u = null;
        try {
            String req = "select * from users where id=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new FosUser(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
                   

            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }

    public FosUser findById(Integer id) {
        FosUser u = null;
        try {
            String req = "select * from users where id=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
               u = new FosUser(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
               rs.getString(8));
                        

            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }

    @Override
    public void create(FosUser u) {

        try {

            String req = "INSERT INTO `users`(`email`, `roles` , `verified` , `password`, `activation_token`, `nom`, `prenom`, `adresse`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(req);
            String token = UUID.randomUUID().toString();
            String Role = u.getRoles();
            st.setString(6, u.getNom());
            st.setInt(3, 0);
            st.setString(1, u.getEmail());
            
          st.setString(7, u.getPrenom());
           st.setString(8, u.getAdresse());
        
            st.setString(4, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
          
            st.setString(5, token);

            st.setString(2, "a:1:{i:0;s:9:\"ROLE_USER\";}");
            

            EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(), "Confirmation du Compte WinIT", "<h1> Cher utilisateur,</h1></br> <p>Nous avons l'honneur de vous accueiller parmi notre communauté. Vous devez confirmer votre compte en copiant le code ci-dessous lors de votre prochaine authentification.</p>"+token+ "</br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de WinIT</h3>");

            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
/*
    @Override
    public void update(FosUser u) {
        try {
            String req = "UPDATE `users` SET `email`=?,`roles`=?,`password`=?,`activation_token`=?,`nom`=?,`prenom`=?,`addresse`=? WHERE `id` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getUsernameCanonical());
            st.setString(1, u.getEmail());
            st.setString(4, u.getEmailCanonical());
            st.setString(6, u.getSalt());
            st.setString(3, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setObject(8, u.getLastLogin());
            st.setObject(10, u.getPasswordRequestedAt());
            st.setString(2, "a:1:{i:0;s:9:\"ROLE_USER\";}");
           

            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
*/
        @Override
    public void ConfirmAccount(FosUser u) {
        String req2 = "update users set verified=? , activation_token=? where email=?  ";

        try {
            PreparedStatement st1 = conn.prepareStatement(req2);
            st1.setInt(1, 1);
            st1.setString(2, null);
            st1.setString(3, u.getEmail());
            EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(), "Compte Savers confirmé ", "<h1> Cher utilisateur,</h1></br> <p>Votre compte a été activé. Nous vous souhaitons une bonne expérience sur notre plateforme.</p></br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de Savers +</h3>");
            st1.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public void delete(FosUser u) {

        try {
            String req = "delete from users where id = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ObservableList<FosUser> findAll() {
        ObservableList<FosUser> users=FXCollections.observableArrayList();

        try {
            String req = "select * from users";
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FosUser u = new FosUser(rs.getInt("id"),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7) 
                        , rs.getString(8));
                        
               
                users.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;

    }

    public String CheckRole(FosUser u) {
        String role = null;
        if (u.getRoles().equals("a:1:{i:0;s:5:\"ADMIN\";}")) {
            role = "admin";
        } else {
            role = "user";
        }

        return role;
    }

    @Override
    public FosUser getUserByEmail(String email) {

        FosUser u = null;
        try {
            String req = "select * from users where email=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                 u = new FosUser(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                   rs.getString(8));
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;

    }

    public void SendMailAndAddTokenToUser(FosUser u) {
        try {
            String token = UUID.randomUUID().toString();
            String req = "update users SET activation_token=?  where email=?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, token);
            st.setString(2, u.getEmail());

            st.executeUpdate();
            EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(), "Code de récupération de mot de passe ", "<h1> Cher utilisateur,</h1></br> <p>Veuillez trouver ci-dessous le code pour la recupération de votre mot de passe.</p>"+token+ "</br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de winIT+</h3>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Boolean CheckIfUserExist(String email) {
        boolean check = false;
        try {
            String req = "select * from users where email=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }

    @Override
    public Boolean CheckToken(FosUser u, String token) {
        boolean check = false;
        try {
            String req = "select * from users where email=? and activation_token" + "=?  ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, u.getEmail());
            st.setString(2, token);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;

    }

    @Override
    public void ressettingpassword(FosUser u) {

        try {
            String req = "update users SET password=?, activation_token=?  where email=?";
            PreparedStatement st = conn.prepareStatement(req);
            String password = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
            st.setString(1, password);
            st.setString(2, null);
            st.setString(3, u.getEmail());
            st.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Boolean Checkconfirmationtoken(String email, String Token) {
        boolean exisit = false;
        try {
            String req = "select * from users where email=? and activation_token=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, email);
            st.setString(2, Token);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                exisit = true;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exisit;
    }

   

    public boolean checkEnabled(String username) {
        boolean exisit = false;
        try {
            String req = "select * from users where nom=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                if (rs.getInt(4) == 0) {
                    exisit = false;
                } else {
                    exisit = true;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exisit;
    }

   

    @Override
    public Boolean CheckIfUsernameExist(String username) {
        boolean check = false;
        try {
            String req = "select * from users where nom=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }

   

}
