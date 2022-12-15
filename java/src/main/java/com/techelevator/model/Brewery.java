package com.techelevator.model;

public class Brewery {


    private int breweryID;
    private String breweryName;
    private String breweryType;
    private String breweryStreet;
    private String breweryCity;
    private String breweryState;
    private String postalCode;
    private String phoneNumber;
    private String websiteUrl;
    private String emailAddress;
    private String photo;
    public String breweryStatus;

    public Brewery(){
    }

    public Brewery(int breweryID, String breweryName, String breweryType, String breweryStreet, String breweryCity, String breweryState, String postalCode, String phoneNumber, String websiteUrl, String emailAddress, String photo, String BreweryStatus) {
        this.breweryID = breweryID;
        this.breweryName = breweryName;
        this.breweryType = breweryType;
        this.breweryStreet = breweryStreet;
        this.breweryCity = breweryCity;
        this.breweryState = breweryState;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.websiteUrl = websiteUrl;
        this.emailAddress = emailAddress;
        this.photo = photo;
    }

    public int getBreweryID() {
        return breweryID;
    }

    public void setBreweryID(int breweryID) {
        this.breweryID = breweryID;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public String getBreweryType() {
        return breweryType;
    }

    public void setBreweryType(String breweryType) {
        this.breweryType = breweryType;
    }

    public String getBreweryStreet() {
        return breweryStreet;
    }

    public void setBreweryStreet(String breweryStreet) {
        this.breweryStreet = breweryStreet;
    }

    public String getBreweryCity() {
        return breweryCity;
    }

    public void setBreweryCity(String breweryCity) {
        this.breweryCity = breweryCity;
    }

    public String getBreweryState() {
        return breweryState;
    }

    public void setBreweryState(String breweryState) {
        this.breweryState = breweryState;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBreweryStatus() {return breweryStatus;}

    public void setBreweryStatus(String breweryStatus) {this.breweryStatus = breweryStatus;}

    @Override
    public String toString() {
        return "Brewery{" +
                "breweryID=" + breweryID +
                ", breweryName='" + breweryName + '\'' +
                ", breweryType='" + breweryType + '\'' +
                ", breweryStreet='" + breweryStreet + '\'' +
                ", breweryCity='" + breweryCity + '\'' +
                ", breweryState='" + breweryState + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", photo='" + photo + '\'' +
                ", breweryStatus='" + breweryStatus + '\'' +
                '}';
    }
}
