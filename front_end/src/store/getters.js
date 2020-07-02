const getters = {
  //user
  token: state => state.user.token,
  userId: state => state.user.userId,
  commentVisible:state => state.user.commentVisible,
  orderActive:state => state.user.orderActive,
  userInfo: state => state.user.userInfo,
  userOrderList: state => state.user.userOrderList,
  hotelListLoading: state => state.hotel.hotelListLoading,
  hotelList: state => state.hotel.hotelList,
  currentHotelInfo: state => state.hotel.currentHotelInfo,
  currentHotelId: state => state.hotel.currentHotelId,
  orderModalVisible: state => state.hotel.orderModalVisible,
  currentOrderRoom: state => state.hotel.currentOrderRoom,
  orderMatchCouponList: state => state.hotel.orderMatchCouponList,
  hotelCommentsList: state => state.hotel.hotelCommentsList,
  allRoomList: state => state.hotel.allRoomList,
  //admin
  managerList: state => state.admin.managerList,
  addManagerModalVisible: state => state.admin.addManagerModalVisible,
  addManagerParams: state => state.admin.addManagerParams,
  changeUserTypeModalVisible: state => state.admin.changeUserTypeModalVisible,
  changeUserTypeParams: state => state.admin.changeUserTypeParams,
  //hotelManager
  orderList: state => state.hotelManager.orderList,
  addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
  addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
  mgrHotelList: state => state.hotelManager.mgrHotelList,
  orderVisible: state => state.hotelManager.orderVisible,
  mgrOrderList: state => state.hotelManager.mgrOrderList,
  manageHotelVisible: state => state.hotelManager.manageHotelVisible,
  couponVisible: state => state.hotelManager.couponVisible,
  addCouponVisible: state => state.hotelManager.addCouponVisible,
  activeHotelId: state => state.hotelManager.activeHotelId,
  couponList: state => state.hotelManager.couponList,
  }
  
  export default getters