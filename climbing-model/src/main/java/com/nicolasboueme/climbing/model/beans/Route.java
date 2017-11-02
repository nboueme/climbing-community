package com.nicolasboueme.climbing.model.beans;

public class Route extends Publication {
    private int publication_id;
    private int sector_id;
    private int parent_publication_id;
    private int height;
    private String quotation;
    private double latitude;
    private double longitude;
    private int points_number;
    private String type_route;

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public int getSector_id() {
        return sector_id;
    }

    public void setSector_id(int sector_id) {
        this.sector_id = sector_id;
    }

    public int getParent_publication_id() {
        return parent_publication_id;
    }

    public void setParent_publication_id(int parent_publication_id) {
        this.parent_publication_id = parent_publication_id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getPoints_number() {
        return points_number;
    }

    public void setPoints_number(int points_number) {
        this.points_number = points_number;
    }

    public String getType_route() {
        return type_route;
    }

    public void setType_route(String type_route) {
        this.type_route = type_route;
    }
}
