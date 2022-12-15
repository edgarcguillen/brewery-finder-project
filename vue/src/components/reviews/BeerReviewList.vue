<template>
  <div class="main-container">
    <ul class="reviews-display">
      <beer-review-item
        v-for="review in this.$store.state.reviews"
        :key="review.id"
        :stars="review.stars"
        :review="review.review"
      >
      </beer-review-item>
    </ul>
  </div>
</template>

<script>
import BeerReviewItem from "./BeerReviewItem.vue";
import ReviewService from "../../services/ReviewService";

export default {
  data() {
    return {
      beerID: this.$route.params.beerID,
    };
  },

  components: { BeerReviewItem },

  created() {
    this.beerID = this.$route.params.beerID;
    this.retrieveReviews();
  },

  methods: {
    retrieveReviews() {
      ReviewService.getReviews(this.beerID).then((response) =>
        this.$store.commit("SET_REVIEWS", response.data)
      );
    },
  },
};
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
  justify-content: center;
  margin: 50px 0 100px 0;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
  margin: auto;
  max-width: 40rem;
  
}

.reviews-display {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: space-evenly;
  
}
</style>
