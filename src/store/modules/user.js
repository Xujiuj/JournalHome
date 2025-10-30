// 用户相关 store 模块
const state = {
  userInfo: null,
  token: localStorage.getItem('token') || null,
  permissions: []
}

const mutations = {
  SET_USER_INFO(state, userInfo) {
    state.userInfo = userInfo
  },
  SET_TOKEN(state, token) {
    state.token = token
    if (token) {
      localStorage.setItem('token', token)
    } else {
      localStorage.removeItem('token')
    }
  },
  SET_PERMISSIONS(state, permissions) {
    state.permissions = permissions
  },
  CLEAR_USER(state) {
    state.userInfo = null
    state.token = null
    state.permissions = []
    localStorage.removeItem('token')
  }
}

const actions = {
  login({ commit }, { userInfo, token }) {
    commit('SET_USER_INFO', userInfo)
    commit('SET_TOKEN', token)
  },
  logout({ commit }) {
    commit('CLEAR_USER')
  },
  setUserInfo({ commit }, userInfo) {
    commit('SET_USER_INFO', userInfo)
  }
}

const getters = {
  isAuthenticated: state => !!state.token,
  currentUser: state => state.userInfo,
  getUserInfo: state => state.userInfo,
  getUserPermissions: state => state.permissions
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}

