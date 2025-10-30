import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router, { applyDynamicRoutes } from './router'

const bootstrap = async () => {
  try {
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

