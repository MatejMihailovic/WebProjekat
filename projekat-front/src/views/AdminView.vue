<template>
    <a type="button" href="/create-menadzer" class="btn btn-primary">Kreiraj Menadžera</a>
    <a type="button" href="/create-dostavljac" class="btn btn-primary">Kreiraj Dostavljača</a>
    <a type="button" href="/create-restoran" class="btn btn-primary">Kreiraj Restoran</a>

    <div class="input-group">
        <input type="search" id="myInput" onkeyup="myFunction()" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
        <button type="button" class="btn btn-outline-primary">search</button>
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
</template>

<script>
import axios from "axios";
export default {
  name: "Admin Vue",
  data: function () {
    return {
      users: [],
    };
  },
   mounted: function () {
    axios
      .get("http://localhost:8080/api/korisnici")
      .then((res) => {
        this.users = res.data
      })
      .catch((err) =>{
        console.log(err)
      })
   },
   methods: {
    
    },
};
</script>

<style scoped>
</style>