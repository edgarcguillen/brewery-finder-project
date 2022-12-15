<template>
  <div class="Main">
      <h1 class="review">
        My Reviews:
      </h1>
      <ul class="allReviews">
        <review-item
        v-for="review in reviews"
        :key="review.id"
        :stars="review.stars"
        :review="review.review"
        :abv="review.abv"
        >
        </review-item>
      </ul>
  </div>
</template>

<script>

import ReviewItem from "./ReviewItem.vue"
import ReviewService from "../../services/ReviewService"

export default {

  data(){
    //isLoading: true
  },
  components: {
    ReviewItem
  },
  created(){
    this.retrieveReviews();
  },

  methods:{
    retrieveReviews(){
      ReviewService.getReviewsByUserId(this.$store.state.user.id).then((response) => {
        this.$store.commit("SET_REVIEWS", response.data);
        this.isLoading = false;
      })
    }
  }
}
</script>

<style  scoped>

.main{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.allReviews{
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  gap: 2em;
  justify-content: space-evenly;
  margin: 0px;
}

</style>