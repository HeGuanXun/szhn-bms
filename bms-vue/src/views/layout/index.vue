<!-- 案列页面 -->
<template>
  <div id="home" class="fullsize">
    <el-container class="fullsize">
      <el-aside width="100px">
        <asidenav></asidenav>
      </el-aside>
      <el-container>
        <el-header>
          <topbar></topbar>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import asidenav from "./nav.vue";
import topbar from "./topbar.vue";
export default {
  data() {
    return {};
  },

  components: { asidenav, topbar },

  computed: {},

  mounted() {},

  methods: {}
};
</script>
<style lang="scss" scoped></style>
