// 认证相关工具函数

// 从token获取用户ID
export const getUserIdFromToken = () => {
  const token = localStorage.getItem('token')
  if (!token) return null

  try {
    const payload = JSON.parse(atob(token.split('.')[1]))
    return payload.userId
  } catch (error) {
    console.error('解析token失败', error)
    return null
  }
}

// 从token获取用户名
export const getUsernameFromToken = () => {
  const token = localStorage.getItem('token')
  if (!token) return null

  try {
    const payload = JSON.parse(atob(token.split('.')[1]))
    return payload.username
  } catch (error) {
    console.error('解析token失败', error)
    return null
  }
}

// 检查是否已登录
export const isAuthenticated = () => {
  const token = localStorage.getItem('token')
  return !!token
}

// 保存token
export const saveToken = (token) => {
  localStorage.setItem('token', token)
}

// 获取token
export const getToken = () => {
  return localStorage.getItem('token')
}

// 清除认证信息
export const clearAuth = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('userInfo')
}

// 保存用户信息
export const saveUserInfo = (userInfo) => {
  localStorage.setItem('userInfo', JSON.stringify(userInfo))
}

// 获取用户信息
export const getUserInfo = () => {
  const userInfo = localStorage.getItem('userInfo')
  return userInfo ? JSON.parse(userInfo) : null
}

// 检查是否有权限
export const hasPermission = (permission) => {
  const userInfo = getUserInfo()
  if (!userInfo || !userInfo.permissions) return false
  return userInfo.permissions.includes(permission)
}