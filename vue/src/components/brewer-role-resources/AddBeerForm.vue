<template>
  <div class="main-container">
    <base-modal v-if="isSuccessful">
      <h3>Successfully added beer</h3>
      <button class="modal-button" @click="routeToBreweryPage()">Okay</button>
    </base-modal>

    <div class="form-headline">
      <header>
        <h1>Add a beer</h1>
      </header>
      <article class="article">
        <p>Add you beer details below</p>
      </article>
    </div>
    <!-- beer form begins -->
    <form @submit.prevent="submitForm()" class="form">
      <!-- <div class="status-message error" v-show="errorMsg !== ''">
        {{ errorMsg }}
      </div> -->

      <div class="brewery-container--details">
        <!-- beer name -->
        <fieldset>
          <label for="beer_name">Beer Name</label>
          <input
            type="text"
            name="beer_name"
            id="beer_name"
            v-model="beer.beerName"
            required
          />
        </fieldset>
        <!-- beer type -->
        <fieldset>
          <label for="brewery_type">Type of beer</label>
          <select
            name="brewery_type"
            id="brewery_type"
            required
            v-model="beer.beerType"
          >
            <option value>Please pick one...</option>
            <option value="Amber Ale">Amber Ale</option>
            <option value="American Brown Ale">American Brown Ale</option>
            <option value="American Pale Ale">American Pale Ale</option>
            <option value="American Wheat Beer">American Wheat Beer</option>
            <option value="Barleywine">Barleywine</option>
            <option value="Belgian Dubbel">Belgian Dubbel</option>
            <option value="English Pale Ale">English Pale Ale</option>
            <option value="India Pale Ale (IPA)">India Pale Ale (IPA)</option>
            <option value="Lager">Lager</option>
            <option value="Pilsner">Pilsner</option>
            <option value="Hefeweizen">Hefeweizen</option>
            <option value="Sour">Sour</option>
            <option value="Stout">Stout</option>
            <option value="Wheat">Wheat</option>
            <option value="Other">Other</option>
          </select>
        </fieldset>
        <!-- beer description -->
        <fieldset>
          <label for="beer_description">Description</label>
          <textarea
            name="beer_description"
            id="beer_description"
            cols="30"
            rows="5"
            placeholder="A perfect blend of haze, aroma, and juicy flavor."
            v-model="beer.beerDescription"
            required
          />
        </fieldset>
        <!-- beer abv -->
        <fieldset>
          <label for="beer_abv">ABV</label>
          <input
            type="number"
            placeholder="8.0%"
            step="0.1"
            min="0"
            max="10"
            v-model="beer.abv"
            required
          />
        </fieldset>
        <!-- beer image -->
        <fieldset>
          <label for="beer_url">Image URL</label>
          <input
            type="text"
            name="beer_url"
            id="beer_url"
            v-model="beer.beerImage"
          />
        </fieldset>
      </div>
      <div class="buttons-container">
        <base-button class="submit-button">Add beer</base-button>
        <router-link :to="{ name: 'MyBrewery' }" class="cancel-button"
          ><p>CANCEL</p></router-link
        >
      </div>
    </form>
  </div>
</template>

<script>
import beerService from "../../services/BeerService";
import BaseButton from "../UI/BaseButton.vue";
import BaseModal from "../UI/BaseModal.vue";

export default {
  components: { BaseButton, BaseModal },
  data() {
    return {
      isSuccessful: false,

      // creating a beer object
      beer: {
        beerName: "",
        beerDescription: "",
        beerImage: "",
        beerType: "",
        abv: "",
      },
      errorMsg: "",
    };
  },

  methods: {
    routeToBreweryPage() {
      this.$router.push(
        `/dashboard/${this.$route.params.brewerID}/brewery/${this.$route.params.breweryID}`
      );
      window.location.reload();
    },
    submitForm() {
      beerService
        .createBeer(this.beer, this.$route.params.breweryID)
        .then((response) => {
          if (response.status >= 200 && response.status < 300) {
            console.log("Beer was added");
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
.main-container {
  width: 700px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.form-headline {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
}

header > h1 {
  font-size: 2.75em;
  margin-bottom: 0px;
  color: rgb(10, 10, 10);
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

.submit-button {
  background-color: #e29569;
}

button:focus span {
  opacity: 1;
  transition: 0.3s;
}

.buttons-container {
  display: flex;
  gap: 2rem;
}

.cancel-button {
  display: flex;
  flex-direction: d;
  padding: 0.75rem 1.5rem;
  border: 2px solid #e29569;
  color: red;
  border-radius: 10px;
  font-size: 1.25rem;
  font-weight: bold;
  color: white;
  cursor: pointer;
  text-align: center;
  align-items: center;
  text-decoration: none;
}

button {
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

.cancel-button:hover,
.cancel-button:active {
  box-shadow: 0 0.5em 0.5em -0.7em;
  background-color: #af975b41;
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
  color: black;
  border: 2px solid rgb(122, 231, 50);
}

button:hover.modal-button {
  background-color: rgb(208, 255, 0);
  transform: scale(1.2);
  box-shadow: 0 0 4px #111;
  transition: 0.3s;
}
</style>
