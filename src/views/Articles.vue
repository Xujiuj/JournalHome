<template>
  <PageScaffold :show-progress="true" :meteor-count="25" background-type="white">
    <div class="articles min-h-screen relative overflow-hidden">
      <div class="relative z-10 pb-8 min-h-screen">
        <!-- Main Content: Left Sidebar + Right Articles Grid -->
        <div class="flex gap-8 px-4">
          <!-- Left Sidebar - Filters -->
          <div class="flex-shrink-0 ml-4">
            <div class="sticky top-0 h-screen flex items-center">
              <div
                  class="bg-gradient-to-br from-slate-900/95 to-slate-800/95 dark:from-slate-900/95 dark:to-slate-800/95 backdrop-blur-sm rounded-2xl p-6 border border-amber-500/30 dark:border-amber-500/30 shadow-lg w-72">
              <h3 class="text-xl font-semibold text-white dark:text-white mb-6 font-serif">Filters</h3>

              <!-- Search -->
              <div class="mb-6">
                <label class="block text-sm font-medium text-slate-200 dark:text-slate-200 mb-3">Search</label>
                <div class="relative">
                  <input
                      v-model="searchQuery"
                      type="text"
                      placeholder="Search articles..."
                      class="w-full px-4 py-3 pr-12 rounded-lg border border-amber-500/30 dark:border-amber-500/30 bg-slate-800/60 dark:bg-slate-800/60 text-white dark:text-white placeholder-slate-400 dark:placeholder-slate-400 focus:outline-none focus:ring-2 focus:ring-amber-400/50 focus:border-amber-400/50 transition-all duration-300 backdrop-blur-sm"
                      @keyup.enter="performSearch"
                  />
                  <button @click="performSearch"
                          class="absolute right-3 top-1/2 transform -translate-y-1/2 text-slate-400 dark:text-slate-400 hover:text-amber-400 dark:hover:text-amber-400 transition-colors duration-300">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                    </svg>
                  </button>
                </div>
              </div>

              <!-- Subject Area Filter -->
              <div class="mb-6">
                <label class="block text-sm font-medium text-slate-200 dark:text-slate-200 mb-3">Subject Area</label>
                <select v-model="selectedCategory" @change="loadArticles"
                        class="w-full px-4 py-3 rounded-lg border border-amber-500/30 dark:border-amber-500/30 bg-slate-800/60 dark:bg-slate-800/60 text-white dark:text-white focus:outline-none focus:ring-2 focus:ring-amber-400/50 focus:border-amber-400/50 transition-all duration-300 backdrop-blur-sm appearance-none cursor-pointer">
                  <option value="">All Subject Areas</option>
                  <option value="Computer Science">Computer Science</option>
                  <option value="Artificial Intelligence">Artificial Intelligence</option>
                  <option value="Machine Learning">Machine Learning</option>
                  <option value="Engineering">Engineering</option>
                  <option value="Mathematics">Mathematics</option>
                  <option value="Physics">Physics</option>
                  <option value="Biology">Biology</option>
                  <option value="Chemistry">Chemistry</option>
                  <option value="Medicine">Medicine</option>
                  <option value="Environmental Science">Environmental Science</option>
                </select>
              </div>

              <!-- Year Filter -->
              <div class="mb-6">
                <label class="block text-sm font-medium text-slate-200 dark:text-slate-200 mb-3">Publication
                  Year</label>
                <select v-model="selectedYear" @change="loadArticles"
                        class="w-full px-4 py-3 rounded-lg border border-amber-500/30 dark:border-amber-500/30 bg-slate-800/60 dark:bg-slate-800/60 text-white dark:text-white focus:outline-none focus:ring-2 focus:ring-amber-400/50 focus:border-amber-400/50 transition-all duration-300 backdrop-blur-sm appearance-none cursor-pointer">
                  <option value="">All Years</option>
                  <option value="2024">2024</option>
                  <option value="2023">2023</option>
                  <option value="2022">2022</option>
                  <option value="2021">2021</option>
                  <option value="2020">2020</option>
                  <option value="2019">2019</option>
                  <option value="2018">2018</option>
                </select>
              </div>

              <!-- Clear Filters -->
              <button @click="clearFilters"
                      class="w-full px-4 py-3 bg-gradient-to-r from-amber-600/70 to-yellow-600/70 hover:from-amber-500/70 hover:to-yellow-500/70 text-white dark:text-white rounded-lg transition-all duration-300 border border-amber-500/50 hover:border-amber-400/50 backdrop-blur-sm font-medium">
                Clear Filters
              </button>
            </div>
            </div>
          </div>

          <!-- Right Content - Articles Grid -->
          <div class="flex-1 pt-20">
            <!-- Loading State -->
            <div v-if="loading" class="text-center py-12">
              <div class="inline-flex items-center gap-3 text-white">
                <div class="w-6 h-6 border-2 border-amber-400 border-t-transparent rounded-full animate-spin"></div>
                <span>Loading articles...</span>
              </div>
            </div>

            <!-- Articles Grid -->
            <div v-else class="space-y-8 pt-4 max-w-4xl mx-auto">
              <article v-for="(article, index) in articles" :key="article.articleId"
                       :class="['group bg-gradient-to-br from-slate-900/95 to-slate-800/95 dark:from-slate-900/95 dark:to-slate-800/95 backdrop-blur-sm rounded-2xl shadow-academic p-5 transition-all duration-500 animate-slide-up border border-amber-500/30 dark:border-amber-500/30 overflow-hidden relative',
                               getArticleCardTheme(index)]"
                       :style="{ animationDelay: (index * 0.1) + 's' }">

                <!-- 流光效果层 -->
                <div
                    class="absolute inset-0 opacity-0 hover:opacity-100 focus:opacity-0 transition-opacity duration-150 pointer-events-none overflow-hidden">
                  <div
                      class="absolute inset-0 bg-gradient-to-r from-transparent via-amber-200/10 to-transparent dark:via-amber-200/5 transform -translate-x-full hover:translate-x-full transition-transform duration-700 ease-in-out"
                      style="will-change: transform;"></div>
                  <!-- 额外的亮光效果 -->
                  <div
                      class="absolute inset-0 bg-gradient-to-r from-transparent via-amber-100/5 to-transparent dark:via-amber-100/3 transform -translate-x-full hover:translate-x-full transition-transform duration-450 ease-in-out"
                      style="animation-delay: 0.06s; will-change: transform;"></div>
                </div>

                <!-- 顶部彩色横条 -->
                <div
                    class="absolute top-0 left-0 w-full h-1 bg-gradient-to-r from-amber-400 to-yellow-400 transform scale-x-0 group-hover:scale-x-100 transition-transform duration-500"></div>

                <div class="relative z-10">
                  <!-- 第一行：文章标题 -->
                  <div class="mb-2">
                    <h3 class="text-xl font-bold text-white dark:text-white font-serif leading-tight group-hover:text-amber-300 dark:group-hover:text-amber-300 transition-colors duration-300 line-clamp-2">
                      <router-link :to="`/articles/${article.articleManuscriptId}`" class="hover:underline">
                        {{ article.articleTitle }}
                      </router-link>
                    </h3>
                  </div>

                  <!-- 第二行：作者信息 -->
                  <div class="flex items-center text-slate-300 dark:text-slate-300 text-sm mb-2 flex-wrap gap-1">
                    <svg class="w-5 h-5 mr-3 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                    </svg>
                    <template v-if="getSortedAuthors(article.articleAuthors).length > 0">
                      <template v-for="(author, index) in getSortedAuthors(article.articleAuthors)" :key="index">
                        <span class="inline relative">
                          {{ author.authorUser?.userRealName }}
                          <sup v-if="author.authorIsCorresponding === 1"
                               class="text-white"
                               title="通讯作者">
                            <svg class="w-3 h-3 inline"
                                 fill="none"
                                 stroke="currentColor"
                                 viewBox="0 0 24 24">
                              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"></path>
                            </svg>
                          </sup>
                        </span>
                        <span v-if="index < getSortedAuthors(article.articleAuthors).length - 1" class="mx-1">,</span>
                      </template>
                    </template>
                    <span v-else>Unknown Authors</span>
                  </div>

                  <!-- 第三行：左边操作按钮，右边日期 -->
                  <div class="flex justify-between items-center mt-3">
                    <!-- 左边：操作按钮 -->
                    <div class="flex items-center gap-4">
                      <!-- 阅读全文 -->
                      <router-link :to="`/articles/${article.articleManuscriptId}`"
                                   class="group/link inline-flex items-center text-white dark:text-white font-semibold hover:text-amber-300 dark:hover:text-amber-300 transition-all duration-300 hover:translate-x-1 text-sm">
                        Read Full Article
                        <svg class="w-5 h-5 ml-2 transition-transform duration-300 group-hover/link:translate-x-1"
                             fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                        </svg>
                      </router-link>

                      <!-- 引用按钮 -->
                      <button @click="copyCitation(article)"
                              class="group/cite inline-flex items-center text-slate-400 dark:text-slate-400 hover:text-green-400 dark:hover:text-green-400 transition-all duration-300 hover:scale-110 text-base relative"
                              data-tooltip="Copy Citation">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M8 16H6a2 2 0 01-2-2V6a2 2 0 012-2h8a2 2 0 012 2v2m-6 12h8a2 2 0 002-2v-8a2 2 0 00-2-2h-8a2 2 0 00-2 2v8a2 2 0 002 2z"></path>
                        </svg>
                        <span class="ml-1 hidden group-hover/cite:inline transition-all duration-300">Cite</span>
                      </button>

                      <!-- 下载按钮 -->
                      <button @click="downloadArticle(article)"
                              class="group/download inline-flex items-center text-slate-400 dark:text-slate-400 hover:text-purple-400 dark:hover:text-purple-400 transition-all duration-300 hover:scale-110 text-base relative"
                              data-tooltip="Download PDF">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M12 10v6m0 0l-3-3m3 3l3-3m2 8H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"></path>
                        </svg>
                        <span
                            class="ml-1 hidden group-hover/download:inline transition-all duration-300">Download</span>
                      </button>
                    </div>

                    <!-- 右边：发表日期 -->
                    <div class="flex items-center text-slate-400 dark:text-slate-400 text-base">
                      <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                              d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                      </svg>
                      {{ formatDate(article.articleOnlineTime) }}
                    </div>
                  </div>
                </div>
              </article>
            </div>

            <!-- No Results -->
            <div v-if="!loading && articles.length === 0" class="text-center py-12">
              <div
                  class="bg-gradient-to-br from-slate-900/95 to-slate-800/95 dark:from-slate-900/95 dark:to-slate-800/95 backdrop-blur-sm rounded-2xl p-8 border border-amber-500/30 dark:border-amber-500/30">
                <h3 class="text-xl font-semibold text-white mb-2">No articles found</h3>
                <p class="text-slate-300 mb-4">Try adjusting your search criteria or browse all articles.</p>
                <button @click="clearFilters"
                        class="px-6 py-2 bg-gradient-to-r from-amber-600/70 to-yellow-600/70 hover:from-amber-500/70 hover:to-yellow-500/70 text-white rounded-lg transition-all duration-300 border border-amber-500/50 hover:border-amber-400/50 backdrop-blur-sm font-medium">
                  Clear Filters
                </button>
              </div>
            </div>

            <!-- Pagination -->
            <div v-if="totalPages > 1" class="flex justify-center items-center gap-4 mt-12">
              <button
                  @click="goToPage(currentPage - 1)"
                  :disabled="currentPage === 1"
                  class="px-4 py-2 bg-gradient-to-r from-amber-600/70 to-yellow-600/70 hover:from-amber-500/70 hover:to-yellow-500/70 text-white rounded-lg transition-all duration-300 border border-amber-500/50 hover:border-amber-400/50 disabled:opacity-50 disabled:cursor-not-allowed backdrop-blur-sm font-medium"
              >
                Previous
              </button>

              <span class="text-slate-300 font-medium">
                Page {{ currentPage }} of {{ totalPages }}
              </span>

              <button
                  @click="goToPage(currentPage + 1)"
                  :disabled="currentPage === totalPages"
                  class="px-4 py-2 bg-gradient-to-r from-amber-600/70 to-yellow-600/70 hover:from-amber-500/70 hover:to-yellow-500/70 text-white rounded-lg transition-all duration-300 border border-amber-500/50 hover:border-amber-400/50 disabled:opacity-50 disabled:cursor-not-allowed backdrop-blur-sm font-medium"
              >
                Next
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </PageScaffold>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {articleApi} from '@/api'
import PageScaffold from '@/components/layout/PageScaffold.vue'
import {DEFAULT_PAGE_SIZE} from '@/config/app'
import {
  normalizeAuthors,
  formatAuthorNames,
  formatDate as formatArticleDate
} from '@/utils/article'

