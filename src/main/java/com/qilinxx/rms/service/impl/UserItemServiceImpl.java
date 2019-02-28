package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.UserItemMapper;
import com.qilinxx.rms.domain.model.UserItem;
import com.qilinxx.rms.service.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserItemServiceImpl implements UserItemService {
    @Autowired
    UserItemMapper userItemMapper;
    @Override
    public void createUserItem(UserItem userItem) {
        userItemMapper.insert(userItem);
    }
}
