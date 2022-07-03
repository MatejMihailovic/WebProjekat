<template>
    <a type="button" href="/create-menadzer" class="btn btn-primary">Kreiraj Menadžera</a>
    <a type="button" href="/create-dostavljac" class="btn btn-primary">Kreiraj Dostavljača</a>
    <a type="button" href="/create-restoran" class="btn btn-primary">Kreiraj Restoran</a>
    <a type="button" href="/profile" class="btn btn-primary">Moj profil</a>
    <a type="button" href="/login"  class="btn btn-primary">Izloguj se</a>

    <div class="input-group">
        <select v-model="filter" class="form-select" aria-label="Default select example">
        <option selected>Choose</option>
        <option value="korisnickoIme">By Username</option>
        <option value="ime">By First Name</option>
        <option value="prezime">By Second Name</option>
        </select>
        <input v-model="value" type="search" v-on:keyup="search(filter, value)" id="myInput" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
    </div>

    <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Username</th>
      <th scope="col">First name</th>
      <th scope="col">Last name</th>
      <th scope="col">Gender</th>
      <th scope="col">Role</th>
      <th scope="col">Date of birth</th>
    </tr>
  </thead>
  <tbody>
    <tr  v-for="user in users" :key="user.id">
      <th scope="row">{{user.id}}</th>
      <td>{{user.korisnickoIme}}</td>
      <td>{{user.ime}}</td>
      <td>{{user.prezime}}</td>
      <td>{{user.pol}}</td>
      <td>{{user.uloga}}</td>
      <td>{{user.datumRodjenja}}</td>
    </tr>
  </tbody>
</table>

  <section id="restorani">
    <h2>Pregled restorana</h2>

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

  </section>
</template>

<script>
import axios from "axios";
import RestoranComp from "../components/RestoranComp.vue";

export default {
  name: "Admin Vue",
  components: { RestoranComp },
  data: function () {
    return {
      users: [],
      filter : "",
      value : "",
      restorani: [],
    };
  },
   mounted: function () {
    axios
      .get("http://localhost:8080/api/svi-korisnici", {withCredentials:true})
      .then((res) => {
        this.users = res.data
      })
      .catch((err) =>{
        console.log(err)
      }),
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
      .get("http://localhost:8080/api/korisnici?search=" + filter + ":" + value, {withCredentials:true})
      .then((res) => {
        this.users = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
    }
    },
};
</script>

<style scoped>
</style>