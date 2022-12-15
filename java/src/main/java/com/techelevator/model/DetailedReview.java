package com.techelevator.model;

public class DetailedReview {

    //Created this new class to be able to get, specifically the BEER_NAME, but
    // other details of the beers being reviewed as well.

    private int beerID;
    private int userID;
    private int reviewID;
    private String beerName;
    private String beerDescription;
    private String beerType;
    private String beerIMG;
    private int stars;
    private String review;
    private Double abv;
    private String breweryName;

    public DetailedReview() {
    }

    public DetailedReview(int beerID, int userID, int reviewID, String beerName, String beerDescription, String beerType, String beerIMG, int stars, String review, Double abv, String breweryName) {
        this.beerID = beerID;
        this.userID = userID;
        this.reviewID = reviewID;
        this.beerName = beerName;
        this.beerDescription = beerDescription;
        this.beerType = beerType;
        this.beerIMG = beerIMG;
        this.stars = stars;
        this.review = review;
        this.abv = abv;
        this.breweryName = breweryName;
    }

    public int getBeerID() {
        return beerID;
    }

    public void setBeerID(int beerID) {
        this.beerID = beerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
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

    public String getBeerType() {
        return beerType;
    }

    public void setBeerType(String beerType) {
        this.beerType = beerType;
    }

    public String getBeerIMG() {
        return beerIMG;
    }

    public void setBeerIMG(String beerIMG) {
        this.beerIMG = beerIMG;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    };

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }
}
