package com.example.vote.entity;

import lombok.Data;

/**
 * @ClassName: VoteOptions
 * @Description: 投票选项 单项
 * @Date : 2020-07-26 10:49
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
public class VoteSingleOptions {

    /**
     * 投票uuid
     */
    private String voteUuid;

    /**
     * 选项uuid
     */
    private String optionsUuid;

    /**
     * 是否匿名
     * 1 ： 匿名
     * 0 ： 不匿名
     */
//    private Integer isAnonymous;

}
