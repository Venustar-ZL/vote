package com.example.vote.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: VoteDetailInfo
 * @Description: 投票详情实体类
 * @Date : 2020-07-26 22:02
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
public class VoteDetailInfo {

    private String voteUuid;

    /**
     * 投票标题
     */
    private String voteTitle;

    /**
     *
     */
    private List<OptionsDetailInfo> optionsDetailInfo;

}
