<template>
  <div class="base-card">
    <base-modal v-if="isSuccessful">
      <h3>Successfully approved brewery</h3>
      <button class="modal-button" @click="reloadPage()">Okay</button>
    </base-modal>

    <!-- Change img element below to link to DB when we have images in DB -->
    <div class="img-container"></div>
    <div class="brewery-details">
      <div class="brewery-details-slot">
        <h2>{{ name }}</h2>
        <h3>{{ street }}</h3>
        <h3>{{ city }}, {{ state }}, {{ zip }}</h3>
      </div>
      <div class="brewery-details-slot">
        <h3>{{ type }}</h3>
        <a :href="website"><img src="" id="link-icon" />{{ website }}</a>
      </div>
    </div>
    <button @click="approveBrewery()" class="approve-button">
      <!-- when clicked routes to brewery details cmp -->
      Approve Brewery
    </button>
  </div>
</template>

<script>
import BreweryService from "../../services/BreweryService.js";
import BaseModal from "../UI/BaseModal.vue";

export default {
  components: { BaseModal },
  // handleErrorResponse: "error",
  data() {
    return {
      isSuccessful: false,
      brewery: { breweryID: this.breweryID },
    };
  },
  props: [
    "breweryID",
    "name",
    "type",
    "street",
    "city",
    "state",
    "zip",
    "country",
    "phone",
    "website",
    "showPendingBrewery",
  ],
  methods: {
    reloadPage() {
      window.location.reload();
    },
    approveBrewery() {
      BreweryService.approveTheBrewery(this.breweryID)
        .then((response) => {
          if (response.status >= 200 && response.status < 300) {
            console.log("Brewery was approved");
            this.isSuccessful = true;
          }
        })
        .catch((error) => {
          console.log("add beer FAILED");
          console.error(error);
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
  width: 500px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  padding-bottom: 30px;
  background-color: rgba(255, 255, 255, 0.767);
}

.img-container {
  height: 275px;
  width: 100%;
  background-image: url(https://images.unsplash.com/photo-1524587253133-266042ff67d0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80);
  background-size: cover;
  background-position: center;
}

.brewery-details {
  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
  justify-content: center;
}

.brewery-details-slot {
  margin: 10px 0 10px 0;
}

button {
  padding: 0.75rem 1.5rem;
  background-color: #e29569be;
  background-size: cover;
  font-size: 1rem;
  box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.24);
  transition: 0.2s all;
  border: none;
  color: black;
  cursor: pointer;
  margin-top: 15px;
}

button:hover {
  background-color: #e2956931;
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
  color: black;
  font-size: 1.25rem;
}

button:hover.modal-button {
  background-color: rgb(208, 255, 0);
  transform: scale(1.2);
  box-shadow: 0 0 4px #111;
  transition: 0.3s;
}
</style>
