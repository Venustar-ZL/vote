package com.example.vote.service;

import com.example.vote.common.CommonResult;
import com.example.vote.entity.PageData;
import com.example.vote.entity.VoteOptionsInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: VoteService
 * @Description: 投票业务类
 * @Date : 2020-07-22 18:07
 * @Author: ZhangLei
 * Version: 1.0
 **/
public interface VoteService {

    /**
     * 获取所有的投票
     * @return
     */
    public CommonResult getAllVotes(String userUuid);

    /**
     * 添加投票
     * @param voteTitle
     * @param voteDescription
     * @param options
     * @param deadline
     * @param isAnonymous
     * @return
     */
    public CommonResult addVote(String voteTitle, String voteDescription, String[] options, String deadline, Integer isAnonymous, String userUuid, Integer voteType);

    /**
     * 参与投票
     * @param userUuid
     * @param voteUuid
     * @return
     */
    public CommonResult joinVote(String userUuid, String voteUuid);

    /**
     * 删除投票
     * @param voteUuid
     * @return
     */
    public CommonResult deleteVote(String voteUuid);

    /**
     * 给选项投票
     * @param userUuid
     * @param voteUuid
     * @param optionsUuid
     * @return
     */
    public CommonResult addSingleVote(String userUuid, String voteUuid, String optionsUuid);

    /**
     * 多项投票
     * @param userUuid
     * @param voteUuid
     * @param optionsUuid
     * @return
     */
    public CommonResult addMultiVote(String userUuid, String voteUuid, String[] optionsUuid);

    /**
     * 获取投票详情
     * @param userUuid
     * @param voteUuid
     * @return
     */
    public CommonResult getVoteInfo(String userUuid, String voteUuid);

    /**
     * 修改投票
     * @param voteOptionsInfo
     * @return
     */
    public CommonResult updateVote(VoteOptionsInfo voteOptionsInfo);

    /**
     * 获取投票详情
     * @param voteUuid
     * @return
     */
    public CommonResult getVoteDetail(String voteUuid);

    /**
     * 获取投票详情
     * @param voteUuid
     * @return
     */
    public List<PageData> getDetail(String voteUuid);

}
