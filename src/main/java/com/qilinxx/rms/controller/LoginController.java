package com.qilinxx.rms.controller;

import com.qilinxx.rms.domain.model.UserInfo;
import com.qilinxx.rms.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


/**
 * fangyusheng
 * 2019/2/22
 * 登录控制层
 */
@Controller
public class LoginController {
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("loginLose")
    public String loginLose(HttpSession session){
        session.removeAttribute("uid");
        session.removeAttribute("sessionId");
        return "error/wrong";
    }
        /**
         * 来到登录页面
         * @return 跳转登录页面
         */
    @GetMapping("login")
    public String login(boolean clearSession,HttpSession session){
            if(clearSession){
                //清空session
                session.removeAttribute("uid");
                session.removeAttribute("sessionId");
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
    @ResponseBody
    @RequestMapping("/loginSub")
    public String login(HttpSession session,String password,int account) {
        UserInfo user = userInfoService.findUserByUid(account);
        if (user == null) {
            return "账号不存在";
        }
        if (!user.getPassword().equals(password)) {
            return "密码错误";
        }
        ServletContext application = session.getServletContext();
        //key 姓名  object sessionId
        @SuppressWarnings("unchecked")
        Map<Integer, Object> loginMap = (Map<Integer, Object>) application.getAttribute("loginMap");
        for (Integer key : loginMap.keySet()) {
            if (account==key) {
                if (session.getId().equals(loginMap.get(key))) {
                    return "在同一地点重复登录";
                } else {
                    loginMap.remove(key);
//                    return "异地已登录，请先退出登录";
                }
            }
        }
        loginMap.put(account, session.getId());

        application.setAttribute("loginMap", loginMap);
        // 将用户保存在session当中
        session.setAttribute("uid", account);
        session.setAttribute("sessionId",loginMap.get(account));
        // session 销毁时间
        session.setMaxInactiveInterval(10 * 60);
        return "登录成功";
    }

}
