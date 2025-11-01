<template>
  <header class="sticky top-0 z-50 bg-white dark:bg-slate-900 shadow-sm border-b border-slate-200 dark:border-slate-700">
    <!-- Main Header Bar -->
    <div class="bg-white dark:bg-slate-900 py-3 min-h-[64px]">
      <div class="container mx-auto px-4">
        <div class="flex justify-between items-center gap-8">
          <!-- Left: Journal Info -->
          <div class="flex items-center gap-4 flex-shrink-0 justify-center">
            <div class="w-12 h-12 bg-slate-800 dark:bg-slate-700 rounded-md flex items-center justify-center flex-shrink-0 overflow-hidden">
              <img
                v-if="logoUrl"
                :src="logoUrl"
                alt="Journal Logo"
                class="w-10 h-10 object-contain"
              />
              <span v-else class="text-white text-xl font-bold font-serif">{{ brandInitial }}</span>
            </div>
            <div class="flex flex-col justify-center">
              <div class="text-xl font-bold text-slate-800 dark:text-slate-200 leading-tight font-serif">
                {{ displayPublisher }}
              </div>
              <div class="text-sm text-slate-500 dark:text-slate-400 font-normal leading-tight -mt-0">
                ISSN: {{ displayIssn }}
              </div>
            </div>
          </div>
          
          <!-- Right: Search and Auth -->
          <div class="flex items-center gap-4">
            <!-- Search Box -->
            <div class="transform transition-transform duration-300 hover:scale-105 origin-center">
              <GlowingSearchBox
                  v-model="searchQuery"
                  placeholder="Search Articles"
                  @search="performSearch"
              />
            </div>
            
            <!-- Auth Section -->
            <div class="flex items-center gap-3">
              <!-- User Avatar & Menu (when logged in) -->
              <div v-if="currentUser" ref="userMenuRef" class="relative group">
                <button
                  @click="toggleUserMenu"
                  type="button"
                  class="flex items-center gap-3 p-2 rounded-full hover:bg-slate-100 dark:hover:bg-slate-800 transition-all duration-300 transform hover:scale-110"
                >
                  <!-- User Avatar -->
                  <div class="w-8 h-8 bg-gradient-to-r from-cyan-500 to-blue-600 rounded-full flex items-center justify-center text-white font-semibold text-sm">
                    {{ currentUser.userRealName ? currentUser.userRealName.charAt(0).toUpperCase() : 'U' }}
                  </div>
                  <svg
                    class="w-4 h-4 text-slate-600 dark:text-slate-400 transition-transform duration-200"
                    :class="{ 'rotate-180': isUserMenuOpen }"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path>
                  </svg>
                </button>

                <!-- User Dropdown Menu -->
                <teleport to="body">
                  <transition name="dropdown">
                    <div
                        v-if="isUserMenuOpen"
                        ref="dropdownMenuRef"
                        class="fixed w-64 bg-white dark:bg-slate-800 rounded-xl shadow-xl border border-slate-200 dark:border-slate-700 py-2 z-[9999] overflow-y-auto"
                        :style="dropdownStyle"
                    >
                  <!-- User Info Header -->
                  <div class="px-4 py-3 border-b border-slate-200 dark:border-slate-700">
                    <div class="flex items-center gap-3">
                      <div class="w-10 h-10 bg-gradient-to-r from-cyan-500 to-blue-600 rounded-full flex items-center justify-center text-white font-semibold">
                        {{ currentUser.userRealName ? currentUser.userRealName.charAt(0).toUpperCase() : 'U' }}
                      </div>
                      <div class="flex-1 min-w-0">
                        <p class="text-sm font-semibold text-slate-900 dark:text-white truncate">
                          {{ currentUser.userRealName || 'User' }}
                        </p>
                        <p class="text-xs text-slate-500 dark:text-slate-400 truncate">
                          {{ currentUser.userEmail || '' }}
                        </p>
                      </div>
                    </div>
                  </div>

                  <!-- Menu Items -->
                  <div class="py-2">
                    <router-link
                      v-for="menu in userMenus"
                      :key="menu.menuId"
                      :to="menu.menuPath"
                      @click="isUserMenuOpen = false"
                      class="flex items-center gap-3 px-4 py-2 text-sm text-slate-700 dark:text-slate-300 hover:bg-slate-50 dark:hover:bg-slate-700 hover:text-slate-900 dark:hover:text-white transition-colors"
                    >
                      <svg v-if="menu.menuIcon" class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" :d="menu.menuIcon"></path>
                      </svg>
                      <span v-else class="w-4 h-4 flex items-center justify-center">
                        <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                        </svg>
                      </span>
                      {{ menu.menuName }}
                    </router-link>

                    <!-- Logout -->
                    <div class="border-t border-slate-200 dark:border-slate-700 mt-2 pt-2">
                      <button
                        @click="handleLogout"
                        class="flex items-center gap-3 px-4 py-2 text-sm text-red-600 dark:text-red-400 hover:bg-red-50 dark:hover:bg-red-900/20 w-full text-left transition-colors"
                      >
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"></path>
                        </svg>
                        Logout
                      </button>
                    </div>
                  </div>
                    </div>
                  </transition>
                </teleport>
              </div>

              <!-- Login/Register Buttons (when not logged in) -->
              <div v-else class="flex items-center gap-3">
              <router-link 
                  :to="branding.registerRoute"
                  class="inline-flex items-center gap-2 px-5 py-2 bg-transparent text-cyan-500 dark:text-cyan-400 border border-cyan-500/40 dark:border-cyan-400/40 rounded-full text-sm font-semibold hover:bg-cyan-500/10 dark:hover:bg-cyan-400/10 hover:border-cyan-500 dark:hover:border-cyan-400 transition-all duration-300 transform hover:scale-110 origin-center"
              >
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z"></path>
                </svg>
                Register
              </router-link>
              <router-link 
                  :to="branding.loginRoute"
                  class="inline-flex items-center gap-2 px-5 py-2 bg-gradient-to-r from-cyan-500 via-blue-600 to-indigo-600 text-white rounded-full text-sm font-semibold hover:from-cyan-600 hover:via-blue-700 hover:to-indigo-700 transition-all duration-300 shadow-lg hover:shadow-xl transform hover:scale-110 origin-center"
              >
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1"></path>
                </svg>
                Login
              </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Navigation Bar -->
    <nav class="bg-white dark:bg-slate-900 border-t border-slate-200 dark:border-slate-700 relative z-30">
      <div class="container mx-auto px-4">
        <div class="flex items-center justify-center gap-0">
          <router-link 
            v-for="item in navItems" 
            :key="item.menuName"
            :to="item.menuPath"
            @click="handleNavClick(item, $event)"
            class="nav-link-gemini group relative px-5 py-3 text-slate-700 dark:text-slate-300 text-sm font-medium transition duration-300 transform origin-center hover:scale-105 hover:text-slate-900 dark:hover:text-slate-100"
            :class="{ 'text-slate-900 dark:text-slate-100': $route.path === item.menuPath }"
          >
            <span class="relative z-10">{{ item.menuName }}</span>
            
            <!-- Hover Background (lowest layer) -->
            <div class="absolute inset-0 bg-gradient-to-r from-cyan-50/80 to-blue-50/80 dark:from-cyan-900/20 dark:to-blue-900/20 opacity-0 group-hover:opacity-100 transition-opacity duration-300 z-0"></div>
            
            <!-- Bottom Gradient Effect - appears only on hover/active, spreads from center -->
            <div class="absolute bottom-0 left-0 right-0 h-1 overflow-hidden z-20">
              <div
                class="w-full h-full bg-gradient-to-r from-cyan-600 via-blue-600 to-indigo-700 transform origin-center transition duration-500 ease-out scale-x-0 group-hover:scale-x-100 opacity-0 group-hover:opacity-100"
                :class="{ 'scale-x-100 opacity-100': $route.path === item.menuPath }"
              ></div>
            </div>
          </router-link>
        </div>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted, nextTick, watch } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import GlowingSearchBox from '@/components/ui/GlowingSearchBox.vue'

