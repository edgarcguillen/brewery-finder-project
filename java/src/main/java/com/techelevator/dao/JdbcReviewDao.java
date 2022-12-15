package com.techelevator.dao;

import com.techelevator.model.DetailedReview;
import com.techelevator.model.Reviews;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDAO{

    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Reviews> reviewsByUser(int userID) {
        List<Reviews> reviews = new ArrayList<>();
        String sql = " SELECT review_id, stars, review, user_id, beer_id " +
                     " FROM reviews " +
                     " WHERE user_id = ? ; " ;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userID);
        while (results.next()) {
            Reviews reviews1 = reviewMapper(results);
            reviews.add(reviews1);
        }
        return reviews;
    }

    @Override
    public List<Reviews> reviewsByBeer(int beerID) {
        List<Reviews> reviews = new ArrayList<>();
        String sql = " SELECT review_id, stars, review, user_id, beer_id " +
                     " FROM reviews " +
                     " WHERE beer_id::int = ? ; " ;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerID);
        while (results.next()) {
            Reviews reviews1 = reviewMapper(results);
            reviews.add(reviews1);
        }
        return reviews;
    }

    @Override
    public void addReview(Reviews reviews) {
        String sql = " INSERT INTO reviews (stars, review, user_id, beer_id) " +
                     " VALUES (?, ?, ?, ?) ; " ;
        jdbcTemplate.update(sql, reviews.getStars(), reviews.getReview(), reviews.getUserID(), reviews.getBeerID());
    }

    @Override
    public List<DetailedReview> getBeerDetailsFromUserReview(int userID){
        List<DetailedReview> detailedReviews = new ArrayList<>();
        String sql =
                " SELECT beers.beer_id, beers.beer_name, beers.beer_description, reviews.review, beers.beer_image, beers.beer_type, beers.abv, reviews.stars, brewery.brewery_name " +
                " FROM beers " +
                " JOIN reviews on beers.beer_id = reviews.beer_id " +
                " JOIN brewery_beerlist on brewery_beerlist.beer_id = beers.beer_id " +
                " JOIN brewery on brewery.brewery_id = brewery_beerlist.beer_id " +
                " WHERE reviews.user_id = ? ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userID);
        while (results.next()){
            DetailedReview detailedReview = userDetailedReviewBeerMapper(results);
            detailedReviews.add(detailedReview);
        }
        return detailedReviews;
    }

    private Reviews reviewMapper(SqlRowSet review){
        Reviews newReview = new Reviews();
        newReview.setReviewID(review.getInt("review_id"));
        newReview.setStars(review.getInt("stars"));
        newReview.setReview(review.getString("review"));
        newReview.setUserID(review.getInt("user_id"));
        newReview.setBeerID(review.getInt("beer_id"));
        return newReview;
    }

    private DetailedReview userDetailedReviewBeerMapper(SqlRowSet detailedReview) {
        DetailedReview newDetailedReview = new DetailedReview();
        newDetailedReview.setReviewID(detailedReview.getInt("beer_id"));
        newDetailedReview.setBeerName(detailedReview.getString("beer_name"));
        newDetailedReview.setBeerDescription(detailedReview.getString("beer_description"));
        newDetailedReview.setBeerType(detailedReview.getString("beer_type"));
        newDetailedReview.setBeerIMG(detailedReview.getString("beer_image"));
        newDetailedReview.setReview(detailedReview.getString("review"));
        newDetailedReview.setStars(detailedReview.getInt("stars"));
        newDetailedReview.setBeerID(detailedReview.getInt("beer_id"));
        newDetailedReview.setAbv(detailedReview.getDouble("abv"));
        newDetailedReview.setBreweryName(detailedReview.getString("brewery_name"));
        return newDetailedReview;
    }
}
