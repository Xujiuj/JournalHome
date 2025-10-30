import apiClient from '../client'

/**
 * 期刊卷期API
 */
export const journalVolumeApi = {
  // 分页查询期刊卷期列表
  getVolumeList: (params) => {
    return apiClient.get('/journal/list', { params })
  },
  
  // 获取期刊卷期详情
  getVolumeById: (id) => {
    return apiClient.get(`/journal/${id}`)
  },
  
  // 新增期刊卷期
  addVolume: (data) => {
    return apiClient.post('/journal', data)
  },
  
  // 修改期刊卷期
  updateVolume: (data) => {
    return apiClient.put('/journal', data)
  },
  
  // 删除期刊卷期
  deleteVolume: (ids) => {
    return apiClient.delete(`/journal/${ids.join(',')}`)
  }
}
