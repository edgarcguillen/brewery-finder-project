<template>
  <div id="app">
    <!--<div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>-->
    <router-view>
     
    </router-view>
  </div>
</template>

<script>
export default {
  destroyed() {}
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;800&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap");

* {
  box-sizing: border-box;
  margin: 0;
}

body {
  background-color: #fffff0;
}



html {
  font-family: "Poppins", sans-serif;
  color: #2c3e50;
  font-size: 16px;
}

h1 {
  font-family: "Poppins", sans-serif;
  line-height: 4.25rem;
}

h2 {
  font-family: "Alfa Slab One", cursive;
  line-height: 2rem;
}

h3,
h4,
h5,
h6,
p {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  line-height: 1.45rem;
}

.route-enter-from {
  opacity: 0;
  transform: translateY(-30px);
}

.route-leave-to {
  opacity: 0;
  transform: translateY(30px);
}

.route-enter-active {
  transition: all 0.3s ease-out;
}

.route-leave-active {
  transition: all 0.3s ease-in;
}

.route-enter-to,
.route-leave-from {
  opacity: 1;
  transform: translateY(0);
}

#app {
  /* font-family: Avenir, Helvetica, Arial, sans-serif; */
  /* -webkit-font-smoothing: antialiased; */
  /* -moz-osx-font-smoothing: grayscale; */
  /* text-align: center; */
  color: #2c3e50;
  /* background-color: #fffff0; */
    line-height: 1rem;
    margin: 0;

}
</style>
