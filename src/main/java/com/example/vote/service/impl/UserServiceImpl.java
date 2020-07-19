package com.example.vote.service.impl;

import com.example.vote.common.CommonResult;
import com.example.vote.dao.UserDao;
import com.example.vote.entity.UserInfo;
import com.example.vote.service.UserService;
import com.example.vote.utils.PasswordUtil;
import com.example.vote.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户业务实现类
 * @Date : 2020-07-18 23:32
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 注册用户
     * @param userName
     * @param password
     * @return
     */
    @Override
    public CommonResult registerUser(String userName, String password) {
        // 密码加密
        password = PasswordUtil.passwordEncode(password);
        String userUuid = UuidUtil.getUUID();
        userDao.addUser(userName, password, userUuid);
        return CommonResult.buildWithData(true, 1, "注册成功", null);
    }

    @Override
    public CommonResult loginUser(String userName, String password) {
        // 判断用户名是否正确
        List<UserInfo> userInfos = userDao.getAllUserInfo();
        boolean userNameFlag = false;
        for (UserInfo user : userInfos) {
            if (userName.equals(user.getUserName())) {
                userNameFlag = true;
                break;
            }
        }
        if (userNameFlag) {
            return CommonResult.buildWithData(false, 0, "用户名不存在", null);
        }

        // 判断密码是否正确
        UserInfo userInfo = userDao.getUserInfo(userName);
        String oldPassword = userInfo.getPassword();
        boolean passwordFlag =  PasswordUtil.matches(password, oldPassword);
        if (!passwordFlag) {
            return CommonResult.buildWithData(false, 0, "密码错误", null);
        }

        return CommonResult.buildWithData(true, 1, "登录成功", null);
    }
}
