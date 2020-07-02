<template>
   <div>
    <a-modal
        :visible="couponVisible"
        title="优惠策略"
        width="900px"
        :footer="null"
        @cancel="cancel"
    >
        <!-- 这里是模态框内容区域，请编写列表代码与添加策略按钮 -->

        <!--1.按钮-->
        <a-form-item style="margin-left: 80.67%">
            <a-button
                    size="large"
                    type="primary"
                    @click="addCoupon"
                    v-text="addCouponButtonText"
            ></a-button>
        </a-form-item>

        <!--2.表格-->
        <a-table
                :columns="columns"
                :dataSource="couponList"
                bordered
        >
            <span slot="couponType" slot-scope="record">
                <!--1生日特惠 2多间特惠 3满减优惠 4限时优惠； 感觉后端应该统一整成枚举量，拿一个数字来记录是什么东西-->
                <a-button type="danger" size="small" v-if="record===1">生日特惠</a-button>
                <a-button type="danger" size="small" v-if="record===2">多间特惠</a-button>
                <a-button type="danger" size="small" v-if="record===3">满减优惠</a-button>
                <a-button type="danger" size="small" v-if="record===4">限时优惠</a-button>
            </span>
        </a-table>


    </a-modal>
    <AddCoupon></AddCoupon>
   </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddCoupon from './addCoupon'
import hotelList from "../../hotel/hotelList";
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
];
export default {
    name: 'coupon',
    data() {
        return {
            columns,
            addCouponButtonText:'+ 添加优惠按钮',//怎么设置多个空格啊
        }
    },
    components: {
        AddCoupon,
    },
    computed: {
        ...mapGetters([
            'couponVisible',
            'couponList',
        ])
    },
    methods: {
        ...mapMutations([
            'set_addCouponVisible',
            'set_couponVisible',
        ]),
        ...mapActions([
            'getHotelCoupon'
        ]),
        cancel() {
            this.set_couponVisible(false);
            this.getHotelCoupon();
        },
        addCoupon() {
            this.set_addCouponVisible(true);
            this.set_couponVisible(false);
        },
    },
}
</script>
<style scoped>

</style>