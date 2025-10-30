import apiClient from '../client'

/**
 * 文章评审API
 */
export const articleReviewApi = {
  // 分页查询评审列表
  getReviewList: (params) => {
    return apiClient.get('/articles/reviews/list', { params })
  },
  
  // 根据文章ID获取评审列表
  getReviewsByArticleId: (articleId) => {
    return apiClient.get(`/articles/reviews/article/${articleId}`)
  },
  
  // 根据评审人ID获取评审列表
  getReviewsByReviewerId: (reviewerId) => {
    return apiClient.get(`/articles/reviews/reviewer/${reviewerId}`)
  },
  
  // 获取评审详情
  getReviewById: (id) => {
    return apiClient.get(`/articles/reviews/${id}`)
  },
  
  // 新增评审
  addReview: (data) => {
    return apiClient.post('/articles/reviews', data)
  },
  
  // 修改评审
  updateReview: (data) => {
    return apiClient.put('/articles/reviews', data)
  },
  
  // 删除评审
  deleteReview: (ids) => {
    return apiClient.delete(`/articles/reviews/${ids.join(',')}`)
  }
}
