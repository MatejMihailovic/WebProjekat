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
            <a class="nav-link" href="/profile">Profil</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/korpa">Korpa</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/login">Odjavi se</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <section>
    <h2>{{ restoran.naziv }}</h2>
    <h5>Tip restorana: {{ restoran.tipRestorana }}</h5>
    <h5>Lokacija: {{ restoran.lokacija }}</h5>
    <h5>Status: {{ restoran.status }}</h5>
    <h5>Prosek ocena: {{ restoran.prosek }}</h5>
    <h5>Komentari: {{ restoran.komentari }}</h5>
  </section>
  <section id="artikliUPonudi">
    <h2>Artikli u ponudi</h2>

    <div class="container-fluid">
      <div class="row">
          <div class="card-body" v-for="artikal in restoran.artikliUPonudi" :key="artikal.id">
            <p>{{ artikal.naziv }}</p>
            <p>{{ artikal.cena }} dinara</p>
            <p>{{ artikal.opis }}</p>
            <p>{{ artikal.kolicina }}</p>
            <button class="btn btn-primary" v-on:click="dodajUKorpu(artikal.id)">
              Dodaj u korpu
            </button>
            </div>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
import ArtikalComp from "../components/ArtikalComp.vue";

export default {
  name: "RestoranView",
  components: { ArtikalComp },
  data: function() {
    return {
      restoran: {},
      filter : "",
      value : "",
    };
  },
  mounted: function() {
    axios
      .get("http://localhost:8080/api/restorani/" + this.$route.params.id, {
        withCredentials: true
      })
      .then(res => {
        this.restoran = res.data;
      })
      .catch(err => {
        console.log(err);
      });

  },
  methods: {
    dodajUKorpu: function(id) {
      axios
      .post("http://localhost:8080/api/porudzbine-dodajArtikal/" + id, {withCredentials:true})
      .then(res => {
         this.$router.push("/korpa");
      })
      .catch((err) =>{
        console.log(err)
      })
    }
  }
};
</script>

<style>
</style>
