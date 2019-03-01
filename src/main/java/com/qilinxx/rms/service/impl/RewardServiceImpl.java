package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.RewardMapper;
import com.qilinxx.rms.domain.model.Reward;
import com.qilinxx.rms.domain.model.RewardExample;
import com.qilinxx.rms.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl implements RewardService {
    @Autowired
    RewardMapper rewardMapper;
    @Override
    public int findRewardByNamePeopleGetTime(String name, String people, long getTime) {
        RewardExample example=new RewardExample();
        example.createCriteria().andNameEqualTo(name).andPeopleEqualTo(people).andGetTimeEqualTo(getTime);
        return rewardMapper.selectCountByExample(example);
    }

    @Override
    public void createReward(Reward reward) {
        rewardMapper.insert(reward);
    }
}
