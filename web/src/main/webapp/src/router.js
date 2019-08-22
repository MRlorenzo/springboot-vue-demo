import Vue from 'vue'
import Router from 'vue-router'
import Index from './views/Index.vue'
import Register from './views/Register'
import Login from './views/Login'
Vue.use(Router)

const router = new Router({
  //mode: 'history',  //history模式不好用。
  base: process.env.BASE_URL,
  routes: [
    { path: '/', redirect: '/index' },
    {
      path: '/index',
      name: 'index',
      component: Index,
      children: [
        { path: '', component: ()=>import('./views/Home') },
        { path: 'home', name: 'home', component: ()=>import('./views/Home') },
        { path:'baseList' , name:'baseList' , component:()=>import('./views/BaseList')}
      ]
    },
    { path: '/register', name: 'register', component: Register },
    { path: '/login', name: 'login', component: Login },
    { path: '*', name: '/404', component: ()=>import('./views/404') }
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (about.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    // }
  ]
})

// 添加路由守卫
router.beforeEach((to, from, next) => {
  
  const isLogin = sessionStorage.sessionId ? true : false;
  if (to.path == "/login" ) {
    next();
  } else {
    isLogin ? next() : next("/login");
  }
})

export default router;