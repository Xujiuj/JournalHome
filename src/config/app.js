const toNumber = (value, fallback) => {
  const parsed = Number(value)
  return Number.isFinite(parsed) && parsed > 0 ? parsed : fallback
}

// 默认分页大小
const DEFAULT_PAGE_SIZE = toNumber(import.meta.env?.VITE_DEFAULT_PAGE_SIZE, 10)

// 首页展示文章数量
const HOME_FEATURED_ARTICLE_LIMIT = toNumber(import.meta.env?.VITE_HOME_FEATURED_LIMIT, 3)

// 首页展示通知数量
const HOME_NOTICE_LIMIT = toNumber(import.meta.env?.VITE_HOME_NOTICE_LIMIT, 3)

// 缓存有效期（毫秒）
const DEFAULT_CACHE_TTL = toNumber(import.meta.env?.VITE_CACHE_TTL, 5 * 60 * 1000)

export {
  DEFAULT_PAGE_SIZE,
  HOME_FEATURED_ARTICLE_LIMIT,
  HOME_NOTICE_LIMIT,
  DEFAULT_CACHE_TTL
}


