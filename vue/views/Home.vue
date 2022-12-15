<template>
  <div class="home">
    <the-header></the-header>
    <brewery-list></brewery-list>
  </div>
</template>

<script>
import BreweryList from "../components/brewery-resources/BreweryList.vue";
import TheHeader from "../components/layouts/TheHeader.vue";

export default {
  name: "home",
  components: { TheHeader, BreweryList },

  //when created do function that check role and routes to dashboard 
  
};

// create
</script>
