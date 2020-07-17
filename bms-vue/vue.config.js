const path = require("path");

function resolve(dir) {
  return path.join(__dirname, dir);
}
module.exports = {

  lintOnSave: false,
  //publicPath: 'https://shophome.oss-cn-beijing.aliyuncs.com/platform/',
  chainWebpack: config => {
    config.resolve.alias
        .set("@$", resolve("src"))
        .set("views", resolve("src/views"))
        .set("api", resolve("src/scripts/api"))
        .set("scripts", resolve("src/scripts"));
    config.plugins.delete('prefetch');

  },
  devServer: {
    port: "8088",
    open: false,
    proxy: {
      "/apiproxy": {
        target: "http://localhost:8080/",
        changeOrigin: true,
        pathRewrite: {
          "^/apiproxy": ""
        },
        secure: false
      }
    }
  }
};
