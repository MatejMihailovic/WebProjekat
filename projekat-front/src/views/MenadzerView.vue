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
      <td><button class="open-button" v-on:click="openForm()">Dodaj artikal</button></td>
    </tr>
    <tr v-for="porudzbina in porudzbine" :key="porudzbina.id">
     <td>{{porudzbina.restoran}}</td> 
    <td>{{porudzbina.datumIVreme}}</td> 
      <td>{{porudzbina.cena}}</td>
      <td>{{porudzbina.status}}</td>
    </tr>
  </tbody>
</table>

<!-- <artikal-comp
      v-for="artikal in artikli"
      :key="artikal.id"
      :artikal="artikal"
    >
    </restoran-comp> -->
<div class="form-popup" id="myForm">
  <form action="/action_page.php" class="form-container">
    <h3>Dodaj artikal</h3>

    <label for="naziv"><b>Naziv</b></label>
    <input type="text" v-model="artikal.naziv" placeholder="Naziv" name="naziv" required><br />

    <label for="cena"><b>Cena</b></label>
    <input type="number"  v-model="artikal.cena" placeholder="Cena" name="cena" required><br />

    <div>Tip: 
     <select v-model="artikal.tip">
    <option disabled>Odaberite tip</option>
    <option value = 0>Jelo</option>
    <option value = 1>Piće</option>
     </select>
    </div><br />

    <label for="kolicina"><b>Količina</b></label>
    <input type="number" v-model="artikal.kolicina" placeholder="Kolicina" name="kolicina" required><br />

    <label for="opis"><b>Opis</b></label>
    <input type="text"  v-model="artikal.opis" placeholder="Opis" name="opis" required><br />

    <div class="image-class">
     
    <label>Photo: </label>
    <input type="file" ref="uploadImage" v-on:change="onImageUpload()" name="image" accept="image/png, image/jpeg" />
     
    </div>

    <button type="button" class="btn" v-on:click="dodajArtikal()">Dodaj</button><br />
    <button type="button" class="btn cancel" v-on:click="closeForm()">Close</button>
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
        naziv: "",
        cena: null,
        tip: null,
        kolicina: null,
        opis: ""
      },
      artikli: [],
      formData: null
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
    onImageUpload(){
        let file = this.$refs.uploadImage.files[0];
        this.formData = new FormData();
        this.formData.append("file",file);
        let json =  JSON.stringify(this.artikal);
        this.formData.append("json", json);
    },
    dodajArtikal : function(){
      axios
      .post("http://localhost:8080/api/artikli/addArtikal", this.formData, {withCredentials:true})
      .then((res) => {
        console.log(res.data)
      })
      .catch((err) =>{
        console.log(err)
      })
    },
    openForm : function() {
      document.getElementById("myForm").style.display = "block";
    },
    closeForm: function () {
      document.getElementById("myForm").style.display = "none"; 
    }
  }
};
</script>

<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}
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
  right: 30px;
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
