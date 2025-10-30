import apiClient from '../client'

/**
 * Subject and Area API
 */
export const subjectApi = {
  // Get all subjects
  getSubjectList: () => {
    return apiClient.get('/sys/subject/list')
  },
  
  // Get areas by subject ID
  getAreasBySubjectId: (subjectId) => {
    return apiClient.get(`/sys/area/subject/${subjectId}`)
  },
  
  // Get all areas
  getAllAreas: () => {
    return apiClient.get('/sys/area/list')
  }
}


