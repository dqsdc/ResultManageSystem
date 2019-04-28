package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.UserInfo;
import com.qilinxx.rms.domain.model.vo.UserInfoVo;

import java.util.List;

public interface UserInfoService {
    /**通过uid查找用户*/
    UserInfo findUserByUid(String uid);
    /**修改用户信息*/
    void modifyUser(UserInfo userInfo);
    /**通过姓名查找用户，返回重复用户集合*/
    List<UserInfo> findUserByName(String name);
    /**通过姓名查找用户，范湖重复数量*/
    int countUserByName(String name);
    /**获取所有的用户信息*/
    List<UserInfoVo> findAllUser();

    int insert(UserInfo userInfo);

    Integer deleteStudentById(String uid);

    String selectNameById(String uId);

    Integer stopStudent(String uid);

    Integer startStudent(String uid);

    Integer editStudent(UserInfo user);

    List<UserInfo> findUserByMid(int mid);
}
