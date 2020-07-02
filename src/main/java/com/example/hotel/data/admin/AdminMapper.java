package com.example.hotel.data.admin;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface AdminMapper {

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addManager(User user);

    /**
     * @return 所有的酒店人、网站营销人
     */
    List<User> getAllManagers();
}
