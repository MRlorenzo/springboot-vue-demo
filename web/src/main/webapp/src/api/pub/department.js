import request from '@/utils/request'

/**
 * 查询部门列表
 */
export function getDepartments() {
  return request({
    url: '/department/departments',
    method: 'get'
  })
}

/**
 * 新增部门
 * @param data
 */
export function addDepartment( data ){
  return request({
    url: '/department/add',
    method: 'post',
    data
  })
}

/**
 * 更新部门
 * @param data
 */
export function updateDepartment( data ) {
  return request({
    url: '/department/update',
    method: 'post',
    data
  })
}

/**
 * 删除一个部门
 * @param id
 */
export function delDepartment( id ) {
  return request({
    url: `/department/del/${id}`,
    method: 'get'
  })
}
