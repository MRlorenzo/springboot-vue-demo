import rootMenus from './root_menus'
import menus from './menus'
import serverUsers from "../server_users";
export default [

  {
    url: '/permission/menu/root',
    type: 'get',
    response: config => {

      return {
        code: 0,
        list: rootMenus
      }
    }
  },

  {
    url: '/permission/menu/all',
    type: 'get',
    response: _=>{
      return {
        code: 0,
        list: menus
      }
    }
  },

  {
    url: '/permission/menu/page',
    type: 'get',
    response: config => {
      let {page , limit , pid} = config.query
      page = page || 1
      limit = limit || 10

      let offset = (page - 1) * limit
      let end = parseInt(offset) + parseInt(limit)
      let totalPage = Math.ceil(menus.length / limit)
      let searchList = menus
        .filter( item => pid == 0 || pid == item.id || pid == item.pid )
      let list = searchList
        .filter((item , index) => index >= offset && index < end)

      return {
        code: 0,
        page: {
          currPage: page,
          list,
          pageSize: limit,
          totalCount: searchList.length,
          totalPage
        }
      }
    }
  },

  {
    url: '/permission/menu/add',
    type: 'post',
    response: _=>{
      return {
        code: 0,
        data: 'success'
      }
    }
  },

  {
    url: '/permission/menu/update',
    type: 'post',
    response: _=>{
      return {
        code: 0,
        data: 'success'
      }
    }
  },

  {
    url: '/permission/menu/del/[A-Za-z0-9]',
    type: 'post',
    response: _=>{
      return {
        code: 0,
        data: 'success'
      }
    }
  },
]
