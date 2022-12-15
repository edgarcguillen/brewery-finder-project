<template>
  <div>
    <the-header></the-header>
    <brewer-dashboard-hero></brewer-dashboard-hero>
    <my-brewery-list :brewerID="this.brewerID"></my-brewery-list>
  </div>
</template>

<script>
import MyBreweryList from '../components/brewer-role-resources/MyBreweryList.vue';
import TheHeader from "../components/layouts/TheHeader.vue";
import BrewerDashboardHero from "../components/layouts/BrewerDashboardHero.vue";

export default {
  components: { TheHeader, MyBreweryList, BrewerDashboardHero },
  data() {
    return {
      brewerID: this.$store.state.user.id,
    };
  },
  created() {
    this.brewerID = this.$store.state.user.id;
    this.$store.commit("SET_ACTIVE_BREWER_DASHBOARD", this.brewerID);
    console.log(this.$store.state.user.id);
  },
};
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-bottom: 100px;
}

.brewery-details {
  margin-bottom: 100px;
}

.button-container {
  padding: 20px;
  display: flex;
  flex-direction: row;
  margin: 0 auto;
  gap: 1rem;
  align-items: center;
  justify-content: center;
}

button {
  width: 300px;
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

button p {
  text-align: left;
}

button:hover {
  background-color: #e295695b;
  border: 1px solid #e29569;
}

.active {
  background-color: #e295695b;
  border: 1px solid #e29569;
  color: black;
}

.off {
  background-color: transparent;
  border: 1px solid #e2956954;
  color: black;
}

h1 {
  font-size: 5rem;
  text-align: center;
  margin-bottom: 50px;
}

h3 {
  font-size: 3rem;
  text-align: center;
  margin-bottom: 50px;
}
</style>
