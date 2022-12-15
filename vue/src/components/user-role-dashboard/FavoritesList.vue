<template>
  <div class="main-container">
    <ul class="brewery-display">
      <favorited-brewery-items
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
        :email="brewery.emailAddress">
      </favorited-brewery-items>
    </ul>
  </div>
</template>

<script>
// need to import and add the BreweryItem cmp here since we are using it in this cmp
//import BreweryItem from "../brewery-resources/BreweryItem.vue";
import FavoritedBreweryItems from "../brewery-resources/FavoritedBreweryItems.vue"

export default {
  components: {
    //BreweryItem, 
    FavoritedBreweryItems,
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
  margin: 0px;
  padding-inline-start: 0px;
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

