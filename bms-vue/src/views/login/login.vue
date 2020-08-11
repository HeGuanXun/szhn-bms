<template>
  <el-form
    :model="loginForm"
    :rules="fieldRules"
    ref="loginForm"
    label-position="left"
    label-width="0px"
    class="demo-ruleForm login-container"
    style="width: 400px"
  >
    <div style="display:flex;  justify-content: flex-end;">
      <h2 class="title">拓尔思Vue2.0测试平台</h2>
    </div>
    <el-form-item prop="account">
      <el-input
        type="text"
        v-model="loginForm.account"
        auto-complete="off"
        placeholder="请输入账号"
        suffix-icon="el-icon-user"
      ></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input
        v-model="loginForm.password"
        auto-complete="off"
        placeholder="请输入密码"
        @keyup.enter.native.prevent="login"
        show-password
      ></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" class="remember">记住我</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button
        type="primary"
        style="width:48%;"
        @click.native.prevent="login"
        :loading="loading"
        >登录</el-button
      >
    </el-form-item>
  </el-form>
</template>

<script>
import vm from "@/main";
import Vue from "vue";
const localStorage = window.localStorage;
const { log } = console;
import url from "api";
import * as types from "scripts/store/mutation-types";
export default {
  name: "Login",
  data() {
    return {
      vm: vm,
      fieldRules: {
        account: [
          {
            required: true,
            message: '请输入账号',
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: "blur"
          }
        ]
      },
      loading: false,
      loginForm: {
        account: "",
        password: ""
      },
      checked: true
    };
  },
  methods: {
    login() {
      const self = this;
      self.loading = true;
      let login = {
        username: self.loginForm.account,
        password: self.loginForm.password,
      };
      this.$axios({
        method: "get",
        url: url.login,
        params: login
      })
        .then(res => {
          console.log(res.data.result);
          self.$store.commit(types.TOKEN, res.data.result.token);
          self.$store.commit(types.USERNAME, res.data.result.name);
          let login_info = JSON.stringify(self.loginForm);
          if (self.checked) {
            localStorage.setItem("login_info", login_info);
          } else {
            if (localStorage.getItem("login_info")) {
              localStorage.removeItem("login_info");
            }
          }
          this.$router.push("home");
          // this.$axios.get(url.navbar_url).then(res => {
          //   let data = res.data.data;
          //   self.$store.commit(types.USERNAME, data.username);
          //   self.$store.commit(types.NAV, data.platformRole);
          //   if (data.platformRole==21 || data.platformRole==31 || data.platformRole==41|| data.platformRole==51){
          //     this.$message.error(self.$t("login.login"));
          //     this.loading = false;
          //   }else {
          //     Vue.prototype.$platformRole = data.platformRole;
          //     this.$router.push("home");
          //   }
          // });
        })
        .catch(error => {
          log(error.response);
          this.$message.error('登录失败，请联系管理员');
          self.loading = false;
        });
    },
    remeber() {
      let self = this;
      if (localStorage.getItem("login_info")) {
        let login_info = JSON.parse(localStorage.getItem("login_info"));
        self.loginForm = login_info;
      }
    }
  },
  mounted() {
    this.remeber();
  },
  computed: {}
};
</script>

<style lang="scss" scoped>
.login-container {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: 100px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  .title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #505458;
  }
  .remember {
    margin: 0px 0px 35px 0px;
  }
}
</style>
