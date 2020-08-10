package com.example.vote.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: VoteOptionsInfo
 * @Description: 投票选项实体类
 * @Date : 2020-07-26 16:58
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
public class VoteOptionsInfo {

    private String voteUuid;

    private String voteTitle;

    private String voteDescription;

    private List<String> optionsDescription;

}
