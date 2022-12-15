<template>
  <!-- takes a brewery detail cmp -->
  <div>
    <the-header></the-header>
    <hero-section>
      <my-brewery-details></my-brewery-details>
    </hero-section>
    <div class="main-container">
      <my-beer-list :breweryID="this.breweryID"></my-beer-list>
    </div>
  </div>
</template>

<script>
// import MyBreweryDetails from "../components/brewer-role-resources/MyBreweryDetails.vue";
import TheHeader from "../components/layouts/TheHeader.vue";
import MyBeerList from "../components/brewer-role-resources/MyBeerList.vue";
import HeroSection from "../components/layouts/HeroSection.vue";
import MyBreweryDetails from "../components/brewer-role-resources/MyBreweryDetails.vue";

export default {
  components: {
    MyBreweryDetails,
    TheHeader,
    MyBeerList,
    HeroSection,
  },
  data() {
    return {
      breweryID: 0,
    };
  },
  created() {
    this.breweryID = this.$route.params.breweryID;
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
