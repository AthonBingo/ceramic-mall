<template>
  <el-card class="product-card" shadow="hover" @click="goToDetail">
    <div class="product-image">
      <el-image
        :src="productImage"
        :alt="product.name"
        fit="cover"
        lazy
      >
        <template #placeholder>
          <div class="image-placeholder">
            <el-icon><Picture /></el-icon>
          </div>
        </template>
        <template #error>
          <div class="image-error">
            <el-icon><PictureFilled /></el-icon>
          </div>
        </template>
      </el-image>
      <div v-if="product.status === 0" class="product-status">
        <el-tag type="info">已下架</el-tag>
      </div>
      <div v-else-if="product.stock === 0" class="product-status">
        <el-tag type="danger">已售罄</el-tag>
      </div>
    </div>
    <div class="product-info">
      <h3 class="product-name" :title="product.name">{{ product.name }}</h3>
      <p class="product-price">
        <span class="price">¥{{ product.price?.toFixed(2) }}</span>
        <span v-if="product.originalPrice" class="original-price">
          ¥{{ product.originalPrice?.toFixed(2) }}
        </span>
      </p>
      <div class="product-meta">
        <span class="sales">已售 {{ product.sales || 0 }}</span>
        <el-rate
          v-if="product.rating"
          v-model="product.rating"
          disabled
          size="small"
        />
      </div>
      <div class="product-actions">
        <el-button
          type="primary"
          size="small"
          :disabled="product.status === 0 || product.stock === 0"
          @click.stop="handleAddToCart"
        >
          <el-icon><ShoppingCart /></el-icon>
          加入购物车
        </el-button>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { useCartStore } from '@/stores/cart'
import { ElMessage } from 'element-plus'
import { Picture, PictureFilled, ShoppingCart } from '@element-plus/icons-vue'
import { parseImages } from '@/utils/format'

const props = defineProps({
  product: {
    type: Object,
    required: true
  }
})

const router = useRouter()
const cartStore = useCartStore()

const productImage = computed(() => {
  const images = parseImages(props.product.images)
  return images[0] || '/placeholder.png'
})

const goToDetail = () => {
  router.push(`/products/${props.product.id}`)
}

const handleAddToCart = async () => {
  const success = await cartStore.addToCart(props.product.id, 1)
  if (success) {
    ElMessage.success('已添加到购物车')
  }
}
</script>

<style scoped>
.product-card {
  cursor: pointer;
  transition: transform 0.3s, box-shadow 0.3s;
  height: 100%;
}

.product-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
}

.product-image {
  position: relative;
  width: 100%;
  height: 200px;
  overflow: hidden;
  border-radius: 4px;
}

.product-image :deep(.el-image) {
  width: 100%;
  height: 100%;
}

.image-placeholder,
.image-error {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f7fa;
  color: #909399;
  font-size: 48px;
}

.product-status {
  position: absolute;
  top: 10px;
  left: 10px;
}

.product-info {
  padding: 10px 0;
}

.product-name {
  margin: 0 0 10px 0;
  font-size: 16px;
  font-weight: 500;
  color: #303133;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.product-price {
  margin: 10px 0;
  display: flex;
  align-items: center;
  gap: 10px;
}

.price {
  font-size: 20px;
  font-weight: bold;
  color: #ff5000;
}

.original-price {
  font-size: 14px;
  color: #c0c4cc;
  text-decoration: line-through;
}

.product-meta {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 10px 0;
  font-size: 14px;
  color: #909399;
}

.product-actions {
  margin-top: 15px;
}
</style>