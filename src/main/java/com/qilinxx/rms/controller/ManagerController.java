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

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        int projectNum = projectService.findProjectByNameHostFrom(project.getName(), project.getHost(), project.getsource());
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

}
