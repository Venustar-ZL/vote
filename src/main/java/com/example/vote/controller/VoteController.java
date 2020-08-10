package com.example.vote.controller;

import com.example.vote.common.CommonResult;
import com.example.vote.entity.*;
import com.example.vote.service.VoteService;
import com.example.vote.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VoteController
 * @Description: 投票控制类
 * @Date : 2020-07-22 16:15
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Controller
@SuppressWarnings("all")
public class VoteController {

    @Autowired
    private VoteService voteService;

    /**
     * 跳转至创建单项投票界面
     * @return
     */
    @RequestMapping("/toSingleCreate")
    public String toSingleCreate() {
        return "/main/singleCreate";
    }

    /**
     * 跳转至创建多项投票界面
     * @return
     */
    @RequestMapping("/toMultiCreate")
    public String toMultiCreate() {
        return "/main/multiCreate";
    }

    /**
     * 跳转至投票界面
     * @return
     */
    @RequestMapping("/toJoin/{voteUuid}")
    public String toJoin(@PathVariable("voteUuid") String voteUuid, HttpSession session, Map<String, Object> map) {
        System.out.println(voteUuid);
        String userUuid = (String)session.getAttribute("userUuid");
        CommonResult commonResult = voteService.joinVote(userUuid, voteUuid);
        System.out.println(commonResult.getResult());
        map.put("voteEventInfo", commonResult.getResult());
        if (commonResult.getMsg() .equals("多项")) {
            return "/main/multiJoin";
        }
        return "/main/join";
    }

    @RequestMapping("/toVoteHall")
    public String toVoteHall(Map<String, Object> map) {
        CommonResult commonResult = voteService.getAllVotes(null);
        map.put("voteList", commonResult.getResult());
        return "main/voteHall";
    }

    /**
     * 跳转至修改界面
     * @param voteUuid
     * @param map
     * @param session
     * @return
     */
    @GetMapping("/updateVoteInfo/{voteUuid}")
    public String toUpdateVote(@PathVariable("voteUuid") String voteUuid, Map<String, Object> map, HttpSession session) {
        String userUuid = (String)session.getAttribute("userUuid");
        CommonResult commonResult = voteService.getVoteInfo(userUuid, voteUuid);
        System.out.println(commonResult.getResult());
        map.put("voteEventInfo", commonResult.getResult());
        return "/main/update";
    }

    /**
     * 展示所有的投票
     * @return
     */
    @GetMapping("/showAllVotes")
    public String getAllVotes(HttpSession session, Map<String, Object> map) {
        String userUuid = (String)session.getAttribute("userUuid");
        CommonResult commonResult = voteService.getAllVotes(userUuid);
        map.put("voteList", commonResult.getResult());
        return "/main/show";
    }

    /**
     * 创建单项投票
     * @param voteVo
     * @param session
     * @return
     */
    @PostMapping("/createSingleVote")
    @ResponseBody
    public CommonResult createSingleVote(@RequestBody VoteVo voteVo, HttpSession session) throws ParseException {
        String voteTitle = voteVo.getVoteTitle();
        String voteDescription = voteVo.getVoteDescription();
        String[] options = voteVo.getOptions();
        String deadline = voteVo.getDeadline();
        Integer isAnonymous = voteVo.getIsAnonymous();
        System.out.println(voteVo);

        if(voteTitle == null || StringUtils.isEmpty(voteTitle)) {
            return CommonResult.buildWithData(true, 0, "标题不能为空", null);
        }

        if(options == null) {
            return CommonResult.buildWithData(true, 0, "选项不能为空", null);
        }

        for (int i = 0; i < options.length; i++) {
            if (options[i] == null || StringUtils.isEmpty(options[i])) {
                return CommonResult.buildWithData(true, 0, "选项不能为空", null);
            }
        }

        if(deadline == null || StringUtils.isEmpty(deadline)) {
            return CommonResult.buildWithData(true, 0, "截止日期不能为空", null);
        }

        deadline = deadline.replace("T", " ");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date dt1 = df.parse(deadline);
        Date dt2 = df.parse(df.format(new Date()));
        if (dt1.getTime() < dt2.getTime()) {
            return CommonResult.buildWithData(true, 0, "截止日期不能小于当前时间", null);
        }

        String userUuid = (String)session.getAttribute("userUuid");
        // 1 为单项投票
        Integer voteType = 1;


        CommonResult commonResult = voteService.addVote(voteTitle, voteDescription, options, deadline, isAnonymous, userUuid, voteType);

        return commonResult;
//        return CommonResult.buildWithData(true, 1, "创建成功", null);
    }

