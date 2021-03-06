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

export function updateRole(data) {
  return request({
    url: `/permission/role/update`,
    method: 'put',
    data
  })
}

export function deleteRole(id) {
  return request({
    url: `/permission/role/del/${id}`,
    method: 'delete'
  })
}
