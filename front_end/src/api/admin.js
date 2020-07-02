import { axios } from '../utils/request'
const api = {
    adminPre: '/api/admin'
}
export function getManagerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllManagers`,
        method: 'POST'
    })
}
export function addManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/addManager`,
        method: 'POST',
        data
    })
}
export function changeUserTypeAPI(data) {
    return axios({
        url:`${api.adminPre}/changeUserType`,
        method:'POST',
        data
    })
}
export function deleteUserAPI(data) {
    return axios({
        url:`${api.adminPre}/deleteUser`,
        method:'POST',
        data
    })
}