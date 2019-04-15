package com.qilinxx.rms.controller;

import com.qilinxx.rms.domain.model.Major;
import com.qilinxx.rms.domain.model.vo.UserInfoVo;
import com.qilinxx.rms.service.MajorService;
import com.qilinxx.rms.util.Commons;
import com.qilinxx.rms.util.DateKit;
import com.qilinxx.rms.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: dqsdc
 * @Date: 2019-04-10 22:50
 * @Description:
 */
@Controller
public class MajorController {
    @Autowired
    MajorService majorService;

    @RequestMapping("showMajor")
    public String showMajor(Model model) {
        List<Major> infoList = majorService.findAllMajor();
        model.addAttribute("majorList", infoList);
        model.addAttribute("commons", new Commons());
        return "admin/major-list";
    }

    @RequestMapping("major-add")
    public String showMajorAdd() {
        return "admin/major-add";
    }


    @RequestMapping("addMajor")
    @ResponseBody
    public String addMajor(Major major) {
        System.out.println(major);
        major.setCreateTime(DateKit.getUnixTimeLong());
        major.setState("1");
        majorService.addMajor(major);
        return "添加成功";
    }

    @RequestMapping("deleteMajor")
    @ResponseBody
    public String deleteMajor(int uid) {
        majorService.deleteMajor(uid);
        return "删除成功";
    }
}
