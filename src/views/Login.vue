<template>
  <PageScaffold :meteor-count="25">
    <div class="container mx-auto px-4 py-12 relative z-10">
      <!-- Breadcrumb Navigation -->
      <nav class="mb-8 pt-4" aria-label="Breadcrumb">
        <ol class="flex items-center space-x-2 text-sm">
          <li>
            <router-link 
              :to="ROUTES.home" 
              class="text-slate-400 hover:text-white transition-colors duration-300 flex items-center"
            >
              <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
              </svg>
              Home
            </router-link>
          </li>
          <li class="text-slate-500">/</li>
          <li class="text-white">Login</li>
        </ol>
      </nav>

      <!-- Login Form Container -->
      <div class="max-w-lg mx-auto">
        <!-- Glow effect background -->
        <div class="absolute -inset-4 bg-gradient-to-r from-cyan-500 via-blue-600 to-indigo-700 rounded-3xl opacity-20 blur-3xl group-hover:opacity-30 transition-opacity"></div>
        
        <div class="relative bg-gradient-to-br from-slate-900/98 via-slate-800/98 to-slate-900/98 backdrop-blur-xl rounded-3xl shadow-2xl p-10 border border-slate-700/50 overflow-hidden">
          <!-- Animated background gradient -->
          <div class="absolute inset-0 bg-gradient-to-br from-cyan-500/5 via-blue-600/5 to-indigo-700/5"></div>
          
          <!-- Logo and Title -->
          <div class="relative text-center mb-10">
            <h1 class="text-4xl font-bold bg-gradient-to-r from-cyan-400 via-blue-500 to-indigo-600 bg-clip-text text-transparent mb-3">
              Welcome Back
            </h1>
            <p class="text-slate-400 text-lg">Sign in to submit your research</p>
          </div>

          <!-- Login Form -->
          <form @submit.prevent="handleLogin" class="relative space-y-6">
            <!-- Username -->
            <div class="form-group">
              <label class="auth-label block text-sm font-semibold mb-3">Email</label>
              <div class="relative group/input">
                <div class="absolute -inset-0.5 bg-gradient-to-r from-cyan-500 to-indigo-600 rounded-xl opacity-0 group-hover/input:opacity-30 blur transition-opacity"></div>
               <input
                 v-model="formData.username"
                   type="email"
                   name="email"
                   autocomplete="email"
                 required
                   class="relative w-full px-5 py-4 bg-slate-900/80 border border-slate-600 rounded-xl text-white placeholder-slate-500 focus:outline-none focus:border-cyan-400 focus:ring-2 focus:ring-cyan-400/20 transition-all"
                   placeholder="Enter your Email"
               />
              </div>
            </div>

            <!-- Password -->
            <div class="form-group">
              <label class="auth-label block text-sm font-semibold mb-3">Password</label>
              <div class="relative group/input">
                <div class="absolute -inset-0.5 bg-gradient-to-r from-cyan-500 to-indigo-600 rounded-xl opacity-0 group-hover/input:opacity-30 blur transition-opacity"></div>
               <input
                 v-model="formData.password"
                 type="password"
                   name="password"
                   autocomplete="current-password"
                   required 
                   :class="[
                     'relative w-full px-5 py-4 bg-slate-900/80 border rounded-xl text-white placeholder-slate-500 focus:outline-none transition-all',
                     loginError ? 'border-red-500 focus:border-red-500 ring-2 ring-red-500/30 animate-shake' : 'border-slate-600 focus:border-cyan-400 focus:ring-2 focus:ring-cyan-400/20'
                   ]"
                   placeholder="Enter your password"
               />
              </div>
              <p v-if="loginError" class="mt-2 text-sm text-red-400 flex items-center gap-2">
                <svg class="w-4 h-4" viewBox="0 0 24 24" fill="none" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01M5.07 19h13.86A2 2 0 0021 17.07L13.41 4.93a2 2 0 00-3.42 0L3 17.07A2 2 0 005.07 19z"/>
                </svg>
                {{ loginError }}
              </p>
            </div>

            <!-- Remember Me and Forgot Password -->
            <div class="flex items-center justify-between text-sm">
              <label class="flex items-center cursor-pointer group/check">
                <input
                  v-model="rememberMe"
                  type="checkbox"
                  id="remember"
                  class="w-4 h-4 text-cyan-600 bg-slate-900 border-slate-600 rounded focus:ring-cyan-500 cursor-pointer"
                />
                <span class="ml-3 text-slate-400 group-hover/check:text-slate-300 transition-colors">
                  Remember me
                </span>
                </label>
              <a href="#" class="text-cyan-400 hover:text-cyan-300 font-medium transition-all duration-300 transform hover:scale-110 inline-block">
                Forgot password?
              </a>
            </div>

            <!-- Login Button -->
            <div class="pt-2">
              <ShimmerButton
                variant="primary"
              :disabled="submitting"
                @click="handleLogin"
                class="w-full"
                type="submit"
              >
                <svg v-if="!submitting" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1"></path>
                </svg>
                <svg v-else class="animate-spin h-5 w-5" fill="none" viewBox="0 0 24 24">
                  <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                  <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                </svg>
                <span v-if="!submitting" class="font-semibold">Sign In</span>
                <span v-else>Signing in...</span>
              </ShimmerButton>
            </div>

            <!-- Divider -->
            <div class="relative py-4">
              <div class="absolute inset-0 flex items-center">
                <div class="w-full border-t border-slate-700"></div>
              </div>
              <div class="relative flex justify-center">
                <span class="px-4 bg-slate-900/80 text-slate-500 text-sm font-medium">or</span>
              </div>
            </div>

            <!-- Register Link -->
            <div>
              <ShimmerButton
                variant="secondary"
                @click="$router.push(branding.registerRoute)"
                class="w-full"
                type="button"
              >
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z"></path>
                </svg>
                <span class="font-semibold">Create New Account</span>
              </ShimmerButton>
            </div>

            <!-- Back to Home -->
            <div class="text-center pt-2">
              <router-link 
                :to="branding.homeRoute"
                class="inline-flex items-center text-slate-500 hover:text-slate-300 transition-colors duration-300 text-sm font-medium group/back"
              >
                <svg class="w-4 h-4 mr-2 transform group-hover/back:-translate-x-1 transition-transform" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 19l-7-7m0 0l7-7m-7 7h18"></path>
                </svg>
                Back to Home
              </router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </PageScaffold>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { authApi } from '@/api'
