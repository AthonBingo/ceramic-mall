import request from './request'

// 获取所有顶级分类
export const getTopLevelCategories = () => {
  return request.get('/categories/top-level')
}

// 获取子分类
export const getChildrenCategories = (parentId) => {
  return request.get(`/categories/children/${parentId}`)
}

// 获取所有分类
export const getAllCategories = () => {
  return request.get('/categories')
}

// 获取分类树
export const getCategoryTree = async () => {
  const topLevelRes = await getTopLevelCategories()
  if (topLevelRes.code !== 0) return []

  const tree = []
  for (const category of topLevelRes.data) {
    const childrenRes = await getChildrenCategories(category.id)
    tree.push({
      ...category,
      children: childrenRes.code === 0 ? childrenRes.data : []
    })
  }
  return tree
}