import apiClient from '../client'

/**
 * 文章API
 */
export const articleApi = {
  // 获取最新文章
  getLatestArticles: (limit = 10) => {
    const normalizedLimit = Number.isFinite(limit) && limit > 0 ? limit : 10
    return apiClient.get(`/articles/lastest/${normalizedLimit}`)
  },
  
  // 搜索文章（按标题等条件）
  searchArticles: (params = {}) => {
    const normalized =
      typeof params === 'string'
        ? { articleTitle: params }
        : (params || {})

    const {
      pageNum = 1,
      pageSize = 10,
      ...filters
    } = normalized

    return apiClient.get('/articles/list', {
      params: {
        pageNum,
        pageSize,
        ...filters
      }
    })
  },
  
  // 根据期刊ID获取文章列表
  getArticlesByJournalId: (journalId, params = {}) => {
    const {
      pageNum = 1,
      pageSize = 10,
      ...filters
    } = params || {}

    return apiClient.get('/articles/list', {
      params: {
        pageNum,
        pageSize,
        articleJournalId: journalId,
        ...filters
      }
    })
  },
  
  // 根据状态获取文章列表
  getArticlesByStatus: (status, params = {}) => {
    const {
      pageNum = 1,
      pageSize = 10,
      ...filters
    } = params || {}

    return apiClient.get('/articles/list', {
      params: {
        pageNum,
        pageSize,
        articleStatusId: status,
        ...filters
      }
    })
  },
  
  // 分页查询文章列表
  getArticleList: (params = {}) => {
    const {
      pageNum = 1,
      pageSize = 10,
      ...filters
    } = params || {}

    return apiClient.get('/articles/list', {
      params: {
        pageNum,
        pageSize,
        ...filters
      }
    })
  },
  
  // 获取文章详情（通过ID）
  getArticleById: (id) => {
    return apiClient.get(`/articles/${id}`)
  },
  
  // 获取文章详情（通过稿件编号）
  getArticleByManuscriptId: (manuscriptId) => {
    return apiClient.get(`/articles/detail/${manuscriptId}`)
  },
  
  // 新增文章
  addArticle: (data) => {
    return apiClient.post('/articles', data)
  },
  
  // 修改文章
  updateArticle: (data) => {
    return apiClient.put('/articles', data)
  },
  
  // 删除文章
  deleteArticle: (ids) => {
    return apiClient.delete(`/articles/${ids.join(',')}`)
  },

  // 提交论文
  submitArticle: (formData) => {
    return apiClient.post('/articles/submit', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  },

  // 上传文件
  uploadFile: (file, type = 'main') => {
    const formData = new FormData()
    formData.append('file', file)
    formData.append('type', type)
    return apiClient.post('/articles/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  },

  // 获取用户的投稿列表
  getUserSubmissions: (userId) => {
    return apiClient.get(`/articles/user/${userId}/submissions`)
  },

  // 获取建议审稿人
  getSuggestedReviewers: (articleId) => {
    return apiClient.get(`/articles/${articleId}/suggested-reviewers`)
  },

  // 添加建议审稿人
  addSuggestedReviewer: (articleId, reviewer) => {
    return apiClient.post(`/articles/${articleId}/suggested-reviewers`, reviewer)
  },

  // 获取避免审稿人
  getOpposedReviewers: (articleId) => {
    return apiClient.get(`/articles/${articleId}/opposed-reviewers`)
  },

  // 添加避免审稿人
  addOpposedReviewer: (articleId, reviewer) => {
    return apiClient.post(`/articles/${articleId}/opposed-reviewers`, reviewer)
  },

}
