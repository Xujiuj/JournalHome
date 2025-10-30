<template>
  <div class="absolute inset-0 overflow-hidden">
    <span
      v-for="(meteor, idx) in meteors"
      :key="'meteor-' + idx"
      class="animate-meteor-effect absolute top-0 h-0.5 w-0.5 rounded-full bg-slate-50 shadow-[0_0_0_1px_#ffffff10] rotate-[215deg]"
      :style="{
        left: meteor.left + 'px',
        animationDelay: meteor.delay + 's',
        animationDuration: meteor.duration + 's',
      }"
    ></span>
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  number: {
    type: Number,
    default: 60
  }
})

const meteors = computed(() => {
  return Array.from({ length: props.number }, () => ({
    left: Math.floor(Math.random() * 800),
    delay: Math.random() + 2,
    duration: Math.floor(Math.random() * 10 + 5)
  }))
})
</script>
