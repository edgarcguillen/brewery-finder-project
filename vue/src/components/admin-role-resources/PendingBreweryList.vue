<template>
  <div class="main-container">
    <ul class="brewery-display">
      <admin-brewery-item-display
        v-for="brewery in this.$store.state.pendingBreweries"
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
      </admin-brewery-item-display>
    </ul>
  </div>
</template>

<script>
// need to import and add the BreweryItem cmp here since we are using it in this cmp
import breweryService from "../../services/BreweryService.js";
import adminBreweryItemDisplay from "../admin-role-resources/AdminBreweryItemDisplay.vue";

export default {
  data() {
    return {
      type: "",
      isLoading: true,
      search: "",
    };
  },
  components: {
    adminBreweryItemDisplay,
  },
  created() {
    this.retrieveBreweries();
  },
  methods: {
    retrieveBreweries() {
      breweryService.getPendingBreweries().then((response) => {
        this.$store.commit("SET_PENDING_BREWERIES", response.data);
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
  margin-top: 100px;
}


.brewery-display {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 2rem;
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
  border: 0.25px solid #5f200f;
}
</style>
