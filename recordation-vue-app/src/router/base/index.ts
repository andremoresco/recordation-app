import Home from "../../views/Home.vue";
import About from "../../views/About.vue";

const baseRoutes = [
    {
        path: "/home",
        component: Home,
        name: "Home",
    },
    {
        path: "/about",
        name: "About",
        component: About
    },
]

export default baseRoutes;
