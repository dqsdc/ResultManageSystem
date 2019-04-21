//package com.qilinxx.rms.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpSession;
//
///**
// * @Auther: dqsdc
// * @Date: 2019-04-21 18:13
// * @Description:
// */
//@Controller
//public class AdminLoginController {
//    /**
//     * 来到登录页面
//     *
//     * @return 跳转登录页面
//     */
//    @GetMapping("loginAdmin")
//    public String login(boolean clearSession, HttpSession session) {
//        ServletContext servletContext = session.getServletContext();
//        String adminState = (String) servletContext.getAttribute("adminState");
//        if (adminState == null) {
//            servletContext.setAttribute("adminState", "offline");
//        }
//        if (clearSession) {
//            //清空session
//            session.invalidate();
//            servletContext.setAttribute("adminState", "offline");
//        }
//        return "admin/admin-login";
//    }
//
//
//    @ResponseBody
//    @PostMapping("loginAdmin")
//    public String login(HttpSession session, String password, String account) {
//        Integer uid = (Integer) session.getAttribute("uid");
//        if (uid != null) {
//            return "您已有账号登录！";
//        }
//        if (account == null) {
//            return "账号不存在";
//        } else {
//            if (!account.equals("admin")) {
//                return "账号错误";
//            }
//        }
//        if (!password.equals("hbnujsj")) {
//            return "密码错误";
//        }
//        ServletContext application = session.getServletContext();
//        //key 姓名  object sessionId
//        String adminState = (String) application.getAttribute("adminState");
//        if (adminState.equals("online")) {
//            return "重复登录";
//        } else {
//            adminState = "online";
//        }
//        application.setAttribute("adminState", adminState);
//        // 将用户保存在session当中
//        session.setAttribute("uid", 4396);
//        // session 销毁时间
//        session.setMaxInactiveInterval(30);
//        return "登录成功";
//    }
//}
