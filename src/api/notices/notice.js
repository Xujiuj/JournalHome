import apiClient from '../client'

/**
 * 通知API
 */
export const noticeApi = {
  // 获取通知列表
  getNotices: (params = {}) => {
    const {
      pageNum = 1,
      pageSize = 50,
      noticeStatus = 1,
      ...filters
    } = params || {}

    return apiClient.get('/notices/list', {
      params: {
        pageNum,
        pageSize,
        noticeStatus,
        ...filters
      }
    })
  },
  
  // 获取置顶通知
  getTopNotices: (limit = 5, params = {}) => {
    const {
      pageNum = 1,
      pageSize = limit,
      noticeStatus = 1,
      ...filters
    } = params || {}

    return apiClient.get('/notices/list', {
      params: {
        pageNum,
        pageSize,
        noticeStatus,
        noticeTop: 1,
        ...filters
      }
    })
  },
  
  // 分页查询通知列表
  getNoticeList: (params) => {
    return apiClient.get('/notices/list', { params })
  },
  
  // 获取通知详情
  getNoticeById: (id) => {
    return apiClient.get(`/notices/${id}`)
  },
  
  // 新增通知
  addNotice: (data) => {
    return apiClient.post('/notices', data)
  },
  
  // 修改通知
  updateNotice: (data) => {
    return apiClient.put('/notices', data)
  },
  
  // 删除通知
  deleteNotice: (ids) => {
    return apiClient.delete(`/notices/${ids.join(',')}`)
  }
}
