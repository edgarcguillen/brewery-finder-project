<template>
  <div class="main-container">
    <ul class="brewery-display">
      <brewery-item
        v-for="brewery in this.$store.state.favoriteBreweries"
        :key="brewery.breweryID"
        :breweryID="brewery.breweryID"
        :name="brewery.breweryName"
        :type="brewery.breweryType"
        :street="brewery.breweryStreet"
        :city="brewery.breweryCity"
        :state="brewery.breweryState"
        :zip="brewery.postalCode"
        :phone="brewery.phoneNumber"
        :website="brewery.websiteUrl"
        :email="brewery.emailAddress"
      >
      </brewery-item>
      
    </ul>
  </div>
</template>

<script>
// need to import and add the BreweryItem cmp here since we are using it in this cmp
import BreweryItem from "../components/brewery-resources/BreweryItem.vue";
import BreweryService from "../services/BreweryService.js";

export default {
  data() {
    return {
      type: "",
      isLoading: true,
      search: "",
    };
  },
  components: {
    BreweryItem, 
  },
  created() {
    this.retrieveBreweries();
  },
  methods: {
    retrieveBreweries() {
      BreweryService.getFavorites(this.$store.state.user).then((response) => {
        this.$store.commit("SET_FAVORITE_BREWERIES", response.data);
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

.filter-container{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  width: 900px;
  margin: 50px auto;
}

.brewery-display {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: space-evenly;
}

fieldset {
  border: none;
  width: 700px;
}


fieldset input,
fieldset select {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  padding: 1rem;
  font-size: 1.15rem;
  padding: 5px 0 5px 10px;
  border-radius: 5px;
  border: .25px solid #5F200F;
}

</style>










