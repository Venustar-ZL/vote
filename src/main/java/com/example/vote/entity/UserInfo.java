package com.example.vote.entity;

import lombok.Data;

/**
 * @ClassName: UserInfo
 * @Description: 用户信息实体类
 * @Date : 2020-07-19 17:15
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
public class UserInfo {

    /**
     * 用户Uuid
     */
    private String userUuid;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

}
