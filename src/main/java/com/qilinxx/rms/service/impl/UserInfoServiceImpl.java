package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.MajorMapper;
import com.qilinxx.rms.domain.mapper.UserInfoMapper;
import com.qilinxx.rms.domain.mapper.UserMajorMapper;
import com.qilinxx.rms.domain.model.Major;
import com.qilinxx.rms.domain.model.UserInfo;
import com.qilinxx.rms.domain.model.UserInfoExample;
import com.qilinxx.rms.domain.model.UserMajor;
import com.qilinxx.rms.domain.model.vo.UserInfoVo;
import com.qilinxx.rms.service.UserInfoService;
import com.qilinxx.rms.util.DateKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    MajorMapper majorMapper;

    @Autowired
    UserMajorMapper userMajorMapper;

    @Override
    public UserInfo findUserByUid(Integer uid) {
        return userInfoMapper.selectByPrimaryKey(uid);
    }

    @Override
    public void modifyUser(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public List<UserInfo> findUserByName(String name) {
        UserInfoExample example=new UserInfoExample();
        example.createCriteria().andNameEqualTo(name);
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public int countUserByName(String name) {
        UserInfoExample example=new UserInfoExample();
        example.createCriteria().andNameEqualTo(name);
        return userInfoMapper.selectCountByExample(example);
    }

    @Override
    public List<UserInfoVo> findAllUser() {
        List<UserInfo> infoList= userInfoMapper.selectAll();
        List<UserInfoVo> infoVos=new ArrayList<>();
        for (UserInfo info:infoList){
            infoVos.add(improve(info));
        }
        return infoVos;
    }

    @Override
    public int insert(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public Integer deleteStudentById(String uid) {
        return userInfoMapper.deleteByPrimaryKey(Integer.parseInt(uid));
    }

    @Override
    public String selectNameById(String uId) {
        return userInfoMapper.selectByPrimaryKey(Integer.parseInt(uId)).getName();
    }

    @Override
    public Integer stopStudent(String uid) {
        UserInfo user=userInfoMapper.selectByPrimaryKey(Integer.parseInt(uid));
        user.setState("1");
        return  userInfoMapper.updateByPrimaryKeySelective(user);

    }

    @Override
    public Integer startStudent(String uid) {
        UserInfo user=userInfoMapper.selectByPrimaryKey(Integer.parseInt(uid));
        user.setState("0");
        return  userInfoMapper.updateByPrimaryKeySelective(user);

    }

    @Override
    public Integer editStudent(UserInfo user) {
        System.out.println("update--->"+user);
        UserInfo old=userInfoMapper.selectByPrimaryKey(user.getUid());
        if(user.getPassword()==null) user.setPassword(old.getPassword());
        if (user.getName()==null) user.setName(old.getName());
        if (user.getCreateTime()==null)user.setCreateTime(old.getCreateTime());
        user.setUpdateTime(DateKit.getUnixTimeLong());
        return  userInfoMapper.updateByPrimaryKeySelective(user);
    }

    private UserInfoVo improve(UserInfo info) {
        Major major=majorMapper.selectByPrimaryKey(info.getMid());
        UserInfoVo userInfoVo=new UserInfoVo();

        List<UserMajor> userMajors= userMajorMapper.selectAllByUid(info.getUid());
        if(userMajors.size()>0){
            StringBuffer buffer=new StringBuffer();
            for (UserMajor userMajor:userMajors) {
              buffer.append(majorMapper.selectByPrimaryKey(userMajor.getMid()).getName()+",");
            }
            userInfoVo.setPermission(buffer.substring(0,buffer.length()-1));
        }
        userInfoVo.setUid(info.getUid());
        userInfoVo.setMajor(major.getName());
        userInfoVo.setName(info.getName());
        userInfoVo.setSex(info.getSex());
        userInfoVo.setTitle(info.getTitle());
        userInfoVo.setProfile(info.getProfile());
        userInfoVo.setState(info.getState());
        return userInfoVo;
    }
}
