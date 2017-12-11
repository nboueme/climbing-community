package com.nicolasboueme.climbing.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Topo extends Publication {
    private int publicationId;
    private String description;
    private String imageUrl;

    private int spotsNumber;
    private Spot topoHasSpot;

    private boolean isLoaned;
    private Date borrowingDate;
    private Date returnDate;

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

    public boolean isLoaned() {
        return isLoaned;
    }

    public void setLoaned(boolean loaned) {
        isLoaned = loaned;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public String getBorrowingDateString() {
        if (borrowingDate != null)
            return new SimpleDateFormat("dd MMMM yyyy").format(borrowingDate);
        else
            return null;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getReturnDateString() {
        if (returnDate != null)
            return new SimpleDateFormat("dd MMMM yyyy").format(returnDate);
        else
            return null;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