const router = useRouter()
const store = useStore()
const searchQuery = ref('')
const ROUTES = Object.freeze({
  articles: '/articles',
  register: '/register',
  login: '/login'
})
const PROTECTED_ROUTES = ['/submit', '/my-submissions']

// 从 Vuex 获取系统信息和菜单
const branding = computed(() => store.getters['sysInfo/brandingInfo'])
const navItems = computed(() => {
  const menus = store.getters['sysInfo/getMenuList'] || []
  return menus
    .filter((item) => item?.menuParentId === 0 && item?.menuPath && item.menuType === 'M')
    .sort((a, b) => (a.menuOrderNum ?? 0) - (b.menuOrderNum ?? 0))
})
const displayPublisher = computed(() => branding.value.publisher)
const displayIssn = computed(() => branding.value.issn)
const logoUrl = computed(() => branding.value.logo)
const brandInitial = computed(() => {
  const source = branding.value.shortName || branding.value.name
  return source.charAt(0).toUpperCase()
})

// 浠?Vuex 鑾峰彇鐢ㄦ埛淇℃伅
const currentUser = computed(() => store.getters['user/currentUser'])
const userMenus = computed(() => {
  const menus = store.getters['sysInfo/getMenuList'] || []
  return menus
    .filter((item) => item?.menuParentId === 8 && item?.menuType === 'M')
    .sort((a, b) => (a.menuOrderNum ?? 0) - (b.menuOrderNum ?? 0))
})

