package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.UserInfo;

public interface UserInfoService {
    /**通过uid查找用户*/
    UserInfo findUserByUid(Integer uid);
    /**修改用户信息*/
    void modifyUser(UserInfo userInfo);
}
