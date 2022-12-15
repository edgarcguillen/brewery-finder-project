package com.techelevator.dao;

import com.techelevator.model.DetailedReview;
import com.techelevator.model.Reviews;

import java.util.List;

public interface ReviewDAO {

    List<Reviews> reviewsByUser(int userID);

    List<Reviews> reviewsByBeer(int beerID);

    void addReview(Reviews reviews);

    List<DetailedReview> getBeerDetailsFromUserReview(int userID);
}
