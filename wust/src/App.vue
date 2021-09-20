<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

import Log_Reg from "./views/Log_Reg";
import Home from "./views/Home";

export default {
  name: 'App',
  components:{
    Log_Reg,
    Home
  }
}
</script>

<style>
#app {
  height: 100%;
}
</style>
