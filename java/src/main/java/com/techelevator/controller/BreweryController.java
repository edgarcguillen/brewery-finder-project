package com.techelevator.controller;


import com.techelevator.dao.JdbcBreweryDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.model.Brewery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("permitAll")
public class BreweryController {
    private final JdbcBreweryDao jdbcBreweryDao;
    private final JdbcUserDao jdbcUserDao;

    public BreweryController(JdbcBreweryDao jdbcBreweryDao, JdbcUserDao jdbcUserDao) {
        this.jdbcBreweryDao = jdbcBreweryDao;
        this.jdbcUserDao = jdbcUserDao;

        if (jdbcBreweryDao.listBreweries().size() < 10) {
            jdbcBreweryDao.addToDatabase(jdbcBreweryDao.APIBreweries());
            jdbcBreweryDao.approveAll();
        }
    }

    //no longer uses the listBreweries method
    @RequestMapping(path = "/breweries", method = RequestMethod.GET)
    public List<Brewery> getAllBreweries(){
        return jdbcBreweryDao.listApprovedBreweries();
    }

    //Link to Brewer Console
    //Registers a new brewery as a pending brewery,
    //and adds brewery_id and user_id to the tables
    //TODO Test Method on Frontend
    @RequestMapping(path = "/dashboard/{userId}/brewery/create", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_BREWER')")
    public void addBrewery(@RequestBody Brewery brewery, Principal principal){
        int breweryId = jdbcBreweryDao.addBrewery(brewery);
        int userId = jdbcUserDao.findIdByUsername(principal.getName());
        jdbcBreweryDao.addUserAndBreweryToAssociativeTable(breweryId, userId);
    }

    @RequestMapping(path = "/breweries/name", method = RequestMethod.GET)
    public List<Brewery> getBreweryByName(@RequestParam(value = "breweryName") String breweryName){
        return jdbcBreweryDao.getBreweryByName(breweryName);
    }

    @RequestMapping(path = "/breweries/city", method = RequestMethod.GET)
    public List<Brewery> getBreweriesByCity(@RequestParam(value = "breweryCity") String breweryCity){
        return jdbcBreweryDao.getBreweryByCity(breweryCity);
    }

    @RequestMapping(path = "/breweries/state", method = RequestMethod.GET)
    public List<Brewery> getBreweriesByState(@RequestParam(value = "breweryState") String breweryState){
        return jdbcBreweryDao.getBreweryByState(breweryState);
    }

    @RequestMapping(path = "/breweries/type", method = RequestMethod.GET)
    public List<Brewery> getBreweriesByType(@RequestParam String breweryType){
        return jdbcBreweryDao.getBreweryByType(breweryType);
    }

    @RequestMapping(path = "/breweries/favorites/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Brewery> getBreweriesIfFavorite(@PathVariable int id){
        return jdbcBreweryDao.getBreweryByFavorite(id);
    }
    //TODO >> need to give access to ROLE_BREWER
    @RequestMapping(path = "/dashboard/{brewerID}/brewery/{breweryID}/edit", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_BREWER')")
    public void updateBrewery(@RequestBody Brewery brewery){
        jdbcBreweryDao.updateBrewery(brewery);
    }

    @RequestMapping(path = "/breweries/", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBrewery(@RequestBody Brewery brewery){
        jdbcBreweryDao.deleteBrewery(brewery);
    }

    //TODO ADD AUTH
    @RequestMapping(path = "/breweries/{id}")
    public Brewery getBreweryByID(@PathVariable int id){
        return jdbcBreweryDao.getBreweryByID(id);
    }

    //TODO Test Method on Frontend and add AUTH
    @RequestMapping(path = "breweries/favorites/{userID}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void addToFavorites(@PathVariable int userID, @RequestBody Brewery brewery){
        jdbcBreweryDao.addFavorite(userID, brewery.getBreweryID());
    }

    //TODO TEST METHOD
    @RequestMapping(path = "/breweries/favorites/{userID}/{breweryID}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteFromFavorites(@PathVariable int userID, @PathVariable int breweryID){
        jdbcBreweryDao.removeFavorite(userID, breweryID);
    }


    //TODO Test Method on Frontend
    @RequestMapping(path = "/admin/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void changeToApproved(@PathVariable int id){
        jdbcBreweryDao.approveBrewery(id);
    }

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public List<Brewery> getPendingBreweries(){
        return jdbcBreweryDao.listPendingBreweries();
    }


    @RequestMapping(path = "/brewer/{id}")
    public List<Brewery> getBreweryByUserId(@PathVariable int id){
        return  jdbcBreweryDao.getBreweryByUserID(id);
    }
}
