import { articleApi, noticeApi } from '@/api'
import {
  DEFAULT_CACHE_TTL,
  HOME_FEATURED_ARTICLE_LIMIT,
  HOME_NOTICE_LIMIT
} from '@/config/app'

const createState = () => ({
  featuredArticles: [],
  latestNotices: [],
  fetchedAt: {
    featuredArticles: 0,
    latestNotices: 0
  },
  loading: {
    featuredArticles: false,
    latestNotices: false
  }
})

const mutations = {
  SET_FEATURED_ARTICLES(state, articles) {
    state.featuredArticles = articles || []
  },
  SET_LATEST_NOTICES(state, notices) {
    state.latestNotices = notices || []
  },
  SET_LOADING(state, { key, value }) {
    state.loading[key] = value
  },
  SET_FETCHED_AT(state, { key, timestamp }) {
    state.fetchedAt[key] = timestamp
  }
}

const shouldRefresh = (state, key, force) => {
  if (force) {
    return true
  }
  const cache = key === 'featuredArticles' ? state.featuredArticles : state.latestNotices
  const timestamp = state.fetchedAt[key] || 0
  if (!cache || cache.length === 0) {
    return true
  }
  return Date.now() - timestamp > DEFAULT_CACHE_TTL
}

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

const actions = {
  async fetchFeaturedArticles({ state, commit }, { force = false, limit = HOME_FEATURED_ARTICLE_LIMIT } = {}) {
    if (!shouldRefresh(state, 'featuredArticles', force)) {
      return state.featuredArticles
    }

    commit('SET_LOADING', { key: 'featuredArticles', value: true })
    try {
      const response = await articleApi.getLatestArticles(limit)
      const data = extractList(response)
      commit('SET_FEATURED_ARTICLES', data.slice(0, limit))
      commit('SET_FETCHED_AT', { key: 'featuredArticles', timestamp: Date.now() })
      return state.featuredArticles
    } finally {
      commit('SET_LOADING', { key: 'featuredArticles', value: false })
    }
  },
  async fetchLatestNotices({ state, commit }, { force = false, limit = HOME_NOTICE_LIMIT } = {}) {
    if (!shouldRefresh(state, 'latestNotices', force)) {
      return state.latestNotices
    }

    commit('SET_LOADING', { key: 'latestNotices', value: true })
    try {
      const response = await noticeApi.getTopNotices(limit)
      const data = extractList(response)
      commit('SET_LATEST_NOTICES', data.slice(0, limit))
      commit('SET_FETCHED_AT', { key: 'latestNotices', timestamp: Date.now() })
      return state.latestNotices
    } finally {
      commit('SET_LOADING', { key: 'latestNotices', value: false })
    }
  }
}

const getters = {
  featuredArticles: (state) => state.featuredArticles,
  latestNotices: (state) => state.latestNotices,
  isLoadingFeatured: (state) => state.loading.featuredArticles,
  isLoadingNotices: (state) => state.loading.latestNotices
}

export default {
  namespaced: true,
  state: createState,
  mutations,
  actions,
  getters
}

