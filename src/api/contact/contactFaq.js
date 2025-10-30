import apiClient from '../client'

const buildFaqParams = (params = {}) => {
  const {
    pageNum = 1,
    pageSize = 50,
    faqStatus = 1,
    ...filters
  } = params || {}

  return {
    pageNum,
    pageSize,
    faqStatus,
    ...filters
  }
}

/**
 * 联系FAQ API
 */
export const contactFaqApi = {
  // 获取常见问题列表
  getFaqs: (params = {}) => {
    return apiClient.get('/contact/faq/list', {
      params: buildFaqParams(params)
    })
  },
  
  // 分页查询常见问题列表
  getFaqList: (params = {}) => {
    return apiClient.get('/contact/faq/list', {
      params: buildFaqParams(params)
    })
  },
  
  // 获取常见问题详情
  getFaqById: (id) => {
    return apiClient.get(`/contact/faq/${id}`)
  },
  
  // 新增常见问题
  addFaq: (data) => {
    return apiClient.post('/contact/faq', data)
  },
  
  // 修改常见问题
  updateFaq: (data) => {
    return apiClient.put('/contact/faq', data)
  },
  
  // 删除常见问题
  deleteFaq: (ids) => {
    return apiClient.delete(`/contact/faq/${ids.join(',')}`)
  }
}
