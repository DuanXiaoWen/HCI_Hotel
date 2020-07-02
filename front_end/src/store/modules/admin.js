import {
    getManagerListAPI,
    addManagerAPI,
    deleteUserAPI,
    changeUserTypeAPI,
} from '../../api/admin'
import { message } from 'ant-design-vue'

const admin = {
    state: {
        managerList: [

        ],
        addManagerModalVisible: false,
        addManagerParams: {
            email:'',
            password:''
        },
        changeUserTypeModalVisible: false,
        changeUserTypeParams: {
            id:0,
            userType:''
        },
    },
    mutations: {
        set_managerList: function(state, data) {
            state.managerList = data
        },
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_changeUserTypeModalVisible: function(state, data) {
            state.changeUserTypeModalVisible = data
        },
        set_changeUserTypeParams: function(state, data) {
            state.changeUserTypeParams = {
                ...state.changeUserTypeParams,
                ...data,
            }
        },
    },
    actions: {
        getManagerList: async({ commit }) => {
            const res = await getManagerListAPI()
            if(res){
                commit('set_managerList', res)
            }
        },
        addManager: async({ state, commit, dispatch }) => {
            const res = await addManagerAPI(state.addManagerParams)
            if(res) {
                commit('set_addManagerParams',{
                    email:'',
                    password:''
                });
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
            }else{
                message.error('添加失败')
            }
        },
        deleteUser: async ({state, commit, dispatch}, data)=> {
            const res = await deleteUserAPI(data);
            if(res){
                message.success('删除成功');
                dispatch('getManagerList');
            }
        },
        changeUserType: async ({state, commit, dispatch})=> {
            //alert(state.changeUserTypeParams.id);
            const res = await changeUserTypeAPI(state.changeUserTypeParams);
            if (res) {
                commit('set_changeUserTypeParams',{
                    id:0
                });
                commit('set_changeUserTypeModalVisible', false)
                message.success('变更成功')
                dispatch('getManagerList')
            }else{
                message.error(res.message)
            }
        }
    }
}
export default admin