// 用户下拉菜单状态
const isUserMenuOpen = ref(false)
const userMenuRef = ref(null)
const dropdownMenuRef = ref(null)
const dropdownStyle = ref({})
const isToggling = ref(false) // 标记正在切换状态

// 更新下拉框位置
const updateDropdownPosition = () => {
  if (!userMenuRef.value) {
    dropdownStyle.value = {}
    return
  }
  
  nextTick(() => {
    if (!userMenuRef.value) return
    
    const rect = userMenuRef.value.getBoundingClientRect()
    const dropdownWidth = 256 // w-64 = 256px
    const margin = 16 // 间距
    const viewportWidth = window.innerWidth
    const viewportHeight = window.innerHeight
    
    // 下拉框右边缘对齐头像右边缘
    let right = viewportWidth - rect.right
    let left = null
    
    // 检查右对齐是否会导致超出左侧边界
    // right 是从右边缘到视口右边缘的距离，所以如果 right 很大，说明头像在左侧
    const leftPosition = rect.right - dropdownWidth // 如果用右对齐，左边缘的位置
    
    if (leftPosition < margin) {
      // 如果右对齐会导致超出左侧，改为左对齐
      left = Math.max(rect.left, margin)
      right = null
      
      // 确保左对齐不会超出右侧
      if (left + dropdownWidth > viewportWidth - margin) {
        left = viewportWidth - dropdownWidth - margin
      }
    } else {
      // 可以右对齐，确保不会超出右侧边界
      if (rect.right > viewportWidth - margin) {
        right = margin
      }
    }
    
    // 计算垂直位置
    let top = rect.bottom + 8
    const dropdownHeight = 200 // 估算下拉框高度
    if (top + dropdownHeight > viewportHeight - margin) {
      top = Math.max(rect.top - dropdownHeight - 8, margin)
    }
    
    const style = {
      top: `${top}px`,
      maxHeight: `${Math.min(viewportHeight - top - margin - 20, 400)}px`
    }
    
    if (left !== null) {
      style.left = `${left}px`
    }
    if (right !== null) {
      style.right = `${right}px`
    }
    
    // Center align to avatar and ensure left-based positioning
    const centerX = rect.left + rect.width / 2
    const computedLeft = Math.max(margin, Math.min(centerX - dropdownWidth / 2, viewportWidth - dropdownWidth - margin))
    style.left = `${computedLeft}px`
    style.right = null
    
    dropdownStyle.value = style
  })
}

