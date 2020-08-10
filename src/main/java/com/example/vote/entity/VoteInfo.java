package com.example.vote.entity;

import lombok.Data;

/**
 * @ClassName: VoteInfo
 * @Description: 投票实体类
 * @Date : 2020-07-22 16:31
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
public class VoteInfo {

    /**
     * 投票uuid
     */
    private String voteUuid;

    /**
     * 投票标题
     */
    private String voteTitle;

    /**
     * 投票描述
     */
    private String voteDescription;

    /**
     * 投票截止日期
     */
    private String deadline;

    /**
     * 是否匿名
     * 1 ： 匿名
     * 0 ： 不匿名
     */
    private Integer isAnonymous;

    /**
     * 投票类型
     * 1 ： 单项
     * 0 ： 多项
     */
    private Integer voteType;

    /**
     * 用户uuid
     */
    private String userUuid;

    /**
     * 投票数目
     */
    private Integer voteNum;

    /**
     * 创建人姓名
     */
    private String userName;

}
