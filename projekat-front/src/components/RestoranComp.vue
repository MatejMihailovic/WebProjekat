<template>
<div class="card" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title"> {{restoran.naziv}} </h5>
    <h6 class="card-subtitle mb-2 text-muted"> {{restoran.tipRestorana}} </h6>
    <h6 class="card-subtitle mb-2 text-muted"> {{restoran.lokacija}} </h6>

    <button class="btn btn-primary" v-on:click="vidiRestoran">Vidi restoran</button>
  </div>
</div>
</template>

<script>
export default {
    name: "RestoranComp",
    props: ["restoran"],
    methods: {
      vidiRestoran: function(){
        
        this.$router.push("/restorani/"+this.restoran.id);
      },
    },
};
</script>

<style>

</style>