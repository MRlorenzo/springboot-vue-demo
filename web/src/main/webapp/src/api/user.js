import request from '@/utils/request'

// 登陆接口
export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

// 获取用户信息接口
export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

// 退出登陆接口
export function logout() {
  return request({
    url: '/user/logout',
    method: 'get'
  })
}
