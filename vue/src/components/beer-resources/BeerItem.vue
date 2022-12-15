<template>
  <div class="base-card">
    <!-- Change img element below to link to DB when we have images in DB -->

    <div class="img-container"></div>
    <div class="beer-details">
      <div class="beer-details-slot">
        <h2>{{ name }}</h2>
        <h4>{{ description }}</h4>
      </div>
      <div class="beer-details-slot">
        <h5>{{ type }} {{ abv }}% ABV</h5>
      </div>
      <button
        @click="viewBeerDetails(id)"
      ></button>
    </div>
  </div>
</template>

<script>
// import breweryService from '../../services/BreweryService';


export default {
  data() {
    return {
      isActive: false,
    };
  },
  props: ["id", "name", "description", "image", "type", "abv"],
  methods: {
    viewBreweryDetails(breweryID) {
      this.$router.push({ name: "BreweryPage", params: { id: breweryID } });
    },
    viewBeerDetails(beerID){
      this.$router.push(`/brewery/${this.$route.params.id}/${beerID}`)
    }
  },
};
</script>

<style scoped>
.base-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 400px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  padding-bottom: 30px;
  background-color: rgba(255, 255, 255, 0.767);
}

.img-container {
  height: 275px;
  width: 275px;
  background-image: url(https://lirp.cdn-website.com/9b54fca7/dms3rep/multi/opt/Seasonal+Beer+CAns-06-1920w.png);
  background-size: contain;
  background-position: center;
}

.beer-details {
  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
}

button {
  padding: 0.75rem 1.5rem;
  background-image: url("https://img.icons8.com/external-solid-adri-ansyah/64/null/external-ui-basic-ui-solid-adri-ansyah-19.png");
  background-color: transparent;
  background-size: cover;
  box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.24);
  transition: 0.2s all;
  border-radius: 50px;
  border: none;
  height: 50px;
  width: 50px;
  color: black;
  cursor: pointer;
  margin-top: 15px;
}

button:hover,
button:active {
  border: none;
  transform: scale(0.95);
  box-shadow: 3px 2px 15px 1px rgba(0, 0, 0, 0.24);
}

.active {
  background-image: url("https://img.icons8.com/fluency/48/000000/checked.png");
  background-size: fit;
  border-radius: 50px;
  height: 50px;
  width: 50px;
  border: none;
}


</style>
