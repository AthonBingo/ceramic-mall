import request from './request'

// 登录
export const login = (data) => {
  return request.post('/auth/login', data)
}

// 注册
export const register = (data) => {
  return request.post('/auth/register', data)
}

// 刷新token
export const refreshToken = (token) => {
  return request.post('/auth/refresh', { token })
}