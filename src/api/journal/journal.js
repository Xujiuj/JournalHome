import apiClient from '../client'

/**
 * 期刊信息API
 */
export const journalApi = {
  // 获取期刊基本信息
  getJournalInfo: () => {
    return apiClient.get('/journal/list')
  }
}