import PageScaffold from '@/components/layout/PageScaffold.vue'
import ShimmerButton from '@/components/ui/ShimmerButton.vue'

const router = useRouter()
const store = useStore()
const submitting = ref(false)
const loginError = ref('')
const rememberMe = ref(false)
const ROUTES = Object.freeze({
  home: '/',
  register: '/register'
})
const branding = computed(() => store.getters['sysInfo/brandingInfo'])
const logoUrl = computed(() => branding.value.logo)

const formData = ref({
  username: '',
  password: ''
})

// 页面加载时自动填充保存的登录信息
onMounted(() => {
  const rememberedEmail = localStorage.getItem('rememberedEmail')
  const rememberedPassword = localStorage.getItem('rememberedPassword')
  
  const rememberedUsername = localStorage.getItem('rememberedUsername') || rememberedEmail
  if (rememberedUsername) {
    formData.value.username = rememberedUsername
    rememberMe.value = true
    
    if (rememberedPassword) {
      formData.value.password = rememberedPassword
    }
  }
})

const handleLogin = async () => {
  submitting.value = true

  try {
    const response = await authApi.login(formData.value)

    if (response && (response.code !== undefined ? response.code === 200 : true)) {
      loginError.value = ''
      // 保存token和用户信息
      const { token, user } = response.data
      
      // 更新Vuex store
      store.dispatch('user/login', { userInfo: user, token })
      
      // 保存登录信息到 localStorage（记住我功能）
      if (rememberMe.value) {
        localStorage.setItem('rememberedUsername', formData.value.username)
        localStorage.setItem('rememberedPassword', formData.value.password)
      } else {
        localStorage.removeItem('rememberedUsername')
        localStorage.removeItem('rememberedPassword')
      }
      
      // Jump to submit page by default (or use redirect query)
      const redirect = router.currentRoute.value.query.redirect || '/submit'
      router.push(redirect)
    } else {
      loginError.value = response?.msg || response?.message || 'Incorrect email or password.'
    }
  } catch (error) {
    console.error('Login error:', error)
    loginError.value = error?.payload?.msg || error?.message || 'Incorrect email or password.'
  } finally {
    submitting.value = false
  }
}
</script>

<style scoped>
/* 自定义滚动条 */
::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-track {
  background: rgba(15, 23, 42, 0.5);
}

::-webkit-scrollbar-thumb {
  background: rgba(251, 191, 36, 0.5);
  border-radius: 4px;
}

::-webkit-scrollbar-thumb:hover {
  background: rgba(251, 191, 36, 0.7);
}

/* Auth labels - harmonize with card cyan/indigo accent */
.auth-label {
  color: #cbd5e1 !important; /* slate-300 */
}

@keyframes shake {
  0%, 100% { transform: translateX(0); }
  20% { transform: translateX(-2px); }
  40% { transform: translateX(2px); }
  60% { transform: translateX(-2px); }
  80% { transform: translateX(2px); }
}

.animate-shake {
  animation: shake 0.25s ease-in-out 0s 1;
}
</style>
