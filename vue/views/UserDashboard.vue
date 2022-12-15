<template>
  <div>
    <the-header></the-header>

      <div class="user-page-top">
          <h1>{{this.$store.state.user.username}}'s  Hoppin'  HomePage</h1>
      </div>

      <div class="button-container">
          <button v-on:click="showFavorites(), retrieveBreweries()">Show Favorite Breweries</button>
          <button v-on:click="showReviews(), retrieveReviews()">Show My Reviews</button>
      </div>

      
      <div class="favorites-section" v-if="showFavoritesBoolean">
        <favorites-list></favorites-list>
      </div>
      
      <div class="user-reviews-container">
        <ul v-if="showReviewsBoolean">
            <h1>Reviews:</h1>
            <user-beer-review-list></user-beer-review-list>
        </ul>`
    </div>
  </div>
</template>

<script>
import TheHeader from "../components/layouts/TheHeader.vue";
import FavoritesList from "../components/user-role-dashboard/FavoritesList.vue";
import BreweryService from '../services/BreweryService.js';
import ReviewService from '../services/ReviewService';
import UserBeerReviewList from '../components/user-role-dashboard/UserBeerReviewList.vue';

export default {
  data(){
    return{
      showFavoritesBoolean : false,
      showReviewsBoolean : false,
    }
  },
  favorites:false,
  reviews:false,
  components: { TheHeader, FavoritesList, UserBeerReviewList},
  created() {
    this.retrieveBreweries();
  },
  methods:{
    showFavorites(){
      this.showReviewsBoolean = false;
      this.showFavoritesBoolean = !this.showFavoritesBoolean;
    }, 
    showReviews(){
      this.showFavoritesBoolean = false;
      this.showReviewsBoolean = !this.showReviewsBoolean;
    },
    retrieveBreweries() {
      BreweryService.getFavorites(this.$store.state.user.id).then((response) => {
        this.$store.commit("SET_FAVORITE_BREWERIES", response.data);
      });
    },
    retrieveReviews(){
      ReviewService.getReviewsByUserId(this.$store.state.user.id).then((response) => {
        this.$store.commit("SET_USER_REVIEWS", response.data);
      })
    }
  }
};
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

header {
  margin-bottom: 100px;
}

.brewery-details {
  margin-bottom: 100px;
}

.button-container {
  padding: 20px;
  display: flex;
  flex-direction: row;
  margin: 0 auto;
  gap: 1rem;
  align-items: center;
  justify-content: center;
 
}

button {
  width: 300px;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  border-radius: 10px;
  font-family: inherit;
  color: black;
  cursor: pointer;
  text-align: center;
}

button p {
  text-align: left;
}

button:hover {
  background-color: #e295695b;
  border: 1px solid #e29569;
}

.active {
  background-color: #e295695b;
  border: 1px solid #e29569;
  color: black;
}

.off {
  background-color: transparent;
  border: 1px solid #e2956954;
  color: black;
}

h1 {
  font-size: 5rem;
  text-align: center;
  margin-bottom: 50px;
}

h3 {
  font-size: 3rem;
  text-align: center;
  margin-bottom: 50px;
}

.favorites-list{
  display: flex;
  flex-direction: row;
  border: 5px solid navy;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

.user-reviews-container ul{
  margin: 0px;
  padding-inline-start: 0px;
}

.favorites-list ul{
  margin: 0px;
  padding-inline-start: 0px;
}
</style>
