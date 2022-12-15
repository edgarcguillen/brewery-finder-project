<template>
  <div id="login" class="form-container">
    <div class="form-headline">
      <header>
        <h1>Welcome back</h1>
      </header>
      <article class="article">
        <p>Discover the best breweries.</p>
      </article>
    </div>
    <!-- start of form -->
    <form class="form" @submit.prevent="login">
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
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
      <base-button type="submit">Log in</base-button>
      <div class="new-account">
        <router-link :to="{ name: 'register' }"
          >Don't have an account? <strong>Sign up for free</strong></router-link
        >
      </div>
      <div class="new-account">
        <router-link :to="{ name: 'home' }"
          >Or <strong>continue as guest</strong></router-link
        >
      </div>
    </form>
  </div>
</template>

<script>
import BaseButton from "../components/UI/BaseButton.vue";
import authService from "../services/AuthService";

export default {
  name: "login",
  components: { BaseButton },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
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
            //added this line for the header
            this.$store.state.isAnonUser = false;

            // if user role is USER route DASHBOARD 
            if (this.$store.state.user.authorities[0].name === "ROLE_USER") {
              this.$router.push("/");
            }
            if (this.$store.state.user.authorities[0].name === "ROLE_BREWER") {
              this.$router.push(`/dashboard/${this.$store.state.user.id}`);
            }
            //if user role is ADMIN route ADMIN DASHBOARD 
            if(this.$store.state.user.authorities[0].name === "ROLE_ADMIN"){
              this.$router.push(`/admin`);
            }
            // else route WELCOME PAGE
            else {
              this.$router.push("/");
            }
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.form-container {
  min-width: 500px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.form-headline {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  width: 500px;
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
  color: rgb(255, 255, 255);
}

.form {
  /* background-color: #f6e7a2; */
  border-radius: 20px 0 0 20px;
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
  font-size: 1.25rem;
  font-weight: bold;
  margin-bottom: 8px;
  align-items: center;
}

fieldset input {
  font-size: 1rem;
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
</style>
