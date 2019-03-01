package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Reward;

public interface RewardService {
    /**通过奖励名称name，获奖人people，获奖时间getTime查重，返回重复个数*/
    int findRewardByNamePeopleGetTime(String name,String people,long getTime);
    /**新建一个奖励记录*/
    void createReward(Reward reward);
}
