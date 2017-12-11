package com.nicolasboueme.climbing.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Publication {
    private int id;
    private int userAccountId;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        if (createdAt != null)
            return new SimpleDateFormat("dd MMMM yyyy").format(createdAt);
        else
            return null;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        if (updatedAt != null)
            return new SimpleDateFormat("dd MMMM yyyy").format(updatedAt);
        else
            return null;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
