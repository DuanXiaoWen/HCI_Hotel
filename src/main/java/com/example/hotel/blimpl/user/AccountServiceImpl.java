package com.example.hotel.blimpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败～";
    private final static String Wrong_Password = "密码错误";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        try {
            accountMapper.createNewAccount(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO deleteUser(int userId) {
        try{
            accountMapper.deleteAccount(userId);
            return ResponseVO.buildSuccess(true);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("删除失败");
        }
    }

    @Override
    public User login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getEmail());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserInfo(int id) {
        return accountMapper.getAccountById(id);
    }

    @Override
    public ResponseVO updateUserBaseInfo(int id,  String username, String phonenumber) {
        try {
            accountMapper.updateAccountBaseInfo(id, username, phonenumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO updateUserPwd(int id,  String oldPassword,String newPassword) {
        try {
            User user=accountMapper.getAccountById(id);
            if (null == user || !user.getPassword().equals(oldPassword)) {
//                System.out.println(oldPassword);
                return ResponseVO.buildFailure(Wrong_Password);
            }
            accountMapper.updateAccountPwd(id,newPassword);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO updateCredit(int id, double credit) {
        try {
            accountMapper.updateCredit(id, credit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO updateUserType(int id, UserType userType) {
        try {
            accountMapper.updateUserType(id, userType.name());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public User getUserByEmail(String email) {
        return accountMapper.getAccountByName(email);
    }
}
