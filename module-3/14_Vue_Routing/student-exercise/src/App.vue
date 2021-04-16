<template>
  <div id="app">
    <router-link v-bind:to="{name:'home'}"> Home |</router-link>
    <router-link v-bind:to="{name:'MyBooks'}"> MyBooks | </router-link>
    <router-link v-bind:to="{name:'NewBook'}"> NewBook </router-link>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2C3E50;
  margin: 60px;
}
</style>