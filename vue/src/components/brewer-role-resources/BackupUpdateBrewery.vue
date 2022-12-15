<template>
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
            <option value="arkansas">Arkansas</option>
            <option value="missouri">Missouri</option>
            <option value="other">Other</option>
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
        <!-- <base-button class="cancel-button" type="button" @click="cancelForm()"
          >Cancel</base-button
        > -->
      </div>
    </form>
  </div>
</template>

<script>
import breweryService from "../../services/BreweryService";
import BaseButton from "../UI/BaseButton.vue";

export default {
  components: { BaseButton },
  data() {
    return {
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
        breweryStatus: "pending",
      },
    };
  },

  // this is creating a brewery
//   created() {
//     this.brewery = this.$store.state.brewery; 
//   },

  methods: {
    cancelForm() {
      this.$router.push(`/dashboard/${this.$route.params.breweryID}`);
    },
    submitForm() {
      // const newBrewery = {
      //   breweryID: this.$route.params.breweryID,
      //   breweryName: this.breweryName,
      //   breweryType: this.breweryType,
      //   breweryStreet: this.breweryStreet,
      //   breweryCity: this.breweryCity,
      //   breweryState: this.breweryState,
      //   postalCode: this.postalCode,
      //   phoneNumber: this.phoneNumber,
      //   websiteUrl: this.websiteUrl,
      //   emailAddress: this.emailAddress,
      //   photo: this.assignBreweryPhoto(),
      //   breweryStatus: "approved",
      // };
      breweryService
        .updateBrewery(this.brewery, this.$store.state.user.id, this.$route.params.breweryID)
        .then((response) => {
          if (response.status == 200) {
            console.log("Brewery successfully updated");
              //TODO should route to dashboard
            this.$router.push(`/dashboard/${this.$route.params.breweryID}`);
          }
        })
        .catch((error) => {
          console.error(error);
          console.log("the form did not submit")
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
.form-container {
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
  background-color: transparent;
  border: 2px solid #e29569;
  color: red;
}
</style>
