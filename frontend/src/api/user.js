import request from './request'

// 获取用户信息
export const getUserInfo = () => {
  return request.get('/user/profile')
}

// 更新用户信息
export const updateUserInfo = (data) => {
  return request.put('/user/profile', data)
}

// 修改密码
export const changePassword = (data) => {
  return request.post('/user/change-password', data)
}

// 上传头像
export const uploadAvatar = (file) => {
  const formData = new FormData()
  formData.append('file', file)
  return request.post('/user/upload-avatar', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}