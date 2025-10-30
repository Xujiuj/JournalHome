const componentRegistry = {
  Home: () => import('@/views/Home.vue'),
  About: () => import('@/views/About.vue'),
  Articles: () => import('@/views/Articles.vue'),
  ArticleDetails: () => import('@/views/ArticleDetails.vue'),
  Notices: () => import('@/views/Notices.vue'),
  Contact: () => import('@/views/Contact.vue'),
  Submit: () => import('@/views/Submit.vue'),
  Register: () => import('@/views/Register.vue'),
  Login: () => import('@/views/Login.vue')
}

export const FALLBACK_COMPONENT = () => import('@/views/Home.vue')

export function resolveComponent(componentName) {
  const loader = componentName ? componentRegistry[componentName] : null
  return loader || FALLBACK_COMPONENT
}

export function hasComponent(componentName) {
  return Boolean(componentRegistry[componentName])
}
