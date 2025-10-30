<template>
  <PageScaffold :meteor-count="25" :show-progress="true">
    <div class="notices min-h-screen relative overflow-hidden">
      <div class="container relative z-10 mx-auto px-4 py-16">
      <!-- Page Header -->
        <section class="relative z-10 py-8 md:py-12">
          <div class="text-center">
            <h1 class="text-4xl lg:text-5xl xl:text-6xl font-bold text-white leading-tight font-serif mb-6 animate-fade-in drop-shadow-2xl">
              Notices
            </h1>
            <p class="text-lg lg:text-xl text-slate-100 leading-relaxed mb-8 animate-slide-up drop-shadow-lg">
              Stay updated with the latest announcements and news
            </p>
          </div>
        </section>

      <!-- Notices List -->
        <section class="relative z-10 py-8">
          <div class="grid gap-8">
            <div v-for="(notice, index) in notices" :key="notice.noticeId" class="group bg-gradient-to-br from-slate-900/95 to-slate-800/95 dark:from-slate-900/95 dark:to-slate-800/95 backdrop-blur-sm rounded-2xl shadow-academic p-8 hover:shadow-hover transition-all duration-700 ease-out hover:-translate-y-3 hover:scale-105 animate-slide-up border border-slate-700/40 overflow-hidden relative" :style="{ animationDelay: (index * 0.1) + 's' }">
              <!-- 流光效果层 -->
              <div class="absolute inset-0 opacity-0 hover:opacity-100 focus:opacity-0 transition-opacity duration-150 pointer-events-none overflow-hidden">
                <div class="absolute inset-0 bg-gradient-to-r from-transparent via-amber-200/10 to-transparent dark:via-amber-200/5 transform -translate-x-full hover:translate-x-full transition-transform duration-700 ease-in-out" style="will-change: transform;"></div>
                <!-- 额外的亮光效果 -->
                <div class="absolute inset-0 bg-gradient-to-r from-transparent via-amber-100/5 to-transparent dark:via-amber-100/3 transform -translate-x-full hover:translate-x-full transition-transform duration-450 ease-in-out" style="animation-delay: 0.06s; will-change: transform;"></div>
              </div>
              <!-- 顶部彩色横条（根据类型变色） -->
              <div class="absolute top-0 left-0 w-full h-1 transform scale-x-0 group-hover:scale-x-100 transition-transform duration-700 ease-out" :class="getTopBarGradient(notice.noticeType)"></div>
              
              <div class="relative z-10">
                <div class="flex items-center justify-between flex-wrap gap-3 mb-4">
                  <div class="flex items-center gap-3">
                    <span :class="getTypeBadgeClasses(notice.noticeType)">
                      {{ getNoticeTypeText(notice.noticeType) }}
                    </span>
                    <span class="text-sm text-slate-400">{{ formatDate(notice.noticePublishDate) }}</span>
                  </div>
                  <div class="text-xs px-2 py-1 rounded-full border border-slate-600 text-slate-300 uppercase tracking-wide">
                    {{ notice.noticePriority || 'NORMAL' }}
                  </div>
                </div>
                
                <h2 class="notice-title">
                  <router-link :to="`/notices/${notice.noticeId}`">{{ notice.noticeTitle }}</router-link>
                </h2>
                
                <p class="notice-content">{{ notice.noticeContent }}</p>
                
                <div class="notice-footer">
                  <div class="notice-views">
                    <i class="icon-eye">👁</i>
                    {{ notice.noticeViews || 0 }} views
                  </div>
                  <router-link :to="`/notices/${notice.noticeId}`" class="group/link inline-flex items-center text-white font-semibold hover:text-amber-300 transition-all duration-500 ease-out hover:translate-x-1">
                    Read More
                    <svg class="w-4 h-4 ml-2 transition-transform duration-500 ease-out group-hover/link:translate-x-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                    </svg>
                  </router-link>
                </div>
              </div>
            </div>
          </div>
        </section>

      <!-- No Notices -->
      <div v-if="notices.length === 0" class="no-notices">
        <h3>No notices available</h3>
        <p>Check back later for updates.</p>
      </div>
    </div>
  </div>
  </PageScaffold>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { noticeApi } from '@/api'
