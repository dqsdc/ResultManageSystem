package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Meeting;

import java.util.List;

public interface MeetingService {
    /**查找重复的个数*/
    Integer countMeetingByNameMeetingTime(String name,long startTime);
    /**查找重复的个数*/
    Integer countMeetingByNameMeetingTimeExceptId(String name,long startTime,String id);
    /**插入一条记录*/
    void createMeeting(Meeting meeting);
    /**通过id查询记录*/
    Meeting findMeetingById(String id);
    /**更新记录*/
    void updateMeeting(Meeting meeting);
    /**通过id删除记录*/
    void deleteMeetingByid(String id);
    /**通过mid查询记录*/
    List<Meeting> findMeetingByMid(Integer mid );
    /**通过mid   与   state 查询记录*/
    int countMeetingByMidState(Integer mid,String  state);
}
