package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.UserInfoMapper;
import com.qilinxx.rms.domain.model.UserInfo;
import com.qilinxx.rms.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public UserInfo findUserByUid(Integer uid) {
        return userInfoMapper.selectByPrimaryKey(uid);
    }

    @Override
    public void modifyUser(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }
}
