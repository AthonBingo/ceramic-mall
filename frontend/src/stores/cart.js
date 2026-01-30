import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { ElMessage } from 'element-plus'
import * as cartAPI from '@/api/cart'

export const useCartStore = defineStore('cart', () => {
  // 状态
  const cartItems = ref([])
  const loading = ref(false)

  // 计算属性
  const totalCount = computed(() => {
    return cartItems.value.reduce((sum, item) => sum + item.quantity, 0)
  })

  const totalAmount = computed(() => {
    return cartItems.value.reduce((sum, item) => {
      return sum + (item.product?.price || 0) * item.quantity
    }, 0)
  })

  // 获取购物车列表
  const getCartList = async () => {
    loading.value = true
    try {
      const res = await cartAPI.getCartList()
      if (res.code === 0) {
        cartItems.value = res.data
      }
    } catch (error) {
      ElMessage.error('获取购物车失败')
    } finally {
      loading.value = false
    }
  }

  // 添加到购物车
  const addToCart = async (productId, quantity = 1) => {
    try {
      const res = await cartAPI.addToCart(productId, quantity)
      if (res.code === 0) {
        await getCartList()
        ElMessage.success('添加成功')
        return true
      } else {
        ElMessage.error(res.message)
        return false
      }
    } catch (error) {
      ElMessage.error('添加失败')
      return false
    }
  }

  // 更新数量
  const updateQuantity = async (cartId, quantity) => {
    try {
      const res = await cartAPI.updateQuantity(cartId, quantity)
      if (res.code === 0) {
        await getCartList()
        return true
      } else {
        ElMessage.error(res.message)
        return false
      }
    } catch (error) {
      ElMessage.error('更新失败')
      return false
    }
  }

  // 从购物车移除
  const removeFromCart = async (cartId) => {
    try {
      const res = await cartAPI.removeFromCart(cartId)
      if (res.code === 0) {
        await getCartList()
        ElMessage.success('移除成功')
        return true
      } else {
        ElMessage.error(res.message)
        return false
      }
    } catch (error) {
      ElMessage.error('移除失败')
      return false
    }
  }

  // 清空购物车
  const clearCart = async () => {
    try {
      const res = await cartAPI.clearCart()
      if (res.code === 0) {
        cartItems.value = []
        ElMessage.success('清空成功')
        return true
      } else {
        ElMessage.error(res.message)
        return false
      }
    } catch (error) {
      ElMessage.error('清空失败')
      return false
    }
  }

  // 计算购物车总金额
  const calculateTotalAmount = async () => {
    try {
      const res = await cartAPI.calculateTotalAmount()
      if (res.code === 0) {
        return res.data
      }
      return 0
    } catch (error) {
      console.error('计算总金额失败', error)
      return 0
    }
  }

  return {
    cartItems,
    loading,
    totalCount,
    totalAmount,
    getCartList,
    addToCart,
    updateQuantity,
    removeFromCart,
    clearCart,
    calculateTotalAmount
  }
})