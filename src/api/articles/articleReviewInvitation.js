import apiClient from '../client'

/**
 * 文章评审邀请API
 */
export const articleReviewInvitationApi = {
  // 分页查询邀请列表
  getInvitationList: (params) => {
    return apiClient.get('/articles/invitations/list', { params })
  },
  
  // 根据流程ID获取邀请列表
  getInvitationsByProcessId: (processId) => {
    return apiClient.get(`/articles/invitations/process/${processId}`)
  },
  
  // 根据评审人ID获取邀请列表
  getInvitationsByReviewerId: (reviewerId) => {
    return apiClient.get(`/articles/invitations/reviewer/${reviewerId}`)
  },
  
  // 获取邀请详情
  getInvitationById: (id) => {
    return apiClient.get(`/articles/invitations/${id}`)
  },
  
  // 新增邀请
  addInvitation: (data) => {
    return apiClient.post('/articles/invitations', data)
  },
  
  // 修改邀请
  updateInvitation: (data) => {
    return apiClient.put('/articles/invitations', data)
  },
  
  // 删除邀请
  deleteInvitation: (ids) => {
    return apiClient.delete(`/articles/invitations/${ids.join(',')}`)
  },
  
  // 发送评审邀请
  sendReviewInvitation: (processId, reviewerId, notes) => {
    return apiClient.post('/articles/invitations/send', null, {
      params: { processId, reviewerId, notes }
    })
  },
  
  // 响应评审邀请
  respondToInvitation: (invitationId, status) => {
    return apiClient.put(`/articles/invitations/respond/${invitationId}`, null, {
      params: { status }
    })
  },
  
  // 更新邀请状态
  updateInvitationStatus: (invitationId, status) => {
    return apiClient.put(`/articles/invitations/status/${invitationId}`, null, {
      params: { status }
    })
  }
}
