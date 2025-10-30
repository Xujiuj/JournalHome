import { enumApi } from '@/api'
import { DEFAULT_CACHE_TTL } from '@/config/app'

const createState = () => ({
  enums: {
    articleStatus: [],
    manuscriptType: [],
    articleType: [],
    submissionType: [],
    fileType: [],
    degree: []
  },
  fetchedAt: {}
})

const mutations = {
  SET_ENUM(state, { key, value }) {
    state.enums[key] = value || []
  },
  SET_FETCHED_AT(state, { key, timestamp }) {
    state.fetchedAt[key] = timestamp
  }
}

const shouldRefresh = (state, key, force) => {
  if (force) {
    return true
  }
  const cached = state.enums[key]
  const lastFetched = state.fetchedAt[key] || 0
  if (!cached || cached.length === 0) {
    return true
  }
  return Date.now() - lastFetched > DEFAULT_CACHE_TTL
}

const loadEnum = async (context, { key, fetcher, force = false }) => {
  const { state, commit } = context
  if (!shouldRefresh(state, key, force)) {
    return state.enums[key]
  }

  const response = await fetcher()
  const payload = response?.data ?? response ?? []
  commit('SET_ENUM', { key, value: Array.isArray(payload) ? payload : [] })
  commit('SET_FETCHED_AT', { key, timestamp: Date.now() })
  return state.enums[key]
}

const actions = {
  ensureArticleStatus({ state, commit }, { force = false } = {}) {
    return loadEnum({ state, commit }, {
      key: 'articleStatus',
      force,
      fetcher: () => enumApi.getArticleStatusOptions()
    })
  },
  ensureManuscriptType({ state, commit }, { force = false } = {}) {
    return loadEnum({ state, commit }, {
      key: 'manuscriptType',
      force,
      fetcher: () => enumApi.getManuscriptTypeOptions()
    })
  },
  ensureArticleType({ state, commit }, { force = false } = {}) {
    return loadEnum({ state, commit }, {
      key: 'articleType',
      force,
      fetcher: () => enumApi.getArticleTypeOptions()
    })
  },
  ensureSubmissionType({ state, commit }, { force = false } = {}) {
    return loadEnum({ state, commit }, {
      key: 'submissionType',
      force,
      fetcher: () => enumApi.getSubmissionTypeOptions()
    })
  },
  ensureFileType({ state, commit }, { force = false } = {}) {
    return loadEnum({ state, commit }, {
      key: 'fileType',
      force,
      fetcher: () => enumApi.getFileTypeOptions()
    })
  },
  ensureDegree({ state, commit }, { force = false } = {}) {
    return loadEnum({ state, commit }, {
      key: 'degree',
      force,
      fetcher: () => enumApi.getDegreeOptions()
    })
  }
}

const getters = {
  articleStatusOptions: (state) => state.enums.articleStatus,
  manuscriptTypeOptions: (state) => state.enums.manuscriptType,
  articleTypeOptions: (state) => state.enums.articleType,
  submissionTypeOptions: (state) => state.enums.submissionType,
  fileTypeOptions: (state) => state.enums.fileType,
  degreeOptions: (state) => state.enums.degree,
  articleStatusMap: (state) => Object.fromEntries((state.enums.articleStatus || []).map(item => [item.value, item.label])),
  articleTypeMap: (state) => Object.fromEntries((state.enums.articleType || []).map(item => [item.value, item.label]))
}

export default {
  namespaced: true,
  state: createState,
  mutations,
  actions,
  getters
}

