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
    public List<UserMajor> findAllUserMajorByUid(String uid) {
        UserMajorExample example=new UserMajorExample();
        example.createCriteria().andUidEqualTo(uid);
        return userMajorMapper.selectByExample(example);
    }

    @Override
    public List<UserMajor> findAllUserMajorByMid(Integer mid) {
        UserMajorExample example=new UserMajorExample();
        example.createCriteria().andMidEqualTo(mid);
        return userMajorMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public int updatePermission(String uid,int[] permission,String power) {
        int num=0;
        userMajorMapper.deleteByIdAndPower(uid,power);
        for (int i: permission) {
            UserMajor major=new UserMajor();
            major.setMid(i);
            major.setUid(uid);
            major.setPower(power);
            userMajorMapper.insert(major);
            num++;
        }
        return num;
    }

    @Override
    public int cancelPermission(String uid,String power) {
        return  userMajorMapper.deleteByIdAndPower(uid,power);
    }
}
