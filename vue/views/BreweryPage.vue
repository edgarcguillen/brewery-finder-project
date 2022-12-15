<template>
  <div>
    <the-header></the-header>
    <hero-section>
      <brewery-details></brewery-details>
    </hero-section>
    <div class="main-container">
      <brewery-beer-list></brewery-beer-list>
    </div>
  </div>
</template>

<script>
import BreweryDetails from "../components/brewery-resources/BreweryDetails.vue";
import TheHeader from "../components/layouts/TheHeader.vue";
import HeroSection from "../components/layouts/HeroSection.vue";
import BreweryBeerList from "../components/brewery-resources/BreweryBeerList.vue";

export default {
  components: {
    BreweryDetails,
    TheHeader,
    BreweryBeerList,
    HeroSection,
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
</style>
