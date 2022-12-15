<template>
  <div class="container">
    <div class="content">
      <div class="form">
        <login></login>
      </div>
      <div class="banner"></div>
    </div>
  </div>
</template>

<script>
import Login from "./Login.vue";
export default {
  components: { Login },
};
</script>

<style scoped>
.container {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  background-color:#fffff0;
}

.content {
  margin: 0 auto;
  width: 1050px;
  height: 600px;
  display: flex;
  background-color: rgba(6, 41, 30, 0.281);
  border-radius: 20px 20px;
}

.banner {
  height: 100%;
  width: 100%;
  background-image: url(https://images.unsplash.com/photo-1524587253133-266042ff67d0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80);
  background-size: cover;
  border-radius: 0 20px 20px 0;
}

</style>
