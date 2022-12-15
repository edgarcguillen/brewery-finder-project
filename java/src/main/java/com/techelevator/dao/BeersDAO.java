package com.techelevator.dao;

import com.techelevator.model.Beers;

import java.util.List;

public interface BeersDAO {

    List<Beers>listBeers();

    List<Beers> listBeersByBrewery(int breweryID);

    int addBeer(Beers beer);

    void updateBeer(Beers beer);

    void deleteBeer(int beerID);

    void addToBreweryList(int breweryID, int beerID);

    void deleteFromBrewerList(int breweryID, int beerID);
}
