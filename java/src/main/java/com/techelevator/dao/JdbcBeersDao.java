package com.techelevator.dao;

import com.techelevator.model.Beers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBeersDao implements BeersDAO{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBeersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Beers> listBeers() {
        List<Beers> beers = new ArrayList<>();
        String sql = " SELECT beers.beer_id, beers.beer_name, beers.beer_description, beers.beer_image, beers.beer_type, beers.abv " +
                     " FROM beers " ;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            Beers beer = beersMapper(result);
            beers.add(beer);
        }
        return beers;
    }

    //TODO CHANGE
    @Override
    public int addBeer(Beers beer) {
        String sql =
                " INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv) " +
                 " VALUES (?, ?, ?, ?, ?) " +
                " RETURNING beer_id; ";

        int beerId = jdbcTemplate.queryForObject(sql, int.class, beer.getBeerName(), beer.getBeerDescription(), beer.getBeerImage(), beer.getBeerType(), beer.getAbv());


        return beerId;
    }

    //TODO TEST METHOD
    public void addBeerToBreweryBeerlistTable(int beerId, int breweryId){
        String sql =    " INSERT INTO brewery_beerlist(brewery_id, beer_id) " +
                        " VALUES (?, ?); ";
        jdbcTemplate.update(sql,beerId,breweryId);
    }


    @Override
    public void updateBeer(Beers beer) {
        List<Beers> beers = listBeers();
        if (beers.contains(beer)) {
            String sql = " UPDATE beers SET beer_name = ?, beer_description = ?, beer_image = ?, abv = ? " +
                         " WHERE beer_id = ? ; " ;
            jdbcTemplate.update(sql, beer.getBeerID(), beer.getBeerName(), beer.getBeerDescription(), beer.getBeerImage(), beer.getBeerType(), beer.getAbv());
        } else throw new IllegalArgumentException("Beer does not exist!");
    }

    @Override
    public void deleteBeer(int beerID) {

            String sql = " DELETE FROM brewery_beerlist " +
                         " WHERE beer_id = ? ; " +
                         " DELETE FROM reviews " +
                         " WHERE beer_id = ? ; " +
                         " DELETE FROM beers " +
                         " WHERE beer_id = ? ; " ;
            jdbcTemplate.update(sql, beerID, beerID, beerID);
//        else throw new IllegalArgumentException("Beer does not exist!");
    }

    @Override
    public List<Beers> listBeersByBrewery(int breweryID) {
        List<Beers> beers = new ArrayList<>();
        String sql= " SELECT beers.beer_id, beers.beer_name, beers.beer_description, beers.beer_image, beers.beer_type, beers.abv " +
                " FROM beers " +
                " JOIN brewery_beerlist " +
                " ON beers.beer_id = brewery_beerlist.beer_id " +
                " WHERE brewery_id = ? ; " ;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, breweryID);
        while (result.next()) {
            Beers beer = beersMapper(result);
            beers.add(beer);
        }
        return beers;
    }

    @Override
    public void addToBreweryList(int breweryID, int beerID) {
        List<Beers> beers = listBeersByBrewery(breweryID);
        if (!beers.contains(beerID)) {
            String sql = " INSERT INTO brewery_beerlist (brewery_id, beer_id) " +
                         " VALUES (?, ?) ; " ;
            jdbcTemplate.update(sql, breweryID, beerID);
        } else throw new IllegalArgumentException("This beer already is in your store!");
    }


    @Override
    public void deleteFromBrewerList(int breweryID, int beerID) {
        List<Beers> beers = listBeersByBrewery(breweryID);
        if (beers.contains(beerID)){
            String sql = " DELETE FROM brewer_beerlist " +
                         " WHERE beer_id ? ; " ;
            jdbcTemplate.update(sql, beerID);
        }
    }


    private Beers beersMapper(SqlRowSet sql){
        Beers newBeer = new Beers();
        newBeer.setBeerID(sql.getInt("beer_id"));
        newBeer.setBeerName(sql.getString("beer_name"));
        newBeer.setBeerDescription(sql.getString("beer_description"));
        newBeer.setBeerImage(sql.getString("beer_image"));
        newBeer.setBeerType(sql.getString("beer_type"));
        newBeer.setAbv(sql.getDouble("abv"));
        return newBeer;
    }


}
