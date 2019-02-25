package com.qilinxx.rms.controller;

import com.qilinxx.rms.domain.model.Major;
import com.qilinxx.rms.domain.model.UserInfo;
import com.qilinxx.rms.domain.model.UserMajor;
import com.qilinxx.rms.service.MajorService;
import com.qilinxx.rms.service.UserInfoService;
import com.qilinxx.rms.service.UserMajorService;
import com.qilinxx.rms.util.DateKit;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserMajorService userMajorService;
    @Autowired
    MajorService majorService;
    /**
     * @return  来到教师成果管理系统页面
     */
    @GetMapping({"1","main"})
    public String main(HttpSession session){
        //以下代码项目完成修改
        session.setAttribute("uid",2013001);
        //以上代码项目完成时修改
        return "manager/main";
    }

    /**
     * @return 显示top层
     */
    @GetMapping("top")
    public String top(HttpSession session, Model model){
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        model.addAttribute("name",user.getName());
        return "manager/top";
    }

    /**
     * @return 显示左侧功能面板
     */
    @GetMapping("left")
    public String left(HttpSession session, Model model){
        List<UserMajor> userMajorList = userMajorService.findAllUserMajorByUid((Integer) session.getAttribute("uid"));
        if(userMajorList.size()!=0){
            List<Major> majorList=new ArrayList<>();
            for (UserMajor um:userMajorList) {
                majorList.add(majorService.findMajorBymid(um.getMid()));
            }
            model.addAttribute("majorList",majorList);
        }
        model.addAttribute("power",userMajorList.size());
        return "manager/left";
    }

    /**
     * @return 显示主页
     */
    @GetMapping("index")
    public String index(){
        return "manager/index";
    }

    /**
     * 展示教师个人信息
     * @return  跳转个人信息页面
     */
    @GetMapping("info")
    public String info(HttpSession session, Model model){
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        Major major = majorService.findMajorBymid(user.getMid());
        model.addAttribute("majorName",major.getName());
        model.addAttribute("user",user);
        model.addAttribute("dateKit",new DateKit());
        return "manager/info/info";
    }
    /**
     * 修改教师个人信息
     * @return  来到修改信息页面
     */
    @GetMapping("info-change")
    public String infoChange(HttpSession session, Model model){
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        Major major = majorService.findMajorBymid(user.getMid());
        List<Major> majorList = majorService.findAllMajor();
        model.addAttribute("majorList",majorList);
        model.addAttribute("majorName",major.getName());
        model.addAttribute("user",user);
        model.addAttribute("dateKit",new DateKit());
        return "manager/info/info-change";
    }

    /**
     * aja修改个人信息
     * @param user  由页面得到的修改信息
     */
    @PostMapping("ajax-info-change")
    @ResponseBody
    public JSONObject ajaxInfoChange(UserInfo user,HttpSession session){
        JSONObject json=new JSONObject();
        UserInfo dbUser = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        boolean same=true;
        if(!user.getName().equals(dbUser.getName())||!user.getSex().equals(dbUser.getSex())||!user.getTitle().equals(dbUser.getTitle())||!user.getBelong().equals(dbUser.getBelong())||!user.getMid().equals(dbUser.getMid())||!user.getProfile().equals(dbUser.getProfile())){
            same=false;
        }
        if(same){
            json.put("msg","请勿重复提交！");
            return json;
        }
        user.setUid(dbUser.getUid());
        user.setState("2");//账号变为待审核状态
        user.setUpdateTime(DateKit.getUnixTimeLong());
        userInfoService.modifyUser(user);
        json.put("msg","信息提交成功，账号待审核！");
        return json;
    }
    /**
     * 修改密码页面
     * @return  来到修改密码页面
     */
    @GetMapping("password-change")
    public String passwordChange(){
        return "manager/info/password-change";
    }
    /**
     * ajax修改密码
     * password3 原密码
     * password  新密码
     */
    @PostMapping("ajax-password-change")
    @ResponseBody
    public JSONObject ajaxInfoChange(UserInfo user,String password3,HttpSession session){
        JSONObject json=new JSONObject();
        UserInfo dbUser = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        if(!dbUser.getPassword().equals(password3)){
            json.put("msg","旧密码错误！");
            return json;
        }
        if(user.getPassword().equals(password3)){
            json.put("msg","新旧密码不能相同！");
            return json;
        }
        user.setUpdateTime(DateKit.getUnixTimeLong());
        userInfoService.modifyUser(user);
        json.put("msg","密码修改成功！");
        return json;
    }
}
