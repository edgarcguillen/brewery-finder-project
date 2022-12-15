<template>
  <div>
    <the-header></the-header>
    <add-brewery-form></add-brewery-form>
  </div>
</template>

<script>
import AddBreweryForm from "../components/brewer-role-resources/AddBreweryForm.vue";
import TheHeader from "../components/layouts/TheHeader.vue";

export default {
  components: { AddBreweryForm, TheHeader },
};
</script>
