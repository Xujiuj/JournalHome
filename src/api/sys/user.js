import apiClient from '../client'

/**
 * 用户管理API
 */
export const userApi = {
  // 分页查询用户列表
  getUserList: (params) => {
    return apiClient.get('/system/user/list', { params })
  },
  
  // 获取用户详情
  getUserById: (id) => {
    return apiClient.get(`/system/user/${id}`)
  },
  
  // 新增用户
  addUser: (data) => {
    return apiClient.post('/system/user', data)
  },
  
  // 修改用户
  updateUser: (data) => {
    return apiClient.put('/system/user', data)
  },
  
  // 删除用户
  deleteUser: (ids) => {
    return apiClient.delete(`/system/user/${ids.join(',')}`)
  },
  
  // 重置用户密码
  resetUserPassword: (data) => {
    return apiClient.put('/system/user/resetPwd', data)
  },
  
  // 修改用户状态
  changeUserStatus: (data) => {
    return apiClient.put('/system/user/changeStatus', data)
  }
}
