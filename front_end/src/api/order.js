import { axios } from '../utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function managedHotelOrdersAPI(hotelId) {
    return axios({
        url: `${api.orderPre}/${hotelId}/allOrders`,
        method: 'GET',

    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}
export function commentAPI(data) {
    return axios({
        url:`${api.orderPre}/addComment`,
        method:`POST`,
        data
    })
}
export function getCommentByOrderIdAPI(orderId) {
    return axios({
        url:`${api.orderPre}/${orderId}/getCommentByOrderId`,
        method:`GET`,
    })
}
export function getHotelCommentAPI(hotelId) {
    return axios({
        url: `${api.orderPre}/${hotelId}/getHotelComment`,
        method: `GET`
    })
}
export function checkInAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/checkIn`,
        method: 'GET',
    })
}
export function checkOutAPI(orderId) {

    return axios({
        url: `${api.orderPre}/${orderId}/checkOut`,
        method: 'GET',
    })
}
