package com.example.vote.controller;

import com.example.vote.common.CommonResult;
import com.example.vote.service.UserService;
import com.example.vote.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: UserController
 * @Description: 用户控制类
 * @Date : 2020-07-18 23:17
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    /**
     * 跳转初始界面
     * @return
     */
    @GetMapping("/")
    public String toIndex() {
        return "/main/index";
    }


    /**
     * 用户功能控制
     * 判断用户是否登录
     * @return
     */
    @RequestMapping("/toMine")
    public String toMine(HttpSession session, Map<String, Object> map) {
        String userUuid = (String)session.getAttribute("userUuid");
        CommonResult commonResult = voteService.getAllVotes(userUuid);
        if (!commonResult.isSuccess()) {
            map.put("errcode", commonResult.getErrCode());
            map.put("msg", commonResult.getMsg());
            return "main/index";
        }
        map.put("voteList", commonResult.getResult());
        System.out.println(map);
        return "main/show";
    }

    /**
     * 跳转至注册页面
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "/main/register";
    }

    /**
     * 注册用户
     * @param userName
     * @param password
     * @return
     */
    @GetMapping("/register")
    public String registerUser(String userName, String password, Map<String, Object> map, HttpSession session) {
        CommonResult commonResult  = userService.registerUser(userName, password);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/main/register";
        }
        session.setAttribute("loginUser", userName);
        return "/main/login";
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @GetMapping("/login")
    public String loginUser(String userName, String password, Map<String, Object> map, HttpSession session) {
        CommonResult commonResult = userService.loginUser(userName, password);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/main/login";
        }
        session.setAttribute("loginUser", userName);
        session.setAttribute("userUuid", (String)commonResult.getResult());
        return "/main/index";
    }


}
