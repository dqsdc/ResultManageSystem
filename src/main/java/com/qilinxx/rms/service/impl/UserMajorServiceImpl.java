package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.UserMajorMapper;
import com.qilinxx.rms.domain.model.UserMajor;
import com.qilinxx.rms.domain.model.UserMajorExample;
import com.qilinxx.rms.service.UserMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMajorServiceImpl implements UserMajorService {
    @Autowired
    UserMajorMapper userMajorMapper;
    @Override
    public List<UserMajor> findAllUserMajorByUid(Integer uid) {
        UserMajorExample example=new UserMajorExample();
        example.createCriteria().andUidEqualTo(uid);
        return userMajorMapper.selectByExample(example);
    }
}
