package com.nicolasboueme.climbing.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private int id;
    private int userAccountId;
    private int publicationId;
    private int parentId;
    private Date createdAt;
    private Date updatedAt;
    private String content;

    private UserAccount author;
    private Publication publication;

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

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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

    public String getUpdatedAt() {
        if (updatedAt != null)
            return new SimpleDateFormat("dd MMMM yyyy, hh:mm").format(updatedAt);
        else
            return null;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserAccount getAuthor() {
        return author;
    }

    public void setAuthor(UserAccount author) {
        this.author = author;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
