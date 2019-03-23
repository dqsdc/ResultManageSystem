package com.qilinxx.rms.controller;

import com.qilinxx.rms.domain.model.*;
import com.qilinxx.rms.service.*;
import com.qilinxx.rms.util.DateKit;
import com.qilinxx.rms.util.FileKit;
import com.qilinxx.rms.util.UploadUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
public class ManagerController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserMajorService userMajorService;
    @Autowired
    MajorService majorService;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserItemService userItemService;
    @Autowired
    DocumentService documentService;
    @Autowired
    ThesisService thesisService;
    @Autowired
    RewardService rewardService;

    /**
     * @return  来到教师成果管理系统页面
     */
    @GetMapping({"main"})
    public String main(HttpSession session){
        //以下代码项目完成修改
        //session.setAttribute("uid",2013001);
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
        boolean achievementDisplay=false; boolean checkDisplay=false;
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        List<UserMajor> userMajorList = userMajorService.findAllUserMajorByUid((Integer) session.getAttribute("uid"));
        Map<Integer,Integer> majorMap=new HashMap<>();
        if(userMajorList.size()!=0){
            List<Major> majorList=new ArrayList<>();
            for (UserMajor um:userMajorList) {
                majorList.add(majorService.findMajorBymid(um.getMid()));
            }
            model.addAttribute("majorList",majorList);
            for (Major major:majorList) {
                int i = projectService.countProjectByMidState(major.getMid(),"0")+thesisService.countThesisByMidState(major.getMid(),"0")+rewardService.countRewardByMidState(major.getMid(),"0");
                majorMap.put(major.getMid(),i);
            }
            model.addAttribute("majorMap",majorMap);
        }
        if(user.getState().equals("3")){
            achievementDisplay=true;
            if(userMajorList.size()!=0){
                checkDisplay=true;
            }
        }
        model.addAttribute("achievementDisplay",achievementDisplay);
        model.addAttribute("checkDisplay",checkDisplay);
        model.addAttribute("user",user);
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
        user.setUid(dbUser.getUid());
        //若是账号第一次使用，通过修改密码激活账号
        if(dbUser.getState().equals("0")){
            user.setState("3");
            user.setUpdateTime(DateKit.getUnixTimeLong());
            userInfoService.modifyUser(user);
            json.put("msg","密码修改成功,激活账号！");
            return json;
        }
        user.setUpdateTime(DateKit.getUnixTimeLong());
        userInfoService.modifyUser(user);
        json.put("msg","密码修改成功！");
        return json;
    }





     //项目上传与其附件保存
    /**
     * @return 来到项目上传页面
     */
    @GetMapping("project-upload")
    public String project(HttpSession session){
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        //清空或者初始化fileList
        FileKit.setProjectFileList(FileKit.clearOrInitList(FileKit.getProjectFileList()));
        FileKit.deleteFile(new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//temp//project"));
        return "manager/upload/project-upload";
    }

    /**
     * ajax 提交项目附件
     * @param file  项目附件
     * @throws IOException
     */
    @PostMapping("ajax-project-file")
    @ResponseBody
    public JSONObject ajaxProjectFile(MultipartFile file, HttpSession session) throws IOException {
        JSONObject json=new JSONObject();
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        List<MultipartFile> projectFileList=FileKit.getProjectFileList();
        //手动去重
        int i=0;
        if(projectFileList.size()!=0){
            for (MultipartFile multipartFile:projectFileList) {
                if(multipartFile.getOriginalFilename().equals(file.getOriginalFilename())){
                    i++;
                }
            }
        }
        if (i==0){
            projectFileList.add(file);
            FileKit.setProjectFileList(projectFileList);

            String path = UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//temp//project";//存储的根路径 临时文件目录
            File dirFile=new File(path);
            dirFile.mkdirs();
            String fileName = file.getOriginalFilename();//原文件名
            File targetFile = new File(path, fileName);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(targetFile));
        }
        return json;
    }

    /**
     * ajax提交项目，保存附件
     * @param startTimeDate    研究开始时间
     * @param endTimeDate      研究结束时间
     * @param setTimeDate      立项时间
     * @throws IOException
     */
    @PostMapping("ajax-project-form")
    @ResponseBody
    public JSONObject ajaxProjectForm(HttpSession session,Project project,String startTimeDate,String endTimeDate,String setTimeDate) throws IOException {
        JSONObject json=new JSONObject();
        //以下三种种错误
        int projectNum = projectService.findProjectByNameHostFrom(project.getName(), project.getHost(), project.getProjectSource());
        if(projectNum!=0){
            json.put("msg","该项目已被提交！");
            return json;
        }
        List<MultipartFile> projectFileList = FileKit.getProjectFileList();
        if(projectFileList.size()==0){
            json.put("msg","请先上传附件！");
            return json;
        }
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        //姓名去重，并重新排序
        String[] names=project.getPeople().replace("，",",").replace("、",",").replace(" ","").split(",");
        Map<String,String> nameMap=new HashMap<>();
        String people="";
        for (String name:names) {
            if(!name.equals("")){
                nameMap.put(name,"");
            }
        }
        Iterator iterator1 = nameMap.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator1.next();
            people=people+(String)entry.getKey()+",";
        }
        nameMap.put(project.getHost(),"");//添加主持人
        if(!nameMap.containsKey(user.getName())){
            json.put("msg","此项目与本账号用户无关！");
            return json;
        }
        project.setPeople(people.substring(0,people.lastIndexOf(",")));
        /**
         * 新建项目记录
         */

        //将日期转化为时间戳
        startTimeDate+=" 00:00:00";endTimeDate+=" 00:00:00";setTimeDate+=" 00:00:00";
        project.setStartTime(Long.parseLong(String.valueOf(DateKit.getUnixTimeByDate(DateKit.dateFormat(startTimeDate)))));
        project.setEndTime(Long.parseLong(String.valueOf(DateKit.getUnixTimeByDate(DateKit.dateFormat(endTimeDate)))));
        project.setSetTime(Long.parseLong(String.valueOf(DateKit.getUnixTimeByDate(DateKit.dateFormat(setTimeDate)))));
        project.setPid(UUID.randomUUID().toString().replace("-",""));
        project.setState("0");
        project.setCreateId(user.getUid());
        project.setMid(user.getMid());
        project.setCreateTime(DateKit.getUnixTimeLong());
        projectService.createProject(project);
        /**
         * 新建用户与项目的关系记录
         */
        UserItem userItem=new UserItem();
        userItem.setItemId(project.getPid());
        userItem.setItemType("project");
        Iterator iterator2 = nameMap.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator2.next();
            List<UserInfo> userInfoList = userInfoService.findUserByName((String)entry.getKey());
            if(userInfoList.size()!=0){
                userItem.setUid(userInfoList.get(0).getUid());
                userItemService.createUserItem(userItem);
            }
        }
        /**
         * 新建附件记录
         */
        Document document=new Document();
        document.setItemId(project.getPid());
        document.setItemType("project");
        String fileName="";
        for (MultipartFile file:projectFileList) {
            fileName=file.getOriginalFilename();
            document.setName(fileName);
            document.setType(fileName.substring(fileName.lastIndexOf(".")));
            document.setPath("upload\\"+user.getUid()+"\\project\\"+project.getCreateTime()+"\\"+fileName);
            documentService.createDocument(document);
            //文件拷贝与删除
            File tempFile=new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//temp//project",fileName);
            File targetFile=new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//project//"+project.getCreateTime(),fileName);
            File pathFile=new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//project//"+project.getCreateTime());
            pathFile.mkdirs();
            FileCopyUtils.copy(tempFile,targetFile);
            FileKit.deleteFile(tempFile);
        }
        FileKit.setProjectFileList(FileKit.clearOrInitList(projectFileList));
        File dirFile = new File(UploadUtil.getUploadFilePath() + "/upload//" + user.getUid() + "//temp//project");
        FileKit.deleteFile(dirFile);
        json.put("msg","提交成功待审核！");
        return json;
    }





    //论文上传与其附件保存
    /**
     * @return 来到论文上传页面
     */
    @GetMapping("thesis-upload")
    public String thesis(HttpSession session){
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        //清空或者初始化fileList
        FileKit.setThesisFileList(FileKit.clearOrInitList(FileKit.getThesisFileList()));
        FileKit.deleteFile(new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//temp//thesis"));
        return "manager/upload/thesis-upload";
    }
    /**
     * ajax 提交论文附件
     * @param file  论文附件
     * @throws IOException
     */
    @PostMapping("ajax-thesis-file")
    @ResponseBody
    public JSONObject ajaxThesisFile(MultipartFile file, HttpSession session) throws IOException {
        JSONObject json=new JSONObject();
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        List<MultipartFile> thesisFileList=FileKit.getThesisFileList();
        //手动去重
        int i=0;
        if(thesisFileList.size()!=0){
            for (MultipartFile multipartFile:thesisFileList) {
                if(multipartFile.getOriginalFilename().equals(file.getOriginalFilename())){
                    i++;
                }
            }
        }
        if (i==0){
            thesisFileList.add(file);
            FileKit.setThesisFileList(thesisFileList);

            String path = UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//temp//thesis";//存储的根路径 临时文件目录
            File dirFile=new File(path);
            dirFile.mkdirs();
            String fileName = file.getOriginalFilename();//原文件名
            File targetFile = new File(path, fileName);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(targetFile));
        }
        return json;
    }

    /**
     *  ajax 提交论文 保存附件
     * @param startPage 起始页码
     * @param endPage   结束页码
     * @throws IOException
     */
    @PostMapping("ajax-thesis-form")
    @ResponseBody
    public JSONObject ajaxThesisForm(Thesis thesis,Integer startPage,Integer endPage, HttpSession session) throws IOException {
        JSONObject json=new JSONObject();
        //以下三种错误
        int thesisNum = thesisService.findThesisByHostName(thesis.getHost(), thesis.getName());
        if(thesisNum!=0){
            json.put("msg","该项目已被提交！");
            return json;
        }
        List<MultipartFile> thesisFileList = FileKit.getThesisFileList();
        if(thesisFileList.size()==0){
            json.put("msg","请先上传附件！");
            return json;
        }
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        //姓名去重，并重新排序
        String[] names=thesis.getPeople().replace("，",",").replace("、",",").replace(" ","").split(",");
        Map<String,String> nameMap=new HashMap<>();
        String people="";
        for (String name:names) {
            if(!name.equals("")){
                nameMap.put(name,"");
            }
        }
        Iterator iterator1 = nameMap.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator1.next();
            people=people+(String)entry.getKey()+",";
        }
        nameMap.put(thesis.getHost(),"");//添加主持人
        if(!nameMap.containsKey(user.getName())){
            json.put("msg","此论文与本账号用户无关！");
            return json;
        }
        thesis.setPeople(people.substring(0,people.lastIndexOf(",")));
        /**
         * 新建论文记录
         */
        thesis.setPageNum(startPage+"-"+endPage);
        thesis.setTid(UUID.randomUUID().toString().replace("-",""));
        thesis.setState("0");
        thesis.setCreateId(user.getUid());
        thesis.setCreateTime(DateKit.getUnixTimeLong());
        thesis.setMid(user.getMid());
        thesisService.createThesis(thesis);
        /**
         * 新建用户与论文的关系记录
         */
        UserItem userItem=new UserItem();
        userItem.setItemId(thesis.getTid());
        userItem.setItemType("thesis");
        Iterator iterator2 = nameMap.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator2.next();
            List<UserInfo> userInfoList = userInfoService.findUserByName((String)entry.getKey());
            if(userInfoList.size()!=0){
                userItem.setUid(userInfoList.get(0).getUid());
                userItemService.createUserItem(userItem);
            }
        }
        /**
         * 新建附件记录
         */
        Document document=new Document();
        document.setItemId(thesis.getTid());
        document.setItemType("thesis");
        String fileName="";
        for (MultipartFile file:thesisFileList) {
            fileName=file.getOriginalFilename();
            document.setName(fileName);
            document.setType(fileName.substring(fileName.lastIndexOf(".")));
            document.setPath("upload\\"+user.getUid()+"\\thesis\\"+thesis.getCreateTime()+"\\"+fileName);
            documentService.createDocument(document);
            //文件拷贝与删除
            File tempFile=new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//temp//thesis",fileName);
            File targetFile=new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//thesis//"+thesis.getCreateTime(),fileName);
            File pathFile=new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//thesis//"+thesis.getCreateTime());
            pathFile.mkdirs();
            FileCopyUtils.copy(tempFile,targetFile);
            FileKit.deleteFile(tempFile);
        }
        FileKit.setThesisFileList(FileKit.clearOrInitList(thesisFileList));
        File dirFile = new File(UploadUtil.getUploadFilePath() + "/upload//" + user.getUid() + "//temp//thesis");
        FileKit.deleteFile(dirFile);

        json.put("msg","提交成功待审核！");
        return json;
    }





    //奖励上传与其附件保存
    /**
     * @return  来到奖励上传页面
     */
    @GetMapping("reward-upload")
    public String reward(HttpSession session){
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        //清空或者初始化fileList
        FileKit.setRewardFileList(FileKit.clearOrInitList(FileKit.getRewardFileList()));
        FileKit.deleteFile(new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//temp//reward"));
        return "manager/upload/reward-upload";
    }

    /**
     * ajax 提交奖励附件
     * @param file  奖励附件
     * @throws IOException
     */
    @PostMapping("ajax-reward-file")
    @ResponseBody
    public JSONObject ajaxRewardFile(MultipartFile file, HttpSession session) throws IOException {
        JSONObject json=new JSONObject();
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        List<MultipartFile> rewardFileList=FileKit.getRewardFileList();
        //手动去重
        int i=0;
        if(rewardFileList.size()!=0){
            for (MultipartFile multipartFile:rewardFileList) {
                if(multipartFile.getOriginalFilename().equals(file.getOriginalFilename())){
                    i++;
                }
            }
        }
        if (i==0){
            rewardFileList.add(file);
            FileKit.setRewardFileList(rewardFileList);

            String path = UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//temp//reward";//存储的根路径 临时文件目录
            File dirFile=new File(path);
            dirFile.mkdirs();
            String fileName = file.getOriginalFilename();//原文件名
            File targetFile = new File(path, fileName);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(targetFile));
        }
        return json;
    }

    /**
     * ajax提交奖励，保存附件
     * @param getTimeDate    研究开始时间
     * @throws IOException
     */
    @PostMapping("ajax-reward-form")
    @ResponseBody
    public JSONObject ajaxProjectForm(HttpSession session,Reward reward,String getTimeDate) throws IOException {
        JSONObject json=new JSONObject();
        //日期转化为时间戳
        getTimeDate+=" 00:00:00";
        reward.setGetTime(Long.parseLong(String.valueOf(DateKit.getUnixTimeByDate(DateKit.dateFormat(getTimeDate)))));

        //姓名去重，并重新排序
        String[] names=reward.getPeople().replace("，",",").replace("、",",").replace(" ","").split(",");
        Map<String,String> nameMap=new HashMap<>();
        String people="";
        for (String name:names) {
            if(!name.equals("")){
                nameMap.put(name,"");
            }
        }
        Iterator iterator1 = nameMap.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator1.next();
            people=people+(String)entry.getKey()+",";
        }
        reward.setPeople(people.substring(0,people.lastIndexOf(",")));

        //以下两种错误
        int rewardNum = rewardService.findRewardByNamePeopleGetTime(reward.getName(), reward.getPeople(),reward.getGetTime());
        if(rewardNum!=0){
            json.put("msg","该奖励已被提交！");
            return json;
        }
        List<MultipartFile> rewardFileList = FileKit.getRewardFileList();
        if(rewardFileList.size()==0){
            json.put("msg","请先上传附件！");
            return json;
        }
        UserInfo user = userInfoService.findUserByUid((Integer) session.getAttribute("uid"));
        if(!nameMap.containsKey(user.getName())){
            json.put("msg","此奖励与本账号用户无关！");
            return json;
        }
        /**
         * 新建奖励记录
         */
        //将日期转化为时间戳
        reward.setGetTime(Long.parseLong(String.valueOf(DateKit.getUnixTimeByDate(DateKit.dateFormat(getTimeDate)))));
        reward.setRid(UUID.randomUUID().toString().replace("-",""));
        reward.setState("0");
        reward.setCreateId(user.getUid());
        reward.setMid(user.getMid());
        reward.setCreateTime(DateKit.getUnixTimeLong());
        rewardService.createReward(reward);
        /**
         * 新建用户与奖励的关系记录
         */
        UserItem userItem=new UserItem();
        userItem.setItemId(reward.getRid());
        userItem.setItemType("reward");
        Iterator iterator2 = nameMap.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator2.next();
            List<UserInfo> userInfoList = userInfoService.findUserByName((String)entry.getKey());
            if(userInfoList.size()!=0){
                userItem.setUid(userInfoList.get(0).getUid());
                userItemService.createUserItem(userItem);
            }
        }
        /**
         * 新建附件记录
         */
        Document document=new Document();
        document.setItemId(reward.getRid());
        document.setItemType("reward");
        String fileName="";
        for (MultipartFile file:rewardFileList) {
            fileName=file.getOriginalFilename();
            document.setName(fileName);
            document.setType(fileName.substring(fileName.lastIndexOf(".")));
            document.setPath("upload\\"+user.getUid()+"\\reward\\"+reward.getCreateTime()+"\\"+fileName);
            documentService.createDocument(document);
            //文件拷贝与删除
            File tempFile=new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//temp//reward",fileName);
            File targetFile=new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//reward//"+reward.getCreateTime(),fileName);
            File pathFile=new File(UploadUtil.getUploadFilePath() + "/upload//"+user.getUid()+"//reward//"+reward.getCreateTime());
            pathFile.mkdirs();
            FileCopyUtils.copy(tempFile,targetFile);
            FileKit.deleteFile(tempFile);
        }
        FileKit.setRewardFileList(FileKit.clearOrInitList(rewardFileList));
        File dirFile = new File(UploadUtil.getUploadFilePath() + "/upload//" + user.getUid() + "//temp//reward");
        FileKit.deleteFile(dirFile);
        json.put("msg","提交成功待审核！");
        return json;
    }









    /**
     * @return  来到项目总览页面
     */
    @GetMapping("project-overview")
    public String projectOverview(HttpSession session,Model model){
        Integer uid=(Integer) session.getAttribute("uid");
        List<UserItem> userItemList = userItemService.findUserItemByUidItemType(uid, "project");
        List<Project> projectList=new ArrayList<>();
        Map<Integer,UserInfo> createrMap=new HashMap<>();
        for (UserItem userItem:userItemList) {
            projectList.add(projectService.findProjectByPid(userItem.getItemId()));
        }
        for (Project project:projectList) {
            createrMap.put(project.getCreateId(),userInfoService.findUserByUid(project.getCreateId()));
        }
        //将projectList倒序
        Collections.reverse(projectList);
        model.addAttribute("uid",uid);
        model.addAttribute("createrMap",createrMap);
        model.addAttribute("projectList",projectList);
        model.addAttribute("dateKit",new DateKit());
        return "manager/overview/project-overview";
    }
    /**
     * @return  来到论文总览页面
     */
    @GetMapping("thesis-overview")
    public String thesisOverview(HttpSession session,Model model){
        Integer uid=(Integer) session.getAttribute("uid");
        List<UserItem> userItemList = userItemService.findUserItemByUidItemType(uid, "thesis");
        List<Thesis> thesisList=new ArrayList<>();
        Map<Integer,UserInfo> createrMap=new HashMap<>();
        for (UserItem userItem:userItemList) {
            thesisList.add(thesisService.findThesisByTid(userItem.getItemId()));
        }
        for (Thesis thesis:thesisList) {
            createrMap.put(thesis.getCreateId(),userInfoService.findUserByUid(thesis.getCreateId()));
        }
        //将projectList倒序
        Collections.reverse(thesisList);
        model.addAttribute("uid",uid);
        model.addAttribute("createrMap",createrMap);
        model.addAttribute("thesisList",thesisList);
        model.addAttribute("dateKit",new DateKit());
        return "manager/overview/thesis-overview";
    }
    /**
     * @return  来到论文总览页面
     */
    @GetMapping("reward-overview")
    public String rewardOverview(HttpSession session,Model model){
        Integer uid=(Integer) session.getAttribute("uid");
        List<UserItem> userItemList = userItemService.findUserItemByUidItemType(uid, "reward");
        List<Reward> rewardList=new ArrayList<>();
        Map<Integer,UserInfo> createrMap=new HashMap<>();
        for (UserItem userItem:userItemList) {
            rewardList.add(rewardService.findRewardByRid(userItem.getItemId()));
        }
        for (Reward reward:rewardList) {
            createrMap.put(reward.getCreateId(),userInfoService.findUserByUid(reward.getCreateId()));
        }
        //将rewardList倒序
        Collections.reverse(rewardList);
        model.addAttribute("uid",uid);
        model.addAttribute("createrMap",createrMap);
        model.addAttribute("rewardList",rewardList);
        model.addAttribute("dateKit",new DateKit());
        return "manager/overview/reward-overview";
    }






    //item公共用方法
    /**
     * ajax删除所传id  的item
     * @param itemType     为item类别
     * @param id        为itemId
     */
    @PostMapping("ajax-item-delete")
    @ResponseBody
    public JSONObject ajaxItemDelete(String itemType,String id){
        JSONObject json=new JSONObject();
        switch(itemType){
            case "project":
                projectService.deleteProjectByPid(id);
                userItemService.deleteUserItemByItemId(id);
                List<Document> documentList1 = documentService.findDocumentByItemId(id);
                if(documentList1.size()!=0){
                    FileKit.deleteFile(new File(UploadUtil.getUploadFilePath()+"//"+documentList1.get(0).getPath()).getParentFile());
                }
                documentService.deleteDocumentByItemId(id);
                break;
            case "thesis":
                thesisService.deleteThesisByTid(id);
                userItemService.deleteUserItemByItemId(id);
                List<Document> documentList2 = documentService.findDocumentByItemId(id);
                if(documentList2.size()!=0){
                    FileKit.deleteFile(new File(UploadUtil.getUploadFilePath()+"//"+documentList2.get(0).getPath()).getParentFile());
                }
                documentService.deleteDocumentByItemId(id);
                break;
            case "reward":
                rewardService.deleteReward(id);
                userItemService.deleteUserItemByItemId(id);
                List<Document> documentList3 = documentService.findDocumentByItemId(id);
                if(documentList3.size()!=0){
                    FileKit.deleteFile(new File(UploadUtil.getUploadFilePath()+"//"+documentList3.get(0).getPath()).getParentFile());
                }
                documentService.deleteDocumentByItemId(id);
                break;
            default:
                json.put("msg","删除失败！");
                return json;
        }
        json.put("msg","删除成功！");
        return json;
    }

    /**
     * @param id    itemId
     * @param itemType  item的类别
     * @return      来到item的详情页面
     */
    @GetMapping("item-detail")
    public String projectDetail(String id,String itemType,Model model,HttpSession session,String from){
        UserInfo user=userInfoService.findUserByUid((Integer)session.getAttribute("uid"));
        boolean display=true;
        if(!from.equals("user")){
            display=false;
        }
        Map<Integer,UserInfo> createrMap=new HashMap<>();
        switch(itemType){
            case "project":
                Project project = projectService.findProjectByPid(id);
                if (project.getState().equals("2")||!project.getCreateId().equals(user.getUid())){
                    display=false;
                }
                createrMap.put(project.getCreateId(),userInfoService.findUserByUid(project.getCreateId()));
                model.addAttribute("project",project);
                break;
            case "thesis":
                Thesis thesis = thesisService.findThesisByTid(id);
                if (thesis.getState().equals("2")){//||!thesis.getCreateId().equals(user.getUid())
                    display=false;
                }
                createrMap.put(thesis.getCreateId(),userInfoService.findUserByUid(thesis.getCreateId()));
                model.addAttribute("thesis",thesis);
                break;
            case "reward":
                Reward reward = rewardService.findRewardByRid(id);
                if (reward.getState().equals("2")||!reward.getCreateId().equals(user.getUid())){
                    display=false;
                }
                createrMap.put(reward.getCreateId(),userInfoService.findUserByUid(reward.getCreateId()));
                model.addAttribute("reward",reward);
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
     * 附件下载
     * @param did   文件document的id
     * @param response
     * @throws IOException
     */
    @GetMapping("download")
    public void  download(Integer did, HttpServletResponse response) throws IOException {
        Document document = documentService.findDocumentByDid(did);
        String fileName = document.getName();// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File(UploadUtil.getUploadFilePath()+"//"+document.getPath());
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /** ajax 附件删除
     * @param did   文件document的id
     * @return
     */
    @PostMapping("ajax-document-delete")
    @ResponseBody
    public JSONObject ajaxDocumentDelete(Integer did,String itemType){
        JSONObject json=new JSONObject();

        Document document = documentService.findDocumentByDid(did);
        List<Document> documentList = documentService.findDocumentByItemId(document.getItemId());
        if(documentList.size()==1){
            json.put("msg","至少保留一个文件！");
            return json;
        }
        FileKit.deleteFile(new File(UploadUtil.getUploadFilePath()+"//"+document.getPath()));
        documentService.deleteDocumentByDid(did);
        //item 记录更新
        switch(itemType){
            case "project":
                Project project=new Project();
                project.setPid(document.getItemId());project.setState("0");project.setUpdateTime(DateKit.getUnixTimeLong());
                projectService.updateProject(project);
                break;
            case "thesis":
                Thesis thesis=new Thesis();
                thesis.setTid(document.getItemId());thesis.setState("0");thesis.setUpdateTime(DateKit.getUnixTimeLong());
                thesisService.updateThesis(thesis);
                break;
            case "reward":
                Reward reward=new Reward();
                reward.setRid(document.getItemId());reward.setState("0");reward.setUpdateTime(DateKit.getUnixTimeLong());
                rewardService.updateReward(reward);
        }
        json.put("msg","删除成功！");
        return json;
    }

    /**
     * @param mid   项目的 专业分类
     * @return  来到项目、论文、奖励审核页面
     */
    @GetMapping("check")
    public String check(Integer mid,Model model){
        Map<Integer,UserInfo> createrMap=new HashMap<>();
        List<Project> projectList = projectService.findProjectByMid(mid);
        int projectNum=0,thesisNum=0,rewardNum=0;
        if(projectList.size()!=0){
            for (Project project:projectList) {
                createrMap.put(project.getCreateId(),userInfoService.findUserByUid(project.getCreateId()));
                if(project.getState().equals("0")){
                    projectNum++;
                }
            }
        }
        List<Thesis> thesisList = thesisService.findThesisByMid(mid);
        if(thesisList.size()!=0){
            for(Thesis thesis:thesisList){
                createrMap.put(thesis.getCreateId(),userInfoService.findUserByUid(thesis.getCreateId()));
                if(thesis.getState().equals("0")){
                    thesisNum++;
                }
            }
        }
        List<Reward> rewardList = rewardService.findRewardByMid(mid);
       if(rewardList.size()!=0){
           for(Reward reward:rewardList){
               createrMap.put(reward.getCreateId(),userInfoService.findUserByUid(reward.getCreateId()));
               if(reward.getState().equals("0")){
                   rewardNum++;
               }
           }
       }
       model.addAttribute("projectNum",projectNum);
       model.addAttribute("thesisNum",thesisNum);
       model.addAttribute("rewardNum",rewardNum);
        model.addAttribute("thesisList",thesisList);
        model.addAttribute("rewardList",rewardList);
        model.addAttribute("projectList",projectList);
        model.addAttribute("createrMap",createrMap);
        model.addAttribute("dateKit",new DateKit());
        return "manager/check/check";
    }

    @PostMapping("ajax-item-start")
    @ResponseBody
    public JSONObject  ajaxItemStart(String itemType,String id){
        JSONObject json=new JSONObject();
        switch(itemType){
            case "project":
                Project project=new Project();
                project.setPid(id);project.setState("2");project.setUpdateTime(DateKit.getUnixTimeLong());
                projectService.updateProject(project);
                break;
            case "thesis":
                Thesis thesis=new Thesis();
                thesis.setTid(id);thesis.setState("2");thesis.setUpdateTime(DateKit.getUnixTimeLong());
                thesisService.updateThesis(thesis);
                break;
            case "reward":
                Reward reward=new Reward();
                reward.setRid(id);reward.setState("2");reward.setUpdateTime(DateKit.getUnixTimeLong());
                rewardService.updateReward(reward);
                break;
        }
        json.put("msg","项目通过审核");
        return json;
    }

    @PostMapping("ajax-item-stop")
    @ResponseBody
    public JSONObject  ajaxItemStop(String itemType,String id){
        JSONObject json=new JSONObject();
        switch(itemType){
            case "project":
                Project project=new Project();
                project.setPid(id);project.setState("1");project.setUpdateTime(DateKit.getUnixTimeLong());
                projectService.updateProject(project);
                break;
            case "thesis":
                Thesis thesis=new Thesis();
                thesis.setTid(id);thesis.setState("1");thesis.setUpdateTime(DateKit.getUnixTimeLong());
                thesisService.updateThesis(thesis);
                break;
            case "reward":
                Reward reward=new Reward();
                reward.setRid(id);reward.setState("1");reward.setUpdateTime(DateKit.getUnixTimeLong());
                rewardService.updateReward(reward);
                break;
        }
        json.put("msg","项目为通过审核");
        return json;
    }

}