import PageScaffold from '@/components/layout/PageScaffold.vue'

const notices = ref([])
const loading = ref(false)

const extractList = (payload) => {
  if (!payload) {
    return []
  }

  if (Array.isArray(payload)) {
    return payload
  }

  if (Array.isArray(payload.data)) {
    return payload.data
  }

  if (Array.isArray(payload.rows)) {
    return payload.rows
  }

  if (payload.data && Array.isArray(payload.data.data)) {
    return payload.data.data
  }

  return []
}

const loadNotices = async () => {
  loading.value = true
  try {
    const response = await noticeApi.getNotices({
      pageNum: 1,
      pageSize: 50,
      noticeStatus: 1
    })
    notices.value = extractList(response)
  } catch (error) {
    console.error('Failed to load notices:', error)
    // 设置默认数据，确保页面正常显示
    notices.value = [
      {
        noticeId: 1,
        noticeTitle: '欢迎访问期刊门户网站',
        noticeContent: '感谢您访问我们的期刊门户网站。我们致力于为科研工作者提供高质量的学术交流平台。',
        noticeType: 'ANNOUNCEMENT',
        noticePriority: 'NORMAL',
        noticePublishDate: new Date().toISOString(),
        noticeViews: 50
      }
    ]
  } finally {
    loading.value = false
  }
}

const formatDate = (dateString) => {
  if (!dateString) return ''
  return new Date(dateString).toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  })
}

const getNoticeTypeText = (type) => {
  const typeMap = {
    'ANNOUNCEMENT': 'Announcement',
    'CALL_FOR_PAPERS': 'Call for Papers',
    'CONFERENCE': 'Conference',
    'DEADLINE': 'Deadline',
    'UPDATE': 'Update'
  }
  return typeMap[type] || type
}

// Gradient palette by type
const TYPE_STYLES = Object.freeze({
  ANNOUNCEMENT: {
    badge: 'from-cyan-500 to-blue-600',
    top: 'from-cyan-400 to-blue-500'
  },
  CALL_FOR_PAPERS: {
    badge: 'from-amber-500 to-yellow-600',
    top: 'from-amber-400 to-yellow-500'
  },
  CONFERENCE: {
    badge: 'from-violet-500 to-fuchsia-600',
    top: 'from-violet-400 to-fuchsia-500'
  },
  DEADLINE: {
    badge: 'from-rose-500 to-red-600',
    top: 'from-rose-400 to-red-500'
  },
  UPDATE: {
    badge: 'from-emerald-500 to-teal-600',
    top: 'from-emerald-400 to-teal-500'
  },
  DEFAULT: {
    badge: 'from-slate-500 to-slate-600',
    top: 'from-slate-400 to-slate-500'
  }
})

const getTypeBadgeClasses = (type) => {
  const sty = TYPE_STYLES[type] || TYPE_STYLES.DEFAULT
  return `inline-flex items-center gap-1 px-3 py-1 rounded-full text-xs font-semibold text-white bg-gradient-to-r ${sty.badge} shadow`
}

const getTopBarGradient = (type) => {
  const sty = TYPE_STYLES[type] || TYPE_STYLES.DEFAULT
  return `bg-gradient-to-r ${sty.top}`
}

onMounted(() => {
  loadNotices()
})
</script>

<style>
@import '../assets/css/notices.css';
@import '../assets/css/utilities.css';

.notices {
  padding: 2rem 0;
  min-height: 100vh;
}

/* No Notices */
.no-notices {
  text-align: center;
  padding: 4rem 2rem;
  background: linear-gradient(135deg, rgba(15, 23, 42, 0.95) 0%, rgba(30, 41, 59, 0.95) 100%);
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(10px);
}

.no-notices h3 {
  color: #ffffff;
  margin-bottom: 1rem;
}

.no-notices p {
  color: #94a3b8;
}
</style>
