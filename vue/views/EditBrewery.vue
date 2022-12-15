<template>
<div>
  <the-header></the-header>
  <div class="main-container">
    <header>
      <update-brewery-form></update-brewery-form>
    </header>
  </div>
</div>
  
</template>

<script>
import UpdateBreweryForm from "../components/brewer-role-resources/UpdateBreweryForm.vue";
import TheHeader from '../components/layouts/TheHeader.vue';

export default {
  components: {
    UpdateBreweryForm,
    TheHeader,
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

header {
  margin-bottom: 30px;
}

h1 {
  font-size: 5rem;
  text-align: center;
}
</style>
