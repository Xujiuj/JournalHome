/**
 * 滚动工具函数
 */

/**
 * 滚动到页面顶部
 * @param {Object} options - 滚动选项
 * @param {number} options.top - 滚动位置，默认为0
 * @param {string} options.behavior - 滚动行为，默认为'smooth'
 * @param {number} options.duration - 动画持续时间（毫秒），当behavior为'smooth'时有效
 */
export const scrollToTop = (options = {}) => {
  const {
    top = 0,
    behavior = 'smooth',
    duration = 300
  } = options

  // 如果浏览器支持scrollTo方法
  if (window.scrollTo) {
    window.scrollTo({
      top,
      behavior,
      left: 0
    })
  } else {
    // 降级方案：直接设置scrollTop
    document.documentElement.scrollTop = top
    document.body.scrollTop = top
  }
}

/**
 * 滚动到指定元素
 * @param {string|Element} selector - 元素选择器或DOM元素
 * @param {Object} options - 滚动选项
 */
export const scrollToElement = (selector, options = {}) => {
  const {
    behavior = 'smooth',
    block = 'start',
    inline = 'nearest',
    offset = 0
  } = options

  const element = typeof selector === 'string' 
    ? document.querySelector(selector) 
    : selector

  if (element) {
    element.scrollIntoView({
      behavior,
      block,
      inline
    })
  }
}

/**
 * 获取当前滚动位置
 * @returns {Object} 包含x和y坐标的对象
 */
export const getScrollPosition = () => {
  return {
    x: window.pageXOffset || document.documentElement.scrollLeft,
    y: window.pageYOffset || document.documentElement.scrollTop
  }
}

/**
 * 检查是否已滚动到页面顶部
 * @param {number} threshold - 阈值，默认为10px
 * @returns {boolean} 是否已滚动到顶部
 */
export const isAtTop = (threshold = 10) => {
  return getScrollPosition().y <= threshold
}

/**
 * 检查是否已滚动到页面底部
 * @param {number} threshold - 阈值，默认为10px
 * @returns {boolean} 是否已滚动到底部
 */
export const isAtBottom = (threshold = 10) => {
  const { y } = getScrollPosition()
  const height = document.documentElement.scrollHeight
  const windowHeight = window.innerHeight
  return y + windowHeight >= height - threshold
}

/**
 * 监听滚动事件
 * @param {Function} callback - 滚动回调函数
 * @param {Object} options - 监听选项
 * @returns {Function} 取消监听的函数
 */
export const onScroll = (callback, options = {}) => {
  const {
    passive = true,
    throttle = 16 // 约60fps
  } = options

  let ticking = false

  const throttledCallback = () => {
    if (!ticking) {
      requestAnimationFrame(() => {
        callback(getScrollPosition())
        ticking = false
      })
      ticking = true
    }
  }

  window.addEventListener('scroll', throttledCallback, { passive })
  
  // 返回取消监听的函数
  return () => {
    window.removeEventListener('scroll', throttledCallback)
  }
}
