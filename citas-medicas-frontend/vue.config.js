const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 3000, // puerto donde corre el frontend
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // backend en el puerto 8080
        changeOrigin: true,
        pathRewrite: { '^/api': '' }
      }
    }
  }
});
