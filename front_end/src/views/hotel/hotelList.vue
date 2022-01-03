<template>
    <div class="hotelList">
        <div>
            <a-form
                    :label-col="labelCol"
                    :wrapper-col="wrapperCol"
                    class="form"
            >
                <a-form-item label="搜索" prop="search" class="search">
                    <a-input

                            v-model="searchNames"
                            type="search"
                            placeholder="根据酒店名搜索"
                            v-decorator="[
                        {validateTrigger: 'blur'}]">
                        <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                    </a-input>
                </a-form-item>

            </a-form>
        </div>
        <div>
            <a-form
                    :label-col="labelCol"
                    :wrapper-col="wrapperCol"
                    class="form"
                    layout="inline"
            >
                <a-form-item label="星级" prop="star" class="star" >
                    <a-checkbox-group v-model="screen.star">
                        <a-checkbox value="zero" name="star">
                            经济型
                        </a-checkbox>
                        <a-checkbox value="Three" name="star">
                            三星
                        </a-checkbox>
                        <a-checkbox value="Four" name="star">
                            四星
                        </a-checkbox>
                        <a-checkbox value="Five" name="star">
                            五星
                        </a-checkbox>
                    </a-checkbox-group>
                </a-form-item>
                <a-form-item label="房型" prop="roomType" class="roomType">
                    <a-checkbox-group v-model="screen.roomType">
                        <a-checkbox value="BigBed" name="roomType">
                            大床房
                        </a-checkbox>
                        <a-checkbox value="DoubleBed" name="roomType">
                            双床房
                        </a-checkbox>
                        <a-checkbox value="Family" name="roomType">
                            家庭房
                        </a-checkbox>
                    </a-checkbox-group>
                </a-form-item>
            </a-form>
        </div>
        <div>
            <a-form
                    :label-col="labelCol"
                    :wrapper-col="wrapperCol"
                    class="form"
                    layout="inline"
            >
                <a-form-item label="评分" class="rate">
                    <a-input-group compact>
                        <a-input style=" width: 70px; text-align: center" placeholder="最小值" v-model="screen.minRate"/>
                        <a-input
                                style=" width: 30px; border-left: 0; pointer-events: none; backgroundColor: #fff"
                                placeholder="~"
                                disabled
                        />
                        <a-input style="width: 70px; text-align: center; border-left: 0" placeholder="最大值" v-model="screen.maxRate"/>
                    </a-input-group>
                </a-form-item>
                <a-form-item label="价格" class="price">
                    <a-input-group compact>
                        <a-input style=" width: 70px; text-align: center" placeholder="最小值" v-model="screen.minPrice"/>
                        <a-input
                                style=" width: 30px; border-left: 0; pointer-events: none; backgroundColor: #fff"
                                placeholder="~"
                                disabled
                        />
                        <a-input style="width: 70px; text-align: center; border-left: 0" placeholder="最大值" v-model="screen.maxPrice"/>
                    </a-input-group>
                </a-form-item>

                <a-form-item label="排序方式" prop="sortKey" class="sortKey">
                    <a-select
                            v-model="sortKey"
                            v-decorator="[
                            { rules: [{ required: true, message: '请选择排序关键字' }] },
                        ]"
                            placeholder="请选择排序关键字"
                    >
                        <a-select-option value='default' :key="sortKey">默认</a-select-option>
                        <a-select-option value='comment' :key="sortKey">评分</a-select-option>
                        <a-select-option value='price' :key="sortKey">价格</a-select-option>
                        <a-select-option value='rank' :key="sortKey">星级</a-select-option>
                    </a-select>
                </a-form-item>
            </a-form>
        </div>
        <br>
        <br>
        <br>
        <a-layout>
            <a-layout-content style="min-width: 800px">
                <a-spin :spinning="hotelListLoading">
                    <div class="card-wrapper">
                        <HotelCard :hotel="item" v-for="item in currentHotelList" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
                        <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                        </div>
                        <!--<a-pagination showQuickJumper :total="hotelList.totalElements" :defaultCurrent="1" @change="pageChange"></a-pagination>a-pagination-->
                    </div>
                </a-spin>
            </a-layout-content>
        </a-layout>
    </div>
