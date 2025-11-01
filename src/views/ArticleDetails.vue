<template>
  <div class="article-details min-h-screen bg-white dark:bg-slate-900 py-8">
    <div class="container mx-auto px-4">
      <!-- 面包屑导航 -->
      <nav class="mb-6 text-sm" aria-label="breadcrumb">
        <ol class="flex items-center space-x-2">
          <li>
            <router-link 
              :to="ROUTES.home" 
              class="text-blue-600 dark:text-blue-400 hover:underline"
            >
              home
            </router-link>
          </li>
          <li class="text-slate-400 dark:text-slate-500">&gt;</li>
          <li>
        <router-link 
          :to="ROUTES.articles" 
              class="text-blue-600 dark:text-blue-400 hover:underline"
        >
              articles
        </router-link>
          </li>
          <li class="text-slate-400 dark:text-slate-500">&gt;</li>
          <li class="text-slate-600 dark:text-slate-400">article</li>
        </ol>
      </nav>

      <div class="flex gap-8">
        <!-- 左侧主内容区 -->
        <div class="flex-1 max-w-4xl">
      <!-- 加载状态 -->
      <div v-if="loading" class="flex justify-center items-center h-64">
        <div class="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-blue-500"></div>
      </div>

      <!-- 文章内容 -->
          <article v-else-if="article">
        <!-- 文章头部 -->
        <header class="mb-8">
          <!-- 文章类型和发布日期 -->
          <div class="mb-4 text-sm text-slate-600 dark:text-slate-400">
            <span>{{ getArticleType() }}</span>
            <span class="mx-2">|</span>
            <span>Published: {{ formatDate(article.articlePublishTime || article.articleSubmitTime) }}</span>
            <span v-if="isOpenAccess" class="ml-2 px-2 py-0.5 bg-green-100 dark:bg-green-900/30 text-green-700 dark:text-green-400 rounded text-xs font-medium">
              Open Access
            </span>
          </div>
          
          <!-- 文章标题 -->
          <h1 class="text-4xl md:text-5xl font-bold text-slate-900 dark:text-white mb-6 leading-tight">
            {{ article.articleTitle }}
          </h1>
          
          <!-- 作者列表 -->
          <div class="mb-4 text-base">
            <template v-for="(author, index) in sortedAuthors" :key="author.authorId">
              <span 
                @click="openAuthorModal($event, author)"
                class="text-blue-600 dark:text-blue-400 hover:underline cursor-pointer inline relative"
              >
                {{ author.authorName }}
                <sup v-if="author.authorIsCorresponding === 1"
                     class="text-blue"
                     title="通讯作者">
                      <svg class="w-3 h-3 inline"
                           fill="none"
                           stroke="currentColor"
                           viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"></path>
                      </svg>
                    </sup>
              </span>
              <span v-if="index < sortedAuthors.length - 1">
                {{ index === sortedAuthors.length - 2 ? ' & ' : ', ' }}
              </span>
            </template>
          </div>
          
          <!-- 期刊和引用信息 -->
          <div class="mb-6 text-base text-slate-600 dark:text-slate-400">
            <a 
              href="#" 
              class="text-blue-600 dark:text-blue-400 hover:underline"
            >
              {{ citationJournal }} ({{ new Date(article.articlePublishTime || article.articleSubmitTime).getFullYear() }})
            </a>
            <span class="mx-2">|</span>
            <a 
              href="#"
              @click="citeArticle"
              class="text-blue-600 dark:text-blue-400 hover:underline cursor-pointer"
            >
              Cite this article
            </a>
          </div>

          <!-- 未编辑版本提示 -->
          <div v-if="!article.articlePublishTime" class="mb-6 border border-yellow-400 bg-yellow-50 dark:bg-yellow-900/20 rounded p-4">
            <div class="flex items-start gap-3">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-yellow-600 dark:text-yellow-400 flex-shrink-0 mt-0.5" viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clip-rule="evenodd" />
              </svg>
              <p class="text-sm text-slate-700 dark:text-slate-300">
                We are providing an unedited version of this manuscript to give early access to its findings. Before final publication, the manuscript will undergo further editing. Please note there may be errors present which affect the content, and all legal disclaimers apply.
              </p>
            </div>
          </div>
        </header>

        <!-- 文章主体 -->
        <div>
          <!-- 摘要 -->
          <section id="abstract" class="abstract-section mb-12">
            <h2 class="text-2xl font-bold text-slate-900 dark:text-white mb-3">Abstract</h2>
            <div class="text-base text-slate-700 dark:text-slate-300 leading-relaxed">
              {{ article.articleAbstract }}
            </div>
          </section>

          <!-- Author information -->
          <section id="author-information" class="mb-12">
            <h2 class="text-2xl font-bold text-slate-900 dark:text-white mb-6">Author information</h2>
            
            <!-- Authors and Affiliations -->
            <div class="mb-6">
              <h3 class="text-xl font-semibold text-slate-900 dark:text-white mb-1">Authors and Affiliations</h3>
              <div class="space-y-2 text-sm">
                <!-- 按顺序显示作者及其机构 -->
                <div v-for="author in sortedAuthors" :key="author.authorId" class="text-slate-900 dark:text-slate-100">
                  <span class="font-semibold text-base inline relative">
                    {{ author.authorName }}
                    :
                  </span>
                  <span class="text-slate-700 dark:text-slate-300 ml-1">{{ author.authorDepartment }}, </span>
                  <span class="text-slate-700 dark:text-slate-300 ml-1">{{ author.authorAffiliation }} in </span>
                  <span class="text-slate-700 dark:text-slate-300 ml-1" v-if="author.authorCity !== '<NA>'">{{ author.authorCity }}, </span>
                  <span class="text-slate-700 dark:text-slate-300 ml-1" v-if="author.authorState !== '<NA>'">{{ author.authorState }}, </span>
                  <span class="text-slate-700 dark:text-slate-300 ml-1">{{ author.authorCountry }}. </span>
                </div>
              </div>
            </div>

            <!-- Corresponding authors -->
            <div v-if="correspondingAuthors.length > 0">
              <h3 class="text-xl font-semibold text-slate-900 dark:text-white mb-1">Corresponding authors</h3>
              <p class="text-sm text-slate-700 dark:text-slate-300">
                <span>Correspondence to </span>
                <template v-for="(author, index) in correspondingAuthors" :key="author.authorId">
                  <a 
                    :href="author.authorEmail ? `mailto:${author.authorEmail}` : '#'"
                    class="text-blue-600 dark:text-blue-400 hover:underline"
                  >
                    {{ author.authorName }}
                  </a>
                  <span v-if="index < correspondingAuthors.length - 1">, </span>
                  <span v-if="index === correspondingAuthors.length - 2"> or </span>
                </template>
                <span>.</span>
              </p>
            </div>
          </section>

          <!-- Supplementary information -->
          <section id="supplementary-information" class="mb-12">
            <h2 class="text-2xl font-bold text-slate-900 dark:text-white mb-6">Supplementary information</h2>
            
            <div class="space-y-4">
              <!-- Supplementary Information -->
              <div>
                <a 
                  href="#"
                  class="text-blue-600 dark:text-blue-400 hover:underline text-base inline-flex items-center gap-1 mb-2"
                >
                  <span>Supplementary Information</span>
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M11 3a1 1 0 100 2h2.586l-6.293 6.293a1 1 0 101.414 1.414L15 6.414V9a1 1 0 102 0V4a1 1 0 00-1-1h-5z" />
                    <path d="M5 5a2 2 0 00-2 2v8a2 2 0 002 2h8a2 2 0 002-2v-3a1 1 0 10-2 0v3H5V7h3a1 1 0 000-2H5z" />
                  </svg>
                </a>
                <p class="text-sm text-slate-600 dark:text-slate-400">
                  {{ article.articleSupplementary }}
                </p>
              </div>

              <!-- Reporting Summary -->
              <div>
                <a 
                  href="#"
                  class="text-blue-600 dark:text-blue-400 hover:underline text-base inline-flex items-center gap-1"
                >
                  <span>Reporting Summary</span>
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M11 3a1 1 0 100 2h2.586l-6.293 6.293a1 1 0 101.414 1.414L15 6.414V9a1 1 0 102 0V4a1 1 0 00-1-1h-5z" />
                    <path d="M5 5a2 2 0 00-2 2v8a2 2 0 002 2h8a2 2 0 002-2v-3a1 1 0 10-2 0v3H5V7h3a1 1 0 000-2H5z" />
                  </svg>
                </a>
              </div>
            </div>
          </section>

          <!-- About this article -->
          <section id="about-this-article" class="mb-12">
            <h2 class="text-2xl font-bold text-slate-900 dark:text-white mb-6">About this article</h2>
            
            <!-- Cite this article -->
            <div class="mb-6">
              <h3 class="text-xl font-bold text-slate-900 dark:text-white mb-4">Cite this article</h3>
              
              <!-- Nature style citation -->
              <div class="text-base text-slate-900 dark:text-slate-100 mb-3 leading-relaxed">
                {{ formatNatureCitation() }}
              </div>
              
              <!-- Copy and Download citation links -->
              <div class="flex items-center gap-4">
                <button
                  @click="copyNatureCitation()"
                  class="text-blue-600 dark:text-blue-400 hover:underline text-sm inline-flex items-center gap-2"
                >
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M8 3a1 1 0 011-1h2a1 1 0 110 2H9a1 1 0 01-1-1z" />
                    <path d="M6 3a2 2 0 00-2 2v11a2 2 0 002 2h8a2 2 0 002-2V5a2 2 0 00-2-2 3 3 0 01-3 3H9a3 3 0 01-3-3z" />
                  </svg>
                  <span>Copy</span>
                </button>
                <button
                  @click="downloadNatureCitation()"
                  class="text-blue-600 dark:text-blue-400 hover:underline text-sm inline-flex items-center gap-2"
                >
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                    <path fill-rule="evenodd" d="M3 17a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zm3.293-7.707a1 1 0 011.414 0L9 10.586V3a1 1 0 112 0v7.586l1.293-1.293a1 1 0 111.414 1.414l-3 3a1 1 0 01-1.414 0l-3-3a1 1 0 010-1.414z" clip-rule="evenodd" />
                  </svg>
                  <span>Download citation</span>
                </button>
              </div>
            </div>

            <!-- Dates -->
            <div class="mb-6 grid grid-cols-3 gap-4 text-sm">
              <div v-if="article.articleSubmitTime">
                <span class="block font-medium text-slate-600 dark:text-slate-400 mb-1">Received</span>
                <span class="block text-slate-900 dark:text-slate-100">{{ formatShortDate(article.articleSubmitTime) }}</span>
              </div>
              <div v-if="article.articleAcceptTime">
                <span class="block font-medium text-slate-600 dark:text-slate-400 mb-1">Accepted</span>
                <span class="block text-slate-900 dark:text-slate-100">{{ formatShortDate(article.articleAcceptTime) }}</span>
              </div>
              <div v-if="article.articlePublishTime">
                <span class="block font-medium text-slate-600 dark:text-slate-400 mb-1">Published</span>
                <span class="block text-slate-900 dark:text-slate-100">{{ formatShortDate(article.articlePublishTime) }}</span>
              </div>
            </div>

            <!-- DOI -->
            <div v-if="article.articleDoi" class="mb-6">
              <h4 class="font-medium text-slate-900 dark:text-white mb-2">DOI</h4>
              <a 
                :href="`https://doi.org/${article.articleDoi}`" 
                target="_blank"
                class="text-blue-600 dark:text-blue-400 hover:underline text-sm"
              >
                https://doi.org/{{ article.articleDoi }}
              </a>
            </div>

            <!-- Subjects -->
            <div v-if="subjectArea">
              <h4 class="font-medium text-slate-900 dark:text-white mb-2">Subjects</h4>
              <div class="flex flex-wrap gap-2">
                <span
                  class="inline-block px-3 py-1 bg-blue-100 dark:bg-blue-900/30 text-blue-700 dark:text-blue-300 text-sm rounded"
                >
                  {{ subjectArea }}
                </span>
              </div>
            </div>
          </section>
        </div>

        <!-- 文章底部 -->
        <footer class="mt-12 pt-8 border-t border-slate-200 dark:border-slate-700">
          <div class="flex flex-col md:flex-row justify-between items-start md:items-center gap-4 mb-8">
            <div class="flex flex-wrap gap-3">
              <button 
                @click="downloadArticle"
                class="inline-flex items-center gap-2 px-4 py-2 text-sm text-slate-700 dark:text-slate-300 hover:bg-slate-100 dark:hover:bg-slate-800 rounded transition-colors"
              >
                <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                  <path fill-rule="evenodd" d="M3 17a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zm3.293-7.707a1 1 0 011.414 0L9 10.586V3a1 1 0 112 0v7.586l1.293-1.293a1 1 0 111.414 1.414l-3 3a1 1 0 01-1.414 0l-3-3a1 1 0 010-1.414z" clip-rule="evenodd" />
                </svg>
                <span>Download PDF</span>
              </button>
              
              <button 
                @click="shareArticle"
                class="inline-flex items-center gap-2 px-4 py-2 text-sm text-slate-700 dark:text-slate-300 hover:bg-slate-100 dark:hover:bg-slate-800 rounded transition-colors"
              >
                <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                  <path d="M15 8a3 3 0 10-2.977-2.63l-4.94 2.47a3 3 0 100 4.319l4.94 2.47a3 3 0 10.895-1.789l-4.94-2.47a3.027 3.027 0 000-.74l4.94-2.47C13.456 7.68 14.19 8 15 8z" />
                </svg>
                <span>Share</span>
              </button>
            </div>
            
            <div class="text-sm text-slate-600 dark:text-slate-400">
              <span>Published in </span>
              <a href="#" class="text-blue-600 dark:text-blue-400 hover:underline">{{ branding.name }}</a>
            </div>
          </div>
        </footer>
      </article>

      <!-- 错误状态 -->
          <div v-else-if="error" class="text-center py-12">
        <div class="text-red-500 mb-4">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16 mx-auto" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
          </svg>
        </div>
        <h3 class="text-xl font-bold text-slate-900 dark:text-white mb-2">加载失败</h3>
        <p class="text-slate-600 dark:text-slate-400 mb-6">{{ error }}</p>
        <router-link 
          to="/articles" 
              class="inline-flex items-center gap-2 px-6 py-3 bg-blue-600 text-white rounded font-medium hover:bg-blue-700 transition-colors"
        >
          返回文章列表
        </router-link>
      </div>
    </div>

        <!-- 右侧固定导航栏 -->
        <div v-if="!loading && !error" class="hidden xl:block flex-shrink-0 w-64">
          <div class="sticky top-40 h-fit z-10">
          <div class="bg-white dark:bg-slate-800 rounded-lg shadow-sm border border-slate-200 dark:border-slate-700 p-4">
            <h3 class="text-sm font-bold text-slate-900 dark:text-white mb-3">Sections</h3>
            <nav class="space-y-2">
              <a 
                href="#abstract"
                @click.prevent="scrollToSection('abstract')"
                class="block text-sm text-blue-600 dark:text-blue-400 hover:underline py-1 whitespace-nowrap"
              >
                Abstract
              </a>
              <a 
                href="#author-information"
                @click.prevent="scrollToSection('author-information')"
                class="block text-sm text-blue-600 dark:text-blue-400 hover:underline py-1 whitespace-nowrap"
              >
                Author information
              </a>
              <a 
                href="#supplementary-information"
                @click.prevent="scrollToSection('supplementary-information')"
                class="block text-sm text-blue-600 dark:text-blue-400 hover:underline py-1 whitespace-nowrap"
              >
                Supplementary information
              </a>
              <a 
                href="#about-this-article"
                @click.prevent="scrollToSection('about-this-article')"
                class="block text-sm text-blue-600 dark:text-blue-400 hover:underline py-1 whitespace-nowrap"
              >
                About this article
              </a>
            </nav>
          </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 作者详情弹窗（气泡式） -->
    <Teleport to="body">
      <div
        v-if="showAuthorModal && selectedAuthor"
        class="fixed inset-0 z-50"
        @click="closeAuthorModal"
        @scroll="closeAuthorModal"
      >
        <!-- 弹窗内容（气泡样式） -->
        <div
          class="author-modal absolute bg-white dark:bg-slate-800 rounded-lg shadow-2xl w-96 p-4"
          :style="{ top: authorModalPosition.top + 'px', left: authorModalPosition.left + 'px' }"
          @click.stop
        >
          <!-- 气泡箭头 -->
          <div class="absolute -top-2 left-6 w-4 h-4 bg-white dark:bg-slate-800 transform rotate-45 border-l border-t border-slate-200 dark:border-slate-700"></div>
          <!-- 关闭按钮 -->
          <button
            @click="closeAuthorModal"
            class="absolute top-4 right-4 text-slate-400 hover:text-slate-600 dark:hover:text-slate-300 transition-colors"
          >
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd" />
            </svg>
          </button>

          <!-- 通讯作者标题 -->
          <div v-if="selectedAuthor.authorIsCorresponding" class="mb-4">
            <h3 class="text-xs font-semibold text-slate-500 dark:text-slate-400 uppercase tracking-wide mb-2">
              Corresponding author
            </h3>
            <h4 class="text-xl font-bold text-slate-900 dark:text-white">
              {{ selectedAuthor.authorName }}
            </h4>
          </div>
          <!-- 普通作者标题 -->
          <div v-else class="mb-4">
            <h4 class="text-xl font-bold text-slate-900 dark:text-white">
              {{ selectedAuthor.authorName }}
            </h4>
          </div>

          <!-- 贡献声明 -->
          <div v-if="selectedAuthor.authorContribution" class="mb-4">
            <p class="text-sm text-slate-700 dark:text-slate-300">
              <span v-if="equalContributors.length > 1">
                These authors contributed equally: 
                <span v-for="(contributor, index) in equalContributors" :key="contributor.authorId">
                  {{ contributor.authorName }}<span v-if="index < equalContributors.length - 1">, </span>
                </span>
              </span>
              <span v-else>
                {{ selectedAuthor.authorContribution }}
              </span>
            </p>
          </div>

          <!-- 单位信息 -->
          <div v-if="selectedAuthor.authorAffiliation" class="mb-4">
            <p class="text-sm text-slate-700 dark:text-slate-300">
              {{ selectedAuthor.authorAffiliation }}
            </p>
          </div>

          <!-- 分隔线 -->
          <hr class="my-4 border-slate-200 dark:border-slate-700" />

          <!-- 通讯作者联系信息 -->
          <div v-if="selectedAuthor.authorIsCorresponding" class="mb-4">
            <div class="flex items-center gap-2 mb-3">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 text-slate-600 dark:text-slate-400" viewBox="0 0 20 20" fill="currentColor">
                <path d="M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z" />
                <path d="M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z" />
              </svg>
              <a 
                v-if="selectedAuthor.authorEmail"
                :href="`mailto:${selectedAuthor.authorEmail}`"
                class="text-sm text-blue-600 dark:text-blue-400 hover:underline"
              >
                Contact {{ selectedAuthor.authorName }}
              </a>
              <span v-else class="text-sm text-slate-600 dark:text-slate-400">
                Contact {{ selectedAuthor.authorName }}
              </span>
            </div>
          </div>

          <!-- 搜索作者文章按钮 -->
          <button
            @click="searchAuthorPublications"
            class="w-full flex items-center justify-center gap-2 px-4 py-2 border-2 border-blue-600 dark:border-blue-400 text-blue-600 dark:text-blue-400 rounded hover:bg-blue-50 dark:hover:bg-blue-900/20 transition-colors"
          >
            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z" clip-rule="evenodd" />
            </svg>
            <span>Search all author publications</span>
          </button>
        </div>
      </div>
    </Teleport>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { articleApi } from '@/api'
