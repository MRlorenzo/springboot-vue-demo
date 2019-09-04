import request from '@/utils/request'

/*
* 获取菜单的分页数据
* */
export function getDataPage( data ){
  return request({
    url: '/permission/menu/page',
    method: 'get',
    params: data
  })
}

/*
* 获取根菜单的列表
* */
export function getRootMenuList( ){
  return request({
    url: '/permission/menu/root',
    method: 'get'
  })
}

/*
* 添加菜单
* */
export function addMenu( data ){
  return request({
    url: '/permission/menu/add',
    method: 'post',
    data
  })
}

/*
* 更新菜单
* */
export function updateMenu( data ){
  return request({
    url: '/permission/menu/update',
    method: 'post',
    data
  })
}

/*
* 根据id删除菜单
* */
export function delMenu( id ){
  return request({
    url: `/permission/menu/del/${id}`,
    method: 'get'
  })
}
