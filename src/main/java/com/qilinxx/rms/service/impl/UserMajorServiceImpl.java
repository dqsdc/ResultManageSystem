package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.UserMajorMapper;
import com.qilinxx.rms.domain.model.UserMajor;
import com.qilinxx.rms.domain.model.UserMajorExample;
import com.qilinxx.rms.service.UserMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public int updatePermission(int uid,int[] permission) {
        int num=0;
        userMajorMapper.deleteByPrimaryKey(uid);
        for (int i: permission) {
            UserMajor major=new UserMajor();
            major.setMid(i);
            major.setUid(uid);
            userMajorMapper.insert(major);
            num++;
        }
        return num;
    }
}
