import apiClient from '../client'

/**
 * 编辑团队API
 */
export const editorialApi = {
  // 获取编辑团队列表
  getEditorialTeam: (params = {}) => {
    const {
      pageNum = 1,
      pageSize = 50,
      ...filters
    } = params || {}

    return apiClient.get('/team/list', {
      params: {
        pageNum,
        pageSize,
        ...filters
      }
    })
  },
  
  // 分页查询编辑团队列表
  getEditorialList: (params = {}) => {
    const {
      pageNum = 1,
      pageSize = 10,
      ...filters
    } = params || {}

    return apiClient.get('/team/list', {
      params: {
        pageNum,
        pageSize,
        ...filters
      }
    })
  },
  
  // 获取编辑团队详情
  getEditorialById: (id) => {
    return apiClient.get(`/team/${id}`)
  },
  
  // 新增编辑团队
  addEditorial: (data) => {
    return apiClient.post('/team', data)
  },
  
  // 修改编辑团队
  updateEditorial: (data) => {
    return apiClient.put('/team', data)
  },
  
  // 删除编辑团队
  deleteEditorial: (ids) => {
    return apiClient.delete(`/team/${ids.join(',')}`)
  }
}
