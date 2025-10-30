<template>
  <button
    :type="type"
    :class="[
      'relative inline-flex h-12 overflow-hidden rounded-xl p-[1px] focus:outline-none focus:ring-2 focus:ring-slate-400 focus:ring-offset-2 focus:ring-offset-slate-50',
      variantClasses,
      { 'opacity-50 cursor-not-allowed': disabled }
    ]"
    :disabled="disabled"
    @click="handleClick"
  >
    <span
      :class="[
        'inline-flex h-full w-full cursor-pointer items-center justify-center rounded-xl px-6 py-1 text-sm font-medium gap-2',
        backgroundClasses
      ]"
    >
      <slot />
    </span>
  </button>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  type: {
    type: String,
    default: 'button'
  },
  variant: {
    type: String,
    default: 'primary',
    validator: (value) => ['primary', 'secondary', 'ghost'].includes(value)
  },
  disabled: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['click'])

const handleClick = (e) => {
  if (!props.disabled) {
    emit('click', e)
  }
}

const variantClasses = computed(() => {
  const variants = {
    primary: 'hover:scale-105 transition-transform duration-300',
    secondary: 'hover:scale-105 transition-transform duration-300',
    ghost: 'hover:scale-105 transition-transform duration-300'
  }
  return variants[props.variant]
})

const backgroundClasses = computed(() => {
  const backgrounds = {
    primary: 'bg-gradient-to-r from-cyan-500 via-blue-600 to-indigo-700 text-white',
    secondary: 'bg-gradient-to-r from-amber-500 via-orange-600 to-red-700 text-white',
    ghost: 'bg-slate-950 text-slate-100'
  }
  return backgrounds[props.variant]
})
</script>

<style scoped>
</style>

