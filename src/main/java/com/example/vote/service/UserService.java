package com.example.vote.service;


import com.example.vote.common.CommonResult;

/**
 * @ClassName: UserService
 * @Description: 用户业务类
 * @Date : 2020-07-18 23:32
 * @Author: ZhangLei
 * Version: 1.0
 **/
public interface UserService {

    /**
     * 注册用户
     * @param userName
     * @param password
     * @return
     */
    public CommonResult registerUser(String userName, String password);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    public CommonResult loginUser(String userName, String password);

}
