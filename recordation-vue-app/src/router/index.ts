import Vue from "vue";
import VueRouter from "vue-router";
import BaseLayout from "../layouts/BaseLayout.vue";
import baseRoutes from "./base";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "BaseLayout",
    component: BaseLayout,
    children: [...baseRoutes],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
