<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 240px">
                        <img
                            alt="example"
                            src="@/assets/cover.jpeg"
                            slot="cover"
                            referrerPolicy="no-referrer"
                            />
                    </a-card>
                    <div class="info">
                        <div class="items" v-if="currentHotelInfo.name">
                            <span class="label">酒店名称：</span>
                            <span class="value">{{ currentHotelInfo.name }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.address">
                            <span class="label">地址</span>
                            <span class="value">{{ currentHotelInfo.address }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.rate">
                            <span class="label">评分:</span> 
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.hotelStar">
                            <span class="label">星级:</span> 
                            <a-rate style="font-size: 15px" :value="calculateStar(currentHotelInfo.hotelStar)" disabled allowHalf/>
                        </div>
                        <div class="items" v-if="currentHotelInfo.description">
                            <span class="label">酒店简介:</span> 
                            <span class="value">{{ currentHotelInfo.description }}</span>
                        </div>
                    </div>
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <RoomList :rooms="currentHotelInfo.rooms"></RoomList>
                    </a-tab-pane>
                    <a-tab-pane tab="酒店评价" key="2">
                        <div>
                            <a-list>
                                <a-list-item :key="index" v-for="(line, index) in hotelCommentsList">
                                    <div>
                                        <a-rate style="font-size: 10px;display: block" :value="line.commentScore" disabled allowHalf/>
                                        <a-avatar size="large" icon="user" style="background: green;display: block;margin: auto"></a-avatar>
                                    </div>
                                        <a-comment
                                            :author="line.userName"
                                            :content="line.comment"
                                            :datetime="line.checkOutTime"
                                    >
                                    </a-comment>
                                    <!--{{index+1}}- : {{line.commentScore}}分-->
                                </a-list-item>
                            </a-list>
                            <!--todo 有空把这里写得漂亮点-->
                        </div>
                    </a-tab-pane>
                    <a-tab-pane tab="酒店详情" key="3">
                        <div>
                            <a-text><a-icon type="home" theme="twoTone" /> 地址：{{currentHotelInfo.address}}</a-text><br>
                            <a-text><a-icon type="shop" theme="twoTone" /> 商圈：{{currentHotelInfo.bizRegion}}</a-text><br>
                            <a-text><a-icon type="phone" theme="twoTone" /> 联系电话：{{currentHotelInfo.phoneNum}}</a-text><br>
                        </div>
                    </a-tab-pane>
                </a-tabs>
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
    }

}
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }
    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 20px;
            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                .label{
                    margin-right: 10px;
                    font-size: 18px;
                }
                .value {
                    margin-right: 15px
                }
            }
        }
    }
</style>