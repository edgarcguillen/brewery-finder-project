<template>
  <div>
    <the-header></the-header>
    <div class="main-container">
      <div class="form-headline">
        <header>
          <h1>Create Account</h1>
        </header>
        <article class="article">
          <p>Are you a brewery owner or beer lover?</p>
        </article>
      </div>
        <div class="button-container">
          <!-- this displays user form -->
          <button
            @click="setSelectedTab('user-registration')"
            :class="storedResButtonMode"
          >
            <h4>I'm a beer lover</h4>
            <p>I want to be able to save my favorite breweries.</p>
          </button>
          <!-- this display brewery form -->
          <button
            @click="setSelectedTab('brewer-registration')"
            :class="addResButtonMode"
          >
            <h4>I'm a brewery owner</h4>
            <p>I want to register my brewery.</p>
          </button>
        </div>

        <keep-alive>
          <component :is="selectedTab"></component>
        </keep-alive>
    </div>
  </div>
</template>
<script>
import authService from "../services/AuthService";
import UserRegistration from "../views/UserRegistration.vue";
import BrewerRegistration from "./BrewerRegistration.vue";
import TheHeader from "../components/layouts/TheHeader.vue";

export default {
  components: { UserRegistration, BrewerRegistration, TheHeader },

  data() {
    return {
      selectedTab: "user-registration",
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
  computed: {
    storedResButtonMode() {
      return this.selectedTab === "user-registration" ? "active" : "off";
    },
    addResButtonMode() {
      return this.selectedTab === "brewer-registration" ? "active" : "off";
    },
  },
  methods: {
    setSelectedTab(tab) {
      this.selectedTab = tab;
    },
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
.main-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.form-headline {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  width: 900px;
  margin-bottom: 20px;
}

.form-headline article {
  margin-top: 5px;
}

article > p {
  font-size: 1.45em;
  color: rgb(4, 2, 124);
  margin-top: 0px;
  padding: 0 100px;
}

h1 {
  font-size: 5rem;
  text-align: center;
  margin-bottom: 10px;
}

.button-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  width: 375px;
  margin: 0 auto;
  gap: 1rem;
  align-items: center;
  justify-content: center;
}

button {
  width: 100%;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  border-radius: 10px;
  font-family: inherit;
  color: white;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

h4 {
  font-size: 1rem;
}

button p {
  text-align: left;
}

button:hover {
  background-color: #e295695b;
  border:1px solid #E29569;
}

.active {
  background-color: #e295695b;
  border:1px solid #E29569;
  color: black;

}

.off {
  background-color: transparent;
  border:1px solid #e2956954;
  color: black;
}
</style>
