<template>
  <div class="sidebar">
    <div v-if="title" class="sidebar-title">
      <h3>{{ title }}</h3>
    </div>
    <div class="sidebar-content">
      <slot></slot>
    </div>
  </div>
</template>

<script setup>
defineProps({
  title: {
    type: String,
    default: ''
  }
})
</script>

<style scoped>
.sidebar {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.sidebar-title {
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid #e4e7ed;
}

.sidebar-title h3 {
  margin: 0;
  font-size: 18px;
  color: #303133;
}

.sidebar-content {
  font-size: 14px;
  color: #606266;
}
</style>