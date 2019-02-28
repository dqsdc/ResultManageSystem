package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.UserItem;

public interface UserItemService {
    /**新建用户与项目关系的记录*/
    void createUserItem(UserItem userItem);
}
