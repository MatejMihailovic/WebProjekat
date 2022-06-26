<template>
  <form>
    <h1>Registracija</h1>
    <div>
      <label for="inputEmail4" class="form-label">Ime</label>
      <input v-model="korisnik.ime" class="form-control" />
    </div>
    <div>
      <label for="inputEmail4" class="form-label">Prezime</label>
      <input v-model="korisnik.prezime" class="form-control" />
    </div>
    <div>
      <label for="inputEmail4" class="form-label">Korisničko ime</label>
      <input v-model="korisnik.korisnickoIme" class="form-control" />
    </div>

    <div>
      <label for="inputPassword4" class="form-label">Lozinka</label>
      <input v-model="korisnik.lozinka" type="password" class="form-control" />
    </div>
    <label for="">Pol:</label> <br>
    <div class="form-check form-check-inline">
      <input
        class="form-check-input"
        type="radio"
        name="inlineRadioOptions"
        id="inlineRadio1"
        value="option1"
      />
      <label class="form-check-label" for="inlineRadio1">Muški</label>
    </div>
    <div class="form-check form-check-inline">
      <input
        class="form-check-input"
        type="radio"
        name="inlineRadioOptions"
        id="inlineRadio2"
        value="option2"
      />
      <label class="form-check-label" for="inlineRadio2">Ženski</label>
    </div>
    <br>
    <label>Datum rodjenja:</label> <br>
    <input type="date" />
    <div class="col-12">
      <button v-on:click="registrujSe()" class="btn btn-primary">
        Prijava
      </button>
    </div>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "RegisterView",
  data: function() {
    return {
      korisnik: {
        ime: "",
        prezime: "",
        korisnickoIme: "",
        lozinka: ""
      }
    };
  },
  methods: {
    registrujSe: function() {
      axios
        .post("http://localhost:8080/api/register", this.korisnik, {
          withCredentials: true
        })
        .then(res => {
          console.log(res);

          this.$router.push("/register");
          alert("Uspesno");
        })
        .catch(error => {
          console.log(error.response);
          alert("Neuspesno");
        });
    }
  }
};
</script>

<style>
h1 {
  color: cornflowerblue;
}
form {
  margin: 5% auto;
  width: 30%;
}
input {
  width: 30%;
  margin: 15px auto;
}
</style>