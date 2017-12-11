package com.nicolasboueme.climbing.model.entity;

public class Topo extends Publication {
    private int publicationId;
    private String description;
    private String imageUrl;

    private int spotsNumber;
    private Spot topoHasSpot;

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Spot getTopoHasSpot() {
        return topoHasSpot;
    }

    public void setTopoHasSpot(Spot topoHasSpot) {
        this.topoHasSpot = topoHasSpot;
    }

    public int getSpotsNumber() {
        return spotsNumber;
    }

    public void setSpotsNumber(int spotsNumber) {
        this.spotsNumber = spotsNumber;
    }
}
