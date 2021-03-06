import {
    addRoomAPI,
    addHotelAPI,
    mgrHotelListAPI,
    submitManageHotelParamsAPI,
    giveUpAPI,
    acceptOrRefuseAPI
} from '../../api/hotelManager'
import {
    checkInAPI,
    checkOutAPI,
    getAllOrdersAPI,
    managedHotelOrdersAPI,
} from '../../api/order'
import {
    addCouponAPI,
    hotelAllCouponsAPI,
    allCouponsAPI,
    deleteCouponAPI,
} from '../../api/coupon'
import { message } from 'ant-design-vue'
import {deleteUserAPI} from "../../api/admin";

const hotelManager = {
    state: {
        orderList: [],
        managedOrders: [],
        mgrHotelList: [],
        addHotelParams: {
            name: '',
            address: '',
            bizRegion:'XiDan',
            hotelStar:'',
            rate: 0,
            description:'',
            phoneNum:'',
            managerId:'',
        },
        addHotelModalVisible: false,
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },
        addRoomModalVisible: false,
        couponVisible: false,
        addCouponVisible: false,
        addCouponHotelVisible: false,
        orderVisible:false,
        manageHotelVisible: false,
        mgrOrderList: [],
        activeHotelId: 0,
        couponList: [],
        currentCoupon: {}
    },
    mutations: {
        set_orderList: function(state, data) {
            state.orderList = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },
        set_addHotelParams: function(state, data) {
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_activeHotelId: function(state, data) {
            state.activeHotelId = data
        },
        set_couponList: function(state, data) {
            state.couponList = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible =data
        },
        set_addCouponHotelVisible: function(state, data) {
            state.addCouponHotelVisible =data
        },
        set_orderVisible: function (state, data) {
            console.log('ordervisible')
            state.orderVisible = data
        },
        set_managedHotelOrders: function (state, data) {
            state.mgrOrderList = data
        },
        set_mgrHotelList: function (state, data) {
            state.mgrHotelList = data
            //console.log(data)
        },
        set_manageHotelVisible: function (state, data) {
            state.manageHotelVisible = data
        },
        set_currentCoupon: function (state, data) {
            state.currentCoupon = data
        }
    },
    actions: {
        getMgrHotelList: async ({state, commit}, id) => {
            const res = await mgrHotelListAPI(id)
            if (res) {
                commit('set_mgrHotelList', res)
            }
        },
        getAllOrders: async({ state, commit }) => {
            const res = await getAllOrdersAPI()
            if(res){
                commit('set_orderList', res)
            }
        },
        addHotel: async({ state, dispatch, commit } ,managerId) => {
            const res = await addHotelAPI(state.addHotelParams)
            if(res){
                dispatch('getHotelList')
                dispatch('getMgrHotelList',managerId)
                commit('set_addHotelParams', {
                    name: '',
                    address: '',
                    bizRegion:'XiDan',
                    hotelStar:'',
                    rate: 0,
                    description:'',
                    phoneNum:'',
                    managerId:'',
                })

                commit('set_addHotelModalVisible', false)
                message.success('????????????')
            }else{
                message.error('????????????')
            }
        },
        addRoom: async({ state, dispatch, commit }) => {
            const res = await addRoomAPI(state.addRoomParams)
            if(res===null){
                commit('set_addRoomModalVisible', false)
                commit('set_addRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                });
                message.success('????????????')
            }else{
                message.error('????????????')
            }
        },
        getHotelCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(state.activeHotelId);
            if(res) {
                // ?????????????????????????????????????????????????????????????????????couponList???
                commit('set_couponList',res);
                //commit ??????????????????????????????
            }
        },
        getAllCoupon: async({ state, commit }) => {
            const res = await allCouponsAPI();
            if(res) {
                // ?????????????????????????????????????????????????????????????????????couponList???
                commit('set_couponList',res);
                //commit ??????????????????????????????
            }
        },
        addHotelCoupon: async({ commit, dispatch }, data) => {

            console.log("enterAddCouponAPI");
            console.log("data",data);
            let res = await addCouponAPI(data);

            if(res){
                dispatch('getHotelCoupon');
                message.success("????????????");
            }else{
                message.error("????????????");
            }
        },
        deleteCoupon: async ({state, commit, dispatch}, data)=> {
            const res = await deleteCouponAPI(data);
            if(res){
                message.success('????????????');
                dispatch('getHotelCoupon');
            }
        },
        checkIn: async ({state, dispatch}, id) => {

            const res = await checkInAPI(id)
            if (res) {
                message.success('?????????')
                dispatch('getManagedOrders')
            }
        },
        checkOut: async({ state, dispatch }, id) => {

            let res = await checkOutAPI(id);

            if(res){
                message.success("?????????");
                dispatch('getManagedOrders');
            }else{
                message.error("fail");
            }
        },
        giveUpHotelFunc:async ({state, dispatch}, data)=>{
            let res= await giveUpAPI(data)
            console.log(res)
            if(res){
                message.success("????????????,???????????????ta??????");
            }
        },
        acceptOrRefuseFunc:async ({state, dispatch}, data)=>{
            let res = await acceptOrRefuseAPI(data)
            console.log(res)
            if(res){
                if(data.accept){
                    message.success("?????????");
                }
                else {
                    message.success("????????????");
                }
            }
        }
        ,
        getManagedOrders: async ({state, commit}) => {
            //console.log("activehotel: "+state.activeHotelId)
            const res = await managedHotelOrdersAPI(state.activeHotelId)
            //console.log(res)
            if (res) {
                commit('set_managedHotelOrders', res)
            }
        },
        //????????????????????????
        submitManageHotelParams: async ({commit, dispatch}, data) => {

            const res = await submitManageHotelParamsAPI(data)
            if (res) {
                dispatch('getMgrHotelList',data.managerId)
                console.log('??????????????????')
                commit('set_manageHotelVisible', false);
                message.success('????????????')
            } else {
                message.error('????????????');
            }
        },

    }
}
export default hotelManager