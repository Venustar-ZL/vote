package com.example.vote.entity;

import lombok.Data;

/**
 * @ClassName: VoteVo
 * @Description: 投票
 * @Date : 2020-07-23 18:38
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
public class VoteVo {

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
     * 选项
     */
    private String[] options;

}
