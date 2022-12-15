<template>
  <div class="main-container">
    <base-modal v-if="isSuccessful">
      <h3>Successfully added brewery</h3>
      <button class="modal-button" @click="routeToBreweryPage()">Okay</button>
    </base-modal>

    <div id="register" class="form-container">
      <!-- start of form -->
      <form @submit.prevent="submitForm()" class="form">
        <!-- <div class="status-message error" v-show="errorMsg !== ''">
        {{ errorMsg }}
      </div> -->

        <!-- start of brewery details (all required??) -->
        <div class="form-headline">
          <header>
            <h3>Please enter your brewery details.</h3>
          </header>
          <article class="article"></article>
        </div>
        <div class="brewery-container--details">
          <fieldset>
            <label for="breweryName">Brewery Name</label>
            <input
              type="text"
              name="breweryName"
              id="breweryName"
              v-model="brewery.breweryName"
            />
          </fieldset>
          <fieldset>
            <label for="brewery_type">Type of brewery</label>
            <select
              name="brewery_type"
              id="brewery_type"
              required
              v-model="brewery.breweryType"
            >
              <option value>Please pick one...</option>
              <option value="microbrewery">Microbrewery</option>
              <option value="brewpubs">Brewpubs</option>
              <option value="taproom ">Taproom</option>
              <option value="regional ">Regional</option>
              <option value="contract  ">Contract</option>
              <option value="other">Other</option>
            </select>
          </fieldset>
          <fieldset>
            <label for="brewery_street">Street</label>
            <input
              type="text"
              name="brewery_street"
              id="brewery_street"
              v-model="brewery.breweryStreet"
            />
          </fieldset>
          <fieldset>
            <label for="brewery_city">City</label>
            <input
              type="text"
              name="brewery_city"
              id="brewery_city"
              v-model="brewery.breweryCity"
            />
          </fieldset>
          <fieldset>
            <label for="brewery_state">State</label>
            <select
              name="brewery_state"
              id="brewery_state"
              required
              v-model="brewery.breweryState"
            >
              <option value>Please pick one...</option>
              <option value="Arkansas">Arkansas</option>
              <option value="Missouri">Missouri</option>
              <option value="Other">Other</option>
            </select>
          </fieldset>
          <fieldset>
            <label for="postal_code">Zip Code</label>
            <input
              type="text"
              name="postal_code"
              id="postal_code"
              v-model="brewery.postalCode"
            />
          </fieldset>
          <fieldset>
            <label for="phone_number">Phone Number</label>
            <input
              type="text"
              name="phone_number"
              id="phone_number"
              v-model="brewery.phoneNumber"
            />
          </fieldset>
          <fieldset>
            <label for="website_url">Website</label>
            <input
              type="text"
              name="website_url"
              id="website_url"
              v-model="brewery.websiteUrl"
            />
          </fieldset>
          <fieldset>
            <label for="contact_email">Email</label>
            <input
              type="text"
              name="contact_email"
              id="contact_email"
              v-model="brewery.emailAddress"
            />
          </fieldset>
        </div>

        <!-- submit form -->
        <div class="buttons-container">
          <base-button class="submit-button">Add brewery</base-button>
          <router-link :to="{name: 'BrewerDashboard'}" class="cancel-button"><p>CANCEL</p></router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import breweryService from "../../services/BreweryService";
import BaseButton from "../UI/BaseButton.vue";
import BaseModal from "../UI/BaseModal.vue";

export default {
  components: { BaseButton, BaseModal },
  data() {
    return {
      isSuccessful: false,
      brewery: {
        breweryName: "",
        breweryType: "",
        breweryStreet: "",
        breweryCity: "",
        breweryState: "",
        postalCode: "",
        phoneNumber: "",
        websiteUrl: "",
        emailAddress: "",
        photo: "",
        status: "pending",
      },
    };
  },

  methods: {
    routeToBreweryPage() {
      this.$router.push(`/dashboard/${this.$route.params.breweryID}`);
    },
    submitForm() {
      this.assignBreweryPhoto();
      // add brewery
      breweryService
        .createBrewery(this.brewery, this.$store.state.user.id)
        .then((response) => {
          if (response.status == 200) {
            this.isSuccessful = true;
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // assigns photo to brewery
    assignBreweryPhoto() {
      if (this.brewery.breweryState === "Arkansas") {
        this.brewery.photo =
          "https://slack-imgs.com/?c=1&o1=ro&url=https%3A%2F%2Fpipandthecity.com%2Fwp-content%2Fuploads%2F2019%2F01%2Fbigstock-Little-Rock-Arkansas-USA-sky-262314112.jpg";
      } else {
        this.brewery.photo =
          "https://slack-imgs.com/?c=1&o1=ro&url=https%3A%2F%2Fi.natgeofe.com%2Fk%2F42ce0ecf-743b-4a6d-a6ff-76273c01195b%2Funited-states-st-louis-arch.jpg";
      }
    },
  },

  // handles errors
  handleErrorResponse(error, verb) {
    if (error.response) {
      this.errorMsg =
        "Error " + verb + " brewery. Request could not be created";
    }
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

.form-container {
  height: 100vh;
  width: 700px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
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

.form-headline {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
}

.form-headline article {
  margin-top: 5px;
}

article > p {
  font-size: 1.15em;
  color: rgb(4, 2, 124);
  margin-top: 0px;
  padding: 0 100px;
}

header > h3 {
  margin-bottom: 0px;
  color: rgb(10, 10, 10);
  font-size: 1.5rem;
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
fieldset select {
  font-size: 1.15rem;
  padding: 5px 0 5px 10px;
  border-radius: 5px;
  border: none;
  align-items: center;
}

fieldset span {
  font-size: 0.65em;
  background-color: tomato;
  padding: 1px 8px;
  border: 1px dotted black;
  border-radius: 20px;
  font-weight: bold;
}

fieldset p {
  margin: 0 0 10px 0;
}

fieldset.username,
fieldset.password {
  font-size: 1.15em;
  padding: 5px 10px;
}

.new-account {
  text-align: center;
}

a {
  text-decoration: none;
}

.buttons-container {
  display: flex;
  gap: 2rem;
}

.submit-button {
  background-color: #e29569;
}

.cancel-button {
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
