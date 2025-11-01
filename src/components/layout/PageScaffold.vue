<template>
  <div :class="['page-scaffold', 'min-h-screen relative overflow-hidden', backgroundClass]">
    <!-- 流星特效容器 -->
    <div
      v-if="meteors"
      class="meteor-container fixed inset-0 overflow-hidden pointer-events-none z-0"
    >
      <Meteors :number="meteorCount" />
    </div>

    <div
      v-if="showProgress"
      class="fixed left-0 top-0 w-1 h-full z-50 hidden lg:block"
    >
      <div class="relative w-full h-full bg-slate-200 dark:bg-slate-700">
        <div
          class="absolute top-0 left-0 w-full bg-gradient-to-b from-cyan-500 via-blue-500 to-indigo-500 transition-all duration-700 ease-out shadow-lg"
          :style="{ height: scrollProgress + '%' }"
        ></div>
        <div class="absolute inset-0 bg-gradient-to-b from-transparent via-white/20 dark:via-white/10 to-transparent"></div>
      </div>
    </div>

    <div class="relative z-10">
      <slot :scrollProgress="scrollProgress"></slot>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, onUnmounted, ref, watch } from 'vue'
import Meteors from '@/components/Meteors.vue'

const props = defineProps({
  showProgress: {
    type: Boolean,
    default: false
  },
  meteors: {
    type: Boolean,
    default: true
  },
  meteorCount: {
    type: Number,
    default: 25
  },
  // 背景类型：'white' (与Article页面一致), 'dark' (深色), 'gradient' (渐变), 'transparent' (透明)
  backgroundType: {
    type: String,
    default: 'white'
  }
})

// 根据背景类型设置类名
const backgroundClass = computed(() => {
  switch (props.backgroundType) {
    case 'dark':
      return 'page-background-dark'
    case 'gradient':
      return 'page-background-gradient'
    case 'transparent':
      return 'page-background-transparent'
    case 'white':
    default:
      return 'page-background-white'
  }
})

const scrollProgress = ref(0)
const enableProgress = computed(() => props.showProgress)

const updateScrollProgress = () => {
  if (!enableProgress.value) {
    scrollProgress.value = 0
    return
  }

  const scrollTop = window.scrollY || document.documentElement.scrollTop || 0
  const scrollHeight = document.documentElement.scrollHeight - document.documentElement.clientHeight
  const progress = scrollHeight > 0 ? (scrollTop / scrollHeight) * 100 : 0
  scrollProgress.value = Math.min(100, Math.max(0, progress))
}

onMounted(() => {
  updateScrollProgress()
  if (enableProgress.value) {
    window.addEventListener('scroll', updateScrollProgress, { passive: true })
  }
})

watch(enableProgress, (enabled, previous) => {
  if (enabled && !previous) {
    updateScrollProgress()
    window.addEventListener('scroll', updateScrollProgress, { passive: true })
  } else if (!enabled && previous) {
    window.removeEventListener('scroll', updateScrollProgress)
  }
})

onUnmounted(() => {
  if (enableProgress.value) {
    window.removeEventListener('scroll', updateScrollProgress)
  }
})
</script>

<style scoped>
@import '@/assets/css/background.css';
</style>
<style>
/* 流星特效样式需要全局作用域 */
@import '@/assets/css/effects.css';
</style>
