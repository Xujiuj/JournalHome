import apiClient from '../client'

/**
 * 菜单管理API
 */
export const menuApi = {
  // 分页查询菜单列表
  getMenuList: (params) => {
    const {
      pageNum = 1,
      pageSize = 100,
      ...filters
    } = params || {}

    return apiClient.get('/sys/menu/list', {
      params: {
        pageNum,
        pageSize,
        ...filters
      }
    })
  },
  
  // 获取菜单详情
  getMenuById: (id) => {
    return apiClient.get(`/sys/menu/${id}`)
  },
  
  // 新增菜单
  addMenu: (data) => {
    return apiClient.post('/sys/menu', data)
  },
  
  // 修改菜单
  updateMenu: (data) => {
    return apiClient.put('/sys/menu', data)
  },
  
  // 删除菜单
  deleteMenu: (ids) => {
    return apiClient.delete(`/sys/menu/${ids.join(',')}`)
  },
  
  // 获取菜单树
  getMenuTree: (params) => {
    return apiClient.get('/sys/menu/treeselect', { params })
  }
}
