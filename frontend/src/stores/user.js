import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { ElMessage } from 'element-plus'
import * as authAPI from '@/api/auth'
import * as userAPI from '@/api/user'

export const useUserStore = defineStore('user', () => {
  // 状态
  const token = ref(localStorage.getItem('token') || '')
  const userInfo = ref(JSON.parse(localStorage.getItem('userInfo') || '{}'))
  const isAuthenticated = computed(() => !!token.value)

  // 登录
  const login = async (username, password) => {
    try {
      const res = await authAPI.login({ username, password })
      if (res.code === 0) {
        token.value = res.data
        localStorage.setItem('token', res.data)
        await getUserInfo()
        ElMessage.success('登录成功')
        return true
      } else {
        ElMessage.error(res.message)
        return false
      }
    } catch (error) {
      ElMessage.error('登录失败')
      return false
    }
  }

  // 注册
  const register = async (userData) => {
    try {
      const res = await authAPI.register(userData)
      if (res.code === 0) {
        ElMessage.success('注册成功')
        return true
      } else {
        ElMessage.error(res.message)
        return false
      }
    } catch (error) {
      ElMessage.error('注册失败')
      return false
    }
  }

  // 获取用户信息
  const getUserInfo = async () => {
    try {
      const res = await userAPI.getUserInfo()
      if (res.code === 0) {
        userInfo.value = res.data
        localStorage.setItem('userInfo', JSON.stringify(res.data))
      }
    } catch (error) {
      console.error('获取用户信息失败', error)
    }
  }

  // 更新用户信息
  const updateUserInfo = async (userData) => {
    try {
      const res = await userAPI.updateUserInfo(userData)
      if (res.code === 0) {
        userInfo.value = { ...userInfo.value, ...userData }
        localStorage.setItem('userInfo', JSON.stringify(userInfo.value))
        ElMessage.success('更新成功')
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

  // 修改密码
  const changePassword = async (oldPassword, newPassword) => {
    try {
      const res = await userAPI.changePassword({ oldPassword, newPassword })
      if (res.code === 0) {
        ElMessage.success('密码修改成功')
        return true
      } else {
        ElMessage.error(res.message)
        return false
      }
    } catch (error) {
      ElMessage.error('密码修改失败')
      return false
    }
  }

  // 登出
  const logout = () => {
    token.value = ''
    userInfo.value = {}
    localStorage.removeItem('token')
    localStorage.removeItem('userInfo')
    ElMessage.success('已登出')
  }

  // 上传头像
  const uploadAvatar = async (file) => {
    try {
      const res = await userAPI.uploadAvatar(file)
      if (res.code === 0) {
        userInfo.value.avatar = res.data
        localStorage.setItem('userInfo', JSON.stringify(userInfo.value))
        ElMessage.success('头像上传成功')
        return true
      } else {
        ElMessage.error(res.message)
        return false
      }
    } catch (error) {
      ElMessage.error('头像上传失败')
      return false
    }
  }

  return {
    token,
    userInfo,
    isAuthenticated,
    login,
    register,
    getUserInfo,
    updateUserInfo,
    changePassword,
    logout,
    uploadAvatar
  }
})