<template>
<div class="card" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title"> {{restoran.naziv}} </h5>
    <h6 class="card-subtitle mb-2 text-muted"> {{restoran.tipRestorana}} </h6>
    <h6 class="card-subtitle mb-2 text-muted"> {{restoran.lokacija}} </h6>

    <button class="btn btn-primary" v-on:click="vidiRestoran">Vidi restoran</button>
    <button class="btn btn-primary" v-if="role=='Admin'" v-on:click="deleteRestoran()">Delete</button>
  </div>
</div>
</template>

<script>
import axios from "axios";
export default {
    name: "RestoranComp",
    props: ["restoran"],
    data: function () {
     return {
      role: ""
    };
  },
    mounted: function(){
      /*axios
        .get("http://localhost:8080/api/korisnici/role", {withCredentials:true})
        .then((res) => {
        console.log(res.data)
        this.role = res.data
      })
      .catch((err) =>{
        console.log(err)
      })*/
    },
    methods: {
      vidiRestoran: function(){
        
        this.$router.push("/restorani/"+this.restoran.id);
      },
      deleteRestoran: function(){
      axios
        .delete("http://localhost:8080/api/admin/delete-restoran/" + this.restoran.id,  {withCredentials:true})
        .then(res => {
          window.location.reload();
      })
      .catch((err) =>{
        console.log(err)
      })
      }
    },
};
</script>

<style>

</style>