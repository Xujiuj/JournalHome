<template>
  <footer class="relative z-10 bg-gradient-to-br from-slate-800/90 to-slate-900/90 backdrop-blur-sm text-white py-16">
    <div class="container mx-auto px-4">
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 mb-12">
        <!-- Journal Info -->
        <div class="space-y-4">
          <h3 class="text-xl font-bold text-slate-100 font-serif">
            {{ displayShortName }}</h3>
          <p class="text-slate-200 leading-relaxed">
            {{ displayMission }}</p>
          <div class="space-y-2 text-sm">
            <p class="text-slate-200"><strong class="text-slate-100">ISSN:</strong> {{
                displayIssn
              }}</p>
            <p class="text-slate-200"><strong class="text-slate-100">Email:</strong>
              <a :href="`mailto:${displayEmail}`" class="text-slate-200 hover:text-slate-100 transition-colors duration-200">
                {{ displayEmail }}
              </a>
            </p>
            <p class="text-slate-200"><strong class="text-slate-100">Phone:</strong>
              <a :href="`tel:${displayPhone}`" class="text-slate-200 hover:text-slate-100 transition-colors duration-200">
                {{ displayPhone }}
              </a>
            </p>
          </div>
        </div>

        <!-- Quick Links -->
        <div class="space-y-4">
          <h4 class="text-lg font-semibold text-slate-100">Quick Links</h4>
          <ul class="space-y-2">
            <li v-for="menuItem in quickLinks" :key="menuItem.menuId">
              <router-link
                  :to="menuItem.menuPath"
                  class="text-slate-200 hover:text-slate-100 transition-colors duration-200"
              >
                {{ menuItem.menuName }}
              </router-link>
            </li>
          </ul>
        </div>

        <!-- For Authors -->
        <div class="space-y-4">
          <h4 class="text-lg font-semibold text-slate-100">For Authors</h4>
          <ul class="space-y-2">
            <li><a href="#" class="text-slate-200 hover:text-slate-100 transition-colors duration-200">Submission
              Guidelines</a></li>
            <li><a href="#" class="text-slate-200 hover:text-slate-100 transition-colors duration-200">Review
              Process</a></li>
            <li><a href="#" class="text-slate-200 hover:text-slate-100 transition-colors duration-200">Publication
              Ethics</a></li>
            <li><a href="#" class="text-slate-200 hover:text-slate-100 transition-colors duration-200">Copyright
              Policy</a></li>
            <li><a href="#" class="text-slate-200 hover:text-slate-100 transition-colors duration-200">Open Access</a>
            </li>
          </ul>
        </div>
      </div>

      <!-- Footer Bottom -->
      <div class="border-t border-slate-600 pt-8">
        <div class="text-center">
          <p class="text-slate-300 text-sm">&copy; {{ currentYear }}
            {{ displayName }}. All rights reserved.</p>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup>
import { computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

// 从 Vuex 获取系统信息和菜单
const branding = computed(() => store.getters['sysInfo/brandingInfo'])
const menuList = computed(() => store.getters['sysInfo/getMenuList'] || [])
const currentYear = computed(() => new Date().getFullYear())
const displayShortName = computed(() => branding.value.shortName)
const displayMission = computed(() => branding.value.mission)
const displayIssn = computed(() => branding.value.issn)
const displayEmail = computed(() => branding.value.email)
const displayPhone = computed(() => branding.value.phone)
const displayName = computed(() => branding.value.name)
const quickLinks = computed(() =>
  menuList.value.filter(
    (item) => item?.menuParentId === 0 && item?.menuType === 'M' && typeof item.menuPath === 'string'
  )
)
</script>

<style>
@import '../assets/css/footer.css';
</style>