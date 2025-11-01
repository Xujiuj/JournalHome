import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router, { applyDynamicRoutes } from './router'
import { authApi } from './api'

const bootstrap = async () => {
  try {
    // 自动登录：如果存在token，尝试获取用户信息
    const token = localStorage.getItem('token')
    if (token) {
      try {
        const response = await authApi.getCurrentUser()
        if (response && response.data) {
          const userInfo = response.data.user || response.data
          store.dispatch('user/login', { userInfo, token })
        }
      } catch (error) {
        // Token无效，清除
        console.warn('Token验证失败，清除登录状态:', error)
        localStorage.removeItem('token')
        store.dispatch('user/logout')
      }
    }

    await store.dispatch('sysInfo/fetchSysInfo')
    const menuList = await store.dispatch('sysInfo/fetchMenuList')
    if (Array.isArray(menuList)) {
      applyDynamicRoutes(menuList)
    }
  } catch (error) {
    console.error('Failed to initialize dynamic routes:', error)
  }

  const app = createApp(App)
  app.use(store)
  app.use(router)
  await router.isReady()
  app.mount('#app')
}

bootstrap()

