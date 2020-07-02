<template>
  <a-card hoverable class="hotelCard ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
    <img
      alt="example"
      src="@/assets/cover.jpeg"
      slot="cover"
      referrerPolicy="no-referrer"
    />
    <a-tooltip :title="hotel.title" placement="top">
      <a-card-meta :title="hotel.name">
        <template slot="description">
          <a-rate style="font-size: 15px" :value="getHotelStar(hotel.hotelStar)" disabled allowHalf/> {{hotel.rate}}分
          <br>
          <a-icon type="fire" theme="twoTone" twoToneColor="#FF4500" v-if="minMoney!=='本店没有合适的房间 >_<'"/> {{minMoney}}
        </template>
      </a-card-meta>
    </a-tooltip>
  </a-card>
</template>
<script>


export default {
  name:'',
  props: {
    hotel: {},
  },
  data() {
    return{
        minMoney:''
    }
  },
    watch:{
      hotel:{
          immediate: true,
          deep:true,
          handler (newValue) {
              let money = newValue.minRoomPrice;
              if(money === Number.MAX_VALUE){
                  this.minMoney = '本店没有合适的房间 >_<'
              }
              else {
                  this.minMoney = money + '元起！'
              }

          }
      }
    },
  methods: {
    getHotelStar(value){
      if(value==='Three')return 3;
      if(value==='Four')return 4;
      if(value==='Five')return 5;
      return 0;
    }
  }
}
</script>
<style scoped lang="less">
    .hotelCard {
        margin: 10px 10px;
        min-width: 180px;
        max-height: 350px;
        img {
          height: 250px;
        }
    }
</style>

<style lang="less">
.hotelCard{
  .ant-card-body{
    padding: 12px
  }
}

.ant-card-hoverable:hover {
  box-shadow: 0 2px 8px rgba(0,0,0,0.4)
}

</style>