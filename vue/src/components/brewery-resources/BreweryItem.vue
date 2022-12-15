<template>
  <header>
    <div class="base-card">
      <!-- Change img element below to link to DB when we have images in DB -->
      <div class="card-slot">
        <div v-if="state === 'Arkansas'">
          <div class="card-slot" id="ark-img"></div>
        </div>
        <div v-if="state === 'Missouri'">
          <div class="card-slot" id="missouri-img"></div>
        </div>
        <div v-if="state != 'Missouri'&& state != 'Arkansas' ">
          <div class="card-slot" id="card-img"></div>
        </div>

      </div>
      <div class="card-slot" id="name-slot">
        <h4>{{ name }}</h4>
      </div>
      <div class="card-slot" id="type-slot">
        <h5>Type of Brewery: {{ type }}</h5>
      </div>
      <div class="card-slot" id="address-block">
        <div class="address-slot" id="street-slot">
          <h6>{{ street }}</h6>
        </div>
        <div class="address-slot" id="cityState-slot">
          <h6>{{ city }}, {{ state }}</h6>
        </div>
        <div class="address-slot" id="zip-slot">
          <h6>{{ zip }}</h6>
        </div>
      </div>

    <!-- brewery details container -->
      <div class="card-slot" id="info-section">

        <div class="add-favorite" v-if="isUser"> 
          <button type="favorite" v-on:click="addBreweryToFavorites(id)" id="fav-button">
            <!-- Bring in method to determine if brewery is in favorites -->

            <img src="../../assets/Heart-Liked.png" id="heart-icon" v-if="checkIfFavorite(id)"/>
            <img src="../../assets/Heart-NotLiked.png" id="heart-icon" v-else/>
          </button>
        </div>

        <div id="website-slot">
          <a :href="website" target="_blank">
            <img src="../../assets/www-link-icon.png" id="link-icon"/>
          </a>
        </div>
        
        <button @click="viewBreweryDetails()">
          <!-- when clicked routes to brewery details cmp -->
          Beer list
        </button>
      </div>
    </div>
  </header>
</template>

<script>
import breweryService from "../../services/BreweryService";

export default {
  props: [
    "id",
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
  created() {
    this.retrieveBreweries();
  },
  methods: {
    viewBreweryDetails() {
      this.$router.push({ name: "BreweryPage", params: { id: this.id } });
    },
    addBreweryToFavorites(id) {
      if (this.$store.state.favoriteBreweries.some(brewery => brewery.breweryID === id)) {
        breweryService.removeBreweryFromFavorites(
        this.$store.state.user.id, id)
        this.isFavorited = true;
      } else {
        breweryService.addBreweryToFavorites(
        this.$store.state.user.id, id);
      }
      location.reload();
     
    },
    checkIfFavorite(id) { 
      if (this.$store.state.favoriteBreweries.some(brewery => brewery.breweryID === id)) {
              return true;
        } else {
              return false;
        }
    },
    retrieveBreweries() {
      if(this.$store.state.user.id >= 0) {
        breweryService.getFavorites(this.$store.state.user.id).then((response) => {
        this.$store.commit("SET_FAVORITE_BREWERIES", response.data);
      });
      }
    },
  },
  computed: {
    isUser(){
      if (this.$store.state.token === "") {
        return false;
      } else if (this.$store.state.user.authorities[0].name === 'ROLE_USER'){
        return true;
      } else if (this.$store.state.user.authorities[0].name === 'ROLE_BREWER') {
        return false;
      } else if (this.$store.state.user.authorities[0].name === 'ROLE_ADMIN') {
        return false;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
.base-card {
  display: flex;
  flex-direction: column;
  width: 300px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  background-color: rgba(255, 255, 255, 0.767);
}

#name-slot {
  width: 100%;
  font-size: 1rem;
  display: flex;
  align-items: center; /* Vertical center alignment */
  padding-top: 10px;
  padding-left: 10px;
  height: 2rem;
}

#type-slot {
  height: 30px;
  font-size: 10px;
  align-items: center; /* Vertical center alignment */
  padding-left: 10px;
}

#card-img {
  height: 250px;
  width: 100%;
  background-image: url(https://images.unsplash.com/photo-1524587253133-266042ff67d0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80);
  background-size: cover;
}

.address-slot {
  height: 15px;
  font-size: 18px;
  display: flex;
  align-items: center; /* Vertical center alignment */
  padding-left: 10px;
}

#website-slot {
  height: 30px;
  font-size: 10px;
  display: flex;
  align-items: center; /* Vertical center alignment */
  padding-left: 10px;
}

button {
  background-color: #e29569; /* Green */
  border: none;
  color: white;
  padding: 5px;
  text-align: center;
  font-size: 12px;
  margin: 5px;
  margin-bottom: 4px;
}

#info-section {
  display: flex;
  flex-direction: row;
  justify-content: right;
  align-items: center;
}

img#link-icon {
  width: 20px;
  height: 20px;
}

img#heart-icon {
  width: 40px;
  height: 40px;
}

.add-favorite {
  display: flex;
  align-content: center;
  margin-bottom: 0px;
  margin-right: -8px;
}

#fav-button{
  background: none;
}

#ark-img {
  height: 250px;
  width: 100%;
  background-image: url(https://pipandthecity.com/wp-content/uploads/2019/01/bigstock-Little-Rock-Arkansas-USA-sky-262314112.jpg);
  background-size: cover;
}

#missouri-img {
  height: 250px;
  width: 100%;
  background-image: url(https://i.natgeofe.com/k/42ce0ecf-743b-4a6d-a6ff-76273c01195b/united-states-st-louis-arch.jpg);
  background-size: cover;
}

</style>
