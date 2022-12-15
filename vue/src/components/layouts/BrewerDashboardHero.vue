<template>
  <div class="main-container">
    <div class="hero-image">
      <h1>My Brewery Dashboard</h1>
      
    </div>
  </div>
</template>

<script>
export default {
  components: {},
};
</script>

<style scoped>
.main-container {
  margin: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.hero-image {
  height: 400px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to top, #ffffffba, #ffffffcc),
    url(https://images.unsplash.com/photo-1436076863939-06870fe779c2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80) center;
}

h1 {
    font-size: 5rem;
}
</style>
