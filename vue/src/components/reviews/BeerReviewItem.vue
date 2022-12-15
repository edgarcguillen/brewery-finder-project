<template>
  <div class="base-card">
    <div class="review-details-slot">
      <div class="avatar-container"></div>
      <p>{{ review }}</p>
    </div>
    <div class="img-container">
      <img
        src="../../assets/beer.png"
        class="ratingStar"
        v-for="n in stars"
        v-bind:key="n"
      />
    </div>
  </div>
</template>

<script>
export default {
  props: ["stars", "review"],
};
</script>

<style scoped>
.base-card {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
  gap: 2rem;
  min-width: 800px;
  height: 100%;
  padding: 1.5rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  background-color: rgba(255, 255, 255, 0.767);
}

.review-details-slot {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  justify-content: left;
  overflow-wrap: break-word;
  max-width: 525px;
}

.img-container {
  margin: 0;
  max-width: 150px;
  background-size: contain;
  background-position: center;
}

img {
  height: 30px;
}

.avatar-container {
  height: 50px;
  width: 50px;
  background-image: url(https://img.icons8.com/external-avatar-andi-nur-abdillah/64/000000/external-avatar-avatarar-bussiness-avatar-andi-nur-abdillah-27.png);
  background-size: contain;
  background-position: center;
}
</style>
