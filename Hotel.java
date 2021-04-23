/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author RYM
 */
public class Hotel {
    private int id;
    private String nom_h;
    private String adresse_h;
    private int etoiles_h;
    private String numtlf_h;
    private String prix_h;
    private int users_id;

    public Hotel() {
    }

    public Hotel(String nom_h, String adresse_h, int etoiles_h, String numtlf_h, String prix_h) {
        this.nom_h = nom_h;
        this.adresse_h = adresse_h;
        this.etoiles_h = etoiles_h;
        this.numtlf_h = numtlf_h;
        this.prix_h = prix_h;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.nom_h);
        hash = 73 * hash + Objects.hashCode(this.adresse_h);
        hash = 73 * hash + this.etoiles_h;
        hash = 73 * hash + Objects.hashCode(this.numtlf_h);
        hash = 73 * hash + Objects.hashCode(this.prix_h);
        hash = 73 * hash + this.users_id;
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
        final Hotel other = (Hotel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.etoiles_h != other.etoiles_h) {
            return false;
        }
        if (this.users_id != other.users_id) {
            return false;
        }
        if (!Objects.equals(this.nom_h, other.nom_h)) {
            return false;
        }
        if (!Objects.equals(this.adresse_h, other.adresse_h)) {
            return false;
        }
        if (!Objects.equals(this.numtlf_h, other.numtlf_h)) {
            return false;
        }
        if (!Objects.equals(this.prix_h, other.prix_h)) {
            return false;
        }
        return true;
    }

    public Hotel(int id, String nom_h, String adresse_h, int etoiles_h, String numtlf_h, String prix_h) {
        this.id = id;
        this.nom_h = nom_h;
        this.adresse_h = adresse_h;
        this.etoiles_h = etoiles_h;
        this.numtlf_h = numtlf_h;
        this.prix_h = prix_h;
    }

    public Hotel(int id, String nom_h, String adresse_h, int etoiles_h, String numtlf_h, String prix_h, int users_id) {
        this.id = id;
        this.nom_h = nom_h;
        this.adresse_h = adresse_h;
        this.etoiles_h = etoiles_h;
        this.numtlf_h = numtlf_h;
        this.prix_h = prix_h;
        this.users_id = users_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_h() {
        return nom_h;
    }

    public void setNom_h(String nom_h) {
        this.nom_h = nom_h;
    }

    public String getAdresse_h() {
        return adresse_h;
    }

    public void setAdresse_h(String adresse_h) {
        this.adresse_h = adresse_h;
    }

    public int getEtoiles_h() {
        return etoiles_h;
    }

    public void setEtoiles_h(int etoiles_h) {
        this.etoiles_h = etoiles_h;
    }

    public String getNumtlf_h() {
        return numtlf_h;
    }

    public void setNumtlf_h(String numtlf_h) {
        this.numtlf_h = numtlf_h;
    }

    public String getPrix_h() {
        return prix_h;
    }

    public void setPrix_h(String prix_h) {
        this.prix_h = prix_h;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
    
    
    
}
