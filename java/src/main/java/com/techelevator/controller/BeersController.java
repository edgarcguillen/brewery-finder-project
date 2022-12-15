package com.techelevator.controller;

import com.techelevator.dao.JdbcBeersDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.model.Beers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@PreAuthorize("permitAll")
public class BeersController {

    private final JdbcBeersDao jdbcBeersDAO;
    private final JdbcUserDao jdbcUserDao;


    public BeersController(JdbcBeersDao jdbcBeersDAO, JdbcUserDao jdbcUserDao) {
        this.jdbcBeersDAO = jdbcBeersDAO;
        this.jdbcUserDao = jdbcUserDao;

    }

    @RequestMapping(path = "/beers", method = RequestMethod.GET)
    public List<Beers> allBeers(){
        return jdbcBeersDAO.listBeers();
    }

    @RequestMapping(path = "/beers/{breweryId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_BREWER')")
    public void addBeer(@RequestBody Beers beers,@PathVariable int breweryId){

        int beerId = jdbcBeersDAO.addBeer(beers);

        // TODO need to find a way to get brewery id to use below for the associative table; this currently does not work since it's grabbing the userId and not the breweryId

        jdbcBeersDAO.addBeerToBreweryBeerlistTable(breweryId, beerId);

    }

    @RequestMapping(path = "/beers/{beerID}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_BREWER')")
    public void deleteBeer(@PathVariable("beerID") int beerID){
        jdbcBeersDAO.deleteBeer(beerID);
    }

    @RequestMapping(path = "/beers", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_BREWER')")
    public void updateBeer(@RequestBody Beers beers){
        jdbcBeersDAO.updateBeer(beers);
    }


    @RequestMapping(path = "/beerlist", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_BREWER')")
    public void addBeerToList(@RequestParam(value = "brewery") int breweryID, int beerID){
        jdbcBeersDAO.addToBreweryList(breweryID, beerID);
    }

    @RequestMapping(path = "/beerlist", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_BREWER')")
    public void deleteBeerFromList(@RequestParam(value = "brewery") int breweryID, int beerID){
        jdbcBeersDAO.deleteFromBrewerList(breweryID, beerID);
    }

    @RequestMapping(path = "/beers/{breweryID}", method = RequestMethod.GET)
    public List<Beers> allBeersByBrewery(@PathVariable int breweryID){
        return jdbcBeersDAO.listBeersByBrewery(breweryID);
    }

}
