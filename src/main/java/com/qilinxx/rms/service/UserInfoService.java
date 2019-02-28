package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    /**通过uid查找用户*/
    UserInfo findUserByUid(Integer uid);
    /**修改用户信息*/
    void modifyUser(UserInfo userInfo);
    /**通过姓名查找用户*/
    List<UserInfo> findUserByName(String name);
}
