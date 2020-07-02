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
        <a-form  v-bind:form="form">
            <a-form-item label="优惠券类型" v-bind="formItemLayout">
                    <a-select
                        @change="changeTypeFunction"
                        size="large"
                        placeholder="请选择优惠券类型"
                        v-decorator="[
                            'couponType',
                    { rules: [{ required: true, message: '宁还没选择优惠券类型' }] }]"
                >
                    <a-select-option @click="clear" :value="c.id" v-for="c in couponList" :key="c.id">{{c.name}}</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="优惠券名称" v-bind="formItemLayout">
                <a-input
                        size="large"
                        type="text"
                        placeholder="请输入优惠券名称"
                        v-decorator="[
                            'couponName',
                    {rules: [{ required: true, message: '宁还没输入优惠券名称' }], validateTrigger: 'blur'}
                  ]"
                >
                </a-input>
            </a-form-item>
            <a-form-item label="优惠券简介" v-bind="formItemLayout">
                <a-input
                        size="large"
                        type="text"
                        placeholder="请输入优惠券简介"
                        v-decorator="[
                            'description',
                    {rules: [{ required: true, message: '宁还没输入优惠券简介' }], validateTrigger: 'blur'}
                  ]"
                >
                </a-input>
            </a-form-item>
            <a-form-item label="目标金额" v-bind="formItemLayout" v-show="changeType==='3'">
                <a-input
                        size="large"
                        type="text"
                        placeholder="请输入目标金额"
                        v-decorator="[
                            'targetMoney',
                    {rules: [{ required: changeType==='3', message: '宁还没输入目标金额' }, { validator: isMoney}], validateTrigger: 'blur'}]"
                >
                </a-input>
            </a-form-item>
            <a-form-item label="目标间数" v-bind="formItemLayout" v-show="changeType==='2'"><!--show ‘字符串’-->
                <a-input
                        size="large"
                        type="text"
                        placeholder="请输入目标间数"
                        v-decorator="[
                            'targetMoney',
                    {rules: [{ required: (changeType==='2')/*和上面对应*/, message: '宁还没输入目标金间数' }, { validator: isMoney}], validateTrigger: 'blur'}]"
                >
                </a-input>
            </a-form-item>
            <!--<a-form-item label="优惠金额" v-bind="formItemLayout" v-show="testDiscountMoney()">    &lt;!&ndash;test用来判断是否需要“优惠金额”&ndash;&gt;
                <a-input
                        size="large"
                        type="text"
                        placeholder="请输入优惠金额"
                        v-decorator="[
                            'discountMoney',
                    {rules: [{ required: (testDiscountMoney()), message: '宁还没输入优惠金额' }, { validator: isMoney}], validateTrigger: 'blur'}
                  ]"
                >
                </a-input>
            </a-form-item>-->
            <a-form-item v-bind="formItemLayout" label="优惠方式">
                <a-select
                        @change="changeDiscountFunction"
                        size="large"
                        placeholder="请选择优惠方式"
                        v-decorator="[
                            'discountType',
                    { rules: [{ required: true, message: '宁还没选择优惠方式喔' }] }]"
                >
                    <a-select-option value='1' :key="discountType">折扣</a-select-option>
                    <a-select-option value='2' :key="discountType">立减</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="折扣值" v-show="testDiscount()">
                <a-input
                        size="large"
                        type="text"
                        placeholder="请输入值为 0-10 的折扣"
                        v-decorator="[
                            'discount',
                    {rules: [{ required: discountType==='1', message: '宁还没输入折扣值' }, { validator: isDiscount}], validateTrigger: 'blur'}
                  ]"
                >
                </a-input>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="立减金额" v-show="testTargetMoney()">
                <a-input
                        size="large"
                        type="text"
                        placeholder="请输入立减金额"
                        v-decorator="[
                            'subMoney',
                    {rules: [{ required: discountType==='2', message: '宁还没输入折扣' }, { validator: isMoney}], validateTrigger: 'blur'}
                  ]"
                >
                </a-input>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="限时时间" v-show="changeType==='4' ">
                <a-range-picker
                        format="YYYY-MM-DD"
                        v-decorator="[
                        'date',
                        {
                            rules: [{ required: changeType==='4' , message: '宁还没选择限时时间' }]
                        }
                    ]"
                        :placeholder="['起始日期','结束日期']"></a-range-picker>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AFormItem from "ant-design-vue/es/form/FormItem";
    const moment = require('moment')
    export default {
        name: 'addCouponModal',
        components: {AFormItem},
        data() {
            return {
                changeType:"",
                discountType:"",
                discount:'0',
                subMoney:'0',
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
                couponList:[
                    {
                        id:'1',
                        name:'生日特惠',
                    },
                    {
                        id:'2',
                        name:'多间优惠',
                    },
                    {
                        id:'3',
                        name:'满减优惠',
                    },
                    {
                        id:'4',
                        name:'限时优惠',
                    }
                ],
                form: this.$form.createForm(this),
            }
        },
        computed: {
            ...mapGetters([
                'activeHotelId',
                'addCouponVisible',
            ]),

        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'addCouponModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_couponVisible',
                'set_addCouponVisible',
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'addHotelCoupon'
            ]),
            isMoney(rule, value, callback) {//is正整数
                const regex = /^\+?[1-9][0-9]*$/;
                if (value === undefined || value === "") {
                    callback()
                }
                if (regex.test(value)) {
                    callback()
                } else {
                    callback(new Error('\t请输入一个正整数'));
                }
            },

            isDiscount(rule, value, callback) {    //折扣：0-10 的 double
                const regex =  /^[0-9]{1}([.]{1}[0-9]+){0,1}$/ ;
                if (value === undefined || value === "") {
                    callback()
                }
                if (regex.test(value)) {
                    callback()
                } else {
                    callback(new Error('\t请输入一个(0-10)的数字'));
                }
            },

            changeTypeFunction(event){
                //console.log(event); console.log(typeof event); 确实是string 类型 ...
                this.changeType=event;
            },

            changeDiscountFunction(event){    //限时优惠的折扣类型
                this.form.setFieldsValue({
                    'subMoney':'',
                    'discount':''
                });
                this.discountType=event;
            },

            testDiscount(){
                return (this.discountType==='1')
            },

            testTargetMoney(){
                return (this.discountType==='2')
            },

            clear(){    //清除已有内容
                this.discount=0;
                this.subMoney=0;
                this.discountType=null;
                this.changeType=null;
                this.form.setFieldsValue({
                    'couponName': '',
                    'couponType': '',
                    'description': '',
                    'targetMoney': '',
                    'discountMoney': '',
                    'startTime': '',
                    'endTime': '',
                    'discountType':'',
                    'subMoney':'',
                    'discount':'',
                    'date':null
                });
            },

            cancel() {
                this.clear();
                this.set_addCouponVisible(false);
            },

            handleSubmit(e) {
                e.preventDefault();

                this.form.validateFieldsAndScroll((err, values) => {
                    console.log("inSetValues");

                    if(this.discountType==='1'){
                        this.discount=this.form.getFieldValue('discount');
                    }else if(this.discountType==='2'){
                        this.subMoney=this.form.getFieldValue('subMoney');
                    }
                    if (!err) {
                        if(this.changeType==='1'){
                            this.birthdayCoupon();
                        }else if(this.changeType==='2' || this.changeType==='3'){
                            console.log("enterTargetCoupon");
                            this.targetCoupon();
                        }else{
                            this.timeCoupon();
                        }
                        this.clear();

                        this.set_addCouponVisible(false);//设置成不可见
                        this.set_couponVisible(true);
                    }
                });
            },
            birthdayCoupon(){
                const data={
                    description: this.form.getFieldValue('description'),
                    name: this.form.getFieldValue('couponName'),
                    type: this.form.getFieldValue('couponType'),
                    hotelId: this.activeHotelId,
                    discount: this.discount,
                    discountMoney: this.subMoney,
                };
                this.addHotelCoupon(data);
            },
            targetCoupon(){
                const data={
                    description: this.form.getFieldValue('description'),
                    name: this.form.getFieldValue('couponName'),
                    type: this.form.getFieldValue('couponType'),
                    hotelId: this.activeHotelId,
                    target:this.form.getFieldValue('targetMoney'),
                    discount: this.discount,
                    discountMoney: this.subMoney,
                };
                console.log("data",data);
                this.addHotelCoupon(data);
            },
            timeCoupon(){
                const data={
                    description: this.form.getFieldValue('description'),
                    name: this.form.getFieldValue('couponName'),
                    type: this.form.getFieldValue('couponType'),
                    hotelId: this.activeHotelId,
                    target:this.form.getFieldValue('targetMoney'),
                    discount: this.discount,
                    discountMoney: this.subMoney,
                    startTime:moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD')+"T00:00:00",
                    endTime:moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD')+"T23:59:59",
                };
                this.addHotelCoupon(data);
            },
        }
    }
</script>