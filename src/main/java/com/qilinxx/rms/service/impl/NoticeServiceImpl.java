package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.NoticeMapper;
import com.qilinxx.rms.domain.model.Notice;
import com.qilinxx.rms.service.NoticeService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: dqsdc
 * @Date: 2019-04-29 17:50
 * @Description:
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public void addNotice(Notice notice) {
        noticeMapper.insert(notice);
    }

    @Override
    public Integer passNoticeByNid(String resultId, String type) {
        return  noticeMapper.passNoticeByNid(resultId,type);
    }

    @Override
    public Notice selectLastNotice(String resultId, String type) {
        return noticeMapper.selectLastNotice(resultId,type);
    }

    @Override
    public Notice latestNotice(String createId, String type) {
        return noticeMapper.latestNotice(createId,type);
    }

    @Override
    public List<Notice> getAllNoticesOrderByTime(String createId,String type) {
        return noticeMapper.getAllNoticesOrderByTime(createId,type);
    }
}
