<template>
  <div :class="['page-scaffold', gradientClass, 'min-h-screen relative overflow-hidden']">
    <div
      v-if="showBackground"
      class="fixed inset-0 w-full h-full overflow-hidden pointer-events-none z-0"
    >
      <div class="absolute top-0 left-0 w-full h-full bg-gradient-to-br from-slate-900/90 via-slate-800/80 to-blue-900/90"></div>
      <Meteors v-if="meteors" :number="meteorCount" />
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
  gradientClass: {
    type: String,
    default: 'bg-gradient-to-br from-slate-900 via-slate-800 to-blue-900'
  },
  showProgress: {
    type: Boolean,
    default: false
  },
  showBackground: {
    type: Boolean,
    default: true
  },
  meteors: {
    type: Boolean,
    default: true
  },
  meteorCount: {
    type: Number,
    default: 25
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
.page-scaffold {
  transition: background 0.3s ease;
}
</style>
