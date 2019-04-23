package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.UserItemMapper;
import com.qilinxx.rms.domain.model.UserItem;
import com.qilinxx.rms.domain.model.UserItemExample;
import com.qilinxx.rms.service.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserItemServiceImpl implements UserItemService {
    @Autowired
    UserItemMapper userItemMapper;
    @Override
    public void createUserItem(UserItem userItem) {
        userItemMapper.insert(userItem);
    }

    @Override
    public List<UserItem> findUserItemByUidItemType(String uid, String itemType) {
        UserItemExample example=new UserItemExample();
        example.createCriteria().andUidEqualTo(uid).andItemTypeEqualTo(itemType);
        return userItemMapper.selectByExample(example);
    }

    @Override
    public void deleteUserItemByItemId(String itemId) {
        UserItemExample example=new UserItemExample();
        example.createCriteria().andItemIdEqualTo(itemId);
        userItemMapper.deleteByExample(example);
    }

    @Override
    public List<UserItem> findAllUserItemByUserType(String itemType) {
        return userItemMapper.selectAllByItemType(itemType);
    }
}