import { useStore } from 'vuex'

const route = useRoute()
const router = useRouter()
const store = useStore()
const ROUTES = Object.freeze({
  home: '/',
  articles: '/articles'
})
const article = ref(null)
const loading = ref(true)
const error = ref(null)
const isBookmarked = ref(false)
const isLiked = ref(false)

// 作者信息
const authors = ref([])
const branding = computed(() => store.getters['sysInfo/brandingInfo'])
const citationJournal = computed(() => branding.value.citationName)
// 作者详情弹窗
const showAuthorModal = ref(false)
const selectedAuthor = ref(null)
const authorModalPosition = ref({ top: 0, left: 0 })

// 引用格式
const citationFormats = ref([])

// 图表数据
const figures = ref([])


// 关键词
const keywords = ref([])

// 枚举数据
const articleStatusOptions = computed(() => store.getters['referenceData/articleStatusOptions'] || [])
const manuscriptTypeOptions = computed(() => store.getters['referenceData/manuscriptTypeOptions'] || [])
const subjectArea = ref('')

// 计算属性：文章类别标签
const categoryTags = computed(() => {
  if (!article.value || !article.value.articleSubjectArea) return []
  return [article.value.articleSubjectArea]
})

// 计算属性：文章是否开放获取
const isOpenAccess = computed(() => {
  // 可以从文章数据中读取，或者设置为true
  return article.value?.articleIsOpenAccess ?? true
})

