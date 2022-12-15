<template>
  <div>
    <the-header></the-header>
    <add-beer-form></add-beer-form>

  </div>
</template>

<script>
import TheHeader from "../components/layouts/TheHeader.vue";
import AddBeerForm from "../components/brewer-role-resources/AddBeerForm.vue"

export default {
  components: { TheHeader, AddBeerForm },
};
</script>
