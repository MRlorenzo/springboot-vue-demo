import { asyncRoutes } from './role/routes.js'
const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'editor-token'
  }
}

let data = {
  "name": "哈 哈哈",
  "roles": [
    "admin"
  ],
  "introduction": null,
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
          "children": null
        }
      ]
    },
    {
      "id": 7,
      "path": "/components",
      "component": "layout/Layout",
      "name": "ComponentDemo",
      "redirect": "noRedirect",
      "alwaysShow": null,
      "hidden": null,
      "meta": "{\"title\":\"components\",\"icon\":\"component\"}",
      "pid": 0,
      "children": [
        {
          "id": 8,
          "path": "tinymce",
          "component": "views/components-demo/tinymce",
          "name": "TinymceDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"tinymce\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 9,
          "path": "markdown",
          "component": "views/components-demo/markdown",
          "name": "MarkdownDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"markdown\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 10,
          "path": "json-editor",
          "component": "views/components-demo/json-editor",
          "name": "JsonEditorDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"jsonEditor\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 11,
          "path": "split-pane",
          "component": "views/components-demo/split-pane",
          "name": "SplitpaneDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"splitPane\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 12,
          "path": "avatar-upload",
          "component": "views/components-demo/avatar-upload",
          "name": "AvatarUploadDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"avatarUpload\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 13,
          "path": "dropzone",
          "component": "views/components-demo/dropzone",
          "name": "DropzoneDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"dropzone\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 14,
          "path": "sticky",
          "component": "views/components-demo/sticky",
          "name": "StickyDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"sticky\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 15,
          "path": "count-to",
          "component": "views/components-demo/count-to",
          "name": "CountToDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"countTo\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 16,
          "path": "mixin",
          "component": "views/components-demo/mixin",
          "name": "ComponentMixinDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"componentMixin\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 17,
          "path": "back-to-top",
          "component": "views/components-demo/back-to-top",
          "name": "BackToTopDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"backToTop\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 18,
          "path": "drag-dialog",
          "component": "views/components-demo/drag-dialog",
          "name": "DragDialogDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"dragDialog\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 19,
          "path": "drag-select",
          "component": "views/components-demo/drag-select",
          "name": "DragSelectDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{ title: 'dragSelect' }",
          "pid": 7,
          "children": null
        },
        {
          "id": 20,
          "path": "dnd-list",
          "component": "views/components-demo/dnd-list",
          "name": "DndListDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"dndList\"}",
          "pid": 7,
          "children": null
        },
        {
          "id": 21,
          "path": "drag-kanban",
          "component": "views/components-demo/drag-kanban",
          "name": "DragKanbanDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"dragKanban\"}",
          "pid": 7,
          "children": null
        }
      ]
    },
    {
      "id": 22,
      "path": "/charts",
      "component": "layout/Layout",
      "name": "Charts",
      "redirect": "noRedirect",
      "alwaysShow": null,
      "hidden": null,
      "meta": "{\"title\":\"charts\",\"icon\":\"chart\"}",
      "pid": 0,
      "children": [
        {
          "id": 23,
          "path": "keyboard",
          "component": "views/charts/keyboard",
          "name": "KeyboardChart",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"keyboardChart\",\"noCache\":true}",
          "pid": 22,
          "children": null
        },
        {
          "id": 24,
          "path": "line",
          "component": "views/charts/line",
          "name": "LineChart",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"lineChart\",\"noCache\":true}",
          "pid": 22,
          "children": null
        },
        {
          "id": 25,
          "path": "mixchart",
          "component": "views/charts/mixChart",
          "name": "MixChart",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"mixChart\",\"noCache\":true}",
          "pid": 22,
          "children": null
        }
      ]
    },
    {
      "id": 26,
      "path": "/tab",
      "component": "layout/Layout",
      "name": null,
      "redirect": null,
      "alwaysShow": null,
      "hidden": null,
      "meta": null,
      "pid": 0,
      "children": [
        {
          "id": 27,
          "path": "index",
          "component": "views/tab/index",
          "name": "Tab",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"tab\",\"icon\":\"tab\"}",
          "pid": 26,
          "children": null
        }
      ]
    },
    {
      "id": 28,
      "path": "/error",
      "component": "layout/Layout",
      "name": "ErrorPages",
      "redirect": "noRedirect",
      "alwaysShow": null,
      "hidden": null,
      "meta": "{\"title\":\"errorPages\",\"icon\":\"404\"}",
      "pid": 0,
      "children": [
        {
          "id": 29,
          "path": "401",
          "component": "views/error-page/401",
          "name": "Page401",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"page401\",\"noCache\":true}",
          "pid": 28,
          "children": null
        },
        {
          "id": 30,
          "path": "404",
          "component": "views/error-page/404",
          "name": "Page404",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"page404\",\"noCache\":true}",
          "pid": 28,
          "children": null
        }
      ]
    },
    {
      "id": 31,
      "path": "/error-log",
      "component": "layout/Layout",
      "name": null,
      "redirect": "noRedirect",
      "alwaysShow": null,
      "hidden": null,
      "meta": null,
      "pid": 0,
      "children": [
        {
          "id": 32,
          "path": "log",
          "component": "views/error-log/index",
          "name": "ErrorLog",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"errorLog\",\"icon\":\"bug\"}",
          "pid": 31,
          "children": null
        }
      ]
    },
    {
      "id": 33,
      "path": "/excel",
      "component": "layout/Layout",
      "name": "Excel",
      "redirect": "/excel/export-excel",
      "alwaysShow": null,
      "hidden": null,
      "meta": "{\"title\":\"excel\",\"icon\":\"excel\"}",
      "pid": 0,
      "children": [
        {
          "id": 34,
          "path": "export-excel",
          "component": "views/excel/export-excel",
          "name": "ExportExcel",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"exportExcel\"}",
          "pid": 33,
          "children": null
        },
        {
          "id": 35,
          "path": "export-selected-excel",
          "component": "views/excel/select-excel",
          "name": "SelectExcel",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"selectExcel\"}",
          "pid": 33,
          "children": null
        },
        {
          "id": 36,
          "path": "export-merge-header",
          "component": "views/excel/merge-header",
          "name": "MergeHeader",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"mergeHeader\"}",
          "pid": 33,
          "children": null
        },
        {
          "id": 37,
          "path": "upload-excel",
          "component": "views/excel/upload-excel",
          "name": "UploadExcel",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"uploadExcel\"}",
          "pid": 33,
          "children": null
        }
      ]
    },
    {
      "id": 38,
      "path": "/zip",
      "component": "layout/Layout",
      "name": null,
      "redirect": "/zip/download",
      "alwaysShow": true,
      "hidden": null,
      "meta": null,
      "pid": 0,
      "children": []
    },
    {
      "id": 40,
      "path": "/pdf",
      "component": "layout/Layout",
      "name": null,
      "redirect": "/pdf/index",
      "alwaysShow": null,
      "hidden": null,
      "meta": null,
      "pid": 0,
      "children": [
        {
          "id": 41,
          "path": "index",
          "component": "views/pdf/index",
          "name": "PDF",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"pdf\",\"icon\":\"pdf\"}",
          "pid": 40,
          "children": null
        }
      ]
    },
    {
      "id": 42,
      "path": "/pdf/download",
      "component": "views/pdf/download",
      "name": null,
      "redirect": null,
      "alwaysShow": null,
      "hidden": true,
      "meta": null,
      "pid": 0,
      "children": []
    },
    {
      "id": 43,
      "path": "/theme",
      "component": "layout/Layout",
      "name": null,
      "redirect": "noRedirect",
      "alwaysShow": null,
      "hidden": null,
      "meta": null,
      "pid": 0,
      "children": [
        {
          "id": 44,
          "path": "index",
          "component": "views/theme/index",
          "name": "Theme",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"theme\",\"icon\":\"theme\"}",
          "pid": 43,
          "children": null
        }
      ]
    },
    {
      "id": 45,
      "path": "/clipboard",
      "component": "layout/Layout",
      "name": null,
      "redirect": "noRedirect",
      "alwaysShow": null,
      "hidden": null,
      "meta": null,
      "pid": 0,
      "children": [
        {
          "id": 46,
          "path": "index",
          "component": "views/clipboard/index",
          "name": "ClipboardDemo",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"clipboardDemo\",\"icon\":\"clipboard\"}",
          "pid": 45,
          "children": null
        }
      ]
    },
    {
      "id": 47,
      "path": "/i18n",
      "component": "layout/Layout",
      "name": null,
      "redirect": null,
      "alwaysShow": null,
      "hidden": null,
      "meta": null,
      "pid": 0,
      "children": [
        {
          "id": 48,
          "path": "index",
          "component": "views/i18n-demo/index",
          "name": "I18n",
          "redirect": null,
          "alwaysShow": null,
          "hidden": null,
          "meta": "{\"title\":\"i18n\",\"icon\":\"international\"}",
          "pid": 47,
          "children": null
        }
      ]
    }
  ]
};

const users = {
  // 'admin-token': {
  //   roles: ['admin'],
  //   introduction: 'I am a super administrator',
  //   avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
  //   name: 'Super Admin',
  //   routes: asyncRoutes
  // },
  'admin-token': data,
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

  // user logout
  {
    url: '/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: 0,
        data: 'success'
      }
    }
  }
]
