import apiClient from '../client'

/**
 * 文章流程API
 */
export const articleProcessApi = {
  // 分页查询流程列表
  getProcessList: (params) => {
    return apiClient.get('/articles/process/list', { params })
  },
  
  // 根据文章ID获取处理流程
  getProcessByArticleId: (articleId) => {
    return apiClient.get(`/articles/process/article/${articleId}`)
  },
  
  // 获取流程详情
  getProcessById: (id) => {
    return apiClient.get(`/articles/process/${id}`)
  },
  
  // 新增流程
  addProcess: (data) => {
    return apiClient.post('/articles/process', data)
  },
  
  // 修改流程
  updateProcess: (data) => {
    return apiClient.put('/articles/process', data)
  },
  
  // 删除流程
  deleteProcess: (ids) => {
    return apiClient.delete(`/articles/process/${ids.join(',')}`)
  },
  
  // 创建文章处理流程
  createProcessForArticle: (articleId, editorId) => {
    return apiClient.post(`/articles/process/create/${articleId}`, null, {
      params: { editorId }
    })
  },
  
  // 更新流程状态
  updateProcessStatus: (processId, status, operatorId) => {
    return apiClient.put(`/articles/process/status/${processId}`, null, {
      params: { status, operatorId }
    })
  },
  
  // 完成文章处理流程
  completeProcess: (processId, decision, reason, operatorId) => {
    return apiClient.put(`/articles/process/complete/${processId}`, null, {
      params: { decision, reason, operatorId }
    })
  }
}
