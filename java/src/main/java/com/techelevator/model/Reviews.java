package com.techelevator.model;

public class Reviews {

    private int reviewID;
    private int stars;
    private String review;
    private int userID;
    private int beerID;

    public Reviews(int reviewID, int stars, String review, int userID, int beerID) {
        this.reviewID = reviewID;
        this.stars = stars;
        this.review = review;
        this.userID = userID;
        this.beerID = beerID;
    }

    public Reviews() {

    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBeerID() {
        return beerID;
    }

    public void setBeerID(int beerID) {
        this.beerID = beerID;
    }
}