const articles = ref([])
const loading = ref(false)
const searchQuery = ref('')
const selectedCategory = ref('')
const selectedYear = ref('')
const currentPage = ref(1)
const pageSize = ref(DEFAULT_PAGE_SIZE)
const totalPages = ref(1)

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

const loadArticles = async () => {
  loading.value = true
  try {
    const params = {
      pageNum: currentPage.value,
      pageSize: pageSize.value,
      articleStatusId: 5
    }

    const trimmedQuery = searchQuery.value.trim()
    if (trimmedQuery) {
      params.articleTitle = trimmedQuery
    }

    if (selectedCategory.value) {
      params.articleSubjectArea = selectedCategory.value
    }

    if (selectedYear.value) {
      params.articlePublishYear = selectedYear.value
    }

    const response = await articleApi.getArticleList(params)
    const list = extractList(response)
    const total = Number.isFinite(response?.total) ? response.total : list.length

    articles.value = list
    totalPages.value = Math.max(1, Math.ceil(total / pageSize.value))
  } catch (error) {
    console.error('Failed to load articles:', error)
    articles.value = []
    totalPages.value = 1
  } finally {
    loading.value = false
  }
}

const performSearch = () => {
  currentPage.value = 1
  loadArticles()
}

const clearFilters = () => {
  searchQuery.value = ''
  selectedCategory.value = ''
  selectedYear.value = ''
  currentPage.value = 1
  loadArticles()
}

