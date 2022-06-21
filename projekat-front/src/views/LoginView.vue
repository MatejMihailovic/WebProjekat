<template>
    
    <form >
        <h1>Prijava</h1>
  <div>
    <label for="inputEmail4" class="form-label">Korisničko ime</label>
    <input v-model="korisnik.korisnickoIme" class="form-control">
  </div>
  
  <div>
    <label for="inputPassword4" class="form-label">Lozinka</label>
    <input v-model="korisnik.lozinka" type="password" class="form-control">
  </div>
  
  <div class="col-12">
    <button v-on:click="prijaviSe()" class="btn btn-primary">Prijava</button>
  </div>
    </form>
</template>

<script>
import axios from "axios";

const loginURL = '/login';

export default {
    name: "LoginView",
    data: function(){
    return {
        korisnik: {
            korisnickoIme: "",
            lozinka: "",
        }, 
    };
    
    },
    methods: {
        prijaviSe: function(){
            axios
                .post("http://localhost:8081/api/login", this.user)
                .then((res) => {
                    console.log(res);
                    this.$router.push("/login");
                })
                .catch((err) => {
                    console.log(err);
                    alert("Neuspesno");
                });
        },
    },
};
</script>

<style scoped>
    h1{
        color: cornflowerblue;
        margin-top: 10%;
    }
    form{
        margin: auto;
    }
    input{
        width: 30%;
        margin: 5px auto;

    }
</style>