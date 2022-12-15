import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:9000",
});

export default {
  // ***** REVIEWS METHODS *****

  //return list of reviews by beer ID (ROLE >> anybody)
  getReviews(beerID) {
    return http.get(`/reviews/${beerID}`);
  },

  //returns a beers by user ID (ROLE >> Registered User)
  getReviewsByUserId(userID) {
    return http.get(`/${userID}/reviews`);
  },

  //creates new review (ROLE >> Registered User)
  createReview(review) {
    return http.post(`/reviews`, review);
  },

};
