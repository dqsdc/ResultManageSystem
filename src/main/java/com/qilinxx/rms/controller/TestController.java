package com.qilinxx.rms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: dqsdc
 * @Date: 2019-02-22 16:45
 * @Description:
 */
@Controller
public class TestController extends BaseController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "hello world";
    }

    @GetMapping("test")
    public String test(){
        return "test";
    }
}

