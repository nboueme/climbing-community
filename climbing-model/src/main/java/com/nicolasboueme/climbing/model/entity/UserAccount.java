package com.nicolasboueme.climbing.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAccount {
    private int id;
    private String pseudo;
    private String email;
    private String password;
    private String imageUrl;
    private String role;
    private Date createdAt;
    private Date updatedAt;

    private Topo topo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedAt() {
        if (createdAt != null)
            return new SimpleDateFormat("dd MMMM yyyy, hh:mm").format(createdAt);
        else
            return null;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String  getUpdatedAt() {
        if (updatedAt != null)
            return new SimpleDateFormat("dd MMMM yyyy, hh:mm").format(updatedAt);
        else
            return null;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }
}
