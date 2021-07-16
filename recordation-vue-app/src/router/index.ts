import Vue from "vue";
import VueRouter from "vue-router";
import BaseLayout from "../layouts/BaseLayout.vue";
import baseRoutes from "./base";
import Login from "../views/Login.vue";

Vue.use(VueRouter);


const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "BaseLayout",
      component: BaseLayout,
      children: [...baseRoutes],
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "Login",
      component: Login,
      meta: {
        guess: true
      }
    },
  ],
});

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (localStorage.getItem('jwt') == null) {
      next({
        name: 'Login',
        params: { nextUrl: to.fullPath }
      })
    } else {
      next();
    }

  } else {
    next();
  }

});


export default router;
