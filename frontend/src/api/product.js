import request from './request'

// 分页查询商品列表
export const getProductList = (params) => {
  return request.get('/products', { params })
}

// 获取商品详情
export const getProductDetail = (id) => {
  return request.get(`/products/${id}`)
}

// 获取热销商品
export const getHotProducts = (limit = 10) => {
  return request.get('/products/hot', { params: { limit } })
}

// 搜索商品
export const searchProducts = (keyword) => {
  return request.get('/products', { params: { keyword } })
}