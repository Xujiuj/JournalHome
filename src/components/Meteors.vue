<template>
  <div class="absolute inset-0 overflow-hidden">
    <span
      v-for="(meteor, idx) in meteors"
      :key="'meteor-' + idx"
      class="animate-meteor-effect absolute top-0 h-0.5 w-0.5 rounded-full bg-white shadow-[0_0_0_1px_#ffffff10] rotate-[215deg]"
      :style="{
        left: meteor.left + 'px',
        animationDelay: meteor.delay + 's',
        animationDuration: meteor.duration + 's',
        '--duration': meteor.duration + 's',
        '--meteor-height': (windowHeight.value * 0.8) + 'px',
        '--meteor-width': (-windowHeight.value * 0.6) + 'px'
      }"
    ></span>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'

const props = defineProps({
  number: {
    type: Number,
    default: 60
  }
})

const windowWidth = ref(typeof window !== 'undefined' ? window.innerWidth : 1920)
const windowHeight = ref(typeof window !== 'undefined' ? window.innerHeight : 1080)

onMounted(() => {
  if (typeof window !== 'undefined') {
    const updateSize = () => {
      windowWidth.value = window.innerWidth
      windowHeight.value = window.innerHeight
    }
    window.addEventListener('resize', updateSize)
    return () => window.removeEventListener('resize', updateSize)
  }
})

const meteors = computed(() => {
  return Array.from({ length: props.number }, () => ({
    left: Math.floor(Math.random() * windowWidth.value),
    delay: Math.random() * 2,
    duration: Math.floor(Math.random() * 8 + 3)
  }))
})
</script>
