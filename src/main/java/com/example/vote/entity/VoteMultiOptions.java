package com.example.vote.entity;

import lombok.Data;

/**
 * @ClassName: VoteMultiOptions
 * @Description: 投票选项 多项
 * @Date : 2020-07-28 10:16
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
public class VoteMultiOptions {

    /**
     * 投票uuid
     */
    private String voteUuid;

    /**
     * 选项uuid
     */
    private String[] optionsUuid;


}



