<template>
  <div class="search-box-container" ref="searchContainer">
    <label class="search-box" @click="toggleInput">
      <span class="search-box__text" :class="{ 'hidden': isInputVisible }">{{ placeholder }}</span>
      <input
        ref="searchInput"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        @keyup.enter="performSearch"
        @click.stop
        type="text"
        class="search-box__input"
        :class="{ 'active': isInputVisible }"
        :placeholder="placeholder"
      />
      <svg 
        class="search-box__icon" 
        @click.stop="performSearch"
        fill="none" 
        stroke="currentColor" 
        viewBox="0 0 24 24"
      >
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
      </svg>
    </label>
  </div>
</template>

<script setup>
import { ref, nextTick, onMounted, onUnmounted } from 'vue'

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  },
  placeholder: {
    type: String,
    default: 'Search articles'
  }
})

const emit = defineEmits(['update:modelValue', 'search'])
const searchInput = ref(null)
const isInputVisible = ref(false)

const toggleInput = () => {
  if (!isInputVisible.value) {
    isInputVisible.value = true
    nextTick(() => {
      searchInput.value?.focus()
      searchInput.value?.classList.add('active')
    })
  } else {
    performSearch()
  }
}

const performSearch = () => {
  // 搜索后保持输入框激活状态，让用户可以继续搜索
  emit('search')
}

const handleClickOutside = (event) => {
  if (searchContainer.value && !searchContainer.value.contains(event.target) && isInputVisible.value) {
    searchInput.value?.classList.remove('active')
    isInputVisible.value = false
  }
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside)
})
</script>

<style scoped>
.search-box-container {
  display: inline-block;
  transition: transform 0.3s ease;
}

.search-box-container:hover {
  transform: scale(1.05);
}

.search-box {
  display: inline-flex;
  align-items: center;
  justify-content: space-between;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  border: 2px solid rgba(14, 165, 233, 0.3);
  border-radius: 999px;
  padding: 0.625rem 1.25rem;
  transition: all 0.3s ease;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  cursor: pointer;
  min-width: 200px;
  height: 44px;
  position: relative;
  overflow: hidden;
}

.search-box:hover {
  border-color: rgba(14, 165, 233, 0.5);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.08), 0 0 0 1px rgba(14, 165, 233, 0.1);
}

.search-box__text {
  font-size: 0.9rem;
  font-weight: 500;
  color: #64748b;
  transition: color 0.3s ease, opacity 0.3s ease;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.search-box__text.hidden {
  opacity: 0;
  pointer-events: none;
}

.search-box:hover .search-box__text:not(.hidden) {
  color: #0ea5e9;
}

.search-box:hover .search-box__icon {
  color: #0ea5e9;
}

.search-box__icon {
  width: 1.125rem;
  height: 1.125rem;
  color: #64748b;
  transition: color 0.3s ease;
  flex-shrink: 0;
  cursor: pointer;
  position: relative;
  z-index: 10;
}

.search-box__input {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border: none;
  background: rgba(255, 255, 255, 0.98);
  outline: none;
  font-size: 0.9rem;
  color: #0f172a;
  padding: 0.625rem 1.25rem;
  border-radius: 999px;
  opacity: 0;
  transform: scale(0.95);
  transition: all 0.3s ease;
  pointer-events: none;
}

.search-box__input:focus {
  border-color: rgba(14, 165, 233, 0.7);
  box-shadow: 0 0 0 3px rgba(14, 165, 233, 0.15);
}

.search-box__input::placeholder {
  color: #94a3b8;
}

/* 深色模式 */
.dark .search-box {
  background: rgba(15, 23, 42, 0.9);
  border-color: rgba(14, 165, 233, 0.4);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
}

.dark .search-box:hover {
  border-color: rgba(14, 165, 233, 0.6);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3), 0 0 0 1px rgba(14, 165, 233, 0.2);
}

.dark .search-box__text {
  color: #94a3b8;
}

.dark .search-box:hover .search-box__text:not(.hidden) {
  color: #38bdf8;
}

.dark .search-box__icon {
  color: #94a3b8;
}

.dark .search-box:hover .search-box__icon {
  color: #38bdf8;
}

.dark .search-box__input {
  background: rgba(15, 23, 42, 0.98);
  color: #e2e8f0;
}

.dark .search-box__input::placeholder {
  color: #64748b;
}

/* 输入框激活状态 */
.search-box__input.active {
  opacity: 1;
  transform: scale(1);
  pointer-events: all;
  padding-right: 2.5rem; /* 为图标留出空间 */
}

/* 响应式 */
@media (max-width: 768px) {
  .search-box {
    min-width: 160px;
    padding: 0.5rem 1rem;
  }
}
</style>
