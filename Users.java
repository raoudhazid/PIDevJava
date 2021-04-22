/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author RYM
 */
public class Users {
     private int id;
     private String nom;
      private String roles;
     private String prenom;
     private String email;
     private String adresse;
     private String password;

    public Users(int id, String nom, String roles, String prenom, String email, String adresse, String password) {
        this.id = id;
        this.nom = nom;
        this.roles = roles;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.password = password;
    }

    public Users(String nomm, String prenomm, String countryy, String mai, String crypted, String rolees, String urll) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Users(String nomm, String prenomm, String countryy, String mai, String rolees, String urll) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Users() {
      
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Users(int id, String nom, String prenom, String email, String adresse, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", nom=" + nom + ", roles=" + roles + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse + ", password=" + password + '}';
    }

    
     
}
