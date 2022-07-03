<template>
  <div class="card" style="width: 18rem;">
   
          <div class="card-body">
            <p>{{ artikal.naziv }}</p>
            <p>{{ artikal.cena }} dinara</p>
            <p>{{ artikal.opis }}</p>
            <p>{{ artikal.kolicina }}</p>
            <!--<img :src="../assets/ + '.png'" width="50">-->
            <img v-bind:src="require(`../assets/${artikal.id}.png`)" />
            <br />

            <button class="btn btn-primary" v-on:click="dodajUKorpu">
              Dodaj u korpu
            </button>
          </div>
   

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ArtikalComp",

  props: ["artikal"],



  methods: {
    dodajUKorpu: function() {
      alert("dodato u korpu");
    }
  }
};
</script>

<style scoped>
img {
  width: 60px;
}
.card{
    height: 300px;
}
button{
    margin-top: 3%;
}
</style>
