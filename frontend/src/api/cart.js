import request from './request'

// 添加到购物车
export const addToCart = (productId, quantity = 1) => {
  return request.post('/cart/add', { productId, quantity })
}

// 获取购物车列表
export const getCartList = () => {
  return request.get('/cart/list')
}

// 更新购物车商品数量
export const updateQuantity = (cartId, quantity) => {
  return request.put('/cart/quantity', { cartId, quantity })
}

// 从购物车移除商品
export const removeFromCart = (cartId) => {
  return request.delete('/cart/remove', { params: { cartId } })
}

// 清空购物车
export const clearCart = () => {
  return request.post('/cart/clear')
}

// 计算购物车总金额
export const calculateTotalAmount = () => {
  return request.get('/cart/total-amount')
}