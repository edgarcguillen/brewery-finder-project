<template>
  <!-- this cmp behave as a wrapper for button content -->
  <button>
    <slot></slot>
  </button>
</template>


<style scoped>
button {
  padding: 0.75rem 1.5rem;
  font-family: inherit;
  background-color: #dabb6f;
  border: none;
  border-radius: 10px;
  font-size: 1.25rem;
  font-weight: bold;
  color: white;
  cursor: pointer;
}


button:hover,
button:active {
  box-shadow: 0 0.5em 0.5em -0.7em;
  background-color: #af975b41;
}
</style>
