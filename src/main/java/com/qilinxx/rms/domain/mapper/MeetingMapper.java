package com.qilinxx.rms.domain.mapper;


import com.qilinxx.rms.domain.model.Meeting;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MeetingMapper extends Mapper<Meeting> {
}