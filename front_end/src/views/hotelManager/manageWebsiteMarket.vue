<template>
    <div class="manageWebsiteMarket-wrapper">

        <a-table
                :columns="columns"
                :dataSource="couponList"
                bordered
        >
            <span slot="couponType" slot-scope="record">
                <!--1生日特惠 2多间特惠 3满减优惠 4限时优惠； 感觉后端应该统一整成枚举量，拿一个数字来记录是什么东西-->
                <a-button type="primary" size="small" v-if="record===1">生日特惠</a-button>
                <a-button type="primary" size="small" v-if="record===2">多间特惠</a-button>
                <a-button type="primary" size="small" v-if="record===3">满减优惠</a-button>
                <a-button type="primary" size="small" v-if="record===4">限时优惠</a-button>
            </span>
            <span slot="action" slot-scope="record">
                <a-button type="danger" size="small" @click="removeCoupon(record)">删除优惠</a-button>
            </span>
        </a-table>

        <div style="width: 100%; text-align: right; margin:20px 0">
            <a-button type="primary" @click="addCoupon"><a-icon type="plus" />添加优惠</a-button>
        </div>
        <AddCoupon></AddCoupon>

    </div>
</template>



<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AddCoupon from './components/addCoupon'
    const columns = [
        // 这里定义列表头
        {
            title: '类型',
            dataIndex: 'couponType',
            scopedSlots: { customRender: 'couponType' }
        },
        {
            title: '折扣',
            dataIndex: 'discount',
        },
        {
            title: '优惠简介',
            dataIndex: 'description',
        },
        {
            title: '优惠金额',
            dataIndex: 'discountMoney'
        },
        {
            title: '操作',
            dataIndex: 'id',
            scopedSlots: { customRender: 'action' }
        },
    ];
    export default {
        name: "manageWebsiteMarket",
        data() {
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns,
                data: [],
                form: this.$form.createForm(this, { name: 'manageWebsiteMarket' }),
            }
        },
        components:{
            AddCoupon,
        },
        mounted() {
        },
        computed: {
            ...mapGetters([
                'couponVisible',
                'couponList',
            ]),
        },
        methods: {
            ...mapMutations([
                'set_couponVisible',
                'set_addCouponVisible',
                'set_activeHotelId',
            ]),
            ...mapActions([
                'getHotelCoupon',
                'getAllCoupon',
                'deleteCoupon',
            ]),
            addCoupon(){
                this.set_addCouponVisible(true);
            },
            removeCoupon(record) {
                this.deleteCoupon(record);
            },
            cancel() {
                this.getAllCoupon();
            },
        }
    }
</script>

<style scoped>

</style>