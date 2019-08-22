import axios from 'axios'
import { Message, Loading } from 'element-ui';
import router from './router'

let loading        //定义loading变量

function startLoading() {    //使用Element loading-start 方法
    loading = Loading.service({
        lock: true,
        text: '加载中...',
        background: 'rgba(0, 0, 0, 0.7)'
    })
}
function endLoading() {    //使用Element loading-close 方法
    loading.close()
}

// 请求拦截  设置统一header
axios.interceptors.request.use(config => {
    // 加载
    startLoading()
    if (sessionStorage.sessionId)
        config.headers.token = sessionStorage.sessionId;
    return config
}, error => {
    return Promise.reject(error)
})

// 响应拦截  
axios.interceptors.response.use(response => {
    endLoading()
    let {code} = response.data;
    if(code ===  403 ||code === undefined){
        router.push('/login');
    }
    return response
}, error => {
    // 错误提醒
    endLoading()
    Message.error(error.response.data)

    return Promise.reject(error)
})

export default axios