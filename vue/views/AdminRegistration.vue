<template>
  <div id="register" class="form-container">
    <div class="form-headline">
      <header>
        <h1>Brewer Account</h1>
      </header>
      <article class="article">
        <p>Discover the best breweries.</p>
      </article>
    </div>

    <!-- start of form -->
    <form class="form" @submit.prevent="register()">
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

      <!-- user details -->
      <div class="form-headline">
        <header>
          <h2>Your Details</h2>
        </header>
        <article class="article">
          <p>Please create a username and password.</p>
        </article>
      </div>
      <div class="user-details">
        <fieldset class="username">
          <label for="username" class="sr-only">Username</label>
          <input
            type="text"
            id="username"
            class="form-control"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
          />
        </fieldset>
        <fieldset class="password">
          <label for="password" class="sr-only">Password</label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required
          />
        </fieldset>
        <fieldset class="confirm-password">
          <input
            type="password"
            id="confirmPassword"
            class="form-control"
            placeholder="Confirm Password"
            v-model="user.confirmPassword"
            required
          />
        </fieldset>
      </div>

      <!-- start of brewery details (all required??) -->
      <div class="brewery-container">
        <div class="form-headline">
          <header>
            <h2>Brewery Details</h2>
          </header>
          <article class="article">
            <p>Please enter your brewery details.</p>
          </article>
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
              <option value="brewpubs">Arkansas</option>
              <option value="microbrewery">Missouri</option>
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
      </div>

      <!-- routes to current user sign in -->
      <div class="new-account">
        <router-link to="/welcome"
          >Have an account? <strong>Login here</strong></router-link
        >
      </div>
      <base-button type="submit">Create Account</base-button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import breweryService from "../services/BreweryService";
import BaseButton from "../components/UI/BaseButton.vue";

export default {
  components: { BaseButton },
  data() {
    return {
      loginSuccessful: false,
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "brewer",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
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
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.assignBreweryPhoto();
            this.registerBrewery();
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    // register() {
    //   if (this.user.password != this.user.confirmPassword) {
    //     this.registrationErrors = true;
    //     this.registrationErrorMsg = "Password & Confirm Password do not match.";
    //   } else {
    //     authService
    //       .register(this.user)
    //       .then((response) => {
    //         if (response.status == 201) {
    //           this.login();
    //           // if(this.loginSuccessful){
    //           //   this.assignBreweryPhoto();
    //           //    this.registerBrewery();
    //           // }
    //         }
    //       })
    //       .catch((error) => {
    //         const response = error.response;
    //         this.registrationErrors = true;
    //         console.log(response.status + "hi");
    //         if (response.status === 400) {
    //           this.registrationErrorMsg = "Bad Request: Validation Errors";
    //         }
    //       });
    //   }
    // },
    registerBrewery() {
      this.assignBreweryPhoto;
      breweryService.createBrewery(this.brewery).then((response) => {
        if (response.status == 201) {
          this.$router.push({
            path: "/dashboard",
          });
        }
      });
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
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

header > h1 {
  font-size: 2.75em;
  margin-bottom: 0px;
  color: rgb(10, 10, 10);
}

header > h2 {
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

.brewery-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.brewery-container--details {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
}

.new-account {
  text-align: center;
}
</style>
