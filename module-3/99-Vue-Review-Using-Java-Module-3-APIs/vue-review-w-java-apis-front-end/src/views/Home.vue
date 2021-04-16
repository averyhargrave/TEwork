<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/squirrel-hotel-2.jpg" />
    <Home />
  </div>
</template>

<script>
               // @ is an alias to /src
import Home from "@/components/Home.vue";

export default {
  name: "home",
  components: {
    Home
  }
};
</script>
