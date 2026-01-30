// 格式化工具函数

// 格式化价格
export const formatPrice = (price) => {
  if (!price && price !== 0) return '¥0.00'
  return `¥${Number(price).toFixed(2)}`
}

// 格式化日期
export const formatDate = (date, format = 'YYYY-MM-DD HH:mm:ss') => {
  if (!date) return ''

  const d = new Date(date)
  const year = d.getFullYear()
  const month = String(d.getMonth() + 1).padStart(2, '0')
  const day = String(d.getDate()).padStart(2, '0')
  const hour = String(d.getHours()).padStart(2, '0')
  const minute = String(d.getMinutes()).padStart(2, '0')
  const second = String(d.getSeconds()).padStart(2, '0')

  const map = {
    'YYYY': year,
    'MM': month,
    'DD': day,
    'HH': hour,
    'mm': minute,
    'ss': second
  }

  return format.replace(/YYYY|MM|DD|HH|mm|ss/g, (matched) => map[matched])
}

// 格式化日期（相对时间）
export const formatRelativeTime = (date) => {
  if (!date) return ''

  const now = new Date()
  const target = new Date(date)
  const diff = now - target

  const minute = 60 * 1000
  const hour = 60 * minute
  const day = 24 * hour
  const week = 7 * day
  const month = 30 * day
  const year = 365 * day

  if (diff < minute) {
    return '刚刚'
  } else if (diff < hour) {
    return Math.floor(diff / minute) + '分钟前'
  } else if (diff < day) {
    return Math.floor(diff / hour) + '小时前'
  } else if (diff < week) {
    return Math.floor(diff / day) + '天前'
  } else if (diff < month) {
    return Math.floor(diff / week) + '周前'
  } else if (diff < year) {
    return Math.floor(diff / month) + '个月前'
  } else {
    return Math.floor(diff / year) + '年前'
  }
}

// 截断字符串
export const truncate = (str, length = 50, suffix = '...') => {
  if (!str) return ''
  if (str.length <= length) return str
  return str.substring(0, length) + suffix
}

// 将图片字符串转为数组
export const parseImages = (images) => {
  if (!images) return []
  if (Array.isArray(images)) return images
  return images.split(',').filter(img => img.trim())
}

// 获取商品状态文本
export const getProductStatusText = (status) => {
  const statusMap = {
    0: '下架',
    1: '上架'
  }
  return statusMap[status] || '未知'
}

// 获取订单状态文本
export const getOrderStatusText = (status) => {
  const statusMap = {
    0: '待支付',
    1: '已支付',
    2: '已发货',
    3: '已完成',
    4: '已退款',
    5: '已取消'
  }
  return statusMap[status] || '未知'
}

// 获取订单状态标签类型
export const getOrderStatusType = (status) => {
  const typeMap = {
    0: 'warning',
    1: 'success',
    2: 'primary',
    3: 'success',
    4: 'info',
    5: 'danger'
  }
  return typeMap[status] || 'info'
}