// 计算属性：排序后的作者信息
const sortedAuthors = computed(() => {
  if (!authors.value || authors.value.length === 0) return []

  // 按作者顺序排序
  return [...authors.value].sort((a, b) => (a.authorOrder || 0) - (b.authorOrder || 0))
})

// 计算属性：对应作者列表
const correspondingAuthors = computed(() => {
  return sortedAuthors.value.filter(author => author.authorIsCorresponding)
})

// 计算属性：是否有作者贡献信息
const hasAuthorContributions = computed(() => {
  return sortedAuthors.value.some(author => author.authorContribution && author.authorContribution.trim())
})

// 计算属性：具有相同贡献的作者
const equalContributors = computed(() => {
  if (!selectedAuthor.value) return []
  return sortedAuthors.value.filter(a => a.authorContribution === selectedAuthor.value.authorContribution && a.authorContribution)
})

// 打开作者详情弹窗（气泡式）
const openAuthorModal = (event, author) => {
  selectedAuthor.value = author
  showAuthorModal.value = true
  
  // 计算弹窗位置（气泡出现在点击的元素下方）
  const rect = event.target.getBoundingClientRect()
  const scrollTop = window.pageYOffset || document.documentElement.scrollTop
  authorModalPosition.value = {
    top: rect.bottom + scrollTop + 8,
    left: rect.left
  }
  
  // 确保弹窗不超出屏幕右侧
  setTimeout(() => {
    const modal = document.querySelector('.author-modal')
    if (modal) {
      const modalRect = modal.getBoundingClientRect()
      if (modalRect.right > window.innerWidth - 20) {
        authorModalPosition.value.left = window.innerWidth - modalRect.width - 20
      }
    }
  }, 0)
}

