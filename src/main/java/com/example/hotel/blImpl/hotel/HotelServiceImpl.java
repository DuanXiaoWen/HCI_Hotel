package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.User;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private final static String UPDATE_ERROR = "修改失败";

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoomService roomService;

    @Override
    public void addHotel(HotelVO hotelVO) throws ServiceException {
        User manager = accountService.getUserInfo(hotelVO.getManagerId());
        if(manager == null || !manager.getUserType().equals(UserType.HotelManager)){
            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotel.setHotelState(-1);
        hotel.setTotalCommentScore(0);
        hotel.setCommentNumber(0);
        hotelMapper.insertHotel(hotel);
    }


    @Override
    public int getRoomTotalNum(Integer hotelId, String roomType) {
        return roomService.getRoomTotalNum(hotelId,roomType);
    }

    @Override
    public List<HotelVO> retrieveHotels() {

        return hotelMapper.selectAllHotel();
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setCurNum(r.getCurNum());
            roomVO.setTotal(r.getTotal());
            return roomVO;
        }).collect(Collectors.toList());
        hotelVO.setRooms(roomVOS);

        return hotelVO;
    }

    @Override
    public void addComment(Integer hotelId, Integer score) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        hotelVO.setTotalCommentScore(hotelVO.getTotalCommentScore()+score);
        hotelVO.setCommentNumber(hotelVO.getCommentNumber()+1);
        hotelVO.setRate(((double)hotelVO.getTotalCommentScore())/((double)hotelVO.getCommentNumber()));
        Hotel hotel = this.hotelVO_TO_Hotel(hotelVO);
        hotelMapper.updateHotel(hotel);
    }
    @Override
    public  List<HotelVO> retrieveMgrHotels(@RequestParam int id){
        return hotelMapper.selectMgrHotel(id);
    };

    @Override
    public ResponseVO updateHotelInfo(Integer hotelId, String name, String address, String bizRegion, String description, String hotelStar, String phoneNum){
        try {
            hotelMapper.updateInfo(hotelId, name,address, bizRegion, description,hotelStar,phoneNum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO giveUpHotel(Integer hotelId, String email) {
        User user = accountService.getUserByEmail(email);
        if(user == null){
            return ResponseVO.buildFailure("email出错，没用找到这个人");
        }
        else if(user.getUserType()!=UserType.HotelManager){
            return ResponseVO.buildFailure("这个人不是酒店管理人员");
        }
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        if(hotelVO.getHotelState()>=0){
            return ResponseVO.buildFailure("该酒店已经在转让中状态");
        }
        Hotel hotel = hotelVO_TO_Hotel(hotelVO);
        hotel.setHotelState(user.getId());
        hotelMapper.updateHotel(hotel);
        return ResponseVO.buildSuccess(true);
    }



    @Override
    public ResponseVO acceptOrRefuseHotel(Integer hotelId, boolean accept) {
        Hotel hotel = hotelVO_TO_Hotel(hotelMapper.selectById(hotelId));
        if(hotel.getHotelState()==-1){
            return ResponseVO.buildFailure("该酒店不在转让中状态");
        }
        if(accept){
            hotel.setManagerId(hotel.getHotelState());
        }
        hotel.setHotelState(-1);
        hotelMapper.updateHotel(hotel);
        return ResponseVO.buildSuccess(true);
    }

    private Hotel hotelVO_TO_Hotel(HotelVO hotelVO){
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelVO,hotel);
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotel.setHotelName(hotelVO.getName());
        return hotel;
    }
}
