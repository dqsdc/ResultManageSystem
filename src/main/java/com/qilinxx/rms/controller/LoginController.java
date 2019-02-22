package com.qilinxx.rms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * fangyusheng
 * 2019/2/22
 * 登录控制层
 */
@Controller
public class LoginController {
    /**
     * 来到登录页面
     * @return 跳转登录页面
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }
}
