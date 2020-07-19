package com.example.vote.controller;

import com.example.vote.common.CommonResult;
import com.example.vote.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName: UserController
 * @Description: 用户控制类
 * @Date : 2020-07-18 23:17
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("/register")
    public String registerUser(String userName, String password) {
        CommonResult commonResult  = userService.registerUser(userName, password);
        if (!commonResult.isSuccess()) {
            return "";
        }
        return "";
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @GetMapping("/login")
    public String loginUser(String userName, String password) {

        return "";
    }

}
