<template>
  <div class="base-card">
    <!-- Change img element below to link to DB when we have images in DB -->

    <base-modal v-if="isSuccessful">
      <h3>Successfully deleted beer</h3>
      <button class="modal-button" @click="routeToBreweryPage()">Okay</button>
    </base-modal>

    <div class="img-container"></div>
    <div class="beer-details">
      <div class="beer-details-slot">
        <h2>{{ name }}</h2>
        <h4>{{ description }}</h4>
      </div>
      <div class="beer-details-slot">
        <h5>{{ type }} {{ abv }}% ABV</h5>
      </div>
      <div class="button-container">
        <button class="reviews-button" @click="viewBeerDetails(beerID)">
          <div class="button-icon"></div>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import BaseModal from "../UI/BaseModal.vue";

export default {
  components: { BaseModal },
  data() {
    return {
      error: null,
      isSuccessful: false,
      isActive: false,
    };
  },
  props: ["beerID", "name", "description", "image", "type", "abv"],
  methods: {
    routeToBreweryPage() {
      this.$router.push(`/dashboard/${this.$route.params.breweryID}`);
    },

    viewBeerDetails(beerID) {
      this.$router.push(`/brewery/${this.$route.params.id}/${beerID}`);
    },
  },
};
</script>

<style scoped>
.base-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 400px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  padding-bottom: 30px;
  background-color: rgba(255, 255, 255, 0.767);
}

.img-container {
  height: 275px;
  width: 275px;
  background-image: url(https://lirp.cdn-website.com/9b54fca7/dms3rep/multi/opt/Seasonal+Beer+CAns-06-1920w.png);
  background-size: contain;
  background-position: center;
}

.beer-details {
  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
}

.primary {
  line-height: 40px;
  transition: ease-in-out 0.2s;
  padding: 0 16px;
}

.button-container {
  margin-top: 15px;
}

.reviews-button {
  position: relative;
  width: 50px;
  height: 50px;
  border-radius: 25px;
  border: transparent;
  background-color: #fff;
  cursor: pointer;
  box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.356);
  transition: 0.2s all;
  color: black;
}

.button-icon {
  height: 100%;
  width: 100%;
  background-image: url(https://img.icons8.com/external-soft-fill-juicy-fish/60/000000/external-review-customer-feedback-soft-fill-soft-fill-juicy-fish-3.png);
  background-size: contain;
  background-position: center;
}

button:hover {
  background-color: #e2956931;
}

</style>
