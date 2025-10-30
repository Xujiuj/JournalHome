import { resolveComponent } from './componentRegistry'

const ensureLeadingSlash = (path) => {
  if (!path) {
    return null
  }
  return path.startsWith('/') ? path : `/${path}`
}

const normalizeName = (menu) => {
  if (menu.menuComponent) {
    return `${menu.menuComponent}_${menu.menuId}`
  }
  if (menu.menuName) {
    const compact = menu.menuName.replace(/\s+/g, '')
    return `${compact || 'menu'}_${menu.menuId}`
  }
  return `menu_${menu.menuId}`
}

const sortMenus = (menus = []) => {
  return [...menus].sort((a, b) => {
    const orderA = a.menuOrderNum ?? 0
    const orderB = b.menuOrderNum ?? 0
    if (orderA === orderB) {
      return (a.menuId ?? 0) - (b.menuId ?? 0)
    }
    return orderA - orderB
  })
}

const buildMenuTree = (menuList = []) => {
  const map = new Map()
  menuList.forEach((item) => {
    map.set(item.menuId, { ...item, children: [] })
  })

  const roots = []
  map.forEach((item) => {
    if (item.menuParentId && map.has(item.menuParentId)) {
      const parent = map.get(item.menuParentId)
      parent.children.push(item)
    } else {
      roots.push(item)
    }
  })

  const attachSortedChildren = (nodes) => {
    sortMenus(nodes).forEach((node) => {
      if (node.children?.length) {
        node.children = sortMenus(node.children)
        attachSortedChildren(node.children)
      }
    })
  }

  attachSortedChildren(roots)
  return sortMenus(roots)
}

const createRouteFromMenu = (menu) => {
  const path = ensureLeadingSlash(menu.menuPath)
  const component = resolveComponent(menu.menuComponent)

  if (!path || !component) {
    return null
  }

  return {
    path,
    name: normalizeName(menu),
    component,
    meta: {
      menuId: menu.menuId,
      parentId: menu.menuParentId,
      title: menu.menuName,
      order: menu.menuOrderNum,
      menuType: menu.menuType
    }
  }
}

export const buildRoutesFromMenu = (menuList = []) => {
  const routes = []

  const traverse = (items = []) => {
    items.forEach((item) => {
      const route = createRouteFromMenu(item)
      if (route) {
        routes.push(route)
      }
      if (item.children?.length) {
        traverse(item.children)
      }
    })
  }

  const tree = buildMenuTree(menuList)
  traverse(tree)
  return routes
}
