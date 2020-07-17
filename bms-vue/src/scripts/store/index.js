import Vue from "vue";
import Vuex from "vuex";
import common from "./common";

Vue.use(Vuex);

const persistStore = store => {
  store.subscribe((mutation, state) => {
    if (window.sessionStorage) {
      window.sessionStorage.setItem("yl-platForm-sess-state", JSON.stringify(state));
    }
  });
};

export default new Vuex.Store({
  modules: {
    common
  },
  plugins: [persistStore]
});
