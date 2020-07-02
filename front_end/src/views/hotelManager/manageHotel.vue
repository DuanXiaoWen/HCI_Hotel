<template>
    <div class="manageHotel-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addHotel"><a-icon type="plus" />添加酒店</a-button>
                </div>
                 <a-table
                    :columns="columns1"
                    :dataSource="mgrHotelList"
                    bordered
                >
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="addRoom(record)">录入房间</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="info" size="small" @click="showCoupon(record)">优惠策略</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="info" size="small" @click="manageOrder(record.id)">订单管理</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="info" size="small" @click="manageHotel(record)">信息修改</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="danger" size="small" @click="giveUpHotel(record)">酒店转让</a-button>


                    </span>
                </a-table>
            </a-tab-pane>

            
        </a-tabs>


        <a-modal
                :visible="giveUpVisible"
                title="转让酒店"
                cancelText="取消"
                okText="确定"
                @cancel="giveUpClear"
                @ok="giveUpSubmit"
        >
            <a-form :form="form" style="margin-top: 10px">
                <a-form-item label="被转让者的邮箱">
                    <a-input
                            size="large"
                            placeholder="请输入ta的邮箱"
                            v-decorator="['email', { rules: [{ required: true, message: '您还没输入ta的邮箱' }] }]"
                    />
                </a-form-item>
            </a-form>
        </a-modal>

        <AddHotelModal></AddHotelModal>
        <AddRoomModal></AddRoomModal>
        <Coupon></Coupon>
        <manageOrder :hotelId="id"></manageOrder>
        <ManageHotelModal :record="clickedRecord"></ManageHotelModal>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddHotelModal from './components/addHotelModal'
import AddRoomModal from './components/addRoomModal'
import Coupon from './components/coupon'
import manageOrder from "./components/manageOrder";
import ManageHotelModal from "./components/manageHotelModal";
const moment = require('moment')
const columns1 = [
    {
        title: '酒店名',
        dataIndex: 'name',
    },
    {
        title: '商圈',
        dataIndex: 'bizRegion',
    },
    {
        title: '地址',
        dataIndex: 'address',
    },
    {
        title: '酒店星级',
        dataIndex: 'hotelStar'
    },
    {
        title: '评分',
        dataIndex: 'rate',
    },
    {
        title: '简介',
        dataIndex: 'description',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];

export default {
    name: 'manageHotel',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns1,
            form: this.$form.createForm(this, { name: 'manageHotel' }),
            clickedRecord:{},
            giveUpVisible:false
        }
    },
    components: {
        AddHotelModal,
        AddRoomModal,
        Coupon,
        manageOrder,
        ManageHotelModal,
    },
    computed: {
        ...mapGetters([
            'userId',
            'orderList',
            'hotelList',
            'mgrHotelList',
            'managedOrders',
            'addHotelModalVisible',
            'addRoomModalVisible',
            'activeHotelId',
            'couponVisible',
            'orderVisible',
        ]),
    },
    async mounted() {

        await this.getMgrHotelList(this.userId)
    },
    methods: {
        ...mapMutations([
            'set_addHotelModalVisible',
            'set_addRoomModalVisible',
            'set_couponVisible',
            'set_activeHotelId',
            'set_orderVisible',
            'set_manageHotelVisible',
        ]),
        ...mapActions([
            'getMgrHotelList',
            'getAllOrders',
            'getHotelCoupon',
            'checkIn',
            'checkOut',
            'getManagedOrders',
            'getHotelById',
            'giveUpHotelFunc'
        ]),
        addHotel() {//没有改后端的
            this.set_addHotelModalVisible(true)
        },
        addRoom(record) {
            this.set_activeHotelId(record.id)
            this.set_addRoomModalVisible(true)
        },
        showCoupon(record) {
            this.set_activeHotelId(record.id)
            this.set_couponVisible(true)
            this.getHotelCoupon(this);
        },
        showOrder(record){
            alert('不是已经够详细了吗');
        },
        deleteHotel(){

        },
        deleteOrder(){

        },
        cIn(id){
            this.checkIn(id)
        },
        cOut(id){
            this.checkOut(id);

        },
        manageOrder(id) {
            this.set_activeHotelId(id)
            this.set_orderVisible(true)
            this.getManagedOrders()
        },
        manageHotel(record) {
            this.clickedRecord=record;
            // console.log(record)
            this.set_activeHotelId(record.id)
            this.set_manageHotelVisible(true)
        },
        giveUpHotel(record){
            this.set_activeHotelId(record.id)
            this.giveUpVisible=true
        },
        giveUpClear(){
            this.form.setFieldsValue({'email': ''});
            this.giveUpVisible = false;
        },
        giveUpSubmit(e){
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if(!err){
                    const data = {
                        'email':this.form.getFieldValue('email'),
                        'hotelId':this.activeHotelId
                    };
                    this.giveUpHotelFunc(data);
                    this.giveUpClear();
                }
            });

        }
    }
}
</script>
<style scoped lang="less">
    .manageHotel-wrapper {
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
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>