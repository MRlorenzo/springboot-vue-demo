// Just a mock data

export const constantRoutes = [
  {
    path: '/redirect',
    component: 'layout/Layout',
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: 'views/redirect/index'
      }
    ]
  },
  {
    path: '/login',
    component: 'views/login/index',
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: 'views/login/auth-redirect',
    hidden: true
  },
  {
    path: '/404',
    component: 'views/error-page/404',
    hidden: true
  },
  {
    path: '/401',
    component: 'views/error-page/401',
    hidden: true
  },
  {
    path: '',
    component: 'layout/Layout',
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: 'views/dashboard/index',
        name: 'Dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/documentation',
    component: 'layout/Layout',
    children: [
      {
        path: 'index',
        component: 'views/documentation/index',
        name: 'Documentation',
        meta: { title: 'documentation', icon: 'documentation', affix: true }
      }
    ]
  },
  {
    path: '/guide',
    component: 'layout/Layout',
    redirect: '/guide/index',
    children: [
      {
        path: 'index',
        component: 'views/guide/index',
        name: 'Guide',
        meta: { title: 'guide', icon: 'guide', noCache: true }
      }
    ]
  }
]

export const asyncRoutes = [
  {
    id: 1,
    path: '/permission',
    component: 'layout/Layout',
    redirect: '/permission/index',
    alwaysShow: true,
    meta: {
      title: 'permission',
      icon: 'lock',
      roles: ['admin', 'editor']
    },
    children: [
      {
        id: 2,
        path: 'page',
        component: 'views/permission/page',
        name: 'PagePermission',
        meta: {
          title: 'pagePermission',
          roles: ['admin']
        }
      },
      {
        id: 3,
        path: 'directive',
        component: 'views/permission/directive',
        name: 'DirectivePermission',
        meta: {
          title: 'directivePermission'
        }
      },
      {
        id: 4,
        path: 'role',
        component: 'views/permission/role',
        name: 'RolePermission',
        meta: {
          title: 'rolePermission',
          roles: ['admin']
        }
      }
    ]
  },

  {
    id: 5,
    path: '/icon',
    component: 'layout/Layout',
    children: [
      {
        id:6 ,
        path: 'index',
        component: 'views/icons/index',
        name: 'Icons',
        meta: { title: 'icons', icon: 'icon', noCache: true }
      }
    ]
  },

  {
    id: 7,
    path: '/components',
    component: 'layout/Layout',
    redirect: 'noRedirect',
    name: 'ComponentDemo',
    meta: {
      title: 'components',
      icon: 'component'
    },
    children: [
      {
        id: 8,
        path: 'tinymce',
        component: 'views/components-demo/tinymce',
        name: 'TinymceDemo',
        meta: { title: 'tinymce' }
      },
      {
        id: 9,
        path: 'markdown',
        component: 'views/components-demo/markdown',
        name: 'MarkdownDemo',
        meta: { title: 'markdown' }
      },
      {
        id: 10,
        path: 'json-editor',
        component: 'views/components-demo/json-editor',
        name: 'JsonEditorDemo',
        meta: { title: 'jsonEditor' }
      },
      {
        id: 11,
        path: 'split-pane',
        component: 'views/components-demo/split-pane',
        name: 'SplitpaneDemo',
        meta: { title: 'splitPane' }
      },
      {
        id: 12,
        path: 'avatar-upload',
        component: 'views/components-demo/avatar-upload',
        name: 'AvatarUploadDemo',
        meta: { title: 'avatarUpload' }
      },
      {
        id: 13,
        path: 'dropzone',
        component: 'views/components-demo/dropzone',
        name: 'DropzoneDemo',
        meta: { title: 'dropzone' }
      },
      {
        id: 14,
        path: 'sticky',
        component: 'views/components-demo/sticky',
        name: 'StickyDemo',
        meta: { title: 'sticky' }
      },
      {
        id: 15,
        path: 'count-to',
        component: 'views/components-demo/count-to',
        name: 'CountToDemo',
        meta: { title: 'countTo' }
      },
      {
        id: 16,
        path: 'mixin',
        component: 'views/components-demo/mixin',
        name: 'ComponentMixinDemo',
        meta: { title: 'componentMixin' }
      },
      {
        id: 17,
        path: 'back-to-top',
        component: 'views/components-demo/back-to-top',
        name: 'BackToTopDemo',
        meta: { title: 'backToTop' }
      },
      {
        id: 18,
        path: 'drag-dialog',
        component: 'views/components-demo/drag-dialog',
        name: 'DragDialogDemo',
        meta: { title: 'dragDialog' }
      },
      {
        id: 19,
        path: 'drag-select',
        component: 'views/components-demo/drag-select',
        name: 'DragSelectDemo',
        meta: { title: 'dragSelect' }
      },
      {
        id: 20,
        path: 'dnd-list',
        component: 'views/components-demo/dnd-list',
        name: 'DndListDemo',
        meta: { title: 'dndList' }
      },
      {
        id: 21,
        path: 'drag-kanban',
        component: 'views/components-demo/drag-kanban',
        name: 'DragKanbanDemo',
        meta: { title: 'dragKanban' }
      }
    ]
  },
  {
    id: 22,
    path: '/charts',
    component: 'layout/Layout',
    redirect: 'noRedirect',
    name: 'Charts',
    meta: {
      title: 'charts',
      icon: 'chart'
    },
    children: [
      {
        id: 23,
        path: 'keyboard',
        component: 'views/charts/keyboard',
        name: 'KeyboardChart',
        meta: { title: 'keyboardChart', noCache: true }
      },
      {
        id: 24,
        path: 'line',
        component: 'views/charts/line',
        name: 'LineChart',
        meta: { title: 'lineChart', noCache: true }
      },
      {
        id: 25,
        path: 'mixchart',
        component: 'views/charts/mixChart',
        name: 'MixChart',
        meta: { title: 'mixChart', noCache: true }
      }
    ]
  },
  // {
  //   path: '/nested',
  //   component: 'layout/Layout',
  //   redirect: '/nested/menu1/menu1-1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: 'views/nested/menu1/index',
  //       name: 'Menu1',
  //       meta: { title: 'menu1' },
  //       redirect: '/nested/menu1/menu1-1',
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: 'views/nested/menu1/menu1-1',
  //           name: 'Menu1-1',
  //           meta: { title: 'menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: 'views/nested/menu1/menu1-2',
  //           name: 'Menu1-2',
  //           redirect: '/nested/menu1/menu1-2/menu1-2-1',
  //           meta: { title: 'menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: 'views/nested/menu1/menu1-2/menu1-2-1',
  //               name: 'Menu1-2-1',
  //               meta: { title: 'menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: 'views/nested/menu1/menu1-2/menu1-2-2',
  //               name: 'Menu1-2-2',
  //               meta: { title: 'menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: 'views/nested/menu1/menu1-3',
  //           name: 'Menu1-3',
  //           meta: { title: 'menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       name: 'Menu2',
  //       component: 'views/nested/menu2/index',
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  // {
  //   path: '/example',
  //   component: 'layout/Layout',
  //   redirect: '/example/list',
  //   name: 'Example',
  //   meta: {
  //     title: 'example',
  //     icon: 'example'
  //   },
  //   children: [
  //     {
  //       path: 'create',
  //       component: 'views/example/create',
  //       name: 'CreateArticle',
  //       meta: { title: 'createArticle', icon: 'edit' }
  //     },
  //     {
  //       path: 'edit/:id(\\d+)',
  //       component: 'views/example/edit',
  //       name: 'EditArticle',
  //       meta: { title: 'editArticle', noCache: true },
  //       hidden: true
  //     },
  //     {
  //       path: 'list',
  //       component: 'views/example/list',
  //       name: 'ArticleList',
  //       meta: { title: 'articleList', icon: 'list' }
  //     }
  //   ]
  // },

  {
    id: 26,
    path: '/tab',
    component: 'layout/Layout',
    children: [
      {
        id: 27,
        path: 'index',
        component: 'views/tab/index',
        name: 'Tab',
        meta: { title: 'tab', icon: 'tab' }
      }
    ]
  },

  {
    id: 28,
    path: '/error',
    component: 'layout/Layout',
    redirect: 'noRedirect',
    name: 'ErrorPages',
    meta: {
      title: 'errorPages',
      icon: '404'
    },
    children: [
      {
        id: 29,
        path: '401',
        component: 'views/error-page/401',
        name: 'Page401',
        meta: { title: 'page401', noCache: true }
      },
      {
        id: 30,
        path: '404',
        component: 'views/error-page/404',
        name: 'Page404',
        meta: { title: 'page404', noCache: true }
      }
    ]
  },

  {
    id: 31,
    path: '/error-log',
    component: 'layout/Layout',
    redirect: 'noRedirect',
    children: [
      {
        id: 32,
        path: 'log',
        component: 'views/error-log/index',
        name: 'ErrorLog',
        meta: { title: 'errorLog', icon: 'bug' }
      }
    ]
  },

  {
    id: 33,
    path: '/excel',
    component: 'layout/Layout',
    redirect: '/excel/export-excel',
    name: 'Excel',
    meta: {
      title: 'excel',
      icon: 'excel'
    },
    children: [
      {
        id: 34,
        path: 'export-excel',
        component: 'views/excel/export-excel',
        name: 'ExportExcel',
        meta: { title: 'exportExcel' }
      },
      {
        id: 35,
        path: 'export-selected-excel',
        component: 'views/excel/select-excel',
        name: 'SelectExcel',
        meta: { title: 'selectExcel' }
      },
      {
        id: 36,
        path: 'export-merge-header',
        component: 'views/excel/merge-header',
        name: 'MergeHeader',
        meta: { title: 'mergeHeader' }
      },
      {
        id: 37,
        path: 'upload-excel',
        component: 'views/excel/upload-excel',
        name: 'UploadExcel',
        meta: { title: 'uploadExcel' }
      }
    ]
  },

  {
    id: 38,
    path: '/zip',
    component: 'layout/Layout',
    redirect: '/zip/download',
    alwaysShow: true,
    meta: { title: 'zip', icon: 'zip' },
    children: [
      {
        id: 39,
        path: 'download',
        component: 'views/zip/index',
        name: 'ExportZip',
        meta: { title: 'exportZip' }
      }
    ]
  },

  {
    id: 40,
    path: '/pdf',
    component: 'layout/Layout',
    redirect: '/pdf/index',
    children: [
      {
        id: 41,
        path: 'index',
        component: 'views/pdf/index',
        name: 'PDF',
        meta: { title: 'pdf', icon: 'pdf' }
      }
    ]
  },
  {
    id: 42,
    path: '/pdf/download',
    component: 'views/pdf/download',
    hidden: true
  },

  {
    id: 43,
    path: '/theme',
    component: 'layout/Layout',
    redirect: 'noRedirect',
    children: [
      {
        id: 44,
        path: 'index',
        component: 'views/theme/index',
        name: 'Theme',
        meta: { title: 'theme', icon: 'theme' }
      }
    ]
  },

  {
    id: 45,
    path: '/clipboard',
    component: 'layout/Layout',
    redirect: 'noRedirect',
    children: [
      {
        id: 46,
        path: 'index',
        component: 'views/clipboard/index',
        name: 'ClipboardDemo',
        meta: { title: 'clipboardDemo', icon: 'clipboard' }
      }
    ]
  },

  {
    id: 47,
    path: '/i18n',
    component: 'layout/Layout',
    children: [
      {
        id: 48,
        path: 'index',
        component: 'views/i18n-demo/index',
        name: 'I18n',
        meta: { title: 'i18n', icon: 'international' }
      }
    ]
  },

  // {
  //   path: 'external-link',
  //   component: 'layout/Layout',
  //   children: [
  //     {
  //       path: 'https://github.com/PanJiaChen/vue-element-admin',
  //       meta: { title: 'externalLink', icon: 'link' }
  //     }
  //   ]
  // },

  { path: '*', redirect: '/404', hidden: true }
]
