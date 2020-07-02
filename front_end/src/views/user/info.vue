<template>
    <div class="info-wrapper">
        <Comment :order="order"></Comment>
        <a-badge :count="replyList.length+applicationList.length"></a-badge>
        <a-tabs>
            <a-tab-pane  tab="业务消息" key="3" v-if="userInfo.userType==='HotelManager'  ">
                <div>
                    <a-list>
                        <a-list-item :key="index" v-for="(line, index) in replyList">
                            {{line.userName}} 请求将 {{line.hotelName}} 转让给你
                            <a-button size="small" type="primary" @click="acceptOrRefuse(line,{'accept':true})" >同意</a-button>
                            <a-button size="small" type="danger" @click="acceptOrRefuse(line,{'accept':false})" >拒绝</a-button>
                        </a-list-item>
                    </a-list>
                    <a-list>
                        <a-list-item :key="index" v-for="(line, index) in applicationList">
                            你申请的将 {{line.hotelName}} 转让给 {{line.userName}} 正在等待回复
                            <a-button size="small" type="danger" @click="acceptOrRefuse(line,{'accept':false})">撤回请求</a-button>
                        </a-list-item>
                    </a-list>
                </div>
            </a-tab-pane>

            <a-tab-pane tab="我的信息" key="1">
                <a-form :form="form" style="margin-top: 30px">
                    
                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                            placeholder="请填写用户名"
                            v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.email }}</span>
                    </a-form-item>
                    
                    <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                            placeholder="请填写手机号"
                            v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.phoneNumber}}</span>
                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="modify">
                        <a-input
                            placeholder="请输入新密码"
                            v-decorator="['password', { rules: [{ required: true, message: '请输入新密码' }] }]"
                        />
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                        <a-button type="primary" @click="saveModify">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                            取消
                        </a-button>
                    </a-form-item>
                     <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                        <a-button type="primary" @click="modifyInfo">
                            修改信息
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="我的订单" key="2">
                <a-table
                    :columns="columns"
                    :dataSource="userOrderList"
                    bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text === 'BigBed'">大床房</span>
                        <span v-if="text === 'DoubleBed'">双床房</span>
                        <span v-if="text === 'Family'">家庭房</span>
                    </span>
                    <a-tag slot="orderState" color="blue" slot-scope="text">
                        {{ text }}
                    </a-tag>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showOrder(record)">详细查看</a-button>
                        <a-divider type="vertical"/>
                         <a-popconfirm
                                 title="你确定撤销该笔订单吗？"
                                 @confirm="confirmCancelOrder(record.id)"
                                 @cancel="cancelCancelOrder"
                                 okText="确定"
                                 cancelText="取消"
                                 v-if="record.orderState === '已预订'"
                         >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>
                        <a-button size="small" @click="comment(record)" v-show="record.orderState === '已退房'">评价</a-button>


                    </span>
                </a-table>
            </a-tab-pane>



        </a-tabs>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import Comment from './comment'
const columns = [
    {  
        title: '订单号',
        dataIndex: 'id',
    },
    {  
        title: '酒店名',
        dataIndex: 'hotelName',
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        scopedSlots: { customRender: 'roomType' }
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '房价',
        dataIndex: 'price',
    },
    {
        title: '状态',
        filters: [{ text: '已预订', value: '已预订' },{ text: '异常', value: '异常' }, { text: '已撤销', value: '已撤销' },{ text: '已执行', value: '已执行' }, { text: '已退房', value: '已退房' },{text: '已评价', value: '已评价'}],        onFilter: (value, record) => record.orderState.includes(value),
        dataIndex: 'orderState',
        scopedSlots: { customRender: 'orderState' }
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
    
  ];
export default {
    name: 'info',
    data(){
        return {
            order:{},
            modify: false,
            formLayout: 'horizontal',
            pagination: {},
            columns,
            data: [],
            form: this.$form.createForm(this, { name: 'coordinated' }),
            replyList:[],
            applicationList:[]

        }
    },
    components: {
        Comment,
    },
    computed: {
        ...mapGetters([
            'userId',
            'userInfo',
            'userOrderList',
            'commentVisible',
            'hotelList',
            'managerList'
        ])
    },
    async mounted() {
        await this.getUserInfo();
        await this.getUserOrders();
        if(this.userInfo.userType==='HotelManager'){
            await this.getManagerList();
            await this.getHotelList();
            console.log(this.managerList);
            console.log(this.hotelList)
            this.hotelList.forEach(aHotel =>{
                if(this.userId === aHotel.hotelState){//别人申请给你
                    let userName = this.managerList.find(value => value.id===aHotel.managerId).userName;
                    this.replyList.push({'hotelId':aHotel.id,'hotelName':aHotel.name,'userName':userName})
                }
                if(this.userId === aHotel.managerId && aHotel.hotelState>0){//你申请给别人
                    let userName = this.managerList.find(value => value.id===aHotel.hotelState).userName;
                    this.applicationList.push({'hotelId':aHotel.id,'hotelName':aHotel.name,'userName':userName})
                }
            })
        }
    },
    methods: {
        ...mapActions([
            'getUserInfo',
            'getUserOrders',
            'updateUserInfo',
            'cancelOrder',
            'getManagerList',
            'getHotelList',
            'acceptOrRefuseFunc'
        ]),
        ...mapMutations([
            'set_CommentVisible',
            'set_OrderActive',
        ]),
        saveModify() {
            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        userName: this.form.getFieldValue('userName'),
                        phoneNumber: this.form.getFieldValue('phoneNumber'),
                        password: this.form.getFieldValue('password')
                    };
                    this.updateUserInfo(data).then(()=>{
                        this.modify = false
                    })
                }
            });
        },
        modifyInfo() {
            setTimeout(() => {
                this.form.setFieldsValue({
                    'userName': this.userInfo.userName,
                    'phoneNumber': this.userInfo.phoneNumber,
                })
            }, 0);
            this.modify = true
        },
        cancelModify() {
            this.modify = false
        },
        confirmCancelOrder(orderId){
            this.cancelOrder(orderId)
        },
        cancelCancelOrder() {

        },
        showOrder(record){
            alert('不是已经够详细了吗');
        },
        comment(record){
            this.order = record;
            this.set_OrderActive(record);
            this.set_CommentVisible(true);
        },
        acceptOrRefuse(hotel, state){
            const data = {
                hotelId:hotel.hotelId,
                accept:state.accept
            };
            this.acceptOrRefuseFunc(data)
        }
    }
}
</script>
<style scoped lang="less">
    .info-wrapper {
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
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>