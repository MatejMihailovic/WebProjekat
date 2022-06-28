<template>
<form>
  <label for="korisnickoIme">Korisnicko ime:</label>
  <input v-model="menadzer.korisnickoIme" /><br />
  <label for="lozinka">Lozinka:</label>
  <input v-model="menadzer.lozinka" type = "password"/><br />
  <label for="ime">Ime:</label>
  <input v-model="menadzer.ime" maxlength = "20"/><br />
  <label for="prezime">Prezime:</label>
  <input v-model="menadzer.prezime" maxlength = "20"/><br />
  <div>Pol: 
  <select v-model="menadzer.pol">
    <option disabled>Odaberite pol</option>
    <option value = 0>Muski</option>
    <option value = 1>Zenski</option>
  </select>
  </div>
  <br />
  <label for="datumRodjenja">Datum rodjenja:</label>
  <input type="text" v-model="menadzer.datumRodjenja" placeholder="dd/mm/yyyy" pattern="[0-9/]{10}" required maxlength="10"/><br />
  <div>Restoran: 
  <select v-model="menadzer.nazivRestorana"  >
    <option v-for="restoran in restorani" :key="restoran.id" value = {{restoran.naziv}}>{{restoran.naziv}}</option>
  </select>
  </div>
  <button v-on:click="submit()">submit</button>
  </form>
</template>

<script>
import axios from "axios"
export default {
  name: "CreateMenadzerView",
  data: function () {
    return {
      menadzer: {
        korisnickoIme : "",
        lozinka : "",
        ime : "",
        prezime : "",
        pol : null,
        datumRodjenja : "",
        nazivRestorana : ""
      },
      restorani : []
    };
  },
   mounted: function () {
    axios
      .get("http://localhost:8080/api/svi-restorani")
      .then((res) => {
        this.restorani = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
   },
  methods: {
    submit: function () {

      axios
        .post("http://localhost:8080/api/admin/create-menadzer", this.menadzer, {withCredentials: true})
        .then((res) => {
          console.log(res);
          this.$router.push("/admin");
          alert("Success!");
        })
        .catch((error) => {
         console.log(error.response)
          alert("Something went wrong!");
          });
    },
  },
};
</script>
<style>
        input,
        select {
            width: 200px;
            height: 25px;
            margin: 2px;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }
</style>