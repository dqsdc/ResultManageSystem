package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.UserItem;

import java.util.List;

public interface UserItemService {
    /**新建用户与项目关系的记录*/
    void createUserItem(UserItem userItem);
    /**通过 项目关联人uid 、项目类型itemType 寻找项目与用户记录*/
    List<UserItem> findUserItemByUidItemType(String uid,String itemType);
    /**通过项目itemId删除 项目和用户关系*/
    void deleteUserItemByItemId(String itemId);

    List<UserItem> findAllUserItemByUserType(String itemType);
}
