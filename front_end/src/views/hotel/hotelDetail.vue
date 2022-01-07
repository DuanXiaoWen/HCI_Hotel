<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 360px">
                        <img
                            alt="example"
                            src="@/assets/cover.jpeg"
                            slot="cover"
                            referrerPolicy="no-referrer"
                            width="360px"
                            height="324px"
                            />
                    </a-card>
                    <div class="info">
                        <div class="items" style="font-size:70px; margin-top:10px; height:70px">
                        <span v-if="currentHotelInfo.name">
                            {{ currentHotelInfo.name }}
                        </span></div>
                        <div class="items"  style="font-size:18px; margin-top:10px; height:18px">
                        <span v-if="currentHotelInfo.address">
                            {{ currentHotelInfo.address }}
                        </span>
                        </div>
                        <div style="height:30px"></div>
                        <div class="items"  style="font-size:18px; height:30px">
                            <span v-if="currentHotelInfo.hotelStar">
                                <span class="label">星级:</span>
                                <a-rate style="font-size: 20px" :value="calculateStar(currentHotelInfo.hotelStar)" disabled allowHalf/>
                            </span>

                            <span style="width:50px"></span>
                            <span style="font-size: 40px; color:#FF9966; padding-bottom: 20px" v-if="currentHotelInfo.rate">
                                {{ currentHotelInfo.rate | numFilter }}
                            </span>
                            <span v-if="currentHotelInfo.rate">
                                分
                            </span>
                        </div>
                        <div class="items" style="font-size:18px; height:18px" v-if="currentHotelInfo.description">
                            酒店简介：
                        </div>
                        <div class="items" style="font-size:18px; margin-bottom:36px; margin-top:5px">
                            <p style="text-indent: 2em">{{ currentHotelInfo.description }}<p/>
<!--                            <p style="text-indent: 2em;">简介要很长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长<p/>-->
                        </div>
                        <div class="items" style="font-size:18px; height:18px" v-if="currentHotelInfo.phoneNum">
                            联系电话： {{currentHotelInfo.phoneNum}}
                        </div>
                    </div>
                </div>
                <div class="block">
                <RoomList :rooms="currentHotelInfo.rooms"></RoomList>
                </div>
                <div class="block">
                <div style="font-size:30px; height:30px">
                    住客评论
                </div>
                    <a-divider></a-divider>
                <div>
                    <a-list>
                        <a-list-item style="align-items: stretch; justify-content: flex-start;" :key="index" v-for="(line, index) in hotelCommentsList">
                            <div style="width:82px; padding-top: 15px">
                                <a-avatar size="large" icon="user" style="background: green;display: block;margin: auto"></a-avatar>
                                <a-rate style="font-size: 10px; display: block" :value="line.commentScore" disabled allowHalf/>
                            </div>
                            <div >
                            <a-comment
                                    :author="line.userName"
                                    :content="line.comment"
                                    :datetime="line.checkOutTime"
                            >
                            </a-comment>
                            </div>
                            <!--{{index+1}}- : {{line.commentScore}}分-->
                        </a-list-item>
                        <a-list-item style="align-items: stretch; justify-content: flex-start;" v-if="hotelCommentsList.length <= 0">
                            <div style="height:82px; padding-left:100px; font-size: 28px">
                                还没有评论哦
                            </div>
                        </a-list-item>
                        <!--{{index+1}}- : {{line.commentScore}}分-->
                    </a-list>
                </div>
                </div>
            </div>
        </a-layout-content>
    </a-layout>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import RoomList from './components/roomList'
import AListItem from "ant-design-vue/es/list/Item";
const commentColumns = [
    {title: '用户', dataIndex: 'userName'},
    {title: '评分', dataIndex: 'commentScore'},
    {title: '评价', dataIndex: 'comment'},
];
export default {
    name: 'hotelDetail',
    components: {
        AListItem,
        RoomList,
    },
    data() {
        return {
            commentColumns,
        }
    },
    computed: {
        ...mapGetters([
            'currentHotelInfo',
            'hotelCommentsList'
        ])
    },
    mounted() {
        this.set_currentHotelId(Number(this.$route.params.hotelId))
        this.getHotelById();
        console.log("getHotelById",this);
        console.log(this.currentHotelInfo);
        this.getHotelCommentsList();

    },
    beforeRouteUpdate(to, from, next) {
        this.set_currentHotelId(Number(to.params.hotelId))
        this.getHotelById()
        next()
    },
    methods: {
        ...mapMutations([
            'set_currentHotelId',
        ]),
        ...mapActions([
            'getHotelById',
            'getHotelCommentsList',
        ]),
        calculateStar(hotelStar){
            if(hotelStar==='Three')return 3;
            if(hotelStar==='Four')return 4;
            if(hotelStar==='Five')return 5;
            return 0;
        }
    },
    filters: {
        numFilter (value) {
            let realVal = ''
            if (!isNaN(value) && value!== '') {
                // 截取当前数据到小数点后两位
                realVal = parseFloat(value).toFixed(1)
            } else {
                realVal = '-'
            }
            return realVal
        }
    }

}
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 10px;
        margin: 50px 250px 50px 80px;
        //width: 1230px;
    }
    .hotel-info {
        //border:10px solid #eddec188;
        border:15px solid #ffffff88;
        background-color: white;
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 0px 0;
            display: flex;
            flex-direction: column;
            margin-left: 30px;
            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                line-height: 0px;
                .label{
                    margin-right: 10px;
                }
                .value {
                    margin-right: 15px
                }
            }
        }
    }
    .block {
        border:15px solid #ffffff88;
        background-color: white;
    }
</style>