import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login/doLogin',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/login/doLogout',
    method: 'post'
  })
}
