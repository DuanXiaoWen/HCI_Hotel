<template>
    <a-modal
        :visible="addCouponVisible"
        title="添加优惠策略"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'addCouponModal',
    data() {
        return {
            formItemLayout: {
                labelCol: {
                    xs: { span: 12 },
                    sm: { span: 6 },
                },
                wrapperCol: {
                    xs: { span: 24 },
                    sm: { span: 16 },
                },
            },
        }
    },
    computed: {
        ...mapGetters([
            'activeHotelId',
            'addCouponVisible',
        ])
    },
    beforeCreate() {
        // 表单名默认为“form”
        this.form = this.$form.createForm(this, { name: 'addCouponModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_addCouponVisible'
        ]),
        ...mapActions([
            // addHotelCoupon：添加酒店策略接口
            'addHotelCoupon'
        ]),
        cancel() {
            this.set_addCouponVisible(false)
        },
        changeType(v){
            if( v == '3') {

            }else{
                this.$message.warning('请实现')
            }
        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        // 这里添加接口参数
                    }
                    this.addHotelCoupon(data)
                }
            });
        },
    }
}
</script>