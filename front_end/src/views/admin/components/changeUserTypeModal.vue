<template>
    <a-modal
        :visible="changeUserTypeModalVisible"
        title="变更用户职位"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-Form :form="form">
            <a-form-item>
                <a-select
                        size="large"
                        v-model="userType"
                        placeholder="请选择用户类型"
                        v-decorator="[
                            'userType',
                    { rules: [{ required: true, message: '宁还没选择用户类型喔' }] }]"
                >
                    <a-select-option value='HotelManager' :key="userType">酒店管理人员</a-select-option>
                    <a-select-option value='WebMarketer' :key="userType">网站营销人员</a-select-option>
                    <a-select-option value='Client' :key="userType">普通用户</a-select-option>
                </a-select>
            </a-form-item>
        </a-Form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'changeUserTypeModal',
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
            userType:""
        }
    },
    computed: {
        ...mapGetters([
            'changeUserTypeModalVisible',
        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'changeUserTypeModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_changeUserTypeModalVisible',
            'set_changeUserTypeParams',
        ]),
        ...mapActions([
            'changeUserType',
        ]),
        cancel() {
            this.set_changeUserTypeModalVisible(false)
        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                //alert(this.userType)
                if (!err) {
                    const data = {
                        userType: this.userType
                    }
                    this.set_changeUserTypeParams(data);
                    this.changeUserType()
                }
            });
        },
    }
}
</script>