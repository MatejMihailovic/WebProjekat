<template>
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
      <td>{{restoran.name}}</td>
      <td>{{restoran.tip}}</td>
      <td>{{restoran.tip}}</td>
      <td>{{restoran.tip}}</td>
      <td>{{restoran.tip}}</td>
    </tr>
  </tbody>
</table>
</template>

<script>
import axios from "axios";

export default {
  name: "MenadzerView",
  data: function() {
    return {
    };
  },
  methods: {
    mounted: function () {
    axios
      .get("http://localhost:8080/api/menadzer/restoran", {withCredentials:true})
      .then((res) => {
      })
      .catch((err) =>{
        console.log(err)
      })
   }
  }
};
</script>

<style>

</style>
