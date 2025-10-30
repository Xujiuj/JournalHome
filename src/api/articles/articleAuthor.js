import apiClient from '../client'

/**
 * 文章作者API
 */
export const articleAuthorApi = {
  // 分页查询作者列表
  getAuthorList: (params) => {
    return apiClient.get('/articles/authors/list', { params })
  },
  
  // 根据文章ID获取作者列表
  getAuthorsByArticleId: (articleId) => {
    return apiClient.get(`/articles/authors/article/${articleId}`)
  },
  
  // 获取作者详情
  getAuthorById: (id) => {
    return apiClient.get(`/articles/authors/${id}`)
  },
  
  // 新增作者
  addAuthor: (data) => {
    return apiClient.post('/articles/authors', data)
  },
  
  // 修改作者
  updateAuthor: (data) => {
    return apiClient.put('/articles/authors', data)
  },
  
  // 删除作者
  deleteAuthor: (ids) => {
    return apiClient.delete(`/articles/authors/${ids.join(',')}`)
  },
  
  // 批量保存文章作者
  saveAuthorsForArticle: (articleId, authors) => {
    return apiClient.post(`/articles/authors/batch/${articleId}`, authors)
  }
}
