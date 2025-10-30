<template>
  <PageScaffold :show-progress="true" :meteor-count="10">
    <div class="min-h-screen bg-white dark:bg-slate-900 py-10">
      <div class="container mx-auto px-4">
        <div class="mb-8 text-center">
          <h1 class="text-3xl md:text-4xl font-bold text-slate-900 dark:text-white">My Submissions</h1>
          <p class="text-slate-600 dark:text-slate-300 mt-2">View and manage your submitted manuscripts</p>
        </div>

        <div v-if="loading" class="flex justify-center py-20">
          <div class="w-8 h-8 border-2 border-cyan-400 border-t-transparent rounded-full animate-spin"></div>
        </div>

        <div v-else>
          <div v-if="submissions.length === 0" class="text-center text-slate-500 dark:text-slate-400 py-16">
            No submissions found.
          </div>

          <div v-else class="space-y-4">
            <article v-for="item in submissions" :key="item.articleId || item.articleManuscriptId" class="p-5 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800">
              <div class="flex justify-between items-start gap-4">
                <div>
                  <h2 class="text-xl font-semibold text-slate-900 dark:text-white">{{ item.articleTitle }}</h2>
                  <div class="text-sm text-slate-500 dark:text-slate-400 mt-1">
                    <span v-if="item.articleManuscriptId">ID: {{ item.articleManuscriptId }}</span>
                    <span v-if="item.articleStatus?.statusName || item.articleStatusId" class="ml-2">
                      Status: {{ item.articleStatus?.statusName || item.articleStatusId }}
                    </span>
                  </div>
                </div>
                <router-link :to="`/articles/${item.articleManuscriptId || item.articleId}`" class="text-cyan-600 dark:text-cyan-400 hover:underline text-sm whitespace-nowrap">View details</router-link>
              </div>
            </article>
          </div>
        </div>
      </div>
    </div>
  </PageScaffold>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useStore } from 'vuex'
import { articleApi } from '@/api'
import PageScaffold from '@/components/layout/PageScaffold.vue'

const store = useStore()
const user = computed(() => store.getters['user/getUserInfo'])

const loading = ref(false)
const submissions = ref([])

const extractList = (payload) => {
  if (!payload) return []
  if (Array.isArray(payload)) return payload
  if (Array.isArray(payload.data)) return payload.data
  if (Array.isArray(payload.rows)) return payload.rows
  if (payload.data && Array.isArray(payload.data.data)) return payload.data.data
  return []
}

const loadSubmissions = async () => {
  loading.value = true
  try {
    let response
    if (user.value?.userId && articleApi.getUserSubmissions) {
      // Prefer dedicated endpoint if backend provides it
      response = await articleApi.getUserSubmissions(user.value.userId)
    } else {
      // Fallback: list with default pagination; backend may filter by auth internally
      response = await articleApi.getArticleList({ pageNum: 1, pageSize: 50 })
    }
    submissions.value = extractList(response)
  } catch (e) {
    console.error('Failed to load submissions:', e)
    submissions.value = []
  } finally {
    loading.value = false
  }
}

onMounted(loadSubmissions)
</script>
