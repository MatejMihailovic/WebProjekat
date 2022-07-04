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

  <section id="porudzbina">
      

  </section>
    
</template>

<script>
import axios from "axios";

export default {
    name: "KorpaComp",
      data: function () {
    return {
      porudzbina: {}
    };
  },
   mounted: function () {
    axios
      .get("http://localhost:8080/api/porudzbine-kupac", {withCredentials:true})
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