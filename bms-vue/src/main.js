import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "@/axios";
import store from "./scripts/store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import "@/styles/index.scss";
import * as types from "scripts/store/mutation-types";
import url from "scripts/api/index";
import VueClipboard from "vue-clipboard2";
Vue.use(VueClipboard);
Vue.prototype.$axios = axios;
Vue.prototype.$url = url;
Vue.prototype.$Store = store;
Vue.use(ElementUI);
Vue.config.productionTip = false;
function restoreState() {
  if (window.location.href.indexOf("#/login") < 0) {
    if (
        !window.sessionStorage ||
        !window.sessionStorage.getItem("yl-platForm-sess-state")
    ) {
      router.replace("/login");
      return;
    }
    //save
    const savedstate = JSON.parse(
        window.sessionStorage.getItem("yl-platForm-sess-state")
    );
    if (savedstate) {
      store.commit(types.RESTORE_STATE, savedstate);
      // store.state.age
      if (!savedstate.common.token) router.replace("/login");
    } else {
      router.replace("/login");
    }
  }
}
let vm = new Vue({
  el: '#app',
  router,
  store,
  created() {
    restoreState();
  },
  render: h => h(App),
}).$mount("#app");
export default vm;
