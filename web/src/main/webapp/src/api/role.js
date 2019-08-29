import request from '@/utils/request'

export function getRoutes() {
  return request({
    url: '/permission/routes',
    method: 'get'
  })
}

export function getRoles() {
  return request({
    url: '/permission/roles',
    method: 'get'
  })
}

export function addRole(data) {
  return request({
    url: '/permission/role',
    method: 'post',
    data
  })
}

export function updateRole(id, data) {
  return request({
    url: `/permission/role/${id}`,
    method: 'put',
    data
  })
}

export function deleteRole(id) {
  return request({
    url: `/permission/role/${id}`,
    method: 'delete'
  })
}
