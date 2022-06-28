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
      }
    };
  },
  methods: {
    prijaviSe: function() {
       axios
        .post("http://localhost:8080/api/login", this.korisnik, {
          withCredentials: true
        })
        .then(res => {
          console.log(res);
          alert("Uspesno");
          this.$router.push("/admin");
        })
        .catch(error => {
          console.log(error.response);
          alert("Neuspesno");
        }); 
         /*  fetch("http://localhost:8080/api/login", {
            method: "POST",
            credentials: 'include',
            headers: {
              Accept: "application/json",
              "Content-type": "application/json",
            },
            body: JSON.stringify(this.korisnik),
            })
            .then((response) => response.json)
            .then((data) => {
              console.log("Success : " + data.toString());
             
              
                  this.$router.push("/admin");

            })
            .catch((err) => {
              console.log("Error : " + err);
              alert(err);
            }); */
    }
  }
};
</script>

<style>
h1 {
  color: cornflowerblue;
  margin-top: 10%;
}
form {
  margin: auto;
}
input {
  width: 30%;
  margin: 5px auto;
}
</style>
