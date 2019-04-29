package com.qilinxx.rms.controller;

import com.qilinxx.rms.domain.model.UserInfo;
import com.qilinxx.rms.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    public String loginLose(HttpSession session, Model model, String msg) {
        System.out.println(msg);
        session.invalidate();
        model.addAttribute("msg", msg.equals("1") ? "请登录" : "异地登录");
        return "error/wrong";
    }

    /**
     * 来到登录页面
     *
     * @return 跳转登录页面
     */
    @RequestMapping("login")
    public String login(boolean clearSession, HttpSession session) {

        ServletContext servletContext = session.getServletContext();
        @SuppressWarnings("unchecked")
        Map<String, Object> loginMap = (Map<String, Object>) servletContext.getAttribute("loginMap");
        if (loginMap == null) {
            loginMap = new HashMap<>();
            servletContext.setAttribute("loginMap", loginMap);
        }
        if (clearSession) {
            String uid = (String) session.getAttribute("uid");
            //清空session
            session.invalidate();
            loginMap.remove(uid);
            servletContext.setAttribute("loginMap", loginMap);
        }
        return "login";
    }

    @ResponseBody
    @RequestMapping("/loginSub")
    public String login(HttpSession session, String password, String account) {
        String uid = (String) session.getAttribute("uid");
        UserInfo user = userInfoService.findUserByUid(account);
        if (uid != null) {
            return "您已有账号登录！";
        }
        if (user == null) {
            return "账号不存在";
        }
        if (!user.getPassword().equals(password)) {
            return "密码错误";
        }
        ServletContext application = session.getServletContext();
        //key 姓名  object sessionId
        @SuppressWarnings("unchecked")
        Map<String, Object> loginMap = (Map<String, Object>) application.getAttribute("loginMap");
        for (String key : loginMap.keySet()) {
            if (account .equals(key)) {
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
        session.setAttribute("sessionId", loginMap.get(account));
        // session 销毁时间
        session.setMaxInactiveInterval(15 * 60);
        if (user.getRemake() != null && user.getRemake().equals("admin")) {
            return "admin";
        } else
            return "登录成功";
    }

}
