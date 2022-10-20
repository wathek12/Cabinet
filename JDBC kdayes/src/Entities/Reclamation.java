/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Administrateur
 */
public class Reclamation {
    private int id;
    
    
    private int user_id ;
    private Date date;
    private String type;
    private String contenu ;
    private String img;

    public Reclamation() {
    }

    public Reclamation(int id, int user_id, Date date, String type, String contenu, String img) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.type = type;
        this.contenu = contenu;
        this.img = img;
    }

    public Reclamation(int user_id, Date date, String type, String contenu, String img) {
        this.user_id = user_id;
        this.date = date;
        this.type = type;
        this.contenu = contenu;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
    
    
}
