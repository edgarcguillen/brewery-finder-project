<template>
  <!-- this cmp behave as a wrapper for button content -->
  <button >
    <slot></slot>
  </button>
</template>

<script>
export default {
  props: ["type", "mode"],
};
</script>

<style scoped>
button {
  padding: 0px 15px;
  height: 45px;
  font-size: 1rem;
  background-color: transparent;
  border:1px solid #E29569;
  border-radius: 10px;
  cursor: pointer;
}

button:hover,
button:active {
  box-shadow: 0 0.5em 0.5em -0.7em;
  background-color: #e295699a;
  color: black;
}
</style>
