package com.techelevator.model;

public class Beers {

    private int beerID;
    private String beerName;
    private String beerDescription;
    private String beerImage;
    private String beerType;
    private Double abv;

    public Beers(int beerID, String beerName, String beerDescription, String beerImage, String beerType, Double abv) {
        this.beerID = beerID;
        this.beerName = beerName;
        this.beerDescription = beerDescription;
        this.beerImage = beerImage;
        this.beerType = beerType;
        this.abv = abv;
    }

    public Beers() {

    }

    public int getBeerID() {
        return beerID;
    }

    public void setBeerID(int beerID) {
        this.beerID = beerID;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBeerDescription() {
        return beerDescription;
    }

    public void setBeerDescription(String beerDescription) {
        this.beerDescription = beerDescription;
    }

    public String getBeerImage() {
        return beerImage;
    }

    public void setBeerImage(String beerImage) {
        this.beerImage = beerImage;
    }

    public String getBeerType() {
        return beerType;
    }

    public void setBeerType(String beerType) {
        this.beerType = beerType;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }
}
