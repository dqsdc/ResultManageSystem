package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Reward;

import java.util.List;

public interface RewardService {
    /**通过奖励名称name，获奖人people，获奖时间getTime查重，返回重复个数*/
    int countRewardByNamePeopleGetTime(String name,String people,long getTime);
    /**通过奖励名称name，获奖人people，获奖时间getTime查重，返回重复个数*/
    int countRewardByNamePeopleGetTimeExceptRid(String name,String people,long getTime,String rid);
    /**新建一个奖励记录*/
    void createReward(Reward reward);
    /**通过奖励rid   删除奖励*/
    void deleteReward(String rid);
    /**通过奖励rid 查找奖励记录*/
    Reward findRewardByRid(String rid);
    /**更新奖励字段*/
    void updateReward(Reward reward);
    /**通过专业mid，查找奖励*/
    List<Reward> findRewardByMid(Integer mid);
    /**通过专业mid ，返回插叙数量*/
    int countRewardByMid(Integer mid);
    /**通过专业mid、状态 ，返回插叙数量*/
    int countRewardByMidState(Integer mid,String state);
}
