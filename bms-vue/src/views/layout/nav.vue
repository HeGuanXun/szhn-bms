<!--  -->
<template>
  <div class="fullsize" id="navBarbox" v-if="reFresh" >
    <el-menu ref="menu"
      :default-active="menu.defaultActive"
      class="el-menu-vertical-demo content"
      style="height:100%;"
      background-color="#545c64"
      text-color="#fff"
      :router="true"
    >
      <div style="width: 166px;margin-top: 70%"></div>
      <el-menu-item
              v-for="(page, i) in navBar"
              :key="i"
              :index="page.url"
      >
        {{ page.name }}
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { eventBus } from "scripts/utils/BUS";
export default {
  data() {
    return {
      reFresh: true,
      menu: {
        defaultActive: ""
      },
      navBar: []
    };
  },

  components: {},

  mounted() {
    let self = this;
    eventBus.$on("navList", nav => {
      self.navBar = nav;
      this.$set(this.menu, 'defaultActive', nav[0].url)
      console.log(nav[0].url);
      this.$router.push(nav[0].url).catch(err => {
        console.debug(err);
      });
      setTimeout(()=>{
        this.reload();
      },200)
    });
  },
  beforeDestroy() {
    eventBus.$off('navList');
  },
  methods: {
    reload() {
      this.reFresh = false
      this.$nextTick(() => {
        this.reFresh = true
      })
    },
    onActive(){
      this.defaultActive = this.defaultActive.toString();
    },
    openHome() {
      this.$router.push("/home");
    }
  }
};
</script>
<style lang="scss">
  html,body {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
  }
  .content {
    background-color:"#545c64";
    margin: 0 auto; /*水平居中*/
    position: relative;
    top: 50%; /*偏移*/
    transform: translateY(-50%);
  }
#navBarbox {
  .el-menu {
    border: 0;
  }
  .erp {
    font-size: 25px;
    color: aliceblue;
    margin-top: 15px;
  }
}
</style>