// 关闭作者详情弹窗
const closeAuthorModal = () => {
  showAuthorModal.value = false
  selectedAuthor.value = null
}

// 搜索作者的所有文章
const searchAuthorPublications = () => {
  if (!selectedAuthor.value) return
  const authorName = selectedAuthor.value.authorName
  // 这里可以跳转到搜索页面
  router.push({ 
    path: '/articles', 
    query: { search: authorName, author: authorName } 
  })
  closeAuthorModal()
}

const fetchArticle = async () => {
  try {
    loading.value = true
    error.value = null
    
    try {
      await Promise.all([
        store.dispatch('referenceData/ensureArticleStatus'),
        store.dispatch('referenceData/ensureManuscriptType')
      ])
    } catch (err) {
      console.warn('Failed to load reference data:', err)
    }
    
    // 获取文章数据（通过manuscriptId）
    const manuscriptId = route.params.manuscriptId
    const articleResponse = await articleApi.getArticleByManuscriptId(manuscriptId)
    article.value = articleResponse.data
    // 获取作者信息
    try {
      if (article.value.articleAuthors && article.value.articleAuthors.length > 0) {
        authors.value = article.value.articleAuthors.map((author, index) => ({
          authorId: author.authorId || index,
          authorName: author.authorUser.userName || '',
          authorAffiliation: author.authorUser.userAffiliation || '',
          authorDepartment: author.authorUser.userDepartment || '',
          authorCity: author.authorUser.userCity || '<NA>',
          authorCountry: author.authorUser.userCountry || '<NA>',
          authorState: author.authorUser.userState || '<NA>',
          authorAddress: author.authorUser.userAddress || '<NA>',
          authorEmail: author.authorUser.userEmail || '',
          authorIsCorresponding: author.authorIsCorresponding || false,
          authorOrder: author.authorOrder || index + 1,
          authorContribution: author.authorContribution || ''
        }))
      }
    } catch (error) {
      console.warn('Failed to load authors:', error)
      authors.value = []
    }

    // 处理关键词
    if (article.value.articleKeywords) {
      keywords.value = article.value.articleKeywords.split(',').map(k => k.trim()).filter(k => k)
    }

    // 获取学科领域
    if (article.value.articleSubjectAreaId) {
      try {
        // TODO: 这里应该从接口获取学科领域名称
        // 目前暂时使用articleSubjectArea字段
        subjectArea.value = article.value.articleSubjectArea || ''
      } catch (err) {
        console.warn('Failed to load subject area:', err)
      }
    }

    // 默认图表数据（暂时没有文件表关联）
    figures.value = [
      { id: 1, title: 'Figure 1', caption: 'Experimental design', description: 'Overview of the experimental setup and methodology used in this study.' },
      { id: 2, title: 'Figure 2', caption: 'Data visualization', description: 'Key findings visualized through comprehensive data analysis.' }
    ]

    // 生成引用格式
    citationFormats.value = generateCitations()
  } catch (err) {
    console.error('Failed to fetch article:', err)
    error.value = 'Failed to load article. Please try again later.'
  } finally {
    loading.value = false
  }
}

