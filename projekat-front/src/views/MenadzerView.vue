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
      <td><a type="button" v-on:click="dodajArtikal()"  class="btn btn-primary">Dodaj artikal</a></td>
    </tr>
    <tr v-for="porudzbina in porudzbine" :key="porudzbina.id">
      <td>{{porudzbina.restoran}}</td> 
      <td>{{porudzbina.datumIVreme}}</td>
      <td>{{porudzbina.cena}}</td>
      <td>{{porudzbina.status}}</td>
    </tr>
  </tbody>
</table>
<div class="form-popup" id="myForm">
  <form action="/action_page.php" class="form-container">
    <h1>Dodaj artikal</h1>

    <label for="naziv"><b>Naziv</b></label>
    <input type="text" placeholder="Naziv" name="naziv" required>

    <label for="cena"><b>Cena</b></label>
    <input type="number" placeholder="Cena" name="cena" required>

    <label for="kolicina"><b>Kolicina</b></label>
    <input type="text" placeholder="Kolicina" name="kolicina" required>

    <label for="opis"><b>Opis</b></label>
    <input type="text" placeholder="Opis" name="opis" required>

    <button type="submit" class="btn">Login</button>
    <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
  </form>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "MenadzerView",
  data: function() {
    return {
      restoran: {},
      porudzbine : [],
      image: null,
      artikal:{

      }
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
    },
    dodajArtikal : function(){
      axios
      .post("http://localhost:8080/api/artikli/addArtikal", {withCredentials:true})
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
{box-sizing: border-box;}

/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 23px;
  right: 28px;
  width: 280px;
}

/* The popup form - hidden by default */
.form-popup {
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
  max-width: 300px;
  padding: 10px;
  background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/login button */
.form-container .btn {
  background-color: #04AA6D;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}

/* Add a red background color to the cancel button */
.form-container .cancel {
  background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}
</style>
