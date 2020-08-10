package com.example.vote.entity;

import lombok.Data;

/**
 * @ClassName: OptionInfo
 * @Description: 选项实体类
 * @Date : 2020-07-23 13:52
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Data
public class OptionsInfo {

    /**
     * 选项uuid
     */
    private String optionsUuid;

    /**
     * 选项描述
     */
    private String optionsDescription;

    /**
     * 选项标记
     */
//    private String optionsMark;

    /**
     * 投票uuid
     */
    private String voteUuid;

    /**
     * 选项数目
     */
    private Integer optionsNum;


}