const goBack = () => {
  router.back()
}

const downloadArticle = () => {
  alert('Downloading article as PDF...')
}

// 生成引用格式
const generateCitations = () => {
  if (!article.value) return []
  
  const formats = []
  
    // 构建作者字符串
    let authorsStr = ''
    if (authors.value.length > 0) {
      if (authors.value.length === 1) {
      authorsStr = authors.value[0].authorName
      } else if (authors.value.length === 2) {
      authorsStr = `${authors.value[0].authorName} & ${authors.value[1].authorName}`
      } else {
      authorsStr = `${authors.value[0].authorName} et al.`
    }
  }
  
  const year = new Date(article.value.articlePublishTime || article.value.articleSubmitTime).getFullYear()
  const journal = citationJournal.value
  const volume = article.value.articleVolume || 'N/A'
  const pages = article.value.articlePages || 'N/A'
  const doi = article.value.articleDoi || 'N/A'
  
  // APA格式
  formats.push({
    label: 'APA',
    content: `${authorsStr} (${year}). ${article.value.articleTitle}. ${journal}, ${volume}(${pages}). https://doi.org/${doi}`
  })
  
  // BibTeX格式
  const bibtexKey = authors.value.length > 0 ? authors.value[0].authorName.split(' ').pop().toLowerCase() : 'author'
  formats.push({
    label: 'BibTeX',
    content: `@article{${bibtexKey}${year},
  title={${article.value.articleTitle}},
  author={${authorsStr}},
  journal={${journal}},
  volume={${volume}},
  pages={${pages}},
  year={${year}},
  doi={${doi}}
}`
  })
  
  return formats
}

