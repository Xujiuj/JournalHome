import { createStore } from 'vuex'
import user from './modules/user'
import sysInfo from './modules/sysInfo'
import referenceData from './modules/referenceData'
import content from './modules/content'

const store = createStore({
  modules: {
    user,
    sysInfo,
    referenceData,
    content
  },
  state: {
    // 全局状态
    loading: false
  },
  mutations: {
    SET_LOADING(state, value) {
      state.loading = value
    }
  },
  actions: {
    setLoading({ commit }, value) {
      commit('SET_LOADING', value)
    }
  },
  getters: {
    isLoading: state => state.loading
  }
})

export default store

