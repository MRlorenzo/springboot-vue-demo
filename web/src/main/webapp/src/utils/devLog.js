const isDev = process.env.NODE_ENV === 'development'

// 自定义的控制台打印日志方法
export function devLog() {
  if (isDev) {
    console.log.call(null, ...arguments)
  }
}
