<template>
  <div class="home">
    <div class="logoVeliki">
        <p class="white-text">dostava<span class="orange-span">019</span></p>

    </div>
    <a type="button" href="/login">Prijavite se</a>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script>


<style scoped>
.logoVeliki{
    font-size:50px;
    text-align: center;
    font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

.logoMali{
    font-size: 20px;
    text-align: left;
    font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

.white-text{
    color: cornflowerblue;
}

.orange-span{
    color: orangered;
}
a{
  text-decoration: none;
}
</style>
