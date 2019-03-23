package com.qilinxx.rms.controller;


import com.qilinxx.rms.domain.model.Log;
import com.qilinxx.rms.service.LogService;
import com.qilinxx.rms.util.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: dqsdc
 * @Date: 2018-09-28 14:54
 * @Description:
 */
@Controller
public class LogController extends BaseController{

    @Autowired
    LogService logService;


    @RequestMapping("admin-system-log")
    public String showSystemLog(HttpServletRequest request){
        List<Log> logs=logService.getAllLog();
        request.setAttribute("logs",logs);
        request.setAttribute("commons",new Commons());
        request.setAttribute("size",logs.size());
        return "admin/system-log.html";
    }
}
