/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Restaurant {

   private int id ; 
   private String nom_r;
   private String adresse_r;
   private String specialites ;
   private int users_id;

    public Restaurant() {
    }

    public Restaurant(String nom_r, String adresse_r, String specialites) {
        this.nom_r = nom_r;
        this.adresse_r = adresse_r;
        this.specialites = specialites;
    }

    
    public Restaurant(int id, String nom_r, String adresse_r, String specialites) {
        this.id = id;
        this.nom_r = nom_r;
        this.adresse_r = adresse_r;
        this.specialites = specialites;
    }

    public Restaurant(int id, String nom_r, String adresse_r, String specialites, int users_id) {
        this.id = id;
        this.nom_r = nom_r;
        this.adresse_r = adresse_r;
        this.specialites = specialites;
        this.users_id = users_id;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.nom_r);
        hash = 97 * hash + Objects.hashCode(this.adresse_r);
        hash = 97 * hash + Objects.hashCode(this.specialites);
        hash = 97 * hash + this.users_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restaurant other = (Restaurant) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.users_id != other.users_id) {
            return false;
        }
        if (!Objects.equals(this.nom_r, other.nom_r)) {
            return false;
        }
        if (!Objects.equals(this.adresse_r, other.adresse_r)) {
            return false;
        }
        if (!Objects.equals(this.specialites, other.specialites)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_r() {
        return nom_r;
    }

    public void setNom_r(String nom_r) {
        this.nom_r = nom_r;
    }

    public String getAdresse_r() {
        return adresse_r;
    }

    public void setAdresse_r(String adresse_r) {
        this.adresse_r = adresse_r;
    }

    public String getSpecialites() {
        return specialites;
    }

    public void setSpecialites(String specialites) {
        this.specialites = specialites;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
    
    
}
