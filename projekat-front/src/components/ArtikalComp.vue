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

            <button class="btn btn-primary" v-if="role=='Kupac'" v-on:click="dodajUKorpu">
              Dodaj u korpu
            </button>
            <button class="btn btn-primary" v-if="role=='Menadzer'" v-on:click="obrisiArtikal">
              Obriši artikal
            </button>
          </div>
   

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ArtikalComp",

  props: ["artikal"],

  data: function () {
     return {
      role: ""
    };
  },
    mounted: function(){
      axios
        .get("http://localhost:8080/api/korisnici/role", {withCredentials:true})
        .then((res) => {
        console.log(res.data)
        this.role = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
    },
  methods: {
    dodajUKorpu: function() {
      alert("dodato u korpu");
    },
    obrisiArtikal: function(){
      axios
       .delete("http://localhost:8080/api/artikli/deleteArtikal/" + this.artikal.id, {withCredentials:true})
        .then(res => {
         window.location.reload();
      })
      .catch((err) =>{
        console.log(err)
      })
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
