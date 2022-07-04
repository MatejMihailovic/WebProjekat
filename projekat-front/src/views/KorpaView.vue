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
            <a class="nav-link" href="/kupac">Početna</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Odjavi se</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <h3>Korpa</h3>
  <section id="porudzbina">
      <table class="table">
  <thead>
    <tr>
      <th scope="col">Ukloni</th>
      <th scope="col">Slika</th>
      <th scope="col">Naziv proizvoda</th>
      <th scope="col">Cena</th>
      <th scope="col">Količina</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="artikal in artikli" :key="artikal.id" :artikal="artikal">
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
  </tbody>
</table>

  </section>
    
</template>

<script>
import axios from "axios";
import ArtikalComp from "../components/ArtikalComp.vue";

export default {
    name: "KorpaView",
      data: function () {
    return {
      porudzbina: {}
    };
  },
   mounted: function () {
    axios
      .get("http://localhost:8080/api/porudzbine-pregledKorpe", {withCredentials:true})
      .then((res) => {
        this.porudzbina = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
   },
   methods: {
   }


}
</script>

<style>
.logoMali {
  font-size: 20px;
  text-align: left;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
}

.white-text {
  color: cornflowerblue;
}

.orange-span {
  color: orangered;
}
</style>