import { axios } from '../utils/request'

const api = {
    userPre: '/api/user'
}
export function loginAPI(data){
    return axios({
        url:`${api.userPre}/login`,
        method: 'POST',
        data
    })
}
export function registerAPI(data){
    return axios({
        url: `${api.userPre}/register`,
        method: 'POST',
        data
    })
}
export function getUserInfoAPI(id){
    return axios({
        url: `${api.userPre}/${id}/getUserInfo`,
        method: 'GET'
    })
}
export function updateBaseInfoAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/updateBaseInfo`,
        method: 'POST',
        data
    })
}
export function updatePasswordAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/updatePassword`,
        method: 'POST',
        data
    })
}