const citeArticle = (event) => {
  // 防止默认的锚点跳转
  event.preventDefault()
  
  // 滚动到 About this article 部分
  const element = document.getElementById('about-this-article')
  if (element) {
    const yOffset = -150 // 偏移量，避免被顶部导航栏遮挡
    const y = element.getBoundingClientRect().top + window.scrollY + yOffset
    window.scrollTo({ top: y, behavior: 'smooth' })
  }
}

// 滚动到指定栏目
const scrollToSection = (sectionId) => {
  const element = document.getElementById(sectionId)
  if (element) {
    const yOffset = -150 // 偏移量，避免被顶部导航栏遮挡
    const y = element.getBoundingClientRect().top + window.scrollY + yOffset
    window.scrollTo({ top: y, behavior: 'smooth' })
  }
}

// 复制引用
const copyCitation = (citation) => {
  navigator.clipboard.writeText(citation).then(() => {
  alert('Citation copied to clipboard!')
  })
}

// 下载引用
const downloadCitation = (citation, format) => {
  const blob = new Blob([citation], { type: 'text/plain' })
  const url = URL.createObjectURL(blob)
  const a = document.createElement('a')
  a.href = url
  a.download = `citation.${format.toLowerCase()}.txt`
  document.body.appendChild(a)
  a.click()
  document.body.removeChild(a)
  URL.revokeObjectURL(url)
}

