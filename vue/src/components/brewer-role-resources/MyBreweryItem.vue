<template>
  <div class="base-card">
    <div class="img-container"></div>
    <div class="brewery-details">
      <div class="brewery-details-name">
        <h2>{{ name }}</h2>
        <h3>{{ street }}</h3>
        <h3>{{ city }}, {{ state }}, {{ zip }}</h3>
      </div>
      <div class="brewery-details-slot">
        <a :href="website"><img src="" id="link-icon" />{{ website }}</a>
        <!-- <h3>{{ type }}</h3> -->
      </div>
    </div>
    <button @click="viewBreweryDetails(breweryID)" class="">See Details</button>
  </div>
</template>

<script>
export default {
  name: "my-brewery-details",
  props: [
    "breweryID",
    "name",
    "type",
    "street",
    "city",
    "state",
    "zip",
    "country",
    "phone",
    "website",
  ],
  methods: {
    viewBreweryDetails(id) {
      this.$router.push(
        `/dashboard/${this.$store.state.user.id}/brewery/${id}`
      );
    },
  },
};
</script>

<style scoped>

.base-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 500px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  padding-bottom: 30px;
  background-color: rgba(255, 255, 255, 0.767);
}

.img-container {
  height: 275px;
  width: 100%;
  background-image: url(https://images.unsplash.com/photo-1524587253133-266042ff67d0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80);
  background-size: cover;
  background-position: center;
}

.brewery-details {
  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
  justify-content: center;
}

.brewery-details-name {
  margin: 10px 0 10px 0;
}

button {
  padding: 0.75rem 1.5rem;
  background-color: #e29569be;
  background-size: cover;
  font-size: 1rem;
  box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.24);
  transition: 0.2s all;
  border: none;
  color: black;
  cursor: pointer;
  margin-top: 15px;
}

button:hover {
  background-color: #e2956931;
}
</style>
