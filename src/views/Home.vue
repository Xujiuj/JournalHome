<template>
  <PageScaffold :show-progress="true" :meteor-count="25" background-type="gradient">
    <div class="home min-h-screen relative overflow-hidden">
      <!-- Hero Section -->
      <section class="hero-section relative z-10 flex items-start justify-center bg-transparent" style="height: 90vh;">
        <div class="container mx-auto px-4 w-full h-full flex items-center justify-center">
          <div class="flex flex-col lg:flex-row items-center justify-center gap-8 lg:gap-12 max-w-6xl mx-auto w-full">
            <div class="flex-1 max-w-3xl flex flex-col justify-center">
              <h2 class="text-4xl lg:text-5xl xl:text-6xl font-bold text-white leading-tight font-serif mb-6 animate-fade-in drop-shadow-2xl">
                {{ heroTitle }}
              </h2>
              <p class="text-base lg:text-lg text-slate-100 leading-relaxed mb-6 animate-slide-up drop-shadow-lg">
                {{ heroSubtitle }}
              </p>
              <div class="flex flex-col sm:flex-row gap-3 lg:gap-4 animate-slide-up">
                <button
                  class="group relative inline-flex items-center gap-2 px-5 py-2.5 lg:px-6 lg:py-3 bg-gradient-to-r from-cyan-500 via-blue-500 to-indigo-500 text-white rounded-full font-semibold text-sm lg:text-base hover:from-cyan-600 hover:via-blue-600 hover:to-indigo-600 hover:shadow-2xl transition-all duration-700 ease-out overflow-hidden"
                  @click="goToArticles"
                  type="button"
                >
                  <svg class="w-4 h-4 transition-transform duration-500 ease-out group-hover:rotate-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.746 0 3.332.477 4.5 1.253v13C19.832 18.477 18.246 18 16.5 18c-1.746 0-3.332.477-4.5 1.253"></path>
                  </svg>
                  Browse Articles
                  <div class="absolute inset-0 bg-gradient-to-r from-white to-white opacity-0 group-hover:opacity-20 transition-opacity duration-500"></div>
                  <div class="absolute inset-0 bg-gradient-to-r from-transparent via-white to-transparent opacity-0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"></div>
                </button>
                <button
                  class="group relative inline-flex items-center gap-2 px-5 py-2.5 lg:px-6 lg:py-3 bg-gradient-to-r from-slate-600 to-slate-700 text-white border-2 border-slate-500 rounded-full font-semibold text-sm lg:text-base hover:from-slate-500 hover:to-slate-600 hover:border-slate-400 hover:shadow-xl transition-all duration-700 ease-out overflow-hidden"
                  @click="goToSubmit"
                  type="button"
                >
                  <svg class="w-4 h-4 transition-transform duration-500 ease-out group-hover:rotate-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path>
                  </svg>
                  Submit Your Research
                  <div class="absolute inset-0 bg-gradient-to-r from-cyan-400 to-blue-400 opacity-0 group-hover:opacity-10 transition-opacity duration-500"></div>
                  <div class="absolute inset-0 bg-gradient-to-r from-transparent via-white to-transparent opacity-0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"></div>
                </button>
              </div>

              <!-- Scroll Arrow -->
              <div class="absolute bottom-8 left-1/2 transform -translate-x-1/2 animate-fade-in cursor-pointer" @click="scrollToNextSection">
                <svg class="scroll-arrow text-slate-300 hover:text-white transition-colors duration-500 ease-out" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M7 13l3 3 3-3M7 6l3 3 3-3"/>
                </svg>
              </div>
            </div>
            <div class="flex-shrink-0 flex items-center justify-center">
              <div class="w-64 h-72 lg:w-72 lg:h-80 bg-gradient-to-br from-cyan-500/20 to-blue-500/20 backdrop-blur-sm border border-cyan-400/30 rounded-3xl flex items-center justify-center overflow-hidden animate-scale-in">
                <img :src="heroLogo" alt="Journal Cover" class="w-full h-full object-cover rounded-3xl" />
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- 下方板块统一背景区域 -->
      <div class="relative z-10 bg-gradient-to-b from-slate-900 via-blue-900 to-indigo-900">
      <!-- Recent Publications Section -->
      <section class="recent-publications-section relative z-10 py-20">
        <div class="container mx-auto px-4">
          <div class="text-center mb-16 animate-fade-in">
            <h2 class="text-3xl lg:text-4xl font-bold text-white mb-4 font-serif">Recent Publications</h2>
            <p class="text-lg text-slate-300 max-w-3xl mx-auto">Explore our most recent peer-reviewed articles from leading researchers worldwide</p>
          </div>
          <div class="space-y-4 mb-12 max-w-4xl mx-auto">
            <div v-if="isLoadingFeatured" class="text-center py-12">
              <div class="inline-flex items-center gap-3 text-white">
                <div class="w-6 h-6 border-2 border-cyan-400 border-t-transparent rounded-full animate-spin"></div>
                <span>正在加载最新文章...</span>
              </div>
            </div>
            <template v-else>
              <article
                v-for="(article, index) in featuredArticles"
                :key="article.articleId || article.articleManuscriptId || index"
                class="group bg-gradient-to-br from-slate-900/95 to-slate-800/95 dark:from-slate-900/95 dark:to-slate-800/95 backdrop-blur-sm rounded-2xl shadow-academic p-5 transition-all duration-700 ease-out animate-slide-up border border-amber-500/30 dark:border-amber-500/30 overflow-hidden relative"
                :style="{ animationDelay: (index * 0.1) + 's' }"
              >
                <div class="absolute inset-0 opacity-0 hover:opacity-100 focus:opacity-0 transition-opacity duration-150 pointer-events-none overflow-hidden">
                  <div class="absolute inset-0 bg-gradient-to-r from-transparent via-amber-200/10 to-transparent dark:via-amber-200/5 transform -translate-x-full hover:translate-x-full transition-transform duration-700 ease-in-out" style="will-change: transform;"></div>
                  <div class="absolute inset-0 bg-gradient-to-r from-transparent via-amber-100/5 to-transparent dark:via-amber-100/3 transform -translate-x-full hover:translate-x-full transition-transform duration-450 ease-in-out" style="animation-delay: 0.06s; will-change: transform;"></div>
                </div>
                <div class="absolute top-0 left-0 w-full h-1 bg-gradient-to-r from-amber-400 to-yellow-400 transform scale-x-0 group-hover:scale-x-100 transition-transform duration-700 ease-out"></div>
                <div class="relative z-10">

                  <div class="mb-2">
                    <h3 class="text-xl font-bold text-white dark:text-white font-serif leading-tight group-hover:text-amber-300 dark:group-hover:text-amber-300 transition-colors duration-500 ease-out line-clamp-2">
                      <router-link
                        :to="`/articles/${article.articleManuscriptId || article.articleId}`"
                        class="hover:underline"
                      >
                        {{ article.articleTitle }}
                      </router-link>
                    </h3>
                  </div>

                  <div class="flex items-center text-slate-300 dark:text-slate-300 text-sm mb-2 flex-wrap gap-1">
                    <svg class="w-5 h-5 mr-3 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                    </svg>
                    <template v-if="getSortedAuthors(article.articleAuthors).length">
                      <template v-for="(author, authorIndex) in getSortedAuthors(article.articleAuthors)" :key="authorIndex">
                        <span class="inline relative">
                          {{ author.authorUser?.userRealName }}
                          <sup
                            v-if="author.authorIsCorresponding === 1"
                            class="text-white"
                            title="通讯作者"
                          >
                            <svg class="w-3 h-3 inline" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"></path>
                            </svg>
                          </sup>
                        </span>
                        <span v-if="authorIndex < getSortedAuthors(article.articleAuthors).length - 1" class="mx-1">,</span>
                      </template>
                    </template>
                    <span v-else>Unknown Authors</span>
                  </div>

                  <div class="flex justify-between items-center mt-3 flex-wrap gap-4">
                    <div class="flex items-center gap-6">
                      <router-link
                        :to="`/articles/${article.articleManuscriptId}`"
                        class="group/link inline-flex items-center text-white dark:text-white font-semibold hover:text-amber-300 dark:hover:text-amber-300 transition-all duration-500 ease-out hover:translate-x-1 text-sm"
                      >
                        Read Full Article
                        <svg class="w-5 h-5 ml-2 transition-transform duration-500 ease-out group-hover/link:translate-x-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                        </svg>
                      </router-link>

