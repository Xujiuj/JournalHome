import apiClient from './client'

/**
 * 枚举API
 */
export const enumApi = {
  // 获取文章状态选项
  getArticleStatusOptions: () => {
    return apiClient.get('/common/enums/article-status')
  },

  // 获取稿件类型选项
  getManuscriptTypeOptions: () => {
    return apiClient.get('/common/enums/manuscript-type')
  },

  // 获取文章类型选项
  getArticleTypeOptions: () => {
    return apiClient.get('/common/enums/article-type')
  },

  // 获取提交类型选项
  getSubmissionTypeOptions: () => {
    return apiClient.get('/common/enums/submission-type')
  },

  // 获取文件类型选项
  getFileTypeOptions: () => {
    return apiClient.get('/common/enums/file-type')
  },

  // 获取学位选项
  getDegreeOptions: () => {
    return apiClient.get('/common/enums/degree')
  }
}

