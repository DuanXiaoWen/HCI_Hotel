import { axios } from '../utils/request'
const api = {
    couponPre: '/api/coupon'
}
export function addCouponAPI(data) {
    return axios({
        url: `${api.couponPre}/addCoupon`,
        method: 'POST',
        data,
    })
}


export function orderMatchCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}


export function hotelAllCouponsAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}