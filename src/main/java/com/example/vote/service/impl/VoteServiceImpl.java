package com.example.vote.service.impl;

import com.example.vote.common.CommonResult;
import com.example.vote.dao.OptionsDao;
import com.example.vote.dao.UserDao;
import com.example.vote.dao.UserVoteDao;
import com.example.vote.dao.VoteDao;
import com.example.vote.entity.*;
import com.example.vote.service.VoteService;
import com.example.vote.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: VoteServceImpl
 * @Description: 投票业务实现类
 * @Date : 2020-07-22 18:08
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteDao voteDao;

    @Autowired
    private OptionsDao optionsDao;

    @Autowired
    private UserVoteDao userVoteDao;

    @Autowired
    private UserDao userDao;

    /**
     * 获取所有的投票
     * @return
     */
    @Override
    public CommonResult getAllVotes(String userUuid) {
        List<VoteInfo> voteInfoList = null;
        if (userUuid == null) {
            voteInfoList = voteDao.getAllVotesWithoutId();
        } else {
            voteInfoList = voteDao.getAllVotes(userUuid);
        }
        if (voteInfoList == null || voteInfoList.size() == 0) {
            return CommonResult.buildWithData(false, 0, "尚未创建任何投票", null);
        }
        return CommonResult.buildWithData(true, 1, "查询成功", voteInfoList);
    }

    /**
     * 添加投票
     * @param voteTitle
     * @param voteDescription
     * @param options
     * @param deadline
     * @param isAnonymous
     * @return
     */
    @Override
    public CommonResult addVote(String voteTitle, String voteDescription, String[] options, String deadline, Integer isAnonymous, String userUuid, Integer voteType) {
        // 先生成投票
        String voteUuid = UuidUtil.getUUID();
        Integer voteNum = 0;

        String userName = userDao.getUserName(userUuid);
        if (isAnonymous == 1) {
            userName = "匿名";
        }
        voteDao.insertVote(voteUuid, voteTitle, voteDescription,deadline, isAnonymous, voteType, userUuid, voteNum, userName);

        // 再创建对应的选项
        for (int i = 0; i < options.length; i++) {
            String optionsUuid = UuidUtil.getUUID();
            String optionsDescription = options[i];
            Integer optionsNum = 0;
            optionsDao.addOption(optionsUuid, optionsDescription, optionsNum, voteUuid);
        }

        return CommonResult.buildWithData(true, 1, "创建成功", voteUuid);
    }

    /**
     * 参与投票获得投票事件
     * @param userUuid
     * @param voteUuid
     * @return
     */
    @Override
    public CommonResult joinVote(String userUuid, String voteUuid) {
        // 首先判断是否投过票
        List<String> voteUuidVos = userVoteDao.getVoteUuids(userUuid);
        boolean voteFlag = false;
        if (voteUuidVos.contains(voteUuid)) {
            voteFlag = true;
        }
        if (voteFlag) {
            return CommonResult.buildWithData(false, 0, "已参与", voteUuid);
        }

        // 再获取投票事件的详情

        // 获取投票主体详情
        VoteInfo voteInfo = voteDao.getVoteInfo(voteUuid);
        // 获取投票选项详情
        List<OptionsInfo> optionsInfoList = optionsDao.getOptions(voteUuid);
        // 生成投票总实体类
        VoteEventInfo voteEventInfo = new VoteEventInfo();
        voteEventInfo.setVoteUuid(voteUuid);
        voteEventInfo.setVoteTitle(voteInfo.getVoteTitle());
        voteEventInfo.setVoteDescription(voteInfo.getVoteDescription());
        voteEventInfo.setOptionsInfoList(optionsInfoList);
        voteEventInfo.setDeadline(voteInfo.getDeadline());
        voteEventInfo.setIsAnonymous(voteInfo.getIsAnonymous());
        voteEventInfo.setUserUuid(voteInfo.getUserUuid());
        voteEventInfo.setVoteType(voteInfo.getVoteType());

        Integer count = 0;
        for (int i = 0; i < optionsInfoList.size(); i++) {
            count += optionsInfoList.get(i).getOptionsNum();
        }
        voteEventInfo.setVoteNum(count);
        if (voteEventInfo.getVoteType() == 0) {
            return CommonResult.buildWithData(true, 1, "多项", voteEventInfo);
        }
        return CommonResult.buildWithData(true, 1, "单项", voteEventInfo);
    }

    /**
     * 删除选项
     * @param voteUuid
     * @return
     */
    @Override
    public CommonResult deleteVote(String voteUuid) {
        voteDao.deleteVOte(voteUuid);
        optionsDao.deleteOptions(voteUuid);
        return CommonResult.buildWithData(true, 1, "删除成功", null);
    }

    /**
     * 给选项投票
     * @param userUuid
     * @param voteUuid
     * @param optionsUuid
     * @return
     */
    @Override
    public CommonResult addSingleVote(String userUuid, String voteUuid, String optionsUuid) {
        String userName = userDao.getUserName(userUuid);
        // 首先在关联表中新增记录
        userVoteDao.addVoteUuids(userUuid, voteUuid, userName);
        // 在选项表中修改记录
        optionsDao.updateOptionsById(optionsUuid);
        // 在投票表中修改记录
        voteDao.addNum(voteUuid);

        return CommonResult.buildWithData(true, 1, "投票成功", null);
    }

    @Override
    public CommonResult addMultiVote(String userUuid, String voteUuid, String[] optionsUuid) {
        String userName = userDao.getUserName(userUuid);
        // 首先在关联表中新增记录
        userVoteDao.addVoteUuids(userUuid, voteUuid, userName);
        // 在选项表中修改记录
        for (int i = 0; i < optionsUuid.length; i++) {
            optionsDao.updateOptionsById(optionsUuid[i]);
        }
        // 在投票表中修改记录
        voteDao.addNum(voteUuid);
        return CommonResult.buildWithData(true, 1, "投票成功", null);
    }

    /**
     * 获取投票详情
     * @param userUuid
     * @param voteUuid
     * @return
     */
    @Override
    public CommonResult getVoteInfo(String userUuid, String voteUuid) {
        // 获取投票主体详情
        VoteInfo voteInfo = voteDao.getVoteInfo(voteUuid);
        // 获取投票选项详情
        List<OptionsInfo> optionsInfoList = optionsDao.getOptions(voteUuid);
        // 生成投票总实体类
        VoteEventInfo voteEventInfo = new VoteEventInfo();
        voteEventInfo.setVoteUuid(voteUuid);
        voteEventInfo.setVoteTitle(voteInfo.getVoteTitle());
        voteEventInfo.setVoteDescription(voteInfo.getVoteDescription());
        voteEventInfo.setOptionsInfoList(optionsInfoList);
        voteEventInfo.setDeadline(voteInfo.getDeadline());
        voteEventInfo.setIsAnonymous(voteInfo.getIsAnonymous());
        voteEventInfo.setUserUuid(voteInfo.getUserUuid());
        voteEventInfo.setVoteType(voteInfo.getVoteType());

        Integer count = 0;
        for (int i = 0; i < optionsInfoList.size(); i++) {
            count += optionsInfoList.get(i).getOptionsNum();
        }
        voteEventInfo.setVoteNum(count);
        return CommonResult.buildWithData(true, 1, "未参与", voteEventInfo);
    }

    /**
     * 修改投票
     * @param voteOptionsInfo
     * @return
     */
    @Override
    public CommonResult updateVote(VoteOptionsInfo voteOptionsInfo) {
        String voteUuid = voteOptionsInfo.getVoteUuid();
        String voteTitle = voteOptionsInfo.getVoteTitle();
        String voteDescription = voteOptionsInfo.getVoteDescription();
        List<String> optionsDescription = voteOptionsInfo.getOptionsDescription();
        voteDao.updateVote(voteUuid, voteTitle, voteDescription);

        // 修改选项
        List<OptionsInfo> optionsInfoList = optionsDao.getOptions(voteUuid);
        for (int i = 0; i < optionsInfoList.size(); i++) {
//            optionsInfoList.get(i).setOptionsDescription(optionsDescription.get(i));
            optionsDao.updateOptionsByDescription(optionsInfoList.get(i).getOptionsUuid(), optionsDescription.get(i));
        }
        return CommonResult.buildWithData(true, 1, "修改成功", null);
    }

    /**
     * 获取投票详情
     * @param voteUuid
     * @return
     */
    @Override
    public CommonResult getVoteDetail(String voteUuid) {
        // 获取投票主体详情
        VoteInfo voteInfo = voteDao.getVoteInfo(voteUuid);
        // 获取投票选项详情
        List<OptionsInfo> optionsInfoList = optionsDao.getOptions(voteUuid);
        // 生成投票总实体类
        VoteDetailInfo voteDetailInfo = new VoteDetailInfo();
        voteDetailInfo.setVoteUuid(voteUuid);
        voteDetailInfo.setVoteTitle(voteInfo.getVoteTitle());

        List<OptionsDetailInfo> optionsDetailInfoList = new ArrayList<>();

        for (int i = 0; i < optionsInfoList.size(); i++) {
            OptionsDetailInfo optionsDetailInfo = new OptionsDetailInfo();
            optionsDetailInfo.setDescription(optionsInfoList.get(i).getOptionsDescription());
            optionsDetailInfo.setNum(optionsInfoList.get(i).getOptionsNum());
            optionsDetailInfoList.add(optionsDetailInfo);
        }
        voteDetailInfo.setOptionsDetailInfo(optionsDetailInfoList);


        return CommonResult.buildWithData(true, 1, "获取成功", voteDetailInfo);
    }

    @Override
    public List<PageData> getDetail(String voteUuid) {

        // 获取投票主体详情
        VoteInfo voteInfo = voteDao.getVoteInfo(voteUuid);
        // 获取投票选项详情
        List<OptionsInfo> optionsInfoList = optionsDao.getOptions(voteUuid);
        // 生成投票总实体类
        VoteDetailInfo voteDetailInfo = new VoteDetailInfo();
        voteDetailInfo.setVoteUuid(voteUuid);

        List<String> optionsDescription = new ArrayList<>();
        List<Integer> optionsNum = new ArrayList<>();
        List<PageData> list = new ArrayList<>();

        for (int i = 0; i < optionsInfoList.size(); i++) {

            PageData pageData = new PageData();
            pageData.setOpDescription(optionsInfoList.get(i).getOptionsDescription());
            pageData.setOptionsNum(optionsInfoList.get(i).getOptionsNum());
            list.add(pageData);

        }



        return list;
    }

}
