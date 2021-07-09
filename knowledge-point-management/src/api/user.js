import request from '@/utils/request'

const baseURL = 'http://localhost:3398'
//const baseURL = 'http://121.4.182.177:3398'

export function login(data) {
  return request({
    url: baseURL + '/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: baseURL + '/logout',
    method: 'post'
  })
}
