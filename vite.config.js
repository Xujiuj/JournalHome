import { defineConfig, loadEnv } from 'vite'
import { fileURLToPath, URL } from 'node:url'
import vue from '@vitejs/plugin-vue'

const toNumber = (value, fallback) => {
  const parsed = Number(value)
  return Number.isFinite(parsed) ? parsed : fallback
}

export default ({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '')

  const host = env.VITE_DEV_SERVER_HOST || '0.0.0.0'
  const port = toNumber(env.VITE_DEV_SERVER_PORT, 8000)
  const enableProxy = env.VITE_DEV_PROXY_ENABLED !== 'false'
  const proxyTarget = env.VITE_DEV_PROXY_TARGET || env.VITE_API_BASE_URL || 'http://localhost:10086'
  const proxyRewriteBase = env.VITE_DEV_PROXY_REWRITE

  const proxy = enableProxy
    ? {
        '/api': {
          target: proxyTarget,
          changeOrigin: true,
          ...(proxyRewriteBase
            ? {
                rewrite: (path) => path.replace(/^\/api/, proxyRewriteBase)
              }
            : {})
        }
      }
    : undefined

  return defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
      host,
      port,
      proxy
    },
    build: {
      sourcemap: mode !== 'production',
      chunkSizeWarningLimit: 1000,
      rollupOptions: {
        output: {
          manualChunks: {
            'vendor-vue': ['vue', 'vue-router', 'vuex'],
            'vendor-animation': ['gsap', 'aos', 'framer-motion'],
            'vendor-ui': ['@headlessui/vue', '@vueuse/core', 'daisyui', 'lucide-vue-next', 'radix-vue']
          }
        }
      }
    },
    optimizeDeps: {
      include: ['axios', 'vue', 'vue-router', 'vuex', '@headlessui/vue']
    },
    css: {
      preprocessorOptions: {
        scss: {
          additionalData: env.VITE_SCSS_ADDITIONAL_DATA || ''
      }
    }
  }
})
}