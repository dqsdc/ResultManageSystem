package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.UserMajor;

import java.util.List;

public interface UserMajorService {
    /**通过用户uid得到所有的权限mid*/
    List<UserMajor> findAllUserMajorByUid(String uid);
    /*通过mid查找记录*/
    List<UserMajor> findAllUserMajorByMid(Integer mid);

    int updatePermission(String uid,int permission[],String power);

    int cancelPermission(String uid,String power);
}
