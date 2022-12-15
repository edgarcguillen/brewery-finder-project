<template>
  <div class="main-container">
    <h3>Beer List</h3>

    <ul class="beer-display">
      <beer-item
        v-for="beer in this.$store.state.beers"
        :key="beer.beerID"
        :id="beer.beerID"
        :name="beer.beerName"
        :description="beer.beerDescription"
        :image="beer.beerImage"
        :type="beer.beerType"
        :abv="beer.abv"
      >
      </beer-item>
    </ul>
  </div>
</template>

<script>
// need to import and add the BreweryItem cmp here since we are using it in this cmp
import BeerItem from "../beer-resources/BeerItem.vue";
import beerService from "../../services/BeerService.js";

export default {
  data() {
    return {
      isLoading: true,
      search: "",
    };
  },
  components: {
    BeerItem,
    
  },
  created() {
    this.retrieveBeers();
  },
  methods: {
    retrieveBeers() {
      beerService.getAllBeers().then((response) => {
        this.$store.commit("SET_BEERS", response.data);
        this.isLoading = false;
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
  margin: 0px;
  padding-inline-start: 0px;
}
</style>
