import request from './request'

// 创建订单
export const createOrder = (data) => {
  return request.post('/orders/create', data)
}

// 获取订单详情
export const getOrderDetail = (orderId) => {
  return request.get(`/orders/${orderId}`)
}

// 获取订单列表
export const getOrderList = (params) => {
  return request.get('/orders/list', { params })
}

// 取消订单
export const cancelOrder = (orderId) => {
  return request.post(`/orders/${orderId}/cancel`)
}

// 支付订单
export const payOrder = (orderId) => {
  return request.post(`/orders/${orderId}/pay`)
}