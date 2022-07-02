<template>
<label for="korisnickoIme">Korisnicko ime:</label>
  <input v-model="user.korisnickoIme" /><br />
  <label for="lozinka">Lozinka:</label>
  <input v-model="user.lozinka" type = "password"/><br />
  <label for="ime">Ime:</label>
  <input v-model="user.ime" maxlength = "20"/><br />
  <label for="prezime">Prezime:</label>
  <input v-model="user.prezime" maxlength = "20"/><br />
  <button v-on:click="update()" class="btn btn-primary">update</button>
</template>

<script>
import axios from "axios";
export default {
  name: "UpdateUserProfileView",
  data: function () {
    return {
      user:{
        korisnickoIme: "",
        ime: "",
        prezime: "",
        lozinka: ""
      }
    };
  },
   mounted: function () {
   },
   methods: {
    update: function(){
        axios
        .put("http://localhost:8080/api/korisnici/ulogovanKorisnik/update", this.user, {withCredentials:true})
        .then((res) => {
          console.log(res);
          this.$router.push("/profile");
        })
        .catch((error) => {
         console.log(error.response)
          alert("Something went wrong!");
          });
    }
   }
}
</script>


<style scoped>
</style>