const toggleBookmark = () => {
  isBookmarked.value = !isBookmarked.value
  alert(isBookmarked.value ? 'Article bookmarked!' : 'Bookmark removed!')
}

const shareArticle = () => {
  if (navigator.share) {
    navigator.share({
      title: article.value?.articleTitle,
      text: article.value?.articleAbstract?.substring(0, 100) + '...',
      url: window.location.href
    })
  } else {
    navigator.clipboard.writeText(window.location.href)
    alert('Link copied to clipboard!')
  }
}

const formatDate = (dateString) => {
  const options = { year: 'numeric', month: 'long', day: 'numeric' }
  return new Date(dateString).toLocaleDateString('en-US', options)
}

const formatShortDate = (dateString) => {
  const options = { year: 'numeric', month: 'long', day: 'numeric' }
  return new Date(dateString).toLocaleDateString('en-US', options)
}

// Nature style citation: LastName, F. M., LastName2, F. M. et al. Title. Journal (Year).
const formatNatureCitation = () => {
  if (!article.value) return ''
  
  // 构建作者字符串
  let authorsStr = ''
  if (authors.value.length > 0) {
    if (authors.value.length === 1) {
      authorsStr = authorLastName(authors.value[0])
    } else if (authors.value.length === 2) {
      authorsStr = `${authorLastName(authors.value[0])}, ${authorLastName(authors.value[1])}`
    } else {
      const firstAuthors = authors.value.slice(0, 2).map(a => authorLastName(a)).join(', ')
      authorsStr = `${firstAuthors} et al.`
    }
  }
  
  const year = new Date(article.value.articlePublishTime || article.value.articleSubmitTime).getFullYear()
  const journal = citationJournal.value
  
  return `${authorsStr} ${article.value.articleTitle}. ${journal} (${year}).`
}

