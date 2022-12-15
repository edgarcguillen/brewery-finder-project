<template>
  <div>
    <the-header></the-header>

    <div class="main-container">
      <h1>My Admin Dashboard</h1>
      <div v-if="listIsEmpty">Brevieweries to approve!</div>
      <div class="pending-breweries-list">
        <pending-brewery-list></pending-brewery-list>
      </div>
    </div>
  </div>
</template>

<script>
import TheHeader from "../components/layouts/TheHeader.vue";
import PendingBreweryList from "../components/admin-role-resources/PendingBreweryList.vue";

export default {
  data() {
    return {
      godMode: false,
    };
  },
  components: { TheHeader, PendingBreweryList },
  methods: {
    activateGodMode() {
      this.godMode = !this.godMode;
    },
  },
  created() {
    this.$store.commit("SET_ACTIVE_BREWER_DASHBOARD", this.brewerID);
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

header {
  margin-bottom: 100px;
}

.brewery-details {
  margin-bottom: 100px;
}

button:hover {
  background-color: #e295695b;
  border: 1px solid #e29569;
}

button {
  width: 300px;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  font-size: 1rem;
  font-weight: bold;
  background-color: #f6a000;
  background-size: cover;
  box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.24);
  transition: 0.2s all;
  border-radius: 10px;
  border: none;
  color: black;
  cursor: pointer;
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
