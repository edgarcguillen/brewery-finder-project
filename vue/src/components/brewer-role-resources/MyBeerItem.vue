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
        <button class="delete-button" @click="deleteBeer(beerID)">
          <span>CONFIRM DELETE</span>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="25"
            height="25"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            stroke-width="2"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
            />
          </svg>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import BeerService from "../../services/BeerService";
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
      this.$router.push(
        `/dashboard/${this.$route.params.brewerID}/brewery/${this.$route.params.breweryID}`
      );
      window.location.reload();
    },
    deleteBeer(id) {
      BeerService.deleteBeer(id)
        .then((response) => {
          if (response.status === 200) {
            this.isSuccessful = true;
          }
        })
        .catch((error) => {
          if (error.response) {
            console.log("Beer deletion failed");
          }
        });
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

button {
  position: relative;
  width: 50px;
  height: 50px;
  border-radius: 25px;
  border: 2px solid rgb(231, 50, 50);
  background-color: #fff;
  cursor: pointer;
  box-shadow: 0 0 10px #333;
  overflow: hidden;
  transition: 0.3s;
}

button.modal-button {
  width: 200px;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  background-color: transparent;
  border-radius: 10px;
  font-family: inherit;
  text-align: center;
  cursor: pointer;
  box-shadow: 0 0 10px #333;
  overflow: hidden;
  transition: 0.3s;
    border: 2px solid rgb(122, 231, 50);
}

button:hover.modal-button {
 background-color: rgb(208, 255, 0);
  transform: scale(1.2);
  box-shadow: 0 0 4px #111;
  transition: 0.3s;
}

button:hover {
  background-color: rgb(245, 207, 207);
  transform: scale(1.2);
  box-shadow: 0 0 4px #111;
  transition: 0.3s;
}

svg {
  color: rgb(231, 50, 50);
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  transition: 0.3s;
}

button:hover svg {
  opacity: 0;
  transition: 0.3s;
  color: black;
}

span {
  width: 150px;
  position: absolute;
  transform: translate(-50%, -50%);
  color: white;
  font-weight: 600;
  transition: 0.3s;
}

button:hover {
  width: 150px;
  height: 50px;
  transition: 0.3s;
}

button:focus span {
  opacity: 1;
  transition: 0.3s;
}

button.modal-button {
  width: 200px;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  background-color: transparent;
  border-radius: 10px;
  font-family: inherit;
  text-align: center;
  cursor: pointer;
}

</style>
