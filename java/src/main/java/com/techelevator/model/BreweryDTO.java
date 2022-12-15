package com.techelevator.model;

public class BreweryDTO {
    private String id;
    private String name;
    private String brewery_type;
    private String street;
    private String addy_2;
    private String addy3;
    private String city;
    private String county;
    private String postal_code;
    private String state;
    private Double longi;
    private Double lati;
    private String phone;
    private String website_url;
    private String brewery_status;
    private String updated;
    private String created;

    public BreweryDTO(){}


    public BreweryDTO(String id, String name, String brewery_type, String street, String addy_2, String addy3, String city, String county, String postal_code, String country, Double longi, Double lati, String phone, String website_url, String brewery_status, String updated, String created) {
        this.id = id;
        this.name = name;
        this.brewery_type = brewery_type;
        this.street = street;
        this.addy_2 = addy_2;
        this.addy3 = addy3;
        this.city = city;
        this.county = county;
        this.postal_code = postal_code;
        this.state = country;
        this.longi = longi;
        this.lati = lati;
        this.phone = phone;
        this.website_url = website_url;
        this.brewery_status = brewery_status;
        this.updated = updated;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrewery_type() {
        return brewery_type;
    }

    public void setBrewery_type(String brewery_type) {
        this.brewery_type = brewery_type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddy_2() {
        return addy_2;
    }

    public void setAddy_2(String addy_2) {
        this.addy_2 = addy_2;
    }

    public String getAddy3() {
        return addy3;
    }

    public void setAddy3(String addy3) {
        this.addy3 = addy3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String country) {
        this.state = country;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite_url() {
        return website_url;
    }

    public void setWebsite_url(String website_url) {
        this.website_url = website_url;
    }

    public String getBrewery_status() {return brewery_status;}

    public void setBrewery_status(String brewery_status) {this.brewery_status = brewery_status;}

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
