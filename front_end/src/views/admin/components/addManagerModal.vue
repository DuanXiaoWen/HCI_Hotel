<template>
    <a-modal
        :visible="addManagerModalVisible"
        title="添加用户"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
    <a-Form :form="form">
        <a-form-item>
                <a-select
                        size="large"
                        placeholder="请选择员工类型"
                        v-decorator="[
                            'userType',
                    { rules: [{ required: true, message: '宁还没选择员工类型喔' }] }]"
                >
                    <a-select-option value='HotelManager' >酒店管理人员</a-select-option>
                    <a-select-option value='WebMarketer' >网站营销人员</a-select-option>
                </a-select>
        </a-form-item>
            <a-form-item v-bind="formItemLayout" label="用户邮箱">
                <a-input
                    v-decorator="[
                        'email',
                        { rules: [{ required: true, type: 'email', message: '请输入邮箱' }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="密码">
                <a-input
                    v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码', }] }
                    ]"
                />
            </a-form-item >
    </a-Form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'addManagerModal',
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
            'addManagerModalVisible',
            'managerList',
        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'addManagerModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_addManagerParams',
        ]),
        ...mapActions([
            'getManagerList',
            'addManager',
        ]),
        cancel() {
            this.set_addManagerModalVisible(false)
        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        userType:this.form.getFieldValue('userType'),
                        email: this.form.getFieldValue('email'),
                        password: this.form.getFieldValue('password')
                    }
                    this.set_addManagerParams(data)
                    this.addManager()
                }
            });
        },
    }
}
</script>