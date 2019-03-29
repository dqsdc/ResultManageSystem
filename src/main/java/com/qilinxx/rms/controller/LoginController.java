package com.qilinxx.rms.controller;

import com.qilinxx.rms.domain.model.UserInfo;
import com.qilinxx.rms.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * fangyusheng
 * 2019/2/22
 * 登录控制层
 */
@Controller
public class LoginController {
    @Autowired
    UserInfoService userInfoService;

    /**
     * 来到登录页面
     * @return 跳转登录页面
     */
    @GetMapping("login")
    public String login(boolean clearSession,HttpSession session){
            if(clearSession==true){
                //清空session
                session.removeAttribute("uid");
            }
        return "login";
    }


    @PostMapping("login")
    public String login(Integer account, String password , Model model, HttpSession session, HttpServletResponse response) throws IOException {
        //下面的账号密码验证可以用shiro代替
        UserInfo user = userInfoService.findUserByUid(account);
        if (user == null) {
            model.addAttribute("error", "账号不存在");
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            return "login";
        }
        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "密码错误");
            model.addAttribute("account", account);
            return "login";
        }
        session.setAttribute("uid", account);
        response.sendRedirect("/main");
        return null;
    }
}
