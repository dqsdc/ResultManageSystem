package com.qilinxx.rms.controller;

import com.qilinxx.rms.domain.model.Major;
import com.qilinxx.rms.domain.model.UserInfo;
import com.qilinxx.rms.service.MajorService;
import com.qilinxx.rms.service.UserInfoService;
import com.qilinxx.rms.util.Commons;
import com.qilinxx.rms.util.DateKit;
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

    @Autowired
    UserInfoService userInfoService;

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

    @RequestMapping("major-update")
    public String showMajorUpdate(Integer mid, Model model) {
        Major major = majorService.findMajorBymid(mid);
        model.addAttribute("major", major);
        return "admin/major-update";
    }
    @RequestMapping("updateMajor")
    @ResponseBody
    public String updateMajor(Major major) {
        majorService.updateMajor(major);
        return "修改成功";
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
    public String deleteMajor(int mid) {
        List<UserInfo> userInfos=userInfoService.findUserByMid(mid);
        if (userInfos.size()>0) return "该专业有用户，不可删除";
        majorService.deleteMajor(mid);
        return "删除成功";
    }
}
