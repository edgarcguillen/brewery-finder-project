<template>
  <div class="main-container">
    <header>
      <h1>{{ this.$store.state.brewery.breweryName }}</h1>
    </header>

    <div class="base-card">
      <div class="img-container"></div>
      <div class="beer-details">
        <div class="beer-details-slot">
          <h3>
            {{ this.$store.state.brewery.breweryStreet }}
            {{ this.$store.state.brewery.breweryCity }} ,
            {{ this.$store.state.brewery.breweryState }},
            {{ this.$store.state.brewery.postalCode }}
          </h3>
          <a href="this.$store.state.brewery.websiteUrl">Go to website</a>
        </div>
      </div>
    </div>
    <!-- <beer-list></beer-list> -->
  </div>
</template>

<script>
import breweryService from "../../services/BreweryService";
// import BeerList from "../beer-resources/BeerList.vue";

export default {
  // components: { BeerList },
  created() {
    this.retrieveBrewery();
  },
  methods: {
    // retrieve brewery by ID
    retrieveBrewery() {
      breweryService
        .getBrewery(this.$route.params.id)
        .then((response) => {
          this.$store.commit("SET_CURRENT_BREWERY", response.data);
          console.log("hello");
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("Brewery not available.");
            this.$router.push({ name: "Home" });
          }
        });
    },
  },
};
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.base-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
}

header {
  margin-bottom: 30px;
}

.beer-details {
  display: flex;
  flex-direction: column;
}

.beer-details-slot {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  text-align: center;
  margin-bottom: 10px;
}

h1 {
  font-size: 5rem;
  text-align: center;
}

h3 {
  font-size: 2rem;
  line-height: 2rem;
}
</style>
