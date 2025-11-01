<template>
  <div id="app">
    <Header />
    <main>
      <router-view v-slot="{ Component }">
        <transition name="fade" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view>
    </main>
    <Footer />
    
    <!-- 回到顶部按钮 -->
    <button 
      v-show="showBackToTop" 
      @click="scrollToTop"
      class="back-to-top"
      :class="{ 'show': showBackToTop }"
      title="回到顶部"
    >
      <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 10l7-7m0 0l7 7m-7-7v18"></path>
      </svg>
    </button>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'

import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import { scrollToTop, onScroll } from './utils/scroll.js'

const showBackToTop = ref(false)

// 滚动监听
let removeScrollListener = null

const handleScroll = (position) => {
  // 当滚动超过300px时显示回到顶部按钮
  showBackToTop.value = position.y > 300
}

onMounted(() => {
  // 开始监听滚动事件
  removeScrollListener = onScroll(handleScroll)
})

onUnmounted(() => {
  // 清理滚动监听
  if (removeScrollListener) {
    removeScrollListener()
  }
})
</script>

<style>
@import './assets/styles/index.css';
</style>

