const normalizeAuthors = (authors) => {
  if (!Array.isArray(authors)) {
    return []
  }
  return authors
    .filter((author) => author && author.authorUser && author.authorUser.userRealName)
    .map((author, index) => ({
      ...author,
      authorOrder: typeof author.authorOrder === 'number' ? author.authorOrder : index
    }))
    .sort((a, b) => a.authorOrder - b.authorOrder)
}

const formatAuthorNames = (authors) => {
  const normalized = normalizeAuthors(authors)
  if (normalized.length === 0) {
    return 'Unknown Authors'
  }
  return normalized.map((author) => author.authorUser.userRealName).join(', ')
}

const splitKeywords = (keywordString) => {
  if (!keywordString || typeof keywordString !== 'string') {
    return []
  }
  return keywordString
    .split(',')
    .map((keyword) => keyword.trim())
    .filter(Boolean)
}

const formatDate = (dateString, locale = 'en-US', options = { year: 'numeric', month: 'short', day: 'numeric' }) => {
  if (!dateString) {
    return ''
  }
  try {
    return new Date(dateString).toLocaleDateString(locale, options)
  } catch (error) {
    console.warn('Failed to format date:', dateString, error)
    return dateString
  }
}

export {
  normalizeAuthors,
  formatAuthorNames,
  splitKeywords,
  formatDate
}


