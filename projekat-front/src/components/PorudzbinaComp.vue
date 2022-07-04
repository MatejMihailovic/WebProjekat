<template>
  <div class="card" style="width: 18rem;">
   
          <div class="card-body">
            
            <p>{{ porudzbina.status }}</p>
            
            

           
          </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PorudzbinaComp",

  props: ["porudzbina"],



  methods: {
 
    }
  
};
</script>

<style scoped>

</style>