</template>
<script>
import HotelCard from './components/hotelCard'
import hotelDetail from './hotelDetail'
import { mapGetters, mapActions, mapMutations } from 'vuex'

export default {
  name: 'home',
  components: {
    HotelCard
  },
  data(){
    return {
        labelCol: { span: 4},
        wrapperCol: { span: 12 },
        emptyBox: [{name: 'box1'}, {name: 'box2'}, {name: 'box3'}],
        searchNames: "",
        screen: {
            star:[],
            roomType: [],
            minRate:'',
            maxRate:'',
            minPrice:'',
            maxPrice:''
        },
        sortKey: "default",
    }
  },
    async mounted() {
        this.getAllRoomList();
        this.allRoomList=this.$store.getters.allRoomList;
        await this.getHotelList().then(response=>{
            //hotelList是加载下来所有的酒店，currentHotelList是处理后的，比如排序、筛选；最后才分页（可选
            this.currentHotelList=[...response];
            this.currentHotelList.reverse();
            // console.log(this.currentHotelList)；
        })
    },
  computed: {
    ...mapGetters([
      'hotelList',
      'hotelListLoading',
      'allRoomList'
    ]),
      currentHotelList(){

          let getHotelStar = function (value) {
              if(value==='Three')return 3;
              if(value==='Four')return 4;
              if(value==='Five')return 5;
              return 0;
          }


          //hotelList是加载下来所有的酒店，currentHotelList是处理后的，比如排序、筛选；最后才分页（可选

        let {hotelList, searchNames} = this;
        let res = [...hotelList];
          res.forEach(item => this.$set(item, 'minRoomPrice', Number.MAX_VALUE));

          if (searchNames.trim()) {
              res = res.filter(hotel =>
                  searchNames.trim().split(/\s+/).every(item => hotel.name.indexOf(item) !== -1))
          }//首先，把酒店的列表根据名字筛选一次。
          //接下来，各种高级筛选、排序的东西都写在这里

          // console.log("名字",res);

          //筛选评分、星级
          let minRate=0;
          let maxRate=5;
          let selectedStar=[];
          if(this.screen.minRate.length!==0){
              minRate=this.screen.minRate;
          }
          if(this.screen.maxRate.length!==0){
              maxRate=this.screen.maxRate;
          }
          if(this.screen.star.length!==0){
              selectedStar=this.screen.star;
          }
          // console.log("selectedStar",selectedStar)

          res=res.filter(hotel=>{
              if(selectedStar.length===0){
                  return (hotel.rate >= minRate && hotel.rate <= maxRate);
              }else if(selectedStar.indexOf("zero")!==-1){
                  // console.log("selectedStar.indexOf(hotel.hotelStar)",selectedStar.indexOf(hotel.hotelStar))
                  return (hotel.rate >= minRate && hotel.rate <= maxRate && (selectedStar.indexOf(hotel.hotelStar)!==-1 || hotel.hotelStar.length===0));
              }else{
                  return (hotel.rate >= minRate && hotel.rate <= maxRate && (selectedStar.indexOf(hotel.hotelStar)!==-1));
              }

          });

          // console.log("评分",res);

          //筛选房型、价格
          //因为存在一个“有的酒店没有房间”的问题(即没有roomType和price)，所以弄得稍微复杂了一点 -.-|||
          let selectedRoom=[];
          let hasSelectedPrice=false;
          let minPrice=0;
          let maxPrice=99999999;
          if(this.screen.roomType.length!==0){
              selectedRoom=[...this.screen.roomType];
          }
          if(this.screen.minPrice.length!==0){
              minPrice=this.screen.minPrice;
              hasSelectedPrice=true;
          }
          if(this.screen.maxPrice.length!==0){
              maxPrice=this.screen.maxPrice;
              hasSelectedPrice=true;
          }

          res=res.filter(hotel=>{
              let room=[...this.allRoomList];
              if(selectedRoom.length===0 && !hasSelectedPrice){
                  return true;
              }else if(selectedRoom.length===0){
                  room=room.filter(room=>{
                      return (room.hotelId===hotel.id && room.price>=minPrice && room.price<=maxPrice)
                  });
              } else{
                  room=room.filter(room=>{
                      return (room.hotelId===hotel.id && selectedRoom.indexOf(room.roomType)!==-1 && room.price>=minPrice && room.price<=maxPrice)
                  });
              }
           return (room.length!==0);
          });
          // console.log("房型/价格",res);

          let rooms=[...this.allRoomList];
          if(selectedRoom.length>0){
              rooms = rooms.filter( a_room => selectedRoom.indexOf(a_room.roomType) !== -1 );
          }
          res.forEach(hotel => {
              rooms.forEach(room => {
                  if (room.hotelId===hotel.id  &&  room.price < hotel.minRoomPrice)
                      hotel.minRoomPrice = room.price;
              });
          });

          if (this.sortKey=="price") {
              // let minprice= []
              // res.forEach(hotel => {
              //     let rooms=[...this.allRoomList];
              //     rooms=rooms.filter(room => {
              //         return (room.hotelId===hotel.id);
              //     });
              //     let minp = 99999999;
              //     rooms.forEach(room => {
              //         if (room.price < minp)
              //             minp = room.price;
              //     });
              //     minprice.push({id: hotel.id, value :minp});
              // });
              // res=res.sort((a, b)=>{
              //     let indexa = minprice.findIndex(item =>{ if(item.id == a.id) return true; });
              //     let indexb = minprice.findIndex(item =>{ if(item.id == b.id) return true; });
              //     return (minprice[indexa].value <= minprice[indexb].value);
              // });
              res=res.sort(function (a, b) {
                  return (a.minRoomPrice - b.minRoomPrice);
              });
              // console.log("价格",res);
          } else if (this.sortKey==="comment") {
              res=res.sort(function (a, b) {
                  return (b.rate - a.rate);
              });
              // console.log("评分",res);
          } else if (this.sortKey==="rank") {
              res=res.sort(function (b, a) {
                  return (getHotelStar( a.hotelStar ) - getHotelStar(b.hotelStar));
                  //return ((a.hotelStar == 'five')||((a.hotelStar == 'four')&&(b.hotelStar != 'five'))||((a.hotelStar == 'three')&&(b.hotelStar == 'three')));
              });
              // console.log("星级",res);
          } else {
              // console.log("默认",res);
          }

        return res;
      }
  },
  methods: {
    ...mapMutations([
      'set_hotelListParams',
      'set_hotelListLoading',
      'set_allRoomList'
    ]),
    ...mapActions([
      'getHotelList',
      'getAllRoomList'
    ]),

    pageChange(page, pageSize) {
       alert('居然可以翻页了?');
       // console.log(this);
      const data = {
        pageNo: page - 1
      };
      this.set_hotelListParams(data)
      this.set_hotelListLoading(true)
      this.getHotelList()
    },
    jumpToDetails(id){
      this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
    },

  }
}
</script>
<style scoped lang="less">
    .hotelList {
        text-align: center;
        padding: 50px 0;
        .emptyBox {
            height: 0;
            margin: 10px 10px
        }
        .card-wrapper{
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            flex-grow: 3;
            min-height: 800px
        }
        .card-wrapper .card-item {
            margin: 30px;
            position: relative;
            height: 188px;
        }
    }
    .search{
        left :-50px;
        width:1600px;
        z-index: 0;
    }
    .star{
        left: 21px;
        width: 595px;
        z-index: 0;
    }
    .roomType{
        left: -125px;
        width: 500px;
        z-index: 0;
    }
    .rate{
        left: 97px;
        width: 350px;
        z-index: 0;
    }
    .price{
        left: -10px;
        width: 350px;
        z-index: 0;
    }
    .sortKey{
        left: -76px;
        width: 450px;
        z-index: 0;
    }
</style>