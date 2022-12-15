<template v-if="this.$store.state.user.authorities[0].name === 'ROLE_USER'">
  <header class="main-header">
    <div main-header__logo>
      <router-link to="/">
        <img src="../../assets/hop.png" />
      </router-link>
    </div>
    <!--- If not logged in --->
    <nav class="main-nav" v-if="this.$store.state.token === ''">
      <nav-tabs class="main-nav__item">
        <router-link to="/register">Get Started</router-link>
      </nav-tabs>
      <nav-tabs class="main-nav__item">
        <router-link to="/welcome">Log in</router-link>
      </nav-tabs>
    </nav>
    <!--- If logged in as brewer --->
    <nav class="main-nav" v-else-if="this.$store.state.user.authorities[0].name === 'ROLE_BREWER'"> 
      <nav-tabs class="main-nav__item">
        <router-link v-bind:to= "{name: 'BrewerDashboard', params: { brewerID: this.$store.state.user.id}}">Dashboard</router-link>
      </nav-tabs>
      <nav-tabs class="main-nav__item">
        <router-link to="/logout">Log out</router-link>
      </nav-tabs>
    </nav>
    <!--- If logged in as admin --->
    <nav class="main-nav" v-else-if="this.$store.state.user.authorities[0].name === 'ROLE_ADMIN'"> 
      <nav-tabs class="main-nav__item">
        <router-link to="/admin">Dashboard</router-link>
      </nav-tabs>
      <nav-tabs class="main-nav__item">
        <router-link to="/logout">Log out</router-link>
      </nav-tabs>
    </nav>
    <!--- If logged in as user --->
    <nav class="main-nav" v-else-if="this.$store.state.user.authorities[0].name === 'ROLE_USER'"> 
      <nav-tabs class="main-nav__item">
        <router-link v-bind:to= "{name: 'UserDashboard', params: { userID: this.$store.state.user.id}}">Dashboard</router-link>
      </nav-tabs>
      <nav-tabs class="main-nav__item">
        <router-link to="/logout">Log out</router-link>
      </nav-tabs>
    </nav>
    
  </header>
</template>

<script>
import NavTabs from "../UI/NavTabs.vue";

export default {
  components: { NavTabs },
};
</script>

<style scoped>
/* these styles are scoped so only applied to this component */

.main-header {
  width: 100%;
  height: 5rem;
  background-color: #ffffffcc;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2px solid rgba(0, 0, 0, 0.329);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
}

.main-nav {
  display: flex;
  margin-right: 30px;
}

.main-header a {
  text-decoration: none;
}

.main-header > div {
  margin-left: 30px;
}

.main-header > div a {
  font-size: 2em;
  font-weight: bold;
  color: #433e3f;
}

.main-nav .main-nav__item {
  max-width: 135px;
  border-radius: 10px;
  text-decoration: none;
  margin: 10px;
  font-weight: bold;
}

.main-header__logo {
  display: flex;
}
img {
  max-height: 50px;
}
</style>
