<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="员工管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addManager"><a-icon type="plus" />添加员工</a-button>
                </div>
                <a-table
                    :columns="columns"
                    :dataSource="managerList"
                    bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="action" slot-scope="text, record">
                        <a-button type="primary"  @click="changeUserType(record)">变更职位</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                                title="确定想删除该员工吗？"
                                @confirm="delUser(record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" >删除员工</a-button>
                        </a-popconfirm>
                    </span>

                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="客户管理" key="2">

            </a-tab-pane>
        </a-tabs>
        <AddManagerModal></AddManagerModal>
        <ChangeUserTypeModal></ChangeUserTypeModal>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddManagerModal from './components/addManagerModal'
import ChangeUserTypeModal from './components/changeUserTypeModal'
const columns = [
    {  
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {  
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
    {
        title: '信用值',
        dataIndex: 'credit',
    },
    {
      title:'员工类型',
      dataIndex:'userType',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
export default {
    name: 'manageUser',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns,
            data: [],
            form: this.$form.createForm(this, { name: 'manageUser' }),
        }
    },
    components: {
        AddManagerModal,
        ChangeUserTypeModal
    },
    computed: {
        ...mapGetters([
            'addManagerModalVisible',
            'managerList'
        ])
    },
    mounted() {
      this.getManagerList()
    },
    methods: {
        ...mapActions([
            'getManagerList',
            'deleteUser'
        ]),
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_changeUserTypeModalVisible',
            'set_changeUserTypeParams',
        ]),
        addManager(){
            this.set_addManagerModalVisible(true)
        },
        changeUserType(user){
            //alert(user.id);
            this.set_changeUserTypeParams({id : user.id})
            this.set_changeUserTypeModalVisible(true)
        },
        delUser(record){
            this.deleteUser(record);
        }
    }
}
</script>
<style scoped lang="less">
    .manageUser-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>