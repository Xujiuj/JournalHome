const normalizeString = (value, fallback) => {
  if (value === undefined || value === null) {
    return fallback
  }

  const normalized = String(value).trim()
  return normalized.length > 0 ? normalized : fallback
}


export { normalizeString as withFallback }

