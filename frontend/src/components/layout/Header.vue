<template>
  <el-header class="header">
    <div class="header-container">
      <!-- Logo -->
      <router-link to="/" class="logo">
        <el-icon class="logo-icon"><Box /></el-icon>
        <span class="logo-text">陶瓷商城</span>
      </router-link>

      <!-- 导航菜单 -->
      <el-menu
        mode="horizontal"
        :default-active="activeMenu"
        class="nav-menu"
        router
      >
        <el-menu-item index="/">首页</el-menu-item>
        <el-menu-item index="/products">商品</el-menu-item>
        <el-menu-item index="/about">关于我们</el-menu-item>
        <el-menu-item index="/contact">联系我们</el-menu-item>
      </el-menu>

      <!-- 搜索框 -->
      <div class="search-box">
        <el-input
          v-model="searchKeyword"
          placeholder="搜索商品..."
          @keyup.enter="handleSearch"
        >
          <template #append>
            <el-button :icon="Search" @click="handleSearch" />
          </template>
        </el-input>
      </div>

      <!-- 用户操作 -->
      <div class="user-actions">
        <template v-if="userStore.isAuthenticated">
          <el-dropdown>
            <span class="user-dropdown">
              <el-avatar :size="32" :src="userStore.userInfo?.avatar">
                {{ userStore.userInfo?.username?.charAt(0) }}
              </el-avatar>
              <span class="username">{{ userStore.userInfo?.username }}</span>
              <el-icon><ArrowDown /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="$router.push('/user/profile')">
                  <el-icon><User /></el-icon>个人中心
                </el-dropdown-item>
                <el-dropdown-item @click="$router.push('/orders')">
                  <el-icon><List /></el-icon>我的订单
                </el-dropdown-item>
                <el-dropdown-item divided @click="handleLogout">
                  <el-icon><SwitchButton /></el-icon>退出登录
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
        <template v-else>
          <el-button type="primary" @click="$router.push('/auth/login')">登录</el-button>
          <el-button @click="$router.push('/auth/register')">注册</el-button>
        </template>

        <!-- 购物车 -->
        <el-badge :value="cartStore.totalCount" :hidden="cartStore.totalCount === 0" class="cart-badge">
          <el-button :icon="ShoppingCart" circle @click="$router.push('/cart')" />
        </el-badge>
      </div>
    </div>
  </el-header>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { useCartStore } from '@/stores/cart'
import {
  Box,
  Search,
  ArrowDown,
  User,
  List,
  SwitchButton,
  ShoppingCart
} from '@element-plus/icons-vue'

const router = useRouter()
const userStore = useUserStore()
const cartStore = useCartStore()

const searchKeyword = ref('')

const activeMenu = computed(() => {
  const path = router.currentRoute.value.path
  if (path.startsWith('/products')) return '/products'
  return path
})

const handleSearch = () => {
  if (searchKeyword.value.trim()) {
    router.push({
      path: '/products',
      query: { keyword: searchKeyword.value.trim() }
    })
  }
}

const handleLogout = async () => {
  userStore.logout()
  router.push('/')
}

// 初始化购物车
cartStore.getCartList()
</script>

<style scoped>
.header {
  background: #fff;
  border-bottom: 1px solid #e4e7ed;
  padding: 0;
  height: 60px;
  position: sticky;
  top: 0;
  z-index: 1000;
}

.header-container {
  max-width: 1200px;
  margin: 0 auto;
  height: 100%;
  display: flex;
  align-items: center;
  padding: 0 20px;
}

.logo {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: #303133;
  margin-right: 40px;
}

.logo-icon {
  font-size: 32px;
  color: #409EFF;
  margin-right: 10px;
}

.logo-text {
  font-size: 24px;
  font-weight: bold;
}

.nav-menu {
  flex: 1;
  border: none;
}

.search-box {
  width: 300px;
  margin-right: 20px;
}

.user-actions {
  display: flex;
  align-items: center;
  gap: 20px;
}

.user-dropdown {
  display: flex;
  align-items: center;
  cursor: pointer;
  outline: none;
}

.username {
  margin: 0 8px;
  font-size: 14px;
}

.cart-badge {
  margin-left: 10px;
}
</style>