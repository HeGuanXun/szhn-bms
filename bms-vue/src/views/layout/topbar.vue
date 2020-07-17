<template>
  <div class="fullsize topbar">
    <div style="margin-left:20px;margin-top:33px">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane
          :label="item.label"
          :name="item.name"
          :data-list="item.navList"
          v-for="(item,index) in tabBarList"
          :key="index"
          :index="item.url"
        ></el-tab-pane>
      </el-tabs>
    </div>
    <div class="centerflex">
      <el-menu class="el-menu-demo" mode="horizontal" default-active>
        <el-submenu index="1">
          <template slot="title" >
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            {{ username }}
          </template>
          <el-menu-item index="logout" @click.native="logout">退出</el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { eventBus } from "scripts/utils/BUS";
const { log } = console;
import url from "api";
import store from "scripts/store";
import * as types from "scripts/store/mutation-types";
export default {
  data() {
    return {
      roleName:null,
      eventBus:'password_eventBus',
      home_url:"/home",
      activeName: "home",
      tabBarList: [
        {
          label:'主页',
          name: "home",
          navList: [
            { name: '主页', url: "/home" }
          ]
        },
        {
          label: '图书',
          name: "book",
          navList: [
            { name: '图书管理', url: "/book" },
          ]
        }
      ],
      isShow:false,
      form:{},
      index:0,
      title:"",
    };
  },
  computed: {
    ...mapGetters([
      'username'
    ])
  },

  mounted() {
    this.roleName = store.getters.username;
    document.title = '图书管理系统';
    eventBus.$emit("navList", this.tabBarList[0].navList);
  },

  methods: {
    handleClick(tab) {
      eventBus.$emit("navList", this.tabBarList[tab.index].navList);
    },

    logout() {
      let self = this;
      let data = "access_token=" + self.$store.getters.token;
      let param = {
        grant_type: "password",
        client_id: "bms",
        client_secret: "bms",
        scope: "xx"
      };
      localStorage.removeItem("login_info");
      self.$store.commit(types.LOGOUT);
      self.$router.push("/login");
      this.$axios
        .delete(url.login, {
          params: param,
          data: data,
          headers: { "Content-Type": "application/x-www-form-urlencoded" }
        })
        .then(function(res) {
          log(res);
        })
        .catch(error => {
          log(error.response.data);
        });
    },
  }
};
</script>
<style >
.topbar {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background-color: rgb(216, 216, 216);
  .el-menu {
    background-color: rgb(0, 0, 0, 0);
    border: 0;
  }
}

.el-menu--popup {
  min-width: 80px !important;
  width: 80px !important;
  .el-menu-item {
    min-width: 80px !important;
    width: 80px !important;
  }
}
.erp{
  font-size: 30px;
  color: aliceblue;
  margin-top: 15px;
}
</style>
