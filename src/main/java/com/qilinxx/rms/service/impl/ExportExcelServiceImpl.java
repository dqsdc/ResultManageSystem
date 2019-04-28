package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.*;
import com.qilinxx.rms.domain.model.*;
import com.qilinxx.rms.domain.model.eo.*;
import com.qilinxx.rms.service.ExportExcelService;
import com.qilinxx.rms.util.DateKit;
import com.qilinxx.rms.util.ExportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * @auther: dqsdc
 * @Date: 2019-03-25 16:17
 * @Description:
 */
@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ThesisMapper thesisMapper;

    @Autowired
    RewardMapper rewardMapper;

    @Autowired
    TextbookMapper textbookMapper;

    @Autowired
    MeetingMapper meetingMapper;

    @Autowired
    MajorMapper majorMapper;

    @Override
    public void exportUserInfo(String[] ids,HttpServletResponse response) {
        ArrayList<UserInfoEo> eos = new ArrayList<>();
        for (String id : ids) {
            UserInfo eo = userInfoMapper.selectByPrimaryKey(id);
            eos.add((UserInfoEo) pojo2Eo(eo,new UserInfoEo()));
        }
        ExportExcelUtil<UserInfoEo> util = new ExportExcelUtil<>();
        String[] columnNames = {"工号", "姓名", "密码","性别","专业","院系"};
        try {
            util.exportExcel("用户导出", columnNames, eos,
                    response.getOutputStream(), ExportExcelUtil.EXCEl_FILE_2007);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportProject(String[] ids,HttpServletResponse response) {
        ArrayList<ProjectEo> eos = new ArrayList<>();
        for (String id : ids) {
            Project eo = projectMapper.selectByPrimaryKey(id);
                eos.add((ProjectEo) pojo2Eo(eo,new ProjectEo()));
        }
        ExportExcelUtil<ProjectEo> util = new ExportExcelUtil<>();
        String[] columnNames = {"项目名称", "项目题目", "项目类型","主持人","参与者","研究开始时间","研究结束时间","立项时间","项目来源","项目经费","项目编号","项目等级","项目类别","项目类型","简介","创建时间"};

        try {
            util.exportExcel("用户导出", columnNames, eos,
                    response.getOutputStream(), ExportExcelUtil.EXCEl_FILE_2007);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportThesis(String[] ids,HttpServletResponse response) {
        ArrayList<ThesisEo> eos = new ArrayList<>();
        for (String id : ids) {
            Thesis eo = thesisMapper.selectByPrimaryKey(id);
            eos.add((ThesisEo) pojo2Eo(eo,new ThesisEo()));
        }
        ExportExcelUtil<ThesisEo> util = new ExportExcelUtil<>();
        String[] columnNames = {"论文名称", "第一作者", "其他作者","期刊","年","卷","期","页码","级别","类别","发表时间","创建时间"};
        try {
            util.exportExcel("用户导出", columnNames, eos,
                    response.getOutputStream(), ExportExcelUtil.EXCEl_FILE_2007);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportReward(String[] ids, HttpServletResponse response) {
         ArrayList<RewardEo> eos = new ArrayList<>();
        for (String id : ids) {
            Reward eo = rewardMapper.selectByPrimaryKey(id);
            eos.add((RewardEo) pojo2Eo(eo,new RewardEo()));
        }
        ExportExcelUtil<RewardEo> util = new ExportExcelUtil<>();
        String[] columnNames = {"获奖名称", "获奖人", "奖励级别","奖励等级","授奖单位","获奖时间","奖励简介","创建时间"};
        try {
            util.exportExcel("用户导出", columnNames, eos,
                    response.getOutputStream(), ExportExcelUtil.EXCEl_FILE_2007);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void exportTextbook(String[] ids, HttpServletResponse response) {
        ArrayList<TextbookEo> eos = new ArrayList<>();
        for (String id : ids) {
            Textbook eo = textbookMapper.selectByPrimaryKey(id);
            eos.add((TextbookEo) pojo2Eo(eo,new TextbookEo()));
        }
        ExportExcelUtil<TextbookEo> util = new ExportExcelUtil<>();
        String[] columnNames = {"教材名称", "主编", "出版社","出版年月","ISBN编号","规划类别","教材获奖","教材简介","创建时间"};
        try {
            util.exportExcel("用户导出", columnNames, eos,
                    response.getOutputStream(), ExportExcelUtil.EXCEl_FILE_2007);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportMeeting(String[] ids, HttpServletResponse response) {
        ArrayList<MeetingEo> eos = new ArrayList<>();
        for (String id : ids) {
            Meeting eo = meetingMapper.selectByPrimaryKey(id);
            eos.add((MeetingEo) pojo2Eo(eo,new MeetingEo()));
        }
        ExportExcelUtil<MeetingEo> util = new ExportExcelUtil<>();
        String[] columnNames = {"会议名称", "参会人员", "主办单位","承办单位","会议开始时间","会议结束时间","会议简介","创建时间"};
        try {
            util.exportExcel("用户导出", columnNames, eos,
                    response.getOutputStream(), ExportExcelUtil.EXCEl_FILE_2007);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Object pojo2Eo(Object pojo, Object eo) {
        Field[] fields = pojo.getClass().getDeclaredFields();
        try {
            Field[] fields2 = eo.getClass().getDeclaredFields();
            for (Field field : fields)
                for (Field aFields2 : fields2) {
                    field.setAccessible(true);
                    aFields2.setAccessible(true);
                    if (field.getName().equals(aFields2.getName())) {
                        if (field.getName().contains("Time")) {
                            aFields2.set(eo, DateKit.formatDateByUnixTime((Long) field.get(pojo),"yyyy-MM-dd"));
                        } else {
                            aFields2.set(eo, field.get(pojo));
                        }
                    }
                }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return eo;
    }
}
