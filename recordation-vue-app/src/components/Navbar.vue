<template>
  <div>
    <b-navbar
      toggleable="md"
      class="shadow-sm px-5"
      type="light"
      variant="light"
    >
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav justified>
          <b-nav-item to="/home">Home</b-nav-item>
          <b-nav-item to="/about">About</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <router-view />
  </div>
</template>
<script>
export default {};
</script>