// 监听菜单打开状态，更新位置
watch(isUserMenuOpen, (newVal) => {
  if (newVal) {
    updateDropdownPosition()
    // 监听窗口大小变化和滚动，更新位置
    window.addEventListener('resize', updateDropdownPosition)
    window.addEventListener('scroll', updateDropdownPosition, true)
  } else {
    window.removeEventListener('resize', updateDropdownPosition)
    window.removeEventListener('scroll', updateDropdownPosition, true)
  }
})

// Methods
const performSearch = () => {
  if (searchQuery.value.trim()) {
    router.push({ path: branding.value.articlesRoute, query: { search: searchQuery.value } })
  }
}

const requiresAuthRoute = (path = '') => {
  if (!path) return false
  return PROTECTED_ROUTES.some((route) => path === route || path.startsWith(`${route}/`))
}

const handleNavClick = (item, event) => {
  const targetPath = item?.menuPath?.trim?.()
  if (!targetPath) {
    return
  }

  const isExternal = /^https?:\/\//i.test(targetPath)
  if (isExternal) {
    return
  }

  if (event) {
    event.preventDefault()
    event.stopPropagation()
  }

  if (requiresAuthRoute(targetPath)) {
    const token = localStorage.getItem('token')
    if (token) {
      router.push(targetPath)
    } else {
      router.push({ path: ROUTES.login, query: { redirect: targetPath } })
    }
  } else {
    router.push(targetPath)
  }
}

const toggleUserMenu = (event) => {
  // 阻止事件冒泡，防止立即触发 closeUserMenu
  if (event) {
    event.stopPropagation()
  }
  isToggling.value = true
  isUserMenuOpen.value = !isUserMenuOpen.value
  // 短暂延迟后重置标志，允许外部点击检查
  setTimeout(() => {
    isToggling.value = false
  }, 100)
}

const handleLogout = async () => {
  try {
    await store.dispatch('user/logout')
    isUserMenuOpen.value = false
    router.push(branding.value.homeRoute)
  } catch (error) {
    console.error('Logout failed:', error)
  }
}

// 点击外部关闭菜单
const closeUserMenu = (event) => {
  // 如果正在切换状态，忽略此次点击
  if (isToggling.value) {
    return
  }
  
  // 检查点击的目标是否在用户菜单容器或下拉菜单内
  if (!event || !event.target) return
  
  const clickedInside = 
    (userMenuRef.value && userMenuRef.value.contains(event.target)) ||
    (dropdownMenuRef.value && dropdownMenuRef.value.contains(event.target))
  
  if (!clickedInside && isUserMenuOpen.value) {
    isUserMenuOpen.value = false
  }
}

onMounted(() => {
  // 使用捕获阶段为 false，确保元素上的事件处理器先执行
  document.addEventListener('click', closeUserMenu, false)
})

onUnmounted(() => {
  document.removeEventListener('click', closeUserMenu)
  window.removeEventListener('resize', updateDropdownPosition)
  window.removeEventListener('scroll', updateDropdownPosition, true)
})
</script>

<style scoped>
/* 下拉框动画 - 从上到下弹出 */
.dropdown-enter-active {
  animation: dropdownSlideDown 0.22s ease-out;
  transform-origin: top center;
}

.dropdown-leave-active {
  animation: dropdownSlideDown 0.18s ease-in reverse;
  transform-origin: top center;
}

@keyframes dropdownSlideDown {
  from {
    opacity: 0;
    transform: translateY(-6px) scaleY(0.96);
  }
  to {
    opacity: 1;
    transform: translateY(0) scaleY(1);
  }
}
</style>
