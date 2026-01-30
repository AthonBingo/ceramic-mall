<template>
  <div id="app">
    <el-config-provider :locale="zhCn">
      <Header v-if="!isAuthPage" />
      <router-view />
      <Footer v-if="!isAuthPage" />
    </el-config-provider>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

import Header from './components/layout/Header.vue'
import Footer from './components/layout/Footer.vue'

const route = useRoute()
const isAuthPage = computed(() => {
  return route.path.startsWith('/auth')
})
</script>

<style>
#app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

body {
  margin: 0;
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', 'Microsoft YaHei', sans-serif;
}

/* 全局样式 */
.page-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  flex: 1;
}

.card {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>