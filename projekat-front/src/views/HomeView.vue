<template>
  <div class="home">
    <div class="logoVeliki">
      <p class="white-text">dostava<span class="orange-span">019</span></p>
    </div>
    <a type="button" href="/login" class="btn btn-primary">Prijavite se</a>
    <p>Još uvek nemate nalog? <a href="/register">Registrujte se</a></p>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "HomeView",
  components: {
    HelloWorld
  }
};
</script>

<style scoped>
.logoVeliki {
  font-size: 50px;
  text-align: center;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
  margin-top: 20%;
}

.logoMali {
  font-size: 20px;
  text-align: left;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
}

.white-text {
  color: cornflowerblue;
}

.orange-span {
  color: orangered;
}
a {
  text-decoration: none;
  margin-top: 10px;
}

p {
  margin-top: 5px;
}
</style>
