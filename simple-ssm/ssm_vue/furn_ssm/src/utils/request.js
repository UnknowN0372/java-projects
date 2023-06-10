import axios from "axios";

const request = axios.create({
    timeout: 500000
})
// request拦截器
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8'
    return config
}, error => {
    return Promise.reject(error)
})
// response 拦截器
request.interceptors.response.use(req => {
        let res = req.data;
        // 直接将返回的对象中的data拿出来
        // 如果是文件,直接返回
        if (req.config.responseType === 'blob') {
            return res;
        }
        // 如果是字符串,直接json化
        if (req.config.responseType === 'string') {
            res = res ? JSON.stringify(res) : res;
        }
        return res
    }, error => {
        console.log('error', error)
        return Promise.reject(error)
    }
)
export default request
