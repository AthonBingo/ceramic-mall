<template>
  <div class="carousel-container">
    <el-carousel
      :interval="4000"
      arrow="always"
      indicator-position="outside"
      height="400px"
    >
      <el-carousel-item v-for="item in items" :key="item.id">
        <div
          class="carousel-item"
          :style="{ backgroundImage: `url(${item.image})` }"
          @click="handleItemClick(item)"
        >
          <div class="carousel-content">
            <h2 class="carousel-title">{{ item.title }}</h2>
            <p class="carousel-desc">{{ item.description }}</p>
            <el-button
              v-if="item.buttonText"
              type="primary"
              size="large"
              @click.stop="handleButtonClick(item)"
            >
              {{ item.buttonText }}
            </el-button>
          </div>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'

const props = defineProps({
  items: {
    type: Array,
    default: () => []
  }
})

const emit = defineEmits(['item-click', 'button-click'])

const handleItemClick = (item) => {
  emit('item-click', item)
}

const handleButtonClick = (item) => {
  emit('button-click', item)
}

onMounted(() => {
  // 如果没有传入轮播项，使用默认的
  if (props.items.length === 0) {
    console.warn('Carousel: No items provided')
  }
})
</script>

<style scoped>
.carousel-container {
  margin-bottom: 40px;
}

.carousel-item {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
  position: relative;
  cursor: pointer;
}

.carousel-item::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.4);
}

.carousel-content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  color: white;
  z-index: 1;
}

.carousel-title {
  font-size: 48px;
  font-weight: bold;
  margin-bottom: 20px;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
}

.carousel-desc {
  font-size: 20px;
  margin-bottom: 30px;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.5);
}

:deep(.el-carousel__container) {
  height: 400px;
}

@media (max-width: 768px) {
  .carousel-title {
    font-size: 32px;
  }

  .carousel-desc {
    font-size: 16px;
  }

  :deep(.el-carousel__container) {
    height: 250px;
  }
}
</style>