<template>
  <div class="card">
    <div class="second">
      <div class="card-element">
        <h2>Beer name:</h2>
        <h3>{{name}}</h3>
      </div>

      <div class="card-element">
        <h2>Reviewed from:</h2>
        <h3>
          {{brewery}}
        </h3>
      </div>
      <div class="card-element">
        <h2>Beer label:</h2>
        <h3>"{{description}}"</h3>
      </div>
      <div class="card-element">
        <h2>Beer type:</h2>
        <h3>{{type}}</h3>
      </div>
      <div class="card-element">
        <h2>Beer abv:</h2>
        <h3>{{abv}}</h3>
      </div>
      <div class="rating">
        <h2>Your rating:</h2>
        <div class="stars">
        <img src="../../assets/beer.png" 
        class="ratingStar"
        v-for="n in stars"
        v-bind:key="n">
      </div>
      </div>
      <div class="card-element" id="review">
        <h2>Your review:</h2>
          <h3> {{ review }}</h3>
      </div>
    
      </div>
  </div>
</template>


<script>
export default {
props: [
  "name",
  "stars",
  "review",
  "description", 
  "type",
  "abv",
  "brewery"
],}
</script>


<style scoped>
.card {
  display: flex;
  flex-direction: column;
  width: 400px;
  height: auto;
  margin: 15px;
  padding: 5px;
  border: 5px solid black;
  justify-content: center;
  overflow-wrap: normal;
}

.stars{
  display: flex;
  flex-direction: row;
  max-width: 20%;
  justify-content: flex-end;
  
}

img{
  max-width: 40%;
  height: auto;
}

h4{
  font-style: italic;
}

.rating{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
 
}


h3{
  bottom: 0;
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  flex-wrap: wrap-reverse;
  justify-content: flex-end;
}

#review{
  display: flex;
  flex-direction: column;
  overflow-wrap: break-word;
}

.second{
  height: auto;
}

</style>