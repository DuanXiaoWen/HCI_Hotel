<template>
    <a-modal
        :visible="addHotelModalVisible"
        title="添加酒店"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="酒店名">
                <a-input
                    placeholder="请填写酒店名称"
                    v-decorator="['hotelName', { rules: [{ required: true, message: '请填写酒店名称' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店地址" v-bind="formItemLayout">
                <a-input
                    placeholder="请填写酒店地址"
                    v-decorator="['address', { rules: [{ required: true, message: '请填写酒店地址' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店星级" v-bind="formItemLayout">
                <a-select
                    v-decorator="[
                    'hotelStar', 
                    { rules: [{ required: true, message: '请选择酒店星级' }] }]"
                    @change="changeStar"
                >
                  <a-select-option value="Three">三星级</a-select-option>
                  <a-select-option value="Four">四星级</a-select-option>
                  <a-select-option value="Five">五星级</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="手机号" v-bind="formItemLayout">
                <a-input
                    placeholder="请填写手机号"
                    v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店简介" v-bind="formItemLayout">
                <a-input
                    type="textarea"
                    :rows="4"
                    placeholder="请填写酒店简介"
                    v-decorator="['description', { rules: [{ required: true, message: '请填写酒店简介' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'addHotelModal',
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
            'userId',
            'addHotelParams',
            'addHotelModalVisible'
        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'addHotelModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_addHotelParams',
            'set_addHotelModalVisible'
        ]),
        ...mapActions([
            'addHotel'
        ]),
        cancel() {
            this.set_addHotelModalVisible(false)
        },
        changeStar(v){

        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        name: this.form.getFieldValue('hotelName'),
                        description: this.form.getFieldValue('description'),
                        address: this.form.getFieldValue('address'),
                        phoneNum: this.form.getFieldValue('phoneNumber'),
                        hotelStar: this.form.getFieldValue('hotelStar'),
                        managerId: Number(this.userId)
                    }
                    this.set_addHotelParams(data)
                    this.addHotel()
                }
            });
        },
    }
}
</script>