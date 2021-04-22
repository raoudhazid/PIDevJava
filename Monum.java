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
public class Monum {
    private int id;
    private String name;
    private String adresse;
    private String destination;
    private String description;

    public Monum(String name, String adresse, String destination, String description, String img) {
        this.name = name;
        this.adresse = adresse;
        this.destination = destination;
        this.description = description;
        this.img = img;
    }

    public Monum(int id, String name, String adresse, String destination, String description, String img) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.destination = destination;
        this.description = description;
        this.img = img;
    }

    public Monum(int id, String name, String adresse, String destination, String description, String img, int users_id) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.destination = destination;
        this.description = description;
        this.img = img;
        this.users_id = users_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private String img;
    private int users_id;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.adresse);
        hash = 11 * hash + Objects.hashCode(this.destination);
        hash = 11 * hash + Objects.hashCode(this.img);
        hash = 11 * hash + this.users_id;
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
        final Monum other = (Monum) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.users_id != other.users_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Monum{" + "id=" + id + ", name=" + name + ", adresse=" + adresse + ", destination=" + destination + ", img=" + img + ", users_id=" + users_id + '}';
    }

    public Monum(String name, String adresse, String destination, String img) {
        this.name = name;
        this.adresse = adresse;
        this.destination = destination;
        this.img = img;
    }

    public Monum(int id, String name, String adresse, String destination, String img, int users_id) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.destination = destination;
        this.img = img;
        this.users_id = users_id;
    }

    public Monum() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
    
    
}
