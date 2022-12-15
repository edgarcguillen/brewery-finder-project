<template>
  <div class="main-container">
    <div class="hero-image">
      <slot></slot>
    </div>
  </div>
</template>


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
    url(https://images.unsplash.com/photo-1505075106905-fb052892c116?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80) center;
}

/* .hero-image {
  height: 400px;
  width: 100%;
  background-image: url("https://images.unsplash.com/photo-1505075106905-fb052892c116?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80");
  background-size: cover;
  background-color: red;
} */
</style>