    /**
     * 创建多项投票
     * @param voteVo
     * @param session
     * @return
     */
    @PostMapping("/createMultiVote")
    @ResponseBody
    public CommonResult createMultiVote(@RequestBody VoteVo voteVo, HttpSession session) throws ParseException {
        String voteTitle = voteVo.getVoteTitle();
        String voteDescription = voteVo.getVoteDescription();
        String[] options = voteVo.getOptions();
        String deadline = voteVo.getDeadline();
        Integer isAnonymous = voteVo.getIsAnonymous();
        System.out.println(voteVo);

        if(voteTitle == null || StringUtils.isEmpty(voteTitle)) {
            return CommonResult.buildWithData(true, 0, "标题不能为空", null);
        }

        if(options == null) {
            return CommonResult.buildWithData(true, 0, "选项不能为空", null);
        }

        for (int i = 0; i < options.length; i++) {
            if (options[i] == null || StringUtils.isEmpty(options[i])) {
                return CommonResult.buildWithData(true, 0, "选项不能为空", null);
            }
        }

        if(deadline == null || StringUtils.isEmpty(deadline)) {
            return CommonResult.buildWithData(true, 0, "截止日期不能为空", null);
        }

        deadline = deadline.replace("T", " ");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date dt1 = df.parse(deadline);
        Date dt2 = df.parse(df.format(new Date()));
        if (dt1.getTime() < dt2.getTime()) {
            return CommonResult.buildWithData(true, 0, "截止日期不能小于当前时间", null);
        }

        String userUuid = (String)session.getAttribute("userUuid");
        // 0 为多项投票
        Integer voteType = 0;

        CommonResult commonResult = voteService.addVote(voteTitle, voteDescription, options, deadline, isAnonymous, userUuid, voteType);

        return commonResult;
    }

    /**
     * 判断是否参与 并获得投票界面数据进行渲染
     * @param voteUuid
     * @param session
     * @return
     */
    @GetMapping("/joinVote")
    @ResponseBody
    public CommonResult joinVote(String voteUuid, HttpSession session) {
        String userUuid = (String)session.getAttribute("userUuid");
        CommonResult commonResult = voteService.joinVote(userUuid, voteUuid);
        return commonResult;

//        return CommonResult.buildWithData(true, 0, "创建成功", null);
    }

    /**
     * 删除投票
     * @param voteUuid
     * @param session
     * @param map
     * @return
     */
    @GetMapping("/deleteVoteInfo/{voteUuid}")
    public String deleteVote(@PathVariable("voteUuid") String voteUuid, HttpSession session, Map<String, Object> map) {
        voteService.deleteVote(voteUuid);
        String userUuid = (String)session.getAttribute("userUuid");
        CommonResult commonResult = voteService.getAllVotes(userUuid);
        map.put("voteList", commonResult.getResult());
        return "/main/show";
    }

    /**
     * 单项投票
     * @param voteSingleOptions
     * @param session
     * @return
     */
    @PostMapping("/addSingleVote")
    @ResponseBody
    public CommonResult addSingleVote(@RequestBody VoteSingleOptions voteSingleOptions, HttpSession session) {
        String userUuid = (String) session.getAttribute("userUuid");
        String voteUuid = voteSingleOptions.getVoteUuid();
        String optionsUuid = voteSingleOptions.getOptionsUuid();
        return voteService.addSingleVote(userUuid, voteUuid, optionsUuid);

    }

    /**
     * 多项投票
     * @param voteMultiOptions
     * @param session
     * @return
     */
    @PostMapping("/addMultiVote")
    @ResponseBody
    public CommonResult addMultiVote(@RequestBody VoteMultiOptions voteMultiOptions, HttpSession session) {
        String userUuid = (String) session.getAttribute("userUuid");
        String voteUuid = voteMultiOptions.getVoteUuid();
        String[] optionsUuid = voteMultiOptions.getOptionsUuid();
        return voteService.addMultiVote(userUuid, voteUuid, optionsUuid);

    }

    @PutMapping("/updateVote")
    @ResponseBody
    public CommonResult updateVote(@RequestBody VoteOptionsInfo voteOptionsInfo) {
        String voteTitle = voteOptionsInfo.getVoteTitle();
        List<String> options = voteOptionsInfo.getOptionsDescription();

        if(voteTitle == null || StringUtils.isEmpty(voteTitle)) {
            return CommonResult.buildWithData(true, 0, "标题不能为空", null);
        }

        if(options == null) {
            return CommonResult.buildWithData(true, 0, "选项不能为空", null);
        }

        for (int i = 0; i < options.size(); i++) {
            if (options.get(i) == null || StringUtils.isEmpty(options.get(i))) {
                return CommonResult.buildWithData(true, 0, "选项不能为空", null);
            }
        }

        return voteService.updateVote(voteOptionsInfo);
    }

    @GetMapping("/getVoteInfo/{voteUuid}")
    public String getVoteDetail(@PathVariable("voteUuid") String voteUuid, Map<String, Object> map) {
        CommonResult commonResult = voteService.getVoteDetail(voteUuid);
        map.put("voteDetailInfo", commonResult.getResult());
        System.out.println(commonResult.getResult());
        return "/main/detail";
    }

    @PostMapping("/detail/{voteUuid}")
    @ResponseBody
    public List<PageData> detail(@PathVariable("voteUuid") String voteUuid){
        List<PageData> list = voteService.getDetail(voteUuid);
//        System.out.println(list);
        return list;
    }
}
