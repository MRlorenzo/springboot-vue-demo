import { asyncRoutes } from './role/routes.js'
import serverUsers from './server_users'

const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'editor-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin',
    routes: asyncRoutes
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor',
    routes: []
  }
}

export default [
  // user login
  {
    url: '/user/login',
    type: 'post',
    response: config => {
      const { username } = config.body
      const { token } = tokens[username]

      // mock error
      if (!token) {
        return {
          code: 500,
          msg: 'Account and password are incorrect.'
        }
      }

      return {
        code: 0,
        token: token
      }
    }
  },

  // get user info
  {
    url: '/user/info',
    type: 'get',
    response: config => {
      // const { token } = config.query
      // 假设我们登陆的账号是admin
      const token = 'admin-token'
      const info = users[token]

      // mock error
      if (!info) {
        return {
          code: 50008,
          msg: 'Login failed, unable to get user details.'
        }
      }

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
