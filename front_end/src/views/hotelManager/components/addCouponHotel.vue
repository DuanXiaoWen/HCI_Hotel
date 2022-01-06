currentCoupon
<template>
    <div class="addCouponHotel-wrapper">
        <a-modal
                :visible="addCouponHotelVisible"
                title="关联酒店"
                width="900px"
                :footer="null"
                @cancel="cancel"
        >
        <a-table
                :columns="columns1"
                :dataSource="mgrHotelList"
                :pagination="{ pageSize: 6 }"
                bordered
        >
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" @click="selected(record)">关联酒店</a-button>
                    </span>
        </a-table>
            <div style="width: 100%; text-align: right">
                <a-button @click="cancel">取消</a-button>
            </div>
        </a-modal>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
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
            title: '简介',
            dataIndex: 'description',
        },
        {
            title: '操作',
            dataIndex: 'id',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
    export default {
        name: 'addCouponHotel',
        data() {
            return {
                columns1
            }
        },
        computed: {
            ...mapGetters([
                'mgrHotelList',
                'currentCoupon',
                'addCouponHotelVisible',
            ])
        },
        methods: {
            ...mapMutations([
                'set_addCouponHotelVisible',
            ]),
            ...mapActions([
                'addHotelCoupon'
            ]),
            selected(record) {
                console.log(this.currentCoupon)
                let data = {
                    description: this.currentCoupon.description,
                    name: this.currentCoupon.couponName,
                    type: this.currentCoupon.couponType,
                    hotelId: record,
                    discount: this.currentCoupon.discount,
                    discountMoney: this.currentCoupon.discountMoney,
                }
                if (data.type > 1)
                    data.target = this.currentCoupon.targetMoney
                if (data.type === 4) {
                    data.startTime=this.currentCoupon.startTime
                    data.endTime=this.currentCoupon.endTime
                }
                this.addHotelCoupon(data);
                this.set_addCouponHotelVisible(false);
            },
            cancel() {
                this.set_addCouponHotelVisible(false);
            },
        },
    }
</script>
<style scoped>

</style>