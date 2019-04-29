package com.qilinxx.rms.controller;

import com.qilinxx.rms.configure.WebConst;
import com.qilinxx.rms.domain.model.*;
import com.qilinxx.rms.domain.model.vo.UserInfoVo;
import com.qilinxx.rms.service.*;
import com.qilinxx.rms.util.Commons;
import com.qilinxx.rms.util.DateKit;
import com.qilinxx.rms.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    TextbookService textbookService;

    @Autowired
    MeetingService meetingService;

    @Autowired
    DocumentService documentService;

    @Autowired
    MajorService majorService;

    @Autowired
    LogService logService;

    @Autowired
    RewardService rewardService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    ExportExcelService exportExcelService;

    @RequestMapping("/adminIndex")
    public String showIndex(HttpServletRequest request) {
        HttpSession session=request.getSession();
        String i= (String) session.getAttribute("uid");
        UserInfo login=userInfoService.findUserByUid(i);
        if (login.getRemake()==null||!login.getRemake().equals("admin")) {
            session.invalidate();
            return "redirect:/login";
        }
        logService.insertLog("管理员登录", "admin", userIp(request));
        return "admin/index";
    }

    @RequestMapping("admin-password")
    public String changeAdminPassword(){
        return "admin/password-change";
    }
    /**
     * @return  来到教材总览页面
     */
    @GetMapping("admin-textbook-overview")
    public String textbookOverview(HttpSession session, Model model){
        List<UserItem> userItemList = userItemService.findAllUserItemByUserType("textbook");
        List<Textbook> textbookList=new ArrayList<>();
        Map<String,UserInfo> createrMap=new HashMap<>();
        for (UserItem userItem:userItemList) {
            textbookList.add(textbookService.findTextbookById(userItem.getItemId()));
        }
        for (Textbook textbook:textbookList) {
            createrMap.put(textbook.getCreateId(),userInfoService.findUserByUid(textbook.getCreateId()));
        }
        //将projectList倒序
        Collections.reverse(textbookList);
        model.addAttribute("createrMap",createrMap);
        model.addAttribute("textbookList",textbookList);
        model.addAttribute("dateKit",new DateKit());
        return "admin/textbook-overview";
    }
    /**
     * @return  来到会议总览页面
     */
    @GetMapping("admin-meeting-overview")
    public String meetingOverview(HttpSession session,Model model){
        List<UserItem> userItemList = userItemService.findAllUserItemByUserType("meeting");
        List<Meeting> meetingList=new ArrayList<>();
        Map<String,UserInfo> createrMap=new HashMap<>();
        for (UserItem userItem:userItemList) {
            meetingList.add(meetingService.findMeetingById(userItem.getItemId()));
        }
        for (Meeting meeting:meetingList) {
            createrMap.put(meeting.getCreateId(),userInfoService.findUserByUid(meeting.getCreateId()));
        }
        //将projectList倒序
        Collections.reverse(meetingList);
        model.addAttribute("createrMap",createrMap);
        model.addAttribute("meetingList",meetingList);
        model.addAttribute("dateKit",new DateKit());
        return "admin/meeting-overview";
    }

    /**
     * @param id    itemId
     * @param itemType  item的类别
     * @return      来到item的详情页面，以项目详情为主要页面
     */
    @GetMapping("admin-item-detail")
    public String itemDetail(String id,String itemType,Model model,HttpSession session,String from){
        boolean display=true;
        if(!from.equals("user")){
            display=false;
        }
        Map<String,UserInfo> createrMap=new HashMap<>();
        switch(itemType){
            case "project":
                Project project = projectService.findProjectByPid(id);
                if (project.getState().equals("2")){
                    display=false;
                }
                createrMap.put(project.getCreateId(),userInfoService.findUserByUid(project.getCreateId()));
                model.addAttribute("project",project);
                break;
            case "thesis":
                Thesis thesis = thesisService.findThesisByTid(id);
                if (thesis.getState().equals("2")){
                    display=false;
                }
                createrMap.put(thesis.getCreateId(),userInfoService.findUserByUid(thesis.getCreateId()));
                model.addAttribute("thesis",thesis);
                break;
            case "reward":
                Reward reward = rewardService.findRewardByRid(id);
                if (reward.getState().equals("2")){
                    display=false;
                }
                createrMap.put(reward.getCreateId(),userInfoService.findUserByUid(reward.getCreateId()));
                model.addAttribute("reward",reward);
                break;
            case "textbook":
                Textbook textbook = textbookService.findTextbookById(id);
                if (textbook.getState().equals("2")){
                    display=false;
                }
                createrMap.put(textbook.getCreateId(),userInfoService.findUserByUid(textbook.getCreateId()));
                model.addAttribute("textbook",textbook);
                break;
            case "meeting":
                Meeting meeting = meetingService.findMeetingById(id);
                if (meeting.getState().equals("2")){
                    display=false;
                }
                createrMap.put(meeting.getCreateId(),userInfoService.findUserByUid(meeting.getCreateId()));
                model.addAttribute("meeting",meeting);
                break;
        }

        List<Document> documentList = documentService.findDocumentByItemId(id);
        model.addAttribute("display",display);
        model.addAttribute("documentList",documentList);
        model.addAttribute("itemType",itemType);
        model.addAttribute("createrMap",createrMap);
        model.addAttribute("dateKit",new DateKit());
        return "manager/detail/item-detail";
    }


    /**
     * @param id    itemId
     * @param itemType  item的类别
     * @return      来到item的详情页面,以附件面为主要页面
     */
    @GetMapping("admin-item-detail-file")
    public String itemDetailFile(String id,String itemType,Model model,HttpSession session,String from){
        boolean display=true;
        if(!from.equals("user")){
            display=false;
        }
        Map<String,UserInfo> createrMap=new HashMap<>();
        switch(itemType){
            case "project":
                Project project = projectService.findProjectByPid(id);
                if (project.getState().equals("2")){
                    display=false;
                }
                createrMap.put(project.getCreateId(),userInfoService.findUserByUid(project.getCreateId()));
                model.addAttribute("project",project);
                break;
            case "thesis":
                Thesis thesis = thesisService.findThesisByTid(id);
                if (thesis.getState().equals("2")){
                    display=false;
                }
                createrMap.put(thesis.getCreateId(),userInfoService.findUserByUid(thesis.getCreateId()));
                model.addAttribute("thesis",thesis);
                break;
            case "reward":
                Reward reward = rewardService.findRewardByRid(id);
                if (reward.getState().equals("2")){
                    display=false;
                }
                createrMap.put(reward.getCreateId(),userInfoService.findUserByUid(reward.getCreateId()));
                model.addAttribute("reward",reward);
                break;
            case "textbook":
                Textbook textbook = textbookService.findTextbookById(id);
                if (textbook.getState().equals("2")){
                    display=false;
                }
                createrMap.put(textbook.getCreateId(),userInfoService.findUserByUid(textbook.getCreateId()));
                model.addAttribute("textbook",textbook);
                break;
            case "meeting":
                Meeting meeting = meetingService.findMeetingById(id);
                if (meeting.getState().equals("2")){
                    display=false;
                }
                createrMap.put(meeting.getCreateId(),userInfoService.findUserByUid(meeting.getCreateId()));
                model.addAttribute("meeting",meeting);
                break;
        }

        List<Document> documentList = documentService.findDocumentByItemId(id);
        model.addAttribute("display",display);
        model.addAttribute("documentList",documentList);
        model.addAttribute("itemType",itemType);
        model.addAttribute("createrMap",createrMap);
        model.addAttribute("dateKit",new DateKit());
        return "manager/detail/item-detail-file";
    }



    @Transactional
    @RequestMapping("/exportUserInfo")
    @ResponseBody
    public void exportUserInfo(String[] chk,HttpServletResponse response){
//        response.setContentType("application/octet-stream");
//        response.setContentType("application/OCTET-STREAM;charset=UTF-8");
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=" +System.currentTimeMillis()+".xlsx");
        System.out.println(Arrays.toString(chk));
        exportExcelService.exportUserInfo(chk,response);
    }
    @Transactional
    @RequestMapping("/exportProject")
    @ResponseBody
    public void exportProject(String[] chk,HttpServletResponse response){
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=" +System.currentTimeMillis()+".xlsx");
        System.out.println(Arrays.toString(chk));
        exportExcelService.exportProject(chk,response);
    }

    @Transactional
    @RequestMapping("/exportThesis")
    @ResponseBody
    public void exportThesis(String[] chk,HttpServletResponse response){
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=" +System.currentTimeMillis()+".xlsx");
        System.out.println(Arrays.toString(chk));
        exportExcelService.exportThesis(chk,response);
    }

    @Transactional
    @RequestMapping("/exportReward")
    @ResponseBody
    public void exportReward(String[] chk,HttpServletResponse response){
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=" +System.currentTimeMillis()+".xlsx");
        System.out.println(Arrays.toString(chk));
        exportExcelService.exportReward(chk,response);
    }

    @Transactional
    @RequestMapping("/exportTextbook")
    @ResponseBody
    public void exportTextbook(String[] chk,HttpServletResponse response){
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=" +System.currentTimeMillis()+".xlsx");
        System.out.println(Arrays.toString(chk));
        exportExcelService.exportTextbook(chk,response);
    }

    @Transactional
    @RequestMapping("/exportMeeting")
    @ResponseBody
    public void exportMeeting(String[] chk,HttpServletResponse response){
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=" +System.currentTimeMillis()+".xlsx");
        System.out.println(Arrays.toString(chk));
        exportExcelService.exportMeeting(chk,response);
    }
    @RequestMapping("/welcome")
    public String showWelcome(Model model) {
        Log log=logService.getLastAdminLog();
        model.addAttribute("log",log);
        model.addAttribute("commons", new Commons());
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
        List<Major> majorList=majorService.findAllMajor();
        model.addAttribute("majorList",majorList);
        model.addAttribute("userInfoList", infoList);
        model.addAttribute("commons", new Commons());
        return "admin/grant-list";
    }

    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    @ResponseBody
    public String updatePermission(@RequestParam("mid") int[] mid, String uid, String power) {
        System.out.println("updatePermission" + mid.length);
        System.out.println("uid:--" + uid);
        System.out.println(power);
        Integer i = userMajorService.updatePermission(uid, mid,power);
        if (i == mid.length)
            return "添加权限完成";
        else
            return "添加失败";
    }

    @RequestMapping(value = "/cancelPermission", method = RequestMethod.POST)
    @ResponseBody
    public String cancelPermission(String uid,String power) {
        System.out.println("uid:--" + uid);
        Integer i=userMajorService.cancelPermission(uid,power);
        System.out.println(i);
        return "添加失败";
    }

    @RequestMapping("/admin-student-add")
    public String Student_add(Model model) {
        List<Major> majorList=majorService.findAllMajor();
        model.addAttribute("majorList",majorList);
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
        userInfo.setPassword(String.valueOf(UUID.UU32().substring(0,6)));
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
            logService.insertLog("成功删除一个学生" + name, "admin", request.getRemoteAddr());
            return "删除成功";
        } else {
            logService.insertLog("删除一个学生" + name + "失败", "admin", request.getRemoteAddr());
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
        UserInfo student = userInfoService.findUserByUid(uid);
        List<Major> majors=majorService.findAllMajor();
        model.addAttribute("majorList",majors);
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
        Map<String, UserInfo> createrMap = new HashMap<>();
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
        Map<String, UserInfo> createrMap = new HashMap<>();
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
        Map<String, UserInfo> createrMap = new HashMap<>();
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