<!--                      <button-->
<!--                        @click="copyArticleCitation(article)"-->
<!--                        class="group/cite inline-flex items-center text-slate-400 dark:text-slate-400 hover:text-green-400 dark:hover:text-green-400 transition-all duration-500 ease-out hover:scale-110 text-base relative"-->
<!--                        type="button"-->
<!--                      >-->
<!--                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">-->
<!--                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 16H6a2 2 0 01-2-2V6a2 2 0 012-2h8a2 2 0 012 2v2m-6 12h8a2 2 0 002-2v-8a2 2 0 00-2-2h-8a2 2 0 00-2 2v8a2 2 0 002 2z"></path>-->
<!--                        </svg>-->
<!--                        <span class="ml-1 hidden group-hover/cite:inline transition-all duration-500 ease-out">Cite</span>-->
<!--                      </button>-->
                    </div>

                    <div class="flex items-center text-slate-400 dark:text-slate-400 text-base">
                      <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                      </svg>
                      {{ getPublicationDate(article) }}
                    </div>
                  </div>

                  <div v-if="getKeywords(article.articleKeywords).length" class="mt-4 flex flex-wrap gap-2">
                    <span
                      v-for="(keyword, keywordIndex) in getKeywords(article.articleKeywords)"
                      :key="keywordIndex"
                      class="inline-flex items-center px-3 py-1 text-xs text-slate-200 bg-slate-700/50 rounded-full"
                    >
                      #{{ keyword }}
                    </span>
                  </div>
                </div>
              </article>

              <div v-if="featuredArticles.length === 0" class="text-center py-12 text-slate-300">
                <p class="text-slate-300">暂无最新文章，敬请期待。</p>
              </div>
            </template>
          </div>
          <div class="text-center">
            <button
              class="group relative inline-flex items-center gap-3 px-8 py-4 bg-gradient-to-r from-slate-700 via-slate-800 to-slate-900 text-white rounded-full font-semibold text-lg hover:from-slate-800 hover:via-slate-900 hover:to-black hover:shadow-2xl transition-all duration-700 ease-out overflow-hidden"
              @click="goToArticles"
              type="button"
            >
              <svg class="w-5 h-5 transition-transform duration-500 ease-out group-hover:rotate-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10"></path>
              </svg>
              View All Articles
              <div class="absolute inset-0 bg-gradient-to-r from-white to-white opacity-0 group-hover:opacity-20 transition-opacity duration-500"></div>
              <div class="absolute inset-0 bg-gradient-to-r from-transparent via-white to-transparent opacity-0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"></div>
            </button>
          </div>
        </div>
      </section>

      <!-- Recent Notices Section -->
      <section class="relative z-10 py-20">
        <div class="container mx-auto px-4">
          <div class="text-center mb-16 animate-fade-in">
            <h2 class="text-3xl lg:text-4xl font-bold text-white mb-4 font-serif">Recent Notices</h2>
            <p class="text-lg text-slate-300 max-w-2xl mx-auto">Stay updated with the latest announcements and important information</p>
          </div>
          <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-12">
            <div v-if="isLoadingNotices" class="col-span-full text-center py-12">
              <div class="inline-flex items-center gap-3 text-white">
                <div class="w-6 h-6 border-2 border-cyan-400 border-t-transparent rounded-full animate-spin"></div>
                <span>正在加载最新公告...</span>
              </div>
            </div>
            <template v-else>
              <div
                v-for="(notice, index) in latestNotices"
                :key="notice.noticeId || index"
                class="group bg-gradient-to-br from-slate-800/95 to-slate-700/95 rounded-2xl shadow-academic p-6 hover:shadow-hover transition-all duration-700 ease-out hover:-translate-y-3 hover:scale-105 animate-slide-up border border-slate-600 overflow-hidden relative"
                :style="{ animationDelay: (index * 0.1) + 's' }"
              >
                <div class="absolute inset-0 opacity-0 hover:opacity-100 focus:opacity-0 transition-opacity duration-150 pointer-events-none overflow-hidden">
                  <div class="absolute inset-0 bg-gradient-to-r from-transparent via-amber-200/10 to-transparent dark:via-amber-200/5 transform -translate-x-full hover:translate-x-full transition-transform duration-700 ease-in-out" style="will-change: transform;"></div>
                  <div class="absolute inset-0 bg-gradient-to-r from-transparent via-amber-100/5 to-transparent dark:via-amber-100/3 transform -translate-x-full hover:translate-x-full transition-transform duration-450 ease-in-out" style="animation-delay: 0.06s; will-change: transform;"></div>
                </div>
                <div class="absolute top-0 left-0 w-full h-1 bg-gradient-to-r from-cyan-400 to-blue-400 transform scale-x-0 group-hover:scale-x-100 transition-transform duration-700 ease-out"></div>
                <div class="relative z-10">
                  <div class="flex items-center justify-between mb-4">
                    <span class="bg-gradient-to-r from-cyan-800 to-blue-800 text-cyan-200 px-3 py-1 rounded-full text-xs font-semibold shadow-sm">
                      {{ getNoticeTypeText(notice.noticeType) }}
                    </span>
                    <div class="flex items-center text-slate-400 text-sm">
                      <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                      </svg>
                      {{ formatDate(notice.noticePublishTime || notice.createTime) }}
                    </div>
                  </div>
                  <h3 class="text-xl font-bold text-white mb-3 font-serif leading-tight group-hover:text-cyan-300 transition-colors duration-500 ease-out line-clamp-2">
                    {{ notice.noticeTitle }}
                  </h3>
                  <p class="text-slate-300 leading-relaxed mb-4 group-hover:text-slate-200 transition-colors duration-500 ease-out line-clamp-4">
                    {{ getNoticeSummary(notice) || '敬请期待更多详情。' }}
                  </p>
                </div>
                <router-link
                  :to="getNoticeLink(notice)"
                  class="absolute bottom-4 left-4 group/link inline-flex items-center text-slate-200 font-semibold hover:text-cyan-300 transition-all duration-500 ease-out hover:translate-x-1"
                >
                  Read More
                  <svg class="w-4 h-4 ml-2 transition-transform duration-500 ease-out group-hover/link:translate-x-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                  </svg>
                </router-link>
              </div>

              <div v-if="latestNotices.length === 0" class="col-span-full text-center py-12 text-slate-300">
                暂无公告，敬请关注。
              </div>
            </template>
          </div>
          <div class="text-center">
            <button
                class="group relative inline-flex items-center gap-3 px-8 py-4 bg-gradient-to-r from-cyan-500 via-blue-500 to-indigo-500 text-white rounded-full font-semibold text-lg hover:from-cyan-600 hover:via-blue-600 hover:to-indigo-600 hover:shadow-2xl transition-all duration-700 ease-out overflow-hidden"
                @click="goToNotices"
                type="button"
            >
              <svg class="w-5 h-5 transition-transform duration-500 ease-out group-hover:rotate-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10"></path>
              </svg>
              View All Notices
              <div class="absolute inset-0 bg-gradient-to-r from-cyan-400 to-blue-400 opacity-0 group-hover:opacity-10 transition-opacity duration-500"></div>
              <div class="absolute inset-0 bg-gradient-to-r from-transparent via-white to-transparent opacity-0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"></div>
            </button>
          </div>
        </div>
      </section>

      <!-- CTA Section -->
      <section class="relative z-10 py-20">
        <div class="container mx-auto px-4">
          <div class="text-center max-w-3xl mx-auto animate-fade-in">
            <h2 class="text-3xl lg:text-4xl font-bold text-white mb-6 font-serif">Ready to Share Your Research?</h2>
            <p class="text-lg text-slate-300 leading-relaxed mb-8">Join thousands of researchers publishing with us. Fast review process, global reach, and open access options available.</p>
            <button @click="goToSubmit" class="group relative inline-flex items-center gap-3 px-8 py-4 bg-gradient-to-r from-slate-700 via-slate-800 to-slate-900 text-white rounded-full font-semibold text-lg hover:from-slate-800 hover:via-slate-900 hover:to-black hover:shadow-2xl transition-all duration-700 ease-out overflow-hidden shadow-elegant">
              <svg class="w-5 h-5 transition-transform duration-500 ease-out group-hover:rotate-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path>
              </svg>
              Submit Your Manuscript
              <div class="absolute inset-0 bg-gradient-to-r from-white to-white opacity-0 group-hover:opacity-20 transition-opacity duration-500"></div>
              <div class="absolute inset-0 bg-gradient-to-r from-transparent via-white to-transparent opacity-0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"></div>
            </button>
          </div>
        </div>
      </section>
      </div>

      <!-- Sticky Banner -->
      <div v-if="showStickyBanner" class="fixed right-6 top-1/2 transform -translate-y-1/2 z-50 hidden lg:block">
        <div class="bg-gradient-to-br from-slate-800 to-slate-900 text-white p-6 rounded-2xl shadow-2xl max-w-xs animate-slide-in-right">
          <div class="flex items-center justify-between mb-4">
            <div class="flex items-center gap-3">
              <div class="w-3 h-3 bg-green-400 rounded-full animate-pulse"></div>
              <span class="text-sm font-semibold">Live Updates</span>
            </div>
            <button @click="showStickyBanner = false" class="text-slate-400 hover:text-white transition-colors duration-200">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
              </svg>
            </button>
          </div>
          <p class="text-sm text-slate-300 mb-4 text-center leading-relaxed">Get the latest research insights delivered to your inbox</p>
          <button @click="goToSubmit" class="group relative w-full bg-gradient-to-r from-cyan-500 to-blue-500 text-white text-sm font-semibold py-3 px-4 rounded-lg hover:from-cyan-600 hover:to-blue-600 transition-all duration-500 ease-out overflow-hidden">
            Subscribe Now
            <div class="absolute inset-0 bg-gradient-to-r from-white to-white opacity-0 group-hover:opacity-20 transition-opacity duration-300"></div>
            <div class="absolute inset-0 bg-gradient-to-r from-transparent via-white to-transparent opacity-0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"></div>
          </button>
        </div>
      </div>
    </div>
  </PageScaffold>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import PageScaffold from '@/components/layout/PageScaffold.vue'
