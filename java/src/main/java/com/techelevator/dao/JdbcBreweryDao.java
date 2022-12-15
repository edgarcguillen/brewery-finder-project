package com.techelevator.dao;

import com.techelevator.model.Brewery;
import com.techelevator.services.BreweryAPIService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBreweryDao implements BreweryDAO{
    private final BreweryAPIService breweryAPIService;
    private final JdbcTemplate jdbcTemplate;


    public JdbcBreweryDao(BreweryAPIService breweryAPIService, JdbcTemplate jdbcTemplate) {
        this.breweryAPIService = breweryAPIService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Brewery> listBreweries() {
        List<Brewery> breweries = new ArrayList<>();
        String sql = " SELECT brewery_id, brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email, brewery_image, brewery_status  " +
                " FROM brewery ; " ;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Brewery brewery = breweryMapper(results);
            breweries.add(brewery);
        }
        return breweries;
    }

    //TODO CHANGE TO QUERY FOR OBJECT
    @Override
    public int addBrewery(Brewery brewery) {
        String sql =
                " INSERT INTO brewery (brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, " +
                " phone_number, website_url, contact_email, brewery_image, brewery_status) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'pending') " +
                " RETURNING brewery_id ; " ;

        int breweryId = jdbcTemplate.queryForObject(sql, int.class, brewery.getBreweryName(), brewery.getBreweryType(), brewery.getBreweryStreet(), brewery.getBreweryCity(),
                brewery.getBreweryState(), brewery.getPostalCode(), brewery.getPhoneNumber(), brewery.getWebsiteUrl(), brewery.getEmailAddress(), brewery.getPhoto());

        return breweryId;
    }


    public List<Brewery> APIBreweries(){
        return breweryAPIService.getMissouriBrewery();
    }


    @Override
    public void addToDatabase(List<Brewery> breweries) {
        for(Brewery one : breweries) {
            addBrewery(one);
        }
    }

    @Override
    public List<Brewery> getBreweryByName(String name) {
        List<Brewery> breweries = new ArrayList<>();
        String sql = " SELECT brewery_id, brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email, brewery_image " +
                " FROM brewery " +
                " WHERE brewery_name = ? ; " ;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, name);
        while (result.next()) {
            breweries.add(breweryMapper(result));
        }
        return breweries;
    }

    @Override
    public List<Brewery> getBreweryByCity(String city) {
        List<Brewery> breweries = new ArrayList<>();
        String sql = " SELECT brewery_id, brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email, brewery_image " +
                " FROM brewery " +
                " WHERE brewery_city = ? ; " ;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, city);
        while (result.next()) {
            breweries.add(breweryMapper(result));
        }
        return breweries;

    }

    @Override
    public List<Brewery> getBreweryByState(String State) {
        List<Brewery> breweries = new ArrayList<>();
        String sql = " SELECT brewery_id, brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email, brewery_image " +
                " FROM brewery " +
                " WHERE brewery_state = ? ; " ;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, State);
        while (result.next()) {
            breweries.add(breweryMapper(result));
        }
        return breweries;
    }

    @Override
    public List<Brewery> getBreweryByType(String type) {
        List<Brewery> breweries = new ArrayList<>();
        String sql = " SELECT brewery_id, brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email, brewery_image " +
                " FROM brewery " +
                " WHERE brewery_type= ? ; " ;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, type);
        while (result.next()) {
            breweries.add(breweryMapper(result));
        }
        return breweries;
    }

    @Override
    public List<Brewery> getBreweryByFavorite(int id) {
        List<Brewery> breweries = new ArrayList<>();
        String sql = " SELECT brewery.brewery_id, brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email, brewery_image, brewery_status  " +
                " FROM brewery " +
                " JOIN favorited ON brewery.brewery_id = favorited.brewery_id " +
                " JOIN users ON favorited.user_id = users.user_id " +
                " WHERE users.user_id = ? ; " ;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        while (result.next()) {
            breweries.add(breweryMapper(result));
        }
        return breweries;
    }

    @Override
    public void updateBrewery(Brewery brewery) {
        String sql = " UPDATE brewery " +
                " SET brewery_name=?, brewery_type=?, brewery_street=?, brewery_city=?, brewery_state=?, postal_code=?, phone_number=?, website_url=?, contact_email=?, brewery_image=?, brewery_status=? " +
                " WHERE brewery_id::int=? ; " ;
        jdbcTemplate.update(sql, brewery.getBreweryName(), brewery.getBreweryType(), brewery.getBreweryStreet(), brewery.getBreweryCity(), brewery.getBreweryState(), brewery.getPostalCode(), brewery.getPhoneNumber(), brewery.getWebsiteUrl(), brewery.getEmailAddress(), brewery.getPhoto(),  brewery.getBreweryStatus(), brewery.getBreweryID());
    }

    @Override
    public void deleteBrewery(Brewery brewery) {
        List<Brewery> breweries = listBreweries();
        if (breweries.contains(brewery.getBreweryID()) ){
            String sql = " DELETE FROM brewery " +
                    " WHERE brewery_id = ? ; " ;
            jdbcTemplate.update(sql, brewery.getBreweryID());
        } else throw new IllegalArgumentException("Brewery Does Not Exist!");    }

    @Override
    public Brewery getBreweryByID(int id) {
        String sql = " SELECT brewery_id, brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email, brewery_image, brewery_status " +
                " FROM brewery " +
                " WHERE brewery_id= ? ; " ;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return breweryMapper(result);
        } else throw new IllegalArgumentException("Brewery Not Found");

    }

    @Override
    public void addFavorite(int userID, int breweryID) {
        String sql = " INSERT INTO favorited (user_id, brewery_id) " +
                " VALUES (?, ?) ; " ;
        jdbcTemplate.update(sql, userID, breweryID);
    }

    @Override
    public void removeFavorite(int userID, int breweryID) {
        String sql = " DELETE FROM favorited " +
                " WHERE user_id = ? AND brewery_id = ?; " ;
        jdbcTemplate.update(sql, userID, breweryID);
    }


    private Brewery breweryMapper(SqlRowSet brewery) {
        Brewery newBrewery = new Brewery();
        newBrewery.setBreweryID(brewery.getInt("brewery_id"));
        newBrewery.setBreweryCity(brewery.getString("brewery_city"));
        newBrewery.setBreweryName(brewery.getString("brewery_name"));
        newBrewery.setBreweryState(brewery.getString("brewery_state"));
        newBrewery.setBreweryStreet(brewery.getString("brewery_street"));
        newBrewery.setBreweryType(brewery.getString("brewery_type"));
        newBrewery.setPhoneNumber(brewery.getString("phone_number"));
        newBrewery.setPostalCode(brewery.getString("postal_code"));
        newBrewery.setWebsiteUrl(brewery.getString("website_url"));
        newBrewery.setEmailAddress(brewery.getString("contact_email"));
        newBrewery.setPhoto(brewery.getString("brewery_image"));
        newBrewery.setBreweryStatus(brewery.getString("brewery_status"));
        return newBrewery;
    }


    @Override
    public void approveBrewery(int id) {
        String sql = "UPDATE brewery " +
                " SET brewery_status = 'approved' " +
                " WHERE brewery_id = ? ";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void approveAll() {
        String sql = "UPDATE brewery " +
                " SET brewery_status = 'approved' ";
        jdbcTemplate.update(sql);
    }

//    public void approveBrewery(Brewery brewery) {
//        List<Brewery> breweries = listBreweries();
//        if (breweries.contains(brewery.getBreweryID())) {
//            String sql = " UPDATE brewery " +
//                         " SET brewery_status = 'approved' " +
//                         " WHERE brewery_id = ? ; " ;
//            jdbcTemplate.update(sql, brewery.getBreweryID());
//        } else throw new IllegalArgumentException("Can't find brewery.");    }


    @Override
    public List<Brewery> listApprovedBreweries() {
        List<Brewery> breweries = new ArrayList<>();
        String sql = " SELECT brewery_id, brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email, brewery_image, brewery_status " +
                " FROM brewery " +
                " WHERE brewery_status = 'approved' ; " ;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Brewery brewery = breweryMapper(results);
            breweries.add(brewery);
        }
        return breweries;
    }

    @Override
    public List<Brewery> listPendingBreweries() {
        List<Brewery> breweries = new ArrayList<>();
        String sql = " SELECT brewery_id, brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email, brewery_image, brewery_status " +
                " FROM brewery " +
                " WHERE brewery_status = 'pending' ; " ;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Brewery brewery = breweryMapper(results);
            breweries.add(brewery);
        }
        return breweries;
    }

    @Override
    public List<Brewery> getBreweryByUserID(int id) {
        List<Brewery> breweries = new ArrayList<>();
        String sql = " SELECT * from brewery " +
                " INNER JOIN brewery_user " +
                " ON brewery.brewery_id = brewery_user.brewery_id " +
                " WHERE brewery_user.user_id = ? " ;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        while (result.next()) {
            Brewery brewery = breweryMapper(result);
            breweries.add(brewery);
        }
        return breweries;

    }

    //TODO Test Method
    public void addUserAndBreweryToAssociativeTable(int breweryId, int userId) {
        String sql = " INSERT INTO brewery_user(brewery_id, user_id) " +
                " VALUES (?,?) " ;
        jdbcTemplate.update(sql, breweryId, userId);
    }

}
