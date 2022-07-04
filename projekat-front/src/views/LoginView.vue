<template>
    <h1>Prijava</h1>
    <div>
      <label for="inputEmail4" class="form-label">Korisničko ime</label>
      <input v-model="korisnik.korisnickoIme" class="form-control" />
    </div>

    <div>
      <label for="inputPassword4" class="form-label">Lozinka</label>
      <input v-model="korisnik.lozinka" type="password" class="form-control" />
    </div>

    <div class="col-12">
      <button v-on:click="prijaviSe()" class="btn btn-primary">Prijava</button>
    </div> 
</template>

<script>
import axios from "axios";

export default {
  name: "LoginView",
  data: function() {
    return {
      korisnik: {
        korisnickoIme: "",
        lozinka: ""
      },
      uloga: ""
    };
  },
  methods: {
    prijaviSe: function() {
       axios
        .post("http://localhost:8080/api/login", this.korisnik, {
          withCredentials: true
        })
        .then(res =>{
          if(res.data == "Admin"){ 
            this.$router.push("/admin");
          }else if(res.data == "Dostavljac"){
            this.$router.push("/dostavljac");
          }else if(res.data == "Menadzer"){
            this.$router.push("/menadzer");
          }else{
            this.$router.push("/kupac");
          }
        })
        .catch(error => {
          console.log(error.response);
          alert("Neuspesno");
        }); 
    }
      
  }
};
</script>

<style scoped>
h1 {
  color: cornflowerblue;
  margin-top: 10%;
}
body {
  margin: auto;
}
input {
  width: 30%;
  margin: 5px auto;
}
</style>
