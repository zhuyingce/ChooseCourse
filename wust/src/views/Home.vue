<template>
  <div class="home">
    <Conformity/>
  </div>
</template>

<script>
  import Conformity from "../components/home/Conformity";

  export default {
    name: "Home",
    components: {
      Conformity
    }
  };
</script>

<style scoped>
  .home {
    height: 100%;
    background-color: whitesmoke;
  }
</style>
