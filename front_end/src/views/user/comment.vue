<template>
    <a-modal
            :visible="commentVisible"
            :title="'宁当前正在评价：'+(orderActive.hotelName)+'   '+(orderActive.roomType)+'房'"
            width="900px"
            @cancel="cancelComment"
            @ok="commentSubmit"
    >
        <a-form  v-bind:form="form" >
            <a-form-item label="评分">
                <a-rate  v-model="commentScore">
                </a-rate>
            </a-form-item>

            <a-form-item label="评论">
                <a-input
                        size="large"
                        type="text"
                        placeholder="请输入评论"
                        v-decorator="[
                        'commentWords',
                    {rules: [ { validator: this.notEmptyString }], validateTrigger: 'blur'}]"
                >
                </a-input>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AFormItem from "ant-design-vue/es/form/FormItem";
    export default {
        name: "comment",
        props:{
            order:{}
        },
        components: {AFormItem},
        data(){
          return{
              form: this.$form.createForm(this),
              commentScore:5,
          }
        },

        computed:{
            ...mapGetters([
                'commentVisible',
                'orderActive'
            ]),

        },

        methods:{
            ...mapActions([
                'commentAction'
            ]),
            notEmptyString(rule, value, callback){
                if (!value){
                    callback(new Error('宁还没输入评论内容'))
                }
                else if (!value.trim()) {
                    callback(new Error('你输入一堆空格干什么???'))
                }
                callback()
            },
            cancelComment(){
                this.set_CommentVisible(false);
                this.commentScore=5;
                this.form.setFieldsValue({'commentWords':''})
            },
            commentSubmit(e){
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if(!err) {
                        const data = {
                            id:this.orderActive.id,
                            hotelId:this.orderActive.hotelId,
                            comment:this.form.getFieldValue('commentWords'),
                            commentScore:this.commentScore,
                        };
                        this.commentAction(data);
                        this.cancelComment();
                    }
                });
            },
            ...mapMutations([
                'set_CommentVisible'
            ]),
        },

    }
</script>

<style scoped>

</style>