import apiClient from '../client'

/**
 * 测试接口API
 */
export const testApi = {
  // 获取服务器信息
  getServerInfo: () => {
    return apiClient.get('/test/info')
  },
  
  // 健康检查
  healthCheck: () => {
    return apiClient.get('/test/health')
  }
}
