import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));

if (currentToken != null) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    // this will eventually be populated via backend data
    beers: [],
    myBeers: [],
    breweries: [],
    myBreweries: [],
    activeBrewerDashboardId : null,
    brewery: {
      name: "",
      type: "",
      street: "",
      city: "",
      state: "",
      zip: "",
      phone: "",
      website: "",
      email: "",
    },beer: {
      beerName: "",
      beerDescription: "",
      beerImage: "",
      beerType: "",
      abv: "",
    },
    review: {
      name: '',
      stars: '',
      review: ""
    },
    reviews: [],
    pendingBreweries:[],
    favoriteBreweries: [],
    userReviews:[],
    token: currentToken || "",
    user: currentUser || {},
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      state.token = "";
      state.user = {};
      axios.defaults.headers.common = {};
      this.$router.push("/logout")
    },

    //these mutation are for breweries

    SET_BREWERIES(state, data) {
      state.breweries = data;
    },

    SET_PENDING_BREWERIES(state, data){
      state.pendingBreweries = data;
    },

    SET_CURRENT_BREWERY(state, data) {
      state.brewery = data;
    },

    SET_FAVORITE_BREWERIES(state, data){
      state.favoriteBreweries = data;
    },

    //these mutations are for beers

    // ***** BREWER *****

    SET_ACTIVE_BREWER_DASHBOARD(state, brewerID) {
      state.activeBrewerDashboardId = brewerID;
    },

    SET_BREWERIES_BY_BREWER_ID(state, data) {
      state.myBreweries = data;
    },

    SET_BEERS_BY_BREWERY_ID(state,data){
      state.myBeers = data;
    },

    //these mutations are for beers

    SET_BEERS(state, data) {
      state.beers = data;
    },
    SET_CURRENT_BEER(state, data){
      state.beer = data;
    },

    //Reviews mutations

    SET_REVIEWS(state, data){
     state.reviews = data;
    },

    SET_REVIEW(state, data){
      state.review = data;
    },

    SET_USER_REVIEWS(state, data){
      state.userReviews = data;
    }
  },
});
