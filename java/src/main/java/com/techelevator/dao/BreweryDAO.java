package com.techelevator.dao;

import com.techelevator.model.Brewery;

import java.util.List;

public interface BreweryDAO {

    List<Brewery> listBreweries();

    int addBrewery(Brewery brewery);

    void addToDatabase(List<Brewery> breweries);


    List<Brewery> getBreweryByName(String name);

    List<Brewery> getBreweryByCity(String city);

    List<Brewery> getBreweryByState(String State);

    List<Brewery> getBreweryByType(String type);

    List<Brewery> getBreweryByFavorite(int userID);

    void updateBrewery(Brewery brewery);

    void deleteBrewery(Brewery brewery);

    Brewery getBreweryByID(int id);

    void addFavorite(int userID, int breweryID);

    void removeFavorite(int userID, int breweryID);

    void approveBrewery(int breweryID);

    void approveAll();

    List<Brewery> listApprovedBreweries();

    List<Brewery> listPendingBreweries();

    List<Brewery> getBreweryByUserID(int id);



}
