<template>
  <header>
    <base-modal v-if="isSuccessful">
      <h3>You have been successfully logged out.</h3>
      <button class="modal-button" @click="routeToHomePage()">Okay</button>
    </base-modal>
  </header>
</template>

<script>
import BaseModal from "../components/UI/BaseModal.vue";

export default {
  components: { BaseModal },
  data() {
    return {
      isSuccessful: false,
    };
  },
  created() {
    this.isSuccessful = true;

    this.$store.commit("LOGOUT");
    this.$router.push("/welcome");
  },
  methods: {
    routeToHomePage() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style scoped>
h1 {
  font-size: 1.5rem;
  text-align: center;
  margin-bottom: 50px;
}
.redirect {
  font-size: 1rem;
  text-align: center;
  margin-bottom: 50px;
}

button.modal-button {
  width: 200px;
  height: 4rem;
  padding: 0.75rem 1.5rem;
  background-color: transparent;
  border-radius: 10px;
  font-family: inherit;
  text-align: center;
  cursor: pointer;
  box-shadow: 0 0 10px #333;
  overflow: hidden;
  transition: 0.3s;
  border: 2px solid rgb(122, 231, 50);
}

button:hover.modal-button {
  background-color: rgb(208, 255, 0);
  transform: scale(1.2);
  box-shadow: 0 0 4px #111;
  transition: 0.3s;
}
</style>
