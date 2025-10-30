import { createRouter, createWebHistory } from 'vue-router'
import { buildRoutesFromMenu } from './dynamicRoutes'
import { resolveComponent } from './componentRegistry'

const dynamicRouteNames = new Set()

const baseRoutes = [
  {
    path: '/articles/:manuscriptId',
    name: 'ArticleDetailsDynamic',
    component: resolveComponent('ArticleDetails') || (() => import('@/views/ArticleDetails.vue')),
    props: true
  },
  {
    path: '/submit',
    name: 'Submit',
    component: resolveComponent('Submit') || (() => import('@/views/Submit.vue'))
  },
  {
    path: '/my-submissions',
    name: 'MySubmissions',
    component: resolveComponent('MySubmissions') || (() => import('@/views/MySubmissions.vue'))
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'WildcardFallback',
    component: resolveComponent('Home') || (() => import('@/views/Home.vue'))
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes: baseRoutes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return {
        ...savedPosition,
        behavior: 'smooth'
      }
    }
    return {
      top: 0,
      behavior: 'smooth'
    }
  }
})

export const applyDynamicRoutes = (menuList = []) => {
  const routes = buildRoutesFromMenu(menuList)
  routes.forEach((route) => {
    if (!router.hasRoute(route.name)) {
      router.addRoute(route)
      dynamicRouteNames.add(route.name)
    }
  })
}

export const resetDynamicRoutes = () => {
  dynamicRouteNames.forEach((name) => {
    if (router.hasRoute(name)) {
      router.removeRoute(name)
    }
  })
  dynamicRouteNames.clear()
}

export default router

// Simple auth guard for submission pages
router.beforeEach((to, from, next) => {
  const requiresAuth = ['/submit', '/my-submissions'].includes(to.path)
  if (!requiresAuth) return next()
  const token = localStorage.getItem('token')
  if (token) return next()
  next({ path: '/login', query: { redirect: to.fullPath } })
})
