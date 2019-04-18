package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.MeetingMapper;
import com.qilinxx.rms.domain.model.Meeting;
import com.qilinxx.rms.domain.model.MeetingExample;
import com.qilinxx.rms.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    MeetingMapper meetingMapper;
    @Override
    public Integer countMeetingByNameMeetingTime(String name, long startTime) {
        MeetingExample example=new MeetingExample();
        example.createCriteria().andNameEqualTo(name).andStartTimeEqualTo(startTime);
        return meetingMapper.selectCountByExample(example);
    }

    @Override
    public Integer countMeetingByNameMeetingTimeExceptId(String name, long startTime, String id) {
        MeetingExample example=new MeetingExample();
        example.createCriteria().andNameEqualTo(name).andStartTimeEqualTo(startTime).andIdNotEqualTo(id);
        return meetingMapper.selectCountByExample(example);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetingMapper.insert(meeting);
    }

    @Override
    public Meeting findMeetingById(String id) {
        return meetingMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateMeeting(Meeting meeting) {
        meetingMapper.updateByPrimaryKeySelective(meeting);
    }

    @Override
    public void deleteMeetingByid(String id) {
        meetingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Meeting> findMeetingByMid(Integer mid) {
        MeetingExample example=new MeetingExample();
        example.createCriteria().andMidEqualTo(mid);
        example.setOrderByClause("state ASC ,create_time DESC");
        return meetingMapper.selectByExample(example);
    }

    @Override
    public int countMeetingByMidState(Integer mid, String state) {
        MeetingExample example = new MeetingExample();
        example.createCriteria().andMidEqualTo(mid).andStateEqualTo(state);
        return meetingMapper.selectCountByExample(example);
    }
}
