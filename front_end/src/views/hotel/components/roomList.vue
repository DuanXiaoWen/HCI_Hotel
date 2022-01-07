<template>
    <div class="room-list">
        <div class="filter">

        </div>
        <div class="order-pad">
        <div style="width: 360px">
        <a-card style="padding:15px">
            <img
                    alt="example"
                    src="@/assets/cover.jpeg"
                    slot="cover"
                    referrerPolicy="no-referrer"
                    width="330px"
                    height="200px"
            />
        </a-card>
        </div>
        <div class="order-info">
            <a-radio-group
                    style="margin-top: 15px"
                    v-model="currentId"
                    value="currentId"
                    @change="handleRoomChange"
            >
                <a-radio-button v-for="(item, index) in rooms" v-bind:key="index" :value="index">
                    {{item.roomType}}
                </a-radio-button>
            </a-radio-group>
            <div style="color:#FF9966">
                <span style="font-size: 40px">￥</span>
                <span style="font-size: 60px">{{currentRoom.price}}</span>
            </div>
            <div style="margin-bottom: 21px">
                <span style="font-size: 18px">{{description}}</span>
            </div>
            <div><a-button type="primary" @click="orderRoom">预订</a-button></div>
        </div>
        </div>
        <OrderModal></OrderModal>
    </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import OrderModal from './orderModal'
const columns = [
    {  
      title: '房型',
      dataIndex: 'roomType',
      key: 'roomType',
    },
    {
      title: '床型',
      dataIndex: 'bedType',
      key: 'bedType',
    },
    {
      title: '早餐',
      dataIndex: 'breakfast',
      key: 'breakfast',
    },
    {
      title: '入住人数',
      key: 'peopleNum',
      dataIndex: 'peopleNum',
    },
    {
      title: '房价',
      key: 'price',
      dataIndex: 'price',
      scopedSlots: { customRender: 'price'}
    },
    {
      title: 'Action',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
export default {
    name:'roomList',
    props: {
        rooms: {
            type: Array
        }
    },
    data() {
        return {
            columns,
            currentRoom: {},
            currentId: 0,
            description: ''
        }
    },
    components: {
        OrderModal
    },
    computed: {
        ...mapGetters([
            'orderModalVisible'
        ])
    },
    mounted() {
    },
    watch: {
        rooms (val) {
            this.currentRoom = this.rooms[0];
            this.setDescription();
        }
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_currentOrderRoom'
        ]),
        ...mapActions([

        ]),
        orderRoom() {
            this.set_currentOrderRoom(this.currentRoom)
            this.set_orderModalVisible(true)
        },
        setDescription() {
            if (this.currentRoom.roomType === '大床房') {
                this.description = '单人床，无早餐'
            } else if (this.currentRoom.roomType === '双床房') {
                this.description = '两张单人床，无早餐'
            } else if (this.currentRoom.roomType === '家庭房') {
                this.description = '双人床，有早餐'
            } else {
                this.description = ''
            }
        },
        handleRoomChange(e) {
            this.currentId = e.target.value;
            const te = this.rooms[this.currentId];
            this.currentRoom = te;
            this.setDescription();
        }
    },
}
</script>
<style scoped lang="less">
    .order-pad {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        width: 1590px;
        .order-info {
            display: flex;
            flex-direction: column;
            margin-left: 30px;
        }
    }
</style>