const goToPage = (page) => {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page
    loadArticles()
  }
}

const formatDate = (dateString) => formatArticleDate(dateString)

const getArticleCardTheme = (index) => {
  const themes = ['article-card-blue', 'article-card-purple', 'article-card-green']
  return themes[index % themes.length]
}

const getSortedAuthors = (authors) => {
  return normalizeAuthors(authors)
}

// 复制引用信息到剪贴板
const copyCitation = async (article) => {
  const citation = `${formatAuthorNames(article.articleAuthors)}. "${article.articleTitle}". ${new Date(article.articleSubmitTime).getFullYear()}.`

  try {
    await navigator.clipboard.writeText(citation)
    // 这里可以添加一个toast提示，但为了简化先用console.log
    console.log('Citation copied to clipboard:', citation)
  } catch (error) {
    console.error('Failed to copy citation:', error)
    // 备用的复制方法
    const textArea = document.createElement('textarea')
    textArea.value = citation
    document.body.appendChild(textArea)
    textArea.select()
    document.execCommand('copy')
    document.body.removeChild(textArea)
  }
}

// 下载文章（这里是模拟功能，实际需要后端支持）
const downloadArticle = (article) => {
  // 实际实现中，这里应该调用API下载PDF文件
  console.log('Downloading article:', article.articleTitle)

  // 模拟下载延迟
  setTimeout(() => {
    const link = document.createElement('a')
    link.href = '#' // 实际应该是PDF文件的URL
    link.download = `${article.articleTitle.replace(/[^a-zA-Z0-9]/g, '_')}.pdf`
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
  }, 500)
}

onMounted(() => {
  loadArticles()
})
</script>
