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
            <a class="nav-link" href="/korpa">Korpa</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Odjavi se</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="input-group">
        <select v-model="filter" class="form-select" aria-label="Default select example">
        <option selected>Choose</option>
        <option value="naziv">Pretrazi po nazivu</option>
        <option value="tipRestorana">Pretrazi po tipu restorana</option>
        <option value="lokacija">Pretrazi po lokaciji</option>
        </select>
        <input v-model="value" type="search" v-on:keyup="search(filter, value)" id="myInput" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
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
</template>

<script>
import axios from "axios";
import RestoranComp from "../components/RestoranComp.vue";
export default {
  name: "KupacView",
  components: { RestoranComp },
  data: function() {
    return {
      restorani: [],
     
      filter : "",
      value : "",
    };
  },
  mounted: function() {
    axios
      .get("http://localhost:8080/api/svi-restorani",{withCredentials: true})
      .then(res => {
        this.restorani = res.data;
      })
      .catch(error => {
        console.log(error);
      });
  },
     methods: {
    search : function(filter, value){
      axios
      .get("http://localhost:8080/api/restorani?search=" + filter + ":" + value, {withCredentials:true})
      .then((res) => {
        this.restorani = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
    }
    },

};
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
#restorani{
    padding: 3%;
    
}
.card{
    margin: auto 5px;
}
</style>
