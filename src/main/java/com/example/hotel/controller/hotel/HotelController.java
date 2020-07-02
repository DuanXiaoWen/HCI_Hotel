package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;


    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) throws ServiceException {

        hotelService.addHotel(hotelVO);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/all")
    public ResponseVO retrieveAllHotels(){
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        roomService.insertRoomInfo(hotelRoom);
        return ResponseVO.buildSuccess();//相当于return ResponseVO.buildSuccess(null);
    }

    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }

    @GetMapping("/allRoomList")
    public ResponseVO getAllRoomList() {
        return ResponseVO.buildSuccess(roomService.getAllRoomList());
    }

    @GetMapping("/{id}/hotelMgr")
    public ResponseVO retrieveMgrHotels(@PathVariable int id) {

        return ResponseVO.buildSuccess(hotelService.retrieveMgrHotels(id));
    }
    @PostMapping("/{id}/hotelInfo/update")
    public ResponseVO updateHotelInfo(@RequestBody HotelVO hotelVO, @PathVariable Integer id){
        return hotelService.updateHotelInfo(id,hotelVO.getName(),hotelVO.getAddress(),hotelVO.getBizRegion(),hotelVO.getDescription(),hotelVO.getHotelStar(),hotelVO.getPhoneNum());

    }

    @PostMapping("/{hotelId}/{email}/giveUpHotel")
    public ResponseVO giveUpHotel(@PathVariable(value = "hotelId")Integer hotelId,@PathVariable(value = "email")String email){
        return hotelService.giveUpHotel(hotelId,email);
    }

    @PostMapping("/{hotelId}/{accept}/acceptOrRefuse")
    public ResponseVO acceptOrRefuse(@PathVariable("hotelId")Integer hotelId, @PathVariable("accept")boolean accept){
        return hotelService.acceptOrRefuseHotel(hotelId,accept);
    }
}
