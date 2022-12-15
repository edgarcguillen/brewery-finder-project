<template>
  <div class="main" v-if="isUser">
    <div class="show-button" v-if="givingReview == false">
      <button @click="showForm">Review the beer?</button>
    </div>
    <div class="form" v-if="givingReview">
      <form @submit.prevent="submitForm()">
        <div class="review">
          <fieldset>
            <label for="review">Write your review!</label>
            <textarea
              class="review-box"
              type="textarea"
              name="review"
              id="review"
              v-model="review.review"
              required
            ></textarea>
          </fieldset>
          <fieldset>
            <label for="stars">How would you rate the beer?</label>
            <input
              type="number"
              placeholder="5"
              step="1"
              min="1"
              max="5"
              v-model="review.stars"
              required
            />
          </fieldset>
        </div>
        <div class="buttons">
          <base-button class="submit"> Add Review</base-button>

          <button type="button" @click="clearForm()" class="cancel">
            Cancel
          </button>
          <!-- <base-button class="cancel" @click="clearForm">Cancel</base-button> -->
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import BaseButton from "../UI/BaseButton.vue";
import ReviewService from "../../services/ReviewService";
export default {
  components: { BaseButton },
  data() {
    return {
      givingReview: false,

      review: {
        review: "",
        stars: "",
        userID: this.$store.state.user.id,
        beerID: this.$route.params.beerID,
      },
    };
  },

  methods: {
    showForm() {
      this.givingReview = true;
    },
    clearForm() {
      this.givingReview = false;
      this.review = "";
      this.stars = "";
    },
    submitForm() {
      console.log(this.review);
      ReviewService.createReview(this.review)
        .then((this.givingReview = false))
        .catch((error) => {
          console.log("add review failed");
          console.error(error);
        });
      window.location.reload();
    },
  },
  computed: {
    isUser() {
      if (this.$store.state.token === "") {
        return false;
      } else if (this.$store.state.user.authorities[0].name === "ROLE_BREWER") {
        return false;
      } else return true;
    },
  },
};
</script>

<style scoped>
.main {
  width: 700px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
}

.form {
  background-color: rgba(6, 41, 30, 0.281);
  border-radius: 20px;
  width: 700px;
  display: flex;
  flex-direction: column;
  gap: 1.8em;
  padding: 40px;
  text-align: left;
  margin-top: 10px;
}

.form fieldset {
  display: flex;
  flex-direction: column;
  border: none;
}

fieldset label {
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 8px;
  align-items: center;
}

fieldset input,
fieldset select,
textarea {
  font-size: 1.15rem;
  padding: 5px 0 5px 10px;
  border-radius: 5px;
  border: none;
  align-items: center;
}

.buttons-container {
  display: flex;
  gap: 2rem;
}

.submit {
  background-color: #e29569;
}
.cancel {
  background-color: transparent;
  border: 2px solid #e29569;
  color: red;
  padding: 0.75rem 1.5rem;
  font-family: inherit;
  background-color: #dabb6f;
  border: none;
  border-radius: 10px;
  font-size: 1.25rem;
  font-weight: bold;
  color: white;
  cursor: pointer;
}

.review-box {
  height: 80px;
  font-size: 0.9em;
}

button {
  width: 300px;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  margin-bottom: 50px;
  font-size: 1rem;
  font-weight: bold;
  background-color: #f6a000;
  background-size: cover;
  box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.24);
  transition: 0.2s all;
    border-radius: 10px;

  border: none;
  color: black;
  cursor: pointer;
  margin-top: 15px;
}

button:hover {
  background-color: #e29569b0;
}

h2 {
  font-size: 1.5rem;
}
</style>