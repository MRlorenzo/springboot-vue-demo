import Mock from 'mockjs'
import { deepClone } from '../../../src/utils/index.js'
import { asyncRoutes } from './routes.js'

const routes = deepClone([ ...asyncRoutes])

const db_roles = [
  {
    "id": 1,
    "name": "管理员",
    "description": "管理员",
    "createUserId": 1,
    "createTime": "2017-04-03T14:50:06.000+0000",
    "isShift": 0,
    "isDel": 0,
    "departmentId": 1,
    "departmentInfo": {
      "departmentId": 4,
      "name": "董事长",
      "enName": "President",
      "departmentCode": "President",
      "sort": 1
    },
    "sort": 1,
    "routes": [
      {
        "id": 1,
        "path": "/permission",
        "component": "layout/Layout",
        "name": "",
        "redirect": "/permission/index",
        "alwaysShow": true,
        "hidden": null,
        "meta": "{\"title\":\"permission\",\"icon\":\"lock\",\"roles\":[\"admin\",\"editor\"]}",
        "pid": 0,
        "sort": 0,
        "perms": null,
        "children": [
          {
            "id": 2,
            "path": "page",
            "component": "views/permission/page",
            "name": "PagePermission",
            "redirect": null,
            "alwaysShow": null,
            "hidden": null,
            "meta": "{\"title\":\"pagePermission\",\"roles\":[\"admin\"]}",
            "pid": 1,
            "sort": 0,
            "perms": null,
            "children": null
          },
          {
            "id": 3,
            "path": "directive",
            "component": "views/permission/directive",
            "name": "DirectivePermission",
            "redirect": null,
            "alwaysShow": null,
            "hidden": null,
            "meta": "{\"title\":\"directivePermission\"}",
            "pid": 1,
            "sort": 0,
            "perms": null,
            "children": null
          },
          {
            "id": 4,
            "path": "role",
            "component": "views/permission/role",
            "name": "RolePermission",
            "redirect": null,
            "alwaysShow": null,
            "hidden": null,
            "meta": "{\"title\":\"rolePermission\",\"roles\":[\"admin\"]}",
            "pid": 1,
            "sort": 0,
            "perms": null,
            "children": null
          }
        ]
      },
      {
        "id": 5,
        "path": "/icon",
        "component": "layout/Layout",
        "name": null,
        "redirect": null,
        "alwaysShow": null,
        "hidden": null,
        "meta": null,
        "pid": 0,
        "sort": 0,
        "perms": null,
        "children": [
          {
            "id": 6,
            "path": "index",
            "component": "views/icons/index",
            "name": "Icons",
            "redirect": null,
            "alwaysShow": null,
            "hidden": null,
            "meta": "{\"title\":\"icons\",\"icon\":\"icon\",\"noCache\":true}",
            "pid": 5,
            "sort": 0,
            "perms": null,
            "children": null
          }
        ]
      }
    ]
  }
]

export default [
  // mock get all routes form server
  {
    url: '/permission/routes',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        list: routes
      }
    }
  },

  // mock get all roles form server
  {
    url: '/permission/roles',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        list: db_roles
      }
    }
  },

  // add role
  {
    url: '/permission/role',
    type: 'post',
    response: {
      code: 0,
      data: {
        key: Mock.mock('@integer(300, 5000)')
      }
    }
  },

  // update role
  {
    url: '/permission/role/update',
    type: 'put',
    response: {
      code: 0,
      data: {
        status: 'success'
      }
    }
  },

  // delete role
  {
    url: '/permission/role/del/[A-Za-z0-9]',
    type: 'delete',
    response: {
      code: 0,
      data: {
        status: 'success'
      }
    }
  }
]
