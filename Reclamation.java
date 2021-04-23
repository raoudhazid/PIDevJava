/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author RYM
 */
public class Reclamation {
     private int id;
     private String type;
    private String message;
    private LocalDate date;
    private int users;

    @Override
    public String toString() {
        return "Reclamation{" + "type=" + type + ", message=" + message + ", date=" + date + '}';
    }

    public Reclamation() {
    }

    public Reclamation(String type, String message, LocalDate date) {
        this.type = type;
        this.message = message;
        this.date = date;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }
    
}
