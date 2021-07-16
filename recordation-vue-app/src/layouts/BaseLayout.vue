<template>
  <div class="app-backgound">
    <Navbar />
    <Home />
  </div>
</template>
<script>
import Navbar from "../components/Navbar.vue";
import Home from "../views/Home.vue"
export default {
  components: { Navbar, Home },
};
</script>
<style lang="scss" scoped>
.app-background {
  background-color: white;
  width: 100vw;
  height: 100vh;
  overflow-x: hidden;
}
</style>
