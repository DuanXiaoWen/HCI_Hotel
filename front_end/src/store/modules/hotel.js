import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI,
    getAllRoomListAPI
} from '../../api/hotel'
import {
    reserveHotelAPI,
    getHotelCommentAPI
} from '../../api/order'
import {
    orderMatchCouponsAPI,
} from '../../api/coupon'

const hotel = {
    state: {
        hotelList: [
            
        ],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: true,
        currentHotelId: '',
        currentHotelInfo: {

        },
        orderModalVisible: false,
        currentOrderRoom: {

        },
        orderMatchCouponList: [

        ],
        hotelCommentsList:[

        ],
        allRoomList:[

        ]
    },
    mutations: {
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },
        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_orderMatchCouponList: function(state, data) {
            state.orderMatchCouponList = data
        },
        set_hotelCommentsList:function (state, data) {
            state.hotelCommentsList = data
        },
        set_allRoomList:function (state,data) {
            state.allRoomList = data
        }
    },

    actions: {
        getHotelList: async({commit, state}) => {
            const res = await getHotelsAPI()
            if(res){
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
            }
            return res;
        },
        getHotelById: async({commit, state}) => {
            console.log(state.currentHotelId)
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId
            })
            if(res){
                commit('set_currentHotelInfo', res)
            }
        },
        addOrder: async({ state, commit }, data) => {
            const res = await reserveHotelAPI(data)
            if(res){
                message.success('预定成功')
                commit('set_orderModalVisible', false)
            }
        },
        getOrderMatchCoupons: async({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI(data)
            if(res){
                commit('set_orderMatchCouponList', res)
            }
        },
        getHotelCommentsList: async ({state, commit}) => {
            const res = await getHotelCommentAPI(state.currentHotelId);
            if(res){
                commit('set_hotelCommentsList',res);
            }
        },
        getAllRoomList: async ({commit})=>{
            const res = await getAllRoomListAPI();
            if(res){
                commit('set_allRoomList',res);
            }
        }
    }
};

export default hotel