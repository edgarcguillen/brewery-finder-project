<template>
  <div class="main-container">
    <ul class="brewery-display">
      <review-item v-for="review in this.$store.state.userReviews" 
      v-bind:key="review.review_id"
      :name="review.beerName"
      :type="review.beerType"
      :review="review.review"
      :description="review.beerDescription"
      :stars="review.stars"
      :img="review.beerIMG"
      :abv="review.abv"
      :brewery="review.breweryName"
      >
      </review-item>
    </ul>
  </div>
</template>

<script>
import ReviewItem from './ReviewItem.vue';

export default {
  components: {
    ReviewItem
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
  padding-inline-start: 0px;

}


ul.brewery-display {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-evenly;
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

