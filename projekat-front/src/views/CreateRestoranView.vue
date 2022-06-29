<template>
  <label for="naziv">Naziv restorana:</label>
  <input v-model="restoran.naziv" type = "text"/><br />
  <label for="tipRestorana">Tip restorana:</label>
  <input v-model="restoran.tipRestorana" type = "text"/><br />
  <h4>Lokacija restorana</h4>
  <label for="geografskaDuzina">Geografska dužina:</label>
  <input type="number" v-model="restoran.geografskaDuzina"/><br />
  <label for="geografskaSirina">Geografska širina:</label>
  <input type="number" v-model="restoran.geografskaSirina"/><br />
  <label for="adresa">Adresa:</label>
  <input v-model="restoran.adresa" type = "text"/><br />

  <button v-on:click="submit()" class="btn btn-primary">submit</button>
</template>

<script>
import axios from "axios"
export default {
  name: "CreateRestoranView",
  data: function () {
    return {
      restoran: {
       "naziv" : "",
       "tipRestorana" : "",
       "geografskaDuzina" : null,
       "geografskaSirina" : null,
       "adresa" : ""
      },
      restorani : []
    };
  },
   mounted: function () {
   },
  methods: {
    submit: function () {

      axios
        .post("http://localhost:8080/api/admin/create-restoran", this.restoran, {withCredentials: true})
        .then((res) => {
          console.log(res);
          this.$router.push("/admin");
        })
        .catch((error) => {
         console.log(error.response)
          alert("Something went wrong!");
          });
    },
  },
};
</script>
<style>
        input,
        select {
            width: 200px;
            height: 25px;
            margin: 2px;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }
</style>