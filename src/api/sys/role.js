import apiClient from '../client'

/**
 * 角色管理API
 */
export const roleApi = {
  // 分页查询角色列表
  getRoleList: (params) => {
    return apiClient.get('/system/role/list', { params })
  },
  
  // 获取角色详情
  getRoleById: (id) => {
    return apiClient.get(`/system/role/${id}`)
  },
  
  // 新增角色
  addRole: (data) => {
    return apiClient.post('/system/role', data)
  },
  
  // 修改角色
  updateRole: (data) => {
    return apiClient.put('/system/role', data)
  },
  
  // 删除角色
  deleteRole: (ids) => {
    return apiClient.delete(`/system/role/${ids.join(',')}`)
  },
  
  // 修改角色状态
  changeRoleStatus: (data) => {
    return apiClient.put('/system/role/changeStatus', data)
  }
}
