package com.qilinxx.rms.controller;

import com.qilinxx.rms.domain.model.*;
import com.qilinxx.rms.domain.model.vo.UserInfoVo;
import com.qilinxx.rms.service.*;
import com.qilinxx.rms.util.Commons;
import com.qilinxx.rms.util.DateKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Auther: dqsdc
 * @Date: 2019-02-22 16:45
 * @Description:
 */
@Controller
public class AdminController extends BaseController {


    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserItemService userItemService;

    @Autowired
    UserMajorService userMajorService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ThesisService thesisService;

    @Autowired
    LogService logService;

    @Autowired
    RewardService rewardService;

    @RequestMapping("/adminIndex")
    public String showIndex(HttpServletRequest request) {
        logService.insertLog("管理员登录", "admin", userIp(request));
        return "admin/index";
    }

    @RequestMapping("/welcome")
    public String showWelcome() {
        return "admin/welcome";
    }

    @RequestMapping("student-list.html")
    public String showMemberList(Model model) {
        List<UserInfoVo> infoList = userInfoService.findAllUser();
        model.addAttribute("userInfoList", infoList);
        model.addAttribute("commons", new Commons());
        return "admin/student-list";
    }

    @RequestMapping("grant-list.html")
    public String showGrantList(Model model) {
        List<UserInfoVo> infoList = userInfoService.findAllUser();

        model.addAttribute("userInfoList", infoList);
        model.addAttribute("commons", new Commons());
        return "admin/grant-list";
    }

    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    @ResponseBody
    public String updatePermission(@RequestParam("mid") int[] mid, int uid) {
        System.out.println("updatePermission" + mid.length);
        System.out.println("uid:--" + uid);
        Integer i = userMajorService.updatePermission(uid, mid);
        if (i == mid.length)
            return "添加权限完成";
        else
            return "添加失败";
    }

    @RequestMapping("/admin-student-add")
    public String Student_add() {
        return "admin/student-add";
    }

    /**
     * @Author: LJM
     * @Description: 添加学生用户
     */
    @ResponseBody
    @RequestMapping("/addStudent")
    public String addStudent(UserInfo userInfo, HttpServletRequest request) {
        System.out.println(userInfo);
        userInfo.setPassword(String.valueOf(userInfo.getUid()));
        userInfo.setCreateTime(DateKit.getUnixTimeLong());

        Integer i = userInfoService.insert(userInfo);
        if (i > 0) {
            logService.insertLog("成功添加" + userInfo.getName(), "admin", request.getRemoteAddr());
        } else {
            logService.insertLog("添加" + userInfo.getName() + "失败", "admin", request.getRemoteAddr());
        }
        return "添加成功";
    }

    /**
     * @Author: LJM
     * @Description: 删除一个用户  从数据库真的删除
     */
    @ResponseBody
    @RequestMapping("/deleteStudent")
    public String deleteStudent(String uid, HttpServletRequest request) {
        String name = userInfoService.selectNameById(uid);
        Integer i = userInfoService.deleteStudentById(uid);

        if (i > 0) {
            logService.insertLog("成功删除一个学生" + name, userId(request), request.getRemoteAddr());
            return "删除成功";
        } else {
            logService.insertLog("删除一个学生" + name + "失败", userId(request), request.getRemoteAddr());
            return "删除失败";
        }

    }

    /**
     * @Author: LJM
     * @Description: 禁用用户
     */
    @ResponseBody
    @RequestMapping("/stopStudent")
    public String stopStudent(String uid, HttpServletRequest request) {
        Integer i = userInfoService.stopStudent(uid);
        String name = userInfoService.selectNameById(uid);
        if (i > 0) {
            logService.insertLog("成功停用" + name, "admin", request.getRemoteAddr());
            return "停用成功";
        } else {
            logService.insertLog("停用" + name + "失败", "admin", request.getRemoteAddr());
            return "停用失败";
        }
    }

    /**
     * @Author: LJM
     * @Description: 启用用户
     */
    @ResponseBody
    @RequestMapping("/startStudent")
    public String startStudent(String uid, HttpServletRequest request) {
        String name = userInfoService.selectNameById(uid);
        Integer i = userInfoService.startStudent(uid);
        logService.insertLog("成功启用" + name, "admin", request.getRemoteAddr());
        return "success";
    }

    /**
     * @Author: LJM
     * @Description: 跳转到修改学生信息页面
     */
    @RequestMapping("student-edit.html")
    public String student_edit(String uid, Model model) {
        UserInfo student = userInfoService.findUserByUid(Integer.parseInt(uid));
        model.addAttribute("student", student);
        return "admin/student-edit";
    }

    /**
     * @Author: LJM
     * @Description: 修改学生信息
     */
    @ResponseBody
    @RequestMapping("/editStudent")
    public String editStudent(UserInfo user, HttpServletRequest request) {
        userInfoService.editStudent(user);
        logService.insertLog("成功修改" + user.getName() + "的信息", "admin", request.getRemoteAddr());
        return "修改成功";
    }

    /**
     * @return 来到项目总览页面
     */
    @GetMapping("admin-project-overview")
    public String projectOverview(Model model) {
        List<UserItem> userItemList = userItemService.findAllUserItemByUserType("project");
        List<Project> projectList = new ArrayList<>();
        Map<Integer, UserInfo> createrMap = new HashMap<>();
        for (UserItem userItem : userItemList) {
            projectList.add(projectService.findProjectByPid(userItem.getItemId()));
        }
        for (Project project : projectList) {
            createrMap.put(project.getCreateId(), userInfoService.findUserByUid(project.getCreateId()));
        }
        //将projectList倒序
        Collections.reverse(projectList);
        model.addAttribute("createrMap", createrMap);
        model.addAttribute("projectList", projectList);
        model.addAttribute("dateKit", new DateKit());
        return "admin/project-overview";
    }

    //来到论文总览页面
    @GetMapping("admin-thesis-overview")
    public String thesisOverview(Model model) {
        List<UserItem> userItemList = userItemService.findAllUserItemByUserType("thesis");
        List<Thesis> thesisList = new ArrayList<>();
        Map<Integer, UserInfo> createrMap = new HashMap<>();
        for (UserItem userItem : userItemList) {
            thesisList.add(thesisService.findThesisByTid(userItem.getItemId()));
        }
        for (Thesis thesis : thesisList) {
            createrMap.put(thesis.getCreateId(), userInfoService.findUserByUid(thesis.getCreateId()));
        }
        //将projectList倒序
        Collections.reverse(thesisList);
        model.addAttribute("createrMap", createrMap);
        model.addAttribute("thesisList", thesisList);
        model.addAttribute("dateKit", new DateKit());
        return "admin/thesis-overview";
    }

    // 来到奖励总览页面
    @GetMapping("admin-reward-overview")
    public String rewardOverview(Model model) {
        List<UserItem> userItemList = userItemService.findAllUserItemByUserType("reward");
        List<Reward> rewardList = new ArrayList<>();
        Map<Integer, UserInfo> createrMap = new HashMap<>();
        for (UserItem userItem : userItemList) {
            rewardList.add(rewardService.findRewardByRid(userItem.getItemId()));
        }
        for (Reward reward : rewardList) {
            createrMap.put(reward.getCreateId(), userInfoService.findUserByUid(reward.getCreateId()));
        }
        //将rewardList倒序
        Collections.reverse(rewardList);
        model.addAttribute("createrMap", createrMap);
        model.addAttribute("rewardList", rewardList);
        model.addAttribute("dateKit", new DateKit());
        return "admin/reward-overview";
    }
}

