import About from "../../views/About.vue";

const baseRoutes = [
    {
        path: "/about",
        name: "About",
        component: About,
        meta: {
            guess: true
        }
    },
]

export default baseRoutes;
