<template>
  <div class="main-container">
    <h3>Beer List</h3>
    <button @click="addBeer">Add Beer</button>

    <ul class="beer-display">
      <my-beer-item
        v-for="beer in this.$store.state.myBeers"
        :key="beer.beerID"
        :beerID="beer.beerID"
        :name="beer.beerName"
        :description="beer.beerDescription"
        :image="beer.beerImage"
        :type="beer.beerType"
        :abv="beer.abv"
      >
      </my-beer-item>
    </ul>
  </div>
</template>

<script>
// need to import and add the BreweryItem cmp here since we are using it in this cmp
import MyBeerItem from "../brewer-role-resources/MyBeerItem.vue";
import beerService from "../../services/BeerService.js";

export default {
  components: {
    MyBeerItem,
  },
  props: ["breweryID"],
  data() {
    return {
      search: "",
    };
  },
  created() {
    this.retrieveBeers();
  },

  methods: {
    // ADDS BEEER TO BREWERYLIST
    addBeer() {
      this.$router.push(
        `/dashboard/${this.$store.state.user.id}/brewery/${this.breweryID}/beer/create`
      );
    },

    // ** GETS BEERS BY BREWERY ID
    retrieveBeers() {
      beerService
        .getBeersByBreweryID(this.$route.params.breweryID)
        .then((response) => {
          this.$store.commit("SET_BEERS_BY_BREWERY_ID", response.data);
        });
    },
  },
};
</script>

<style scoped>
.main-container {
  margin: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 100px 0 200px 0;
}

h3 {
  font-size: 3rem;
  margin: 50px 0 50px 0;
}

.beer-display {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: center;
  margin: 0px;
  padding-inline-start: 0px;
}

button {
  width: 200px;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  margin-bottom: 50px;
  background-color: transparent;
  border-radius: 10px;
  font-family: inherit;
  text-align: center;
  color: black;
  cursor: pointer;
}

button:hover {
  background-color: #e295695b;
  border: 1px solid #e29569;
}
</style>
