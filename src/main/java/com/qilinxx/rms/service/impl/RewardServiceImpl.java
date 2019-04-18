package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.RewardMapper;
import com.qilinxx.rms.domain.model.Reward;
import com.qilinxx.rms.domain.model.RewardExample;
import com.qilinxx.rms.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {
    @Autowired
    RewardMapper rewardMapper;
    @Override
    public int countRewardByNamePeopleGetTime(String name, String people, long getTime) {
        RewardExample example=new RewardExample();
        example.createCriteria().andNameEqualTo(name).andPeopleEqualTo(people).andGetTimeEqualTo(getTime);
        return rewardMapper.selectCountByExample(example);
    }

    @Override
    public int countRewardByNamePeopleGetTimeExceptRid(String name, String people, long getTime, String rid) {
        RewardExample example=new RewardExample();
        example.createCriteria().andNameEqualTo(name).andPeopleEqualTo(people).andGetTimeEqualTo(getTime).andRidNotEqualTo(rid);
        return rewardMapper.selectCountByExample(example);
    }

    @Override
    public void createReward(Reward reward) {
        rewardMapper.insert(reward);
    }

    @Override
    public void deleteReward(String rid) {
        rewardMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public Reward findRewardByRid(String rid) {
        return rewardMapper.selectByPrimaryKey(rid);
    }

    @Override
    public void updateReward(Reward reward) {
        rewardMapper.updateByPrimaryKeySelective(reward);
    }

    @Override
    public List<Reward> findRewardByMid(Integer mid) {
        RewardExample example=new RewardExample();
        example.createCriteria().andMidEqualTo(mid);
        example.setOrderByClause("state ASC ,create_time DESC");
        return rewardMapper.selectByExample(example);
    }

    @Override
    public int countRewardByMid(Integer mid) {
        RewardExample example=new RewardExample();
        example.createCriteria().andMidEqualTo(mid);
        return rewardMapper.selectCountByExample(example);
    }

    @Override
    public int countRewardByMidState(Integer mid, String state) {
        RewardExample example=new RewardExample();
        example.createCriteria().andMidEqualTo(mid).andStateEqualTo(state);
        return rewardMapper.selectCountByExample(example);
    }
}
