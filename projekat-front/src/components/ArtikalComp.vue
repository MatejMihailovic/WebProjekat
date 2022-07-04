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
            <button class="btn btn-primary" v-if="role=='Menadzer'" v-on:click="openForm1(this.artikal.id)">
             Izmeni artikal
            </button>
          </div>
  </div>
  <div class="form-popup" id="myForm1">
    <h3>Izmeni artikal</h3>

    <label for="naziv"><b>Naziv</b></label>
    <input type="text" v-model="temp.naziv" placeholder="Naziv" name="naziv" required><br />

    <label for="cena"><b>Cena</b></label>
    <input type="number"  v-model="temp.cena" placeholder="Cena" name="cena" required><br />

    <div>Tip: 
     <select v-model="artikal.tip">
    <option disabled>Odaberite tip</option>
    <option value = 0>Jelo</option>
    <option value = 1>Piće</option>
     </select>
    </div><br />

    <label for="kolicina"><b>Količina</b></label>
    <input type="number" v-model="temp.kolicina" placeholder="Kolicina" name="kolicina" required><br />

    <label for="opis"><b>Opis</b></label>
    <input type="text"  v-model="temp.opis" placeholder="Opis" name="opis" required><br />

    <button type="button" class="btn" v-on:click="izmeniArtikal()">Izmeni</button><br />
    <button type="button" class="btn cancel" v-on:click="closeForm1()">Close</button>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "ArtikalComp",

  props: ["artikal"],

  data: function () {
     return {
      username: "",
      id: null,
      role: "",
      temp:{
        naziv: "",
        cena: null,
        tip: null,
        kolicina: null,
        opis: ""
      }
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
      }),
       axios
        .get("http://localhost:8080/api/korisnici/username", {withCredentials:true})
        .then((res) => {
        console.log(res.data)
        this.username = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
    },
  methods: {
    dodajUKorpu: function() {
      console.log(typeof(this.username));
      console.log(this.username);
      axios
      .post("http://localhost:8080/api/porudzbine-dodajArtikal/" + this.artikal.id, this.username, {withCredentials:true})
      .then(res => {
         this.$router.push("/korpa");
      })
      .catch((err) =>{
        console.log(err)
      })
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
    },
    openForm1 : function(id) {
      this.id = id;
      document.getElementById("myForm1").style.display = "block";
    },
    closeForm1: function () {
      this.id = id;
      document.getElementById("myForm1").style.display = "none"; 
      window.location.reload();
    },
    izmeniArtikal : function(){
      console.log(this.id)
      axios
      .put("http://localhost:8080/api/artikli/updateArtikal/" + this.id, this.temp, {withCredentials:true})
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

#myForm1{
  margin: auto;
  width: 50%;
  border: 3px solid green;
  padding: 10px;
}
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
