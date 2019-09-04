import { asyncRoutes } from './role/routes.js'
import serverUsers from './server_users'
import { deepClone } from "../../src/utils";


export default [
  // user login
  {
    url: '/user/login',
    type: 'post',
    response: config => {

      return {
        code: 0,
        token: 'admin-token'
      }
    }
  },

  // get user info
  {
    url: '/user/info',
    type: 'get',
    response: config => {
      // 假设我们登陆的账号是admin
      const [info] = deepClone(serverUsers)

      // mock error
      if (!info) {
        return {
          code: 50008,
          msg: 'Login failed, unable to get user details.'
        }
      }

      info.routes = asyncRoutes
      info.roles = ['admin']

      return {
        code: 0,
        entity:{
          ...info
        }
      }
    }
  },

  {
    url: '/user/page',
    type: 'get',
    response: config => {
      let {page , limit} = config.query
      page = page || 1
      limit = limit || 10

      let offset = (page - 1) * limit
      let end = offset + limit
      let totalPage = Math.ceil(serverUsers.length / limit)
      let list = serverUsers.filter((item , index) => index >= offset && index < end)
      return {
        code: 0,
        page: {
          currPage: page,
          list,
          pageSize: limit,
          totalCount: serverUsers.length,
          totalPage
        }
      }
    }
  },
  // update user
  {
    url: '/user/update',
    type: 'post',
    response: _=>{
      return {
        code: 0,
        data: 'success'
      }
    }
  },
  // add user
  {
    url: '/user/add',
    type: 'post',
    response: _=>{
      return {
        code: 0,
        data: 'success'
      }
    }
  },

  {
    url: '/user/del/[A-Za-z0-9]',
    type: 'get',
    response: _=>{
      return {
        code: 0,
        data: 'success'
      }
    }
  },

  // user logout
  {
    url: '/user/logout',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        data: 'success'
      }
    }
  }
]
