import Vue from "vue";
import VueRouter from "vue-router";
if (!window.VueRouter) Vue.use(VueRouter);

const routes = [
  { path: "/login", component: () => import("../views/login/login") },
  {
    path: "",
    name: "index",
    redirect: "/home",
    component: () => import("../views/layout"),
    children: [
      {
        path: "home",
        name: "home",
        component: () => import("../views/home/index"),
        meta: { title: "首页" }
      },
      {
        path: "book",
        name: "book",
        component: () => import("../views/book/index"),
        meta: { title: "图书" }
      },
    ]
  }
];

const router = new VueRouter({
  routes
});

//路由守卫
router.beforeEach((to, from, next) => {
  let store = JSON.parse(window.sessionStorage.getItem("yl-platForm-sess-state"));
  if (store?.common?.token) {
    if (to.path === "/login") {
      next({ path: "/home" });
      eventBus.$emit("titleChange", "首页");
    } else {
      next();
    }
  } else {
    if (to.path === "/login") {
      //loop
      next();
    } else {
      next({ path: "/login" });
    }
  }
});

export default router;
