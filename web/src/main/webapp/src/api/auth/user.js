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

//获取用户分页数据
export function getDataPage( data ){
  return request({
    url: '/user/page',
    method: 'get',
    params: data
  })
}

export function delUser( id ){
  return request({
    url: `/user/del/${id}`,
    method: 'get'
  })
}

export function updateUser( data ){
  return request({
    url: '/user/update',
    method: 'post',
    data
  })
}

export function addUser( data ){
  return request({
    url: '/user/add',
    method: 'post',
    data
  })
}

// 退出登陆接口
export function logout() {
  return request({
    url: '/user/logout',
    method: 'get'
  })
}
