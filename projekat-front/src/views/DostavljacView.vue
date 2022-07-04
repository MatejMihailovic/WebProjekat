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
            <a class="nav-link" href="/login">Odjavi se</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <section id="restorani">
    <h2>Pregled restorana</h2>

    <div class="input-group">
      <select
        v-model="filter1"
        class="form-select"
        aria-label="Default select example"
      >
        <option selected>Choose</option>
        <option value="naziv">By Name</option>
        <option value="tipRestorana">By Type</option>
        <option value="lokacija">By Location</option>
      </select>
      <input
        v-model="value1"
        type="search"
        v-on:keyup="search1(filter1, value1)"
        id="myInput"
        class="form-control rounded"
        placeholder="Search"
        aria-label="Search"
        aria-describedby="search-addon"
      />
    </div>

    <section id="restorani">
      <div class="container-fluid">
        <div class="row">
          <restoran-comp
            v-for="restoran in restorani"
            :key="restoran.id"
            :restoran="restoran"
          >
          </restoran-comp>
        </div>
      </div>
    </section>
  </section>
  <section id="porudzbine">
      <h2>Pregled porudzbina koje cekaju</h2>
      <porudzbina-comp
            v-for="porudzbina in porudzbine"
            :key="porudzbina.id"
            :porudzbina="porudzbina"
          >
          </porudzbina-comp>
  </section>
    <section id="njegovePorudzbine">
      <h2>Pregled vaših porudzbina</h2>
      <porudzbina-comp
            v-for="njegovaPorudzbina in njegovePorudzbine"
            :key="njegovaPorudzbina.id"
            :njegovaPorudzbina="njegovaPorudzbina"
          >
          </porudzbina-comp>
  </section>
</template>

<script>
import axios from "axios";
import RestoranComp from "../components/RestoranComp.vue";
import PorudzbinaComp from "../components/PorudzbinaComp.vue";
export default {
  name: "DostavljacVue",
  components: { RestoranComp },
  data: function() {
    return {
      filter1: "",
      value1: "",
      restorani: [],
      porudzbine: [],
      njegovePorudzbine: []
    };
  },
  mounted: function() {
    axios
      .get("http://localhost:8080/api/svi-restorani", { withCredentials: true })
      .then(res => {
        this.restorani = res.data;
      })
      .catch(error => {
        console.log(error);
      });
      axios
      .get("http://localhost:8080/api/porudzbine-cekaDostavljaca", { withCredentials: true })
      .then(res => {
        this.porudzbine = res.data;
      })
      .catch(error => {
        console.log(error);
      });
    axios
      .get("http://localhost:8080/api/porudzbine-dostavljac", { withCredentials: true })
      .then(res => {
        this.njegovePorudzbine = res.data;
      })
      .catch(error => {
        console.log(error);
      });
  },
  methods: {

     search1 : function(filter1, value1){
      axios
      .get("http://localhost:8080/api/restorani?search=" + filter1 + ":" + value1, {withCredentials:true})
      .then((res) => {
        this.restorani = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
    }
   }
};
</script>

<style scoped>
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
