// 文章相关 store 模块
const state = {
  articleList: [],
  currentArticle: null,
  total: 0,
  pageSize: 10,
  currentPage: 1,
  filters: {
    status: null,
    type: null,
    keyword: null
  }
}

const mutations = {
  SET_ARTICLE_LIST(state, articleList) {
    state.articleList = articleList
  },
  SET_CURRENT_ARTICLE(state, article) {
    state.currentArticle = article
  },
  SET_TOTAL(state, total) {
    state.total = total
  },
  SET_CURRENT_PAGE(state, page) {
    state.currentPage = page
  },
  SET_PAGE_SIZE(state, size) {
    state.pageSize = size
  },
  SET_FILTERS(state, filters) {
    state.filters = { ...state.filters, ...filters }
  },
  RESET_FILTERS(state) {
    state.filters = {
      status: null,
      type: null,
      keyword: null
    }
  },
  CLEAR_ARTICLE(state) {
    state.currentArticle = null
  }
}

const actions = {
  setArticleList({ commit }, { list, total }) {
    commit('SET_ARTICLE_LIST', list)
    if (total !== undefined) {
      commit('SET_TOTAL', total)
    }
  },
  setCurrentArticle({ commit }, article) {
    commit('SET_CURRENT_ARTICLE', article)
  },
  setPage({ commit }, page) {
    commit('SET_CURRENT_PAGE', page)
  },
  setFilters({ commit }, filters) {
    commit('SET_FILTERS', filters)
  },
  resetFilters({ commit }) {
    commit('RESET_FILTERS')
    commit('SET_CURRENT_PAGE', 1)
  },
  clearArticle({ commit }) {
    commit('CLEAR_ARTICLE')
  }
}

const getters = {
  getArticleList: state => state.articleList,
  getCurrentArticle: state => state.currentArticle,
  getTotal: state => state.total,
  getCurrentPage: state => state.currentPage,
  getPageSize: state => state.pageSize,
  getFilters: state => state.filters,
  getTotalPages: state => Math.ceil(state.total / state.pageSize)
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}

