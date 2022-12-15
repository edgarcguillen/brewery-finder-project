<template>
  <div>
    <div class="main-container">
      <button @click="addBrewery"><h3>Add Brewery</h3></button>

      <!-- will show content if brewery exists, else message -->

      <!-- if loading show some loading icon -->
      <div class="loading" v-if="isLoading">
        <img src="../../assets/hop.png" />
      </div>

      <ul class="brewery-display">
        <my-brewery-item
          v-for="brewery in this.$store.state.myBreweries"
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
        ></my-brewery-item>
      </ul>
    </div>
  </div>
</template>

<script>
import BreweryService from "../../services/BreweryService";
import MyBreweryItem from "./MyBreweryItem.vue";

export default {
  components: { MyBreweryItem },
  props: ["brewerID"],
  data() {
    return {
      title: "",
      isLoading: false,
      errorMsg: "",
    };
  },
  created() {
    this.retrieveBreweries();
  },
  methods: {
    addBrewery() {
      this.$router.push({
        name: "AddBrewery",
        params: { brewerID: this.brewerID },
      });
    },
    retrieveBreweries() {
      BreweryService.getBreweryByUserId(this.brewerID)
        .then((response) => {
          this.$store.commit("SET_BREWERIES_BY_BREWER_ID", response.data);
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert(
              "Brewery not available. This brewery may have been deleted or you have entered an invalid brewery ID."
            );
            this.$router.push({ name: "welcome" });
          }
        });
    },
  },
};
</script>

<style scoped>
.main-container {
  margin-top: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 100px 0 100px 0;
}

.brewery-display {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  gap: 2rem;
  margin: 0px;
  padding-inline-start: 0px;
}

button:hover {
  background-color: #e295695b;
  border: 1px solid #e29569;
}

button {
  width: 300px;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  margin-bottom: 50px;
  font-size: 1rem;
  font-weight: bold;
  background-color: #f6a000;
  background-size: cover;
  box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.24);
  transition: 0.2s all;
  border-radius: 10px;

  border: none;
  color: black;
  cursor: pointer;
  margin-top: 15px;
}

button:hover {
  background-color: #e29569b0;
}

h2 {
  font-size: 1.5rem;
}
</style>
