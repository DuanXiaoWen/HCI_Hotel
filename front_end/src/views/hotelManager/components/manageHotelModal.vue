<template>
    <a-modal
            :visible="manageHotelVisible"
            title="酒店信息维护"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="酒店名称">
                <a-input
                        :placeholder="record.name"
                        v-decorator="['name', {
                            rules: [
                                { required: true, message: '请输入酒店名称' }
                            ]
                        }]"
                />
            </a-form-item>

            <a-form-item v-bind="formItemLayout" label="地址商圈">
                <a-select
                        :placeholder="record.bizRegion"
                        v-decorator="['bizRegion', {
                            rules: [
                                { required: true, message: '请选择商圈' }
                            ]
                        }]"
                >
                    <a-select-option value="XiDan">
                        西单
                    </a-select-option>
                    <a-select-option value="SanLiTun">
                        三里屯
                    </a-select-option>
                    <a-select-option value="WangFuJing">
                        王府井
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="酒店详细地址">
                <a-input
                        :placeholder="record.address"
                        v-decorator="['address', {
                            rules: [
                                { required: true, message: '请填写酒店详细地址' }
                            ]
                        }]"
                />
            </a-form-item>
            <a-form-item label="酒店星级">
                <a-radio-group initialvalue="a" v-decorator="['star', {
                    rules: [ {
                        required: true,message: '请选择酒店星级'
                    } ]
                }]">

                    <a-radio-button value="Three">
                        三星
                    </a-radio-button>
                    <a-radio-button value="Four">
                        四星
                    </a-radio-button>
                    <a-radio-button value="Five">
                        五星
                    </a-radio-button>
                </a-radio-group>
            </a-form-item>
            <a-form-item label="酒店电话">
                <a-input
                        :placeholder="record.phoneNum"
                        v-decorator="['phonenum', {
                    rules: [ {
                        required: true, message: 'Please input correct phone num'
                    } ]
                }]"
                        v-if="true"
                />
                <span v-else>
                            {{}}
                        </span>
            </a-form-item>
            <a-form-item label="酒店简介">
                <a-textarea
                        :placeholder="record.description"

                        v-decorator="['desc', {
                    rules: [ {
                        required: true, message: '请填写酒店的描述信息'
                    } ]        }]"

                        v-if="true"

                />
                <span v-else>
                            {{}}
                        </span>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'

    export default {
        name: 'manageHotelModal',
        props: {
            record: Object,
        },
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: {span: 12},
                        sm: {span: 6},
                    },
                    wrapperCol: {
                        xs: {span: 26},
                        sm: {span: 16},
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
                'manageHotelVisible',

            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'manageHotelModal'});

        },
        mounted() {
            //this.getResidences()
        },
        methods: {
            ...mapMutations([
                'set_manageHotelVisible'
            ]),
            ...mapActions([
                'submitManageHotelParams',

            ]),
            cancel() {
                this.set_manageHotelVisible(false)
            },
            changeStar(v) {
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            id: this.record.id,
                            name: this.form.getFieldValue('name'),
                            description: this.form.getFieldValue('desc'),
                            bizRegion: this.form.getFieldValue('bizRegion'),
                            address: this.form.getFieldValue('address'),
                            phoneNum: this.form.getFieldValue('phonenum'),
                            hotelStar: this.form.getFieldValue('star'),
                            rate: this.record.rate,
                            managerId: this.record.managerId,
                            rooms: this.record.rooms
                        }
                        this.submitManageHotelParams(data)
                    }
                });
            },
        }
    }
</script>
