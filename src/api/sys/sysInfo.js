import apiClient from '../client'

/**
 * 系统信息管理API
 */
export const sysInfoApi = {
  // 分页查询系统信息列表
  getSysInfoList: (params) => {
    return apiClient.get('/sys/info/list', { params })
  },
  
  // 获取系统信息详情
  getSysInfoById: (id) => {
    return apiClient.get(`/sys/info/${id}`)
  },

  
  // 新增系统信息
  addSysInfo: (data) => {
    return apiClient.post('/sys/info', data)
  },
  
  // 修改系统信息
  updateSysInfo: (data) => {
    return apiClient.put('/sys/info', data)
  },
  
  // 删除系统信息
  deleteSysInfo: (ids) => {
    return apiClient.delete(`/sys/info/${ids.join(',')}`)
  }
}
