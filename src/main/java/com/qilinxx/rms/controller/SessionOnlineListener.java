package com.qilinxx.rms.controller;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class SessionOnlineListener implements HttpSessionListener, HttpSessionAttributeListener {

    // 参数
    private ServletContext sc;

    private ArrayList<Integer> list = new ArrayList<>();//用来存储Session值

    private int onlineCount = 0;


    // 新建一个session时触发此操作
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sc = se.getSession().getServletContext();
        onlineCount++;
        sc.setAttribute("onlineCount", onlineCount);
        System.out.println("创建:" + onlineCount);
    }


    // 销毁一个session时触发此操作
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        onlineCount--;
        list.remove(se.getSession().getAttribute("uid"));
        removeLoginMap(se.getSession());
        sc.setAttribute("onlineCount", onlineCount);
        System.out.println("销毁:" + onlineCount);
    }


    // 在session中添加对象时触发此操作，在list中添加一个对象
    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        System.out.println(sbe.getSession().getAttribute("uid"));
        list.add((Integer) sbe.getSession().getAttribute("uid"));
        sc.setAttribute("onlineCount", onlineCount);
        System.out.println("向session中添加值:" + onlineCount);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        sc.setAttribute("onlineCount", onlineCount);
        System.out.println("值替换");
    }

    private void removeLoginMap(HttpSession session) {
        Integer uid = (Integer) session.getAttribute("uid");
        list.remove(uid);
        Map<Integer, Object> loginMap = (Map<Integer, Object>) sc.getAttribute("loginMap");
        loginMap.remove(uid);
        sc.setAttribute("loginMap", loginMap);
    }
}