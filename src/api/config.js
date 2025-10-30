// API 基础配置
// 在开发环境中使用相对路径以便通过 Vite 代理
// 在生产环境中使用环境变量或配置的后端地址
const API_BASE_URL = import.meta.env?.VITE_API_BASE_URL ?? '/api'

// 请求超时时间（毫秒）
const REQUEST_TIMEOUT = Number(import.meta.env?.VITE_API_TIMEOUT) || 10000

// 业务成功状态码
const SUCCESS_CODE = Number(import.meta.env?.VITE_API_SUCCESS_CODE) || 200

// 请求默认配置
const requestConfig = {
  timeout: REQUEST_TIMEOUT,
  headers: {
    'Content-Type': 'application/json'
  }
}

// 响应拦截配置
const responseConfig = {
  success: (response) => {
    const payload = response?.data

    // 仅当返回对象中包含 code 字段时进行业务校验
    if (payload && typeof payload === 'object' && Object.prototype.hasOwnProperty.call(payload, 'code')) {
      if (payload.code !== SUCCESS_CODE) {
        const error = new Error(payload.msg || payload.message || '业务请求失败')
        error.name = 'BusinessError'
        error.code = payload.code
        error.payload = payload
        throw error
      }
    }

    return payload
  },
  error: (error) => {
    const normalizedError = error instanceof Error ? error : new Error('网络请求异常')
    normalizedError.name = normalizedError.name || 'NetworkError'
    return normalizedError
  }
}

export { API_BASE_URL, REQUEST_TIMEOUT, SUCCESS_CODE, requestConfig, responseConfig }
