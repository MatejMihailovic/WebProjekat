<template>
 <nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#"
        ><div class="logoMali">
          <p class="white-text">dostava<span class="orange-span">019</span></p>
        </div></a
      >
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#">Profil</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="../views/LoginView.vue">Odjavi se</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
    <h1>Moj restoran</h1>
    <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Type</th>
      <th scope="col">Status</th>
      <th scope="col">Address</th>
      <th scope="col">Rating</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>{{restoran.naziv}}</td>
      <td>{{restoran.tipRestorana}}</td>
      <td>{{restoran.status}}</td>
      <td>{{restoran.lokacija}}</td>
      <td>{{restoran.prosek}}</td>
    </tr>
    <tr>
      <td><a type="button" v-on:click="prikaziPorudzbine()"  class="btn btn-primary">Prikaži porudžbine</a></td>
    </tr>
    <tr v-for="porudzbina in porudzbine" :key="porudzbina.id">
      <td>{{porudzbina.restoran}}</td> 
      <td>{{porudzbina.datumIVreme}}</td>
      <td>{{porudzbina.cena}}</td>
      <td>{{porudzbina.status}}</td>
    </tr>
  </tbody>
</table>
</template>

<script>
import axios from "axios";

export default {
  name: "MenadzerView",
  data: function() {
    return {
      restoran: {},
      porudzbine : []
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/api/menadzer-restoran", {withCredentials:true})
      .then((res) => {
        this.restoran = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
   },
  methods: {
    prikaziPorudzbine : function (){
      axios
      .get("http://localhost:8080/api/menadzer-porudzbine", {withCredentials:true})
      .then((res) => {
        this.porudzbine = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
    }
  },
};
</script>

<style>

</style>
