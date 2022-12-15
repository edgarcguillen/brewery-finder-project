<template>
  <div class="main-container">
    <div class="filter-container">
      <fieldset>
        <input
          type="text"
          id="breweryNameFilter"
          v-model="search"
          placeholder="Search by name, city, or state"
        />
      </fieldset>
      <fieldset>
        <select id="typeFilter" v-model="type">
          <option value>Sort by type</option>
          <option value="micro">Microbrewery</option>
          <option value="regional">Regional</option>
          <option value="brewpub">Brewpub</option>
          <option value="large">Large</option>
          <option value="proprietor">Proprietor</option>
        </select>
      </fieldset>
    </div>
    <ul class="brewery-display">
      <brewery-item
        v-for="brewery in filteredList"
        :key="brewery.breweryID"
        :id="brewery.breweryID"
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
import BreweryItem from "./BreweryItem.vue";
import BreweryService from "../../services/BreweryService.js";

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
      BreweryService.getAllBreweries().then((response) => {
        this.$store.commit("SET_BREWERIES", response.data);
        this.isLoading = false;
      });
    },
  },
  computed: {
    filterByType() {
      const filteredBreweries = this.$store.state.breweries;

      // loops through each brewery and checks if the type matches the user-selected option and if yes it is included in the filter results.
      return filteredBreweries.filter((brewery) => {
        if (!brewery.breweryType.indexOf(this.type)) {
          return true;
        }
        return false;
      });
    },

    filteredList() {
      const filteredBreweries = this.$store.state.breweries;
      if (this.search != "") {
        return filteredBreweries.filter((brewery) => {
          if (
            brewery.breweryName
              .toLowerCase()
              .includes(this.search.toLowerCase())
          ) {
            return true;
          }
          if (
            brewery.breweryState
              .toLowerCase()
              .includes(this.search.toLowerCase())
          ) {
            return true;
          }
          if (
            brewery.breweryCity
              .toLowerCase()
              .includes(this.search.toLowerCase())
          ) {
            return true;
          }
          return false;
        });
      }
      return this.filterByType;
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
  margin: 50px 0 100px 0;
}

.filter-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin: 50px auto;
}

.filter-container fieldset > input,
.filter-container fieldset > select,
.filter-container fieldset {
  width: 400px;
  border: none;
}

.brewery-display {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: space-evenly;
  margin: 0px;
  padding-inline-start: 0px;
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
