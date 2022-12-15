package com.techelevator.controller;


import com.techelevator.dao.JdbcReviewDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.model.DetailedReview;
import com.techelevator.model.Reviews;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("permitAll")
public class ReviewController {
    private final JdbcReviewDao jdbcReviewDao;
    private final JdbcUserDao jdbcUserDao;

    public ReviewController(JdbcReviewDao jdbcReviewDao, JdbcUserDao jdbcUserDao) {
        this.jdbcReviewDao = jdbcReviewDao;
        this.jdbcUserDao = jdbcUserDao;
    }

    @RequestMapping(path = "/reviews/{beerID}", method = RequestMethod.GET)
    public List<Reviews> listReviewByBeer(@PathVariable int beerID){
        return jdbcReviewDao.reviewsByBeer(beerID);
    }

    @RequestMapping(path = "/{userID}/reviews", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<DetailedReview> listReviewByUser(@PathVariable int userID){
        return jdbcReviewDao.getBeerDetailsFromUserReview(userID);
    }

    @RequestMapping(path = "/reviews", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void postReview(@RequestBody Reviews reviews){
        jdbcReviewDao.addReview(reviews);
    }

}
