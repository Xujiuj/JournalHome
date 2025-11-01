import axios from 'axios'
import { API_BASE_URL, requestConfig, responseConfig } from './config'

// 创建 axios 实例
const axiosInstance = axios.create({
  baseURL: API_BASE_URL,
  ...requestConfig
})

// 简单的请求去重管理（仅针对 GET / HEAD 请求）
const inFlightRequests = new Map()
const dedupMethods = new Set(['get', 'head'])

const getRequestKey = (config) => {
  const { method = 'get', url = '', params, data } = config
  const normalizedMethod = method.toLowerCase()
  const paramsKey = params ? JSON.stringify(params) : ''
  const dataKey = data && typeof data === 'object' ? JSON.stringify(data) : data || ''
  return `${normalizedMethod}|${url}|${paramsKey}|${dataKey}`
}

const rawRequest = axiosInstance.request.bind(axiosInstance)

axiosInstance.request = (config) => {
  const normalizedConfig = typeof config === 'string' ? { url: config } : { ...config }
  normalizedConfig.method = (normalizedConfig.method || 'get').toLowerCase()

  const skipDedup = normalizedConfig?.meta?.skipDedup === true
  const shouldDedup = dedupMethods.has(normalizedConfig.method) && !skipDedup

  const requestKey = shouldDedup ? getRequestKey(normalizedConfig) : null

  if (shouldDedup && requestKey && inFlightRequests.has(requestKey)) {
    return inFlightRequests.get(requestKey)
  }

  const requestPromise = rawRequest(normalizedConfig)
    .finally(() => {
      if (requestKey) {
        inFlightRequests.delete(requestKey)
      }
    })

  if (shouldDedup && requestKey) {
    inFlightRequests.set(requestKey, requestPromise)
  }

  return requestPromise
}

// 请求拦截器
axiosInstance.interceptors.request.use(
  (config) => {
    const finalConfig = config
    const token = localStorage.getItem('token')
    if (token) {
      finalConfig.headers = finalConfig.headers || {}
      finalConfig.headers.Authorization = `Bearer ${token}`
    }
    
    // 如果是FormData，删除Content-Type让浏览器自动设置（包括boundary）
    if (config.data instanceof FormData) {
      delete finalConfig.headers['Content-Type']
    }
    
    return finalConfig
  },
  (error) => Promise.reject(responseConfig.error(error))
)

// 响应拦截器
axiosInstance.interceptors.response.use(
  (response) => responseConfig.success(response),
  (error) => {
    if (error && error.name === 'BusinessError') {
      return Promise.reject(error)
    }
    return Promise.reject(responseConfig.error(error))
  }
)

export default axiosInstance
