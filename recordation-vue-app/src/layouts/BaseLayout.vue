<template>
  <div class="app-backgound">
    <Navbar />
  </div>
</template>
<script>
import Navbar from "../components/Navbar.vue";
export default {
  components: { Navbar },
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
