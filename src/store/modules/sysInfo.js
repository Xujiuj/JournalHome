// 系统信息相关 store 模块
import {sysInfoApi, menuApi} from '@/api'

const FALLBACK_BRANDING = Object.freeze({
    name: 'Journal of Science and Technology',
    shortName: 'Journal of Science and Technology',
    tagline: 'A leading journal in science and technology research',
    mission: 'Advancing scientific knowledge and technological innovation through rigorous peer-reviewed research.',
    citationName: 'Journal of Science and Technology',
    issn: '1234-5678',
    description: 'A leading journal in science and technology research',
    foundedYear: '2009',
    impactFactor: '2.5',
    publisher: 'Science Press',
    website: 'https://www.journal.com',
    logo: '/journal-logo.png'
})

const FALLBACK_CONTACT = Object.freeze({
    email: 'editorial@journal.com',
    phone: '+1 (555) 123-4567',
    address: '123 Science Street, Research City, RC 12345'
})

const sanitizeString = (value, fallback) => {
    if (value === undefined || value === null) {
        return fallback
    }
    const str = String(value).trim()
    return str.length > 0 ? str : fallback
}

const buildBranding = (sysInfo = {}) => ({
    name: sanitizeString(sysInfo.infoName, FALLBACK_BRANDING.name),
    shortName: sanitizeString(sysInfo.infoSimpleName, FALLBACK_BRANDING.shortName),
    tagline: sanitizeString(sysInfo.infoComment, FALLBACK_BRANDING.tagline),
    mission: sanitizeString(sysInfo.infoMission, FALLBACK_BRANDING.mission),
    citationName: sanitizeString(sysInfo.infoCitationName, sysInfo.infoName || FALLBACK_BRANDING.citationName),
    issn: sanitizeString(sysInfo.infoIssn, FALLBACK_BRANDING.issn),
    description: sanitizeString(sysInfo.infoDescription, FALLBACK_BRANDING.description),
    foundedYear: sanitizeString(sysInfo.infoFoundedYear, FALLBACK_BRANDING.foundedYear),
    impactFactor: sanitizeString(sysInfo.infoImpactFactor, FALLBACK_BRANDING.impactFactor),
    publisher: sanitizeString(sysInfo.infoPublisher, FALLBACK_BRANDING.publisher),
    website: sanitizeString(sysInfo.infoWebsite, FALLBACK_BRANDING.website),
    logo: sanitizeString(sysInfo.infoLogoUrl || sysInfo.infoLogo, FALLBACK_BRANDING.logo),
    address: sanitizeString(sysInfo.infoAddress, FALLBACK_BRANDING.address),
    email: sanitizeString(sysInfo.infoEmail, FALLBACK_BRANDING.email),
    phone: sanitizeString(sysInfo.infoPhone, FALLBACK_BRANDING.phone),
    // default routes for navigation usage in components
    homeRoute: '/',
    loginRoute: '/login',
    registerRoute: '/register',
    articlesRoute: '/articles',


})

const buildContact = (sysInfo = {}) => ({
    email: sanitizeString(sysInfo.infoEmail, FALLBACK_CONTACT.email),
    phone: sanitizeString(sysInfo.infoPhone, FALLBACK_CONTACT.phone),
    address: sanitizeString(sysInfo.infoAddress, FALLBACK_CONTACT.address)
})

const state = {
    sysInfo: null,
    menuList: [],
    currentMenu: null
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

const mutations = {
    SET_SYS_INFO(state, sysInfo) {
        state.sysInfo = sysInfo
    },
    SET_MENU_LIST(state, menuList) {
        state.menuList = menuList
    },
    SET_CURRENT_MENU(state, menu) {
        state.currentMenu = menu
    }
}

const actions = {
    async fetchSysInfo({commit, state}) {
        if (state.sysInfo) {
            return state.sysInfo
        }
        try {
            const response = await sysInfoApi.getSysInfoById(1)
            commit('SET_SYS_INFO', response.data)
            return state.sysInfo
        } catch (error) {
            console.error('Failed to fetch sys info:', error)
            throw error
        }
    },
    setSysInfo({commit}, sysInfo) {
        commit('SET_SYS_INFO', sysInfo)
    },
    setMenuList({commit}, menuList) {
        commit('SET_MENU_LIST', menuList)
    },
    setCurrentMenu({commit}, menu) {
        commit('SET_CURRENT_MENU', menu)
    },
    async fetchMenuList({commit, state}, {force = false} = {}) {
        if (!force && state.menuList.length > 0) {
            return state.menuList
        }
        try {
            const response = await menuApi.getMenuList({menuVisible: 1, menuStatus: 1})
            const list = extractList(response)

            const sortedList = [...list].sort((a, b) => {
                const orderA = Number.isFinite(a?.menuOrderNum) ? a.menuOrderNum : 0
                const orderB = Number.isFinite(b?.menuOrderNum) ? b.menuOrderNum : 0
                if (orderA === orderB) {
                    return (a.menuId ?? 0) - (b.menuId ?? 0)
                }
                return orderA - orderB
            })

            commit('SET_MENU_LIST', sortedList)
            return sortedList
        } catch (error) {
            console.error('Failed to fetch menu list:', error)
            throw error
        }
    }
}

const getters = {
    getSysInfo: (state) => state.sysInfo,
    getMenuList: (state) => state.menuList,
    getCurrentMenu: (state) => state.currentMenu,
    brandingInfo: (state) => buildBranding(state.sysInfo),
    contactInfo: (state) => buildContact(state.sysInfo),
    logoUrl: (state, getters) => getters.brandingInfo.logo
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}

