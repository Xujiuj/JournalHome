import apiClient from '../client'

/**
 * 联系消息API
 */
export const contactMessageApi = {
  // 提交联系消息
  submitMessage: (message) => {
    return apiClient.post('/contact/message', message)
  },
  
  // 分页查询联系消息列表
  getContactList: (params) => {
    return apiClient.get('/contact/message/list', { params })
  },
  
  // 获取联系消息详情
  getContactById: (id) => {
    return apiClient.get(`/contact/message/${id}`)
  },
  
  // 新增联系消息
  addContact: (data) => {
    return apiClient.post('/contact/message', data)
  },
  
  // 修改联系消息
  updateContact: (data) => {
    return apiClient.put('/contact/message', data)
  },
  
  // 删除联系消息
  deleteContact: (ids) => {
    return apiClient.delete(`/contact/message/${ids.join(',')}`)
  },
  
  // 回复联系消息
  replyContact: (data) => {
    return apiClient.put('/contact/message/reply', data)
  }
}
