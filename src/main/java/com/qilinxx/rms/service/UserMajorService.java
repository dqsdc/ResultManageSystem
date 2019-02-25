package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.UserMajor;

import java.util.List;

public interface UserMajorService {
    /**通过用户uid得到所有的权限mid*/
    List<UserMajor> findAllUserMajorByUid(Integer uid);
}
