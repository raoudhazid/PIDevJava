/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.time.LocalDate;
import java.util.Date;
/**
 *
 * @author RYM
 */
public class Event {
     private int id;
    private String type;
    private String titre;
    private String description;
 
    private int users; 
    private LocalDate date_event;

    @Override
    public String toString() {
        return "Event{" + "type=" + type + ", titre=" + titre + ", description=" + description + ", date_event=" + date_event + '}';
    }

    

    public Event() {
    }

  
    

    public LocalDate getDate_event() {
        return date_event;
    }

    public void setDate_event(LocalDate date_event) {
        this.date_event = date_event;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public void setDate_event(int i, java.sql.Date date) {
     
    }
    
  public Event(String type, String titre, String description, LocalDate date_event) {
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.date_event = date_event;
    }

    
}
