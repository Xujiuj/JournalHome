<template>
  <div class="relative inline-flex overflow-hidden rounded-full p-[1px] focus-within:ring-2 focus-within:ring-cyan-400 focus-within:ring-offset-2 focus-within:ring-offset-slate-50 dark:focus-within:ring-offset-slate-900">
    <!-- Shimmer effect - same as button -->
    <span class="absolute inset-[-1000%] animate-[spin_2s_linear_infinite] bg-[conic-gradient(from_90deg_at_50%_50%,#E2CBFF_0%,#393BB2_50%,#E2CBFF_100%)]" />
    
    <!-- Search box -->
    <div class="relative flex items-center bg-slate-50 dark:bg-slate-800 rounded-full px-4 py-2 backdrop-blur-3xl">
      <!-- Search icon -->
      <svg
        class="w-4 h-4 text-slate-400 dark:text-slate-500 mr-2 transition-colors duration-300"
        fill="none"
        stroke="currentColor"
        viewBox="0 0 24 24"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
        ></path>
      </svg>
      
      <!-- Input -->
      <input
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        @keyup.enter="$emit('search')"
        type="text"
        :placeholder="placeholder"
        class="bg-transparent border-none outline-none text-slate-800 dark:text-slate-200 text-sm placeholder-slate-400 dark:placeholder-slate-500 w-full min-w-[180px]"
      />
    </div>
  </div>
</template>

<script setup>
defineProps({
  modelValue: {
    type: String,
    default: ''
  },
  placeholder: {
    type: String,
    default: 'Search...'
  }
})

defineEmits(['update:modelValue', 'search'])
</script>

<style scoped>
@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>

