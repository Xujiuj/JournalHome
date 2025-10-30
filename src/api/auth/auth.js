import apiClient from '../client'

/**
 * 认证API
 */
export const authApi = {
  // 用户登录
  login: (credentials) => {
    return apiClient.post('/auth/login', credentials)
  },
  
  // 用户注册
  register: (userData) => {
    const payload = {
      username: userData.username,
      email: userData.email,
      password: userData.password,
      realName: userData.realName,
      affiliation: userData.affiliation,
      phone: userData.phone,
      department: userData.department,
      title: userData.title,
      position: userData.position,
      orcid: userData.orcid,
      country: userData.country,
      state: userData.state,
      city: userData.city,
      postalCode: userData.postalCode,
      address: userData.address,
      classification: userData.classification,
      keywords: userData.keywords,
      biography: userData.biography
    }

    return apiClient.post('/auth/register', payload)
  },
  
  // 用户登出
  logout: () => {
    return apiClient.post('/auth/logout')
  },
  
  // 获取当前用户信息
  getCurrentUser: () => {
    return apiClient.get('/auth/me')
  }
}