// 获取作者姓名的姓氏（假设格式是 "FirstName LastName"）
const authorLastName = (author) => {
  if (!author || !author.authorName) return 'Unknown'
  const parts = author.authorName.split(' ')
  if (parts.length === 1) return parts[0]
  
  // 返回姓氏和名首字母
  const lastName = parts[parts.length - 1]
  const firstName = parts[0]
  return `${lastName}, ${firstName.charAt(0)}.`
}

const copyNatureCitation = () => {
  const citation = formatNatureCitation()
  navigator.clipboard.writeText(citation).then(() => {
    alert('Citation copied to clipboard!')
  })
}

const downloadNatureCitation = () => {
  const citation = formatNatureCitation()
  downloadCitation(citation, 'Nature')
}

const handleImageError = (event) => {
  console.error('Image failed to load:', event.target.src)
  // 可以设置一个占位图
  event.target.style.display = 'none'
}

// 获取文章类型
const getArticleType = () => {
  if (!article.value) return 'Article'
  
  // 尝试从文章数据中获取类型
  if (article.value.articleTypeId && manuscriptTypeOptions.value.length > 0) {
    const type = manuscriptTypeOptions.value.find(t => t.value === article.value.articleTypeId)
    if (type) return type.label
  }
  
  return 'Article'
}

// 获取文章状态文本
const getStatusText = (statusId) => {
  const status = articleStatusOptions.value.find(s => s.value === statusId)
  return status ? status.label : 'Unknown'
}

// 获取文章状态标签样式
const getStatusClass = (statusId) => {
  // 根据状态ID返回对应的样式
  const status = articleStatusOptions.value.find(s => s.value === statusId)
  if (!status) {
    return 'bg-blue-100 text-blue-800 dark:bg-blue-900 dark:text-blue-200'
  }
  
  // 基于状态名称返回不同的样式
  const label = status.label.toLowerCase()
  if (label.includes('published')) {
    return 'bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-200'
  } else if (label.includes('review')) {
    return 'bg-yellow-100 text-yellow-800 dark:bg-yellow-900 dark:text-yellow-200'
  } else if (label.includes('accepted')) {
    return 'bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-200'
  } else if (label.includes('rejected')) {
    return 'bg-red-100 text-red-800 dark:bg-red-900 dark:text-red-200'
  } else if (label.includes('revision')) {
    return 'bg-orange-100 text-orange-800 dark:bg-orange-900 dark:text-orange-200'
  } else if (label.includes('submitted')) {
    return 'bg-gray-100 text-gray-800 dark:bg-gray-700 dark:text-gray-200'
  } else if (label.includes('withdrawn')) {
    return 'bg-gray-100 text-gray-800 dark:bg-gray-700 dark:text-gray-200'
  }
  
  return 'bg-blue-100 text-blue-800 dark:bg-blue-900 dark:text-blue-200'
}

onMounted(() => {
  fetchArticle()
})
</script>
