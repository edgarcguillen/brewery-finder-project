<template>
  <div id="register" class="form-container">
    <!-- start of form -->
    <form class="form" @submit.prevent="register">
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

      <!-- user details -->
      <div class="form-headline">
        <header>
          <h3>Discover the best breweries</h3>
        </header>
      </div>

      <div class="user-details">
        <fieldset class="username">
          <label for="username" class="formInput">Username</label>
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
          <label for="password" class="formInput">Password</label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required
          />
        </fieldset>
        <fieldset class="password">
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

      <!-- routes to current user sign in -->
      <div class="new-account">
        <router-link to="/welcome"
          >Have an account? <strong>Login here</strong></router-link
        >
      </div>

      <!-- submit form -->
      <base-button type="submit">Log in</base-button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import BaseButton from "../components/UI/BaseButton.vue";

export default {
  components: { BaseButton },

  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      console.log(this.user);
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/welcome",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
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
