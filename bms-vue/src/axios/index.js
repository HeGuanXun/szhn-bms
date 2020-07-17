import axios from "axios";
import store from "scripts/store";
import { MessageBox } from "element-ui";
import code from "scripts/utils/businessCode";

axios.defaults.timeout = 15000;

axios.interceptors.request.use(
  config => {
      config.withCredentials = false;
      config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
      if (store.getters.token) {
      config.headers["Authorization"] = "Bearer " + store.getters.token;
    }
      return config;
  },
  error => {
    Promise.reject(error);
  }
);

function error(text) {
  MessageBox.confirm(text, {
    showCancelButton:false,
    showConfirmButton:false,
    type: "warning"
  })
}

// respone拦截器
axios.interceptors.response.use(
  response => {
    const res = response.data;
    if (res?.status && res.status != 200) {
        if (code.code[res.businessCode]) {
            error(code.code[res.businessCode].ch);
      } else {
            error("系统错误，请联系管理员查询原因");
      }
      return Promise.reject(res);
    } else {
      return response;
    }
  }
);

export default axios;