import {
  splitKeywords,
  normalizeAuthors,
  formatAuthorNames,
  formatDate as formatArticleDate
} from '@/utils/article'

const router = useRouter()
const store = useStore()

const ROUTES = Object.freeze({
  home: '/',
  articles: '/articles',
  submit: '/submit',
  notices: '/notices'
})

// 从 Vuex 获取系统信息
const branding = computed(() => store.getters['sysInfo/brandingInfo'])
const heroTitle = computed(() => branding.value.name)
const heroSubtitle = computed(() => branding.value.tagline)
const heroLogo = computed(() => branding.value.logo)
const citationJournal = computed(() => branding.value.citationName)

const featuredArticles = computed(() => store.getters['content/featuredArticles'] || [])
const latestNotices = computed(() => store.getters['content/latestNotices'] || [])
const isLoadingFeatured = computed(() => store.getters['content/isLoadingFeatured'])
const isLoadingNotices = computed(() => store.getters['content/isLoadingNotices'])
const articleStatusMap = computed(() => store.getters['referenceData/articleStatusMap'] || {})
const showStickyBanner = ref(true)

const loadData = async () => {
  try {
    await Promise.all([
      store.dispatch('content/fetchFeaturedArticles'),
      store.dispatch('content/fetchLatestNotices'),
      store.dispatch('referenceData/ensureArticleStatus')
    ])
  } catch (error) {
    console.error('Failed to load home data:', error)
  }
}

