package com.example.vote.entity;

import lombok.Data;

/**
 * @ClassName: UserVoteInfo
 * @Description: 用户投票关联表
 * @Date : 2020-07-23 22:48
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
public class UserVoteInfo {

    /**
     * 用户uuid
     */
    private String userUuid;

    /**
     * 用户姓名 可匿名
     */
    private String userName;

    /**
     * 投票uuid
     */
    private String voteUuid;

}
