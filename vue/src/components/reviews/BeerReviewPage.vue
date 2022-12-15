<template>
  <div>
    <the-header></the-header>
    <hero-section>
      <h1>Beer Reviews</h1>
    </hero-section>
    <add-beer-review></add-beer-review>
    <beer-review-list></beer-review-list>
  </div>
</template>

<script>
import TheHeader from "../layouts/TheHeader.vue";
import AddBeerReview from "./AddBeerReview.vue";
import BeerReviewList from "./BeerReviewList.vue";
import HeroSection from "../layouts/HeroSection.vue";

export default {
  components: { TheHeader, BeerReviewList, AddBeerReview, HeroSection },
};
</script>

<style scoped>
h1 {
  font-size: 5rem;
  margin: 100px;
}
</style>