const formatDate = (dateString) => {
  return formatArticleDate(dateString, 'en-US', {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  })
}

const getStatusText = (status) => {
  return articleStatusMap.value?.[status] || status
}

const getKeywords = (keywords) => {
  return splitKeywords(keywords)
}

const getSortedAuthors = (authors) => normalizeAuthors(authors)

const getAuthorNames = (authors) => formatAuthorNames(authors)

const getPublicationDate = (article) => {
  return formatArticleDate(
    article?.articlePublishTime || article?.articleOnlineTime || article?.articleSubmitTime,
    'en-US',
    {
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    }
  )
}

const goToArticles = () => {
  router.push(ROUTES.articles)
}

const goToNotices = () => {
  router.push(ROUTES.notices)
}

const copyArticleCitation = async (article) => {
  const year = article?.articleSubmitTime ? new Date(article.articleSubmitTime).getFullYear() : new Date().getFullYear()
  const citation = `${getAuthorNames(article?.articleAuthors)}. "${article?.articleTitle}". ${citationJournal.value} (${year}).`
  try {
    await navigator.clipboard.writeText(citation)
  } catch (error) {
    console.error('Failed to copy citation:', error)
  }
}

const stripHtml = (value) => {
  if (!value || typeof value !== 'string') {
    return ''
  }
  return value.replace(/<[^>]+>/g, '')
}

const getNoticeSummary = (notice) => {
  if (!notice) {
    return ''
  }
  if (notice.noticeSummary) {
    return notice.noticeSummary
  }
  if (notice.noticeContent) {
    const text = stripHtml(notice.noticeContent)
    return text.length > 120 ? `${text.slice(0, 117)}...` : text
  }
  return ''
}

const getNoticeLink = (notice) => {
  if (notice?.noticeId) {
    return {
      path: ROUTES.notices,
      query: { highlight: notice.noticeId }
    }
  }
  return { path: ROUTES.notices }
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

const goToSubmit = () => {
  const token = localStorage.getItem('token')
  if (token) {
    router.push(ROUTES.submit)
  } else {
    router.push({ path: '/login', query: { redirect: ROUTES.submit } })
  }
}

// 平滑滚动到下一栏
const scrollToNextSection = () => {
  const nextSection = document.querySelector('.recent-publications-section')
  
  if (nextSection) {
    // 计算滚动位置，让 Recent Publications 刚好占满一页
    const sectionTop = nextSection.offsetTop
    const viewportHeight = window.innerHeight
    const sectionHeight = nextSection.offsetHeight
    
    // 如果部分高度小于视口高度，则滚动到顶部
    // 如果部分高度大于视口高度，则滚动到合适位置让标题可见
    const scrollTo = sectionHeight < viewportHeight ? sectionTop : sectionTop - 80
    
    window.scrollTo({
      top: scrollTo,
      behavior: 'smooth'
    })
  }
}

// 键盘事件处理
const handleKeyDown = (event) => {
  // 空格键或下箭头键滚动到下一栏
  if (event.code === 'Space' || event.code === 'ArrowDown') {
    event.preventDefault()
    scrollToNextSection()
  }
}

// 滚轮事件处理
let isScrolling = false
let lastScrollTime = 0
const scrollThrottle = 1000 // 1秒内只允许一次滚动跳转

const handleWheel = (event) => {
  if (isScrolling) return
  
  const currentTime = Date.now()
  if (currentTime - lastScrollTime < scrollThrottle) return
  
  const scrollTop = window.pageYOffset || document.documentElement.scrollTop
  const isAtTop = scrollTop < 50
  const isAtRecentPublications = scrollTop > 50 && scrollTop < 1500
  
  if (isAtTop && event.deltaY > 0) {
    // 在页面顶部向下滚动时跳转到下一栏
    event.preventDefault()
    event.stopPropagation()
    isScrolling = true
    lastScrollTime = currentTime
    scrollToNextSection()
    
    // 防止连续触发
    setTimeout(() => {
      isScrolling = false
    }, 20)
  } else if (isAtRecentPublications && event.deltaY < 0) {
    // 在 Recent Publications 部分向上滚动时回到简介页面
    event.preventDefault()
    event.stopPropagation()
    isScrolling = true
    lastScrollTime = currentTime
    scrollToHeroSection()
    
    // 防止连续触发
    setTimeout(() => {
      isScrolling = false
    }, 20)
  }
}

// 滚动到简介页面
const scrollToHeroSection = () => {
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  })
}

onMounted(() => {
  loadData()
  window.addEventListener('keydown', handleKeyDown)
  window.addEventListener('wheel', handleWheel, { passive: false })
})

onUnmounted(() => {
  window.removeEventListener('keydown', handleKeyDown)
  window.removeEventListener('wheel', handleWheel)
})
</script>
