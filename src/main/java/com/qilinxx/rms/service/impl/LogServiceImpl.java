package com.qilinxx.rms.service.impl;


import com.qilinxx.rms.domain.mapper.LogMapper;
import com.qilinxx.rms.domain.model.Log;
import com.qilinxx.rms.service.LogService;
import com.qilinxx.rms.util.DateKit;
import com.qilinxx.rms.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: LJM
 * @Date: 2018-09-27 11:46
 * @Description: 日志打印服务类
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    /**
     * 保存一条日志（与学生和老师无关的操作）
     *
     * @param action 具体操作名称
     * @param userId 当前登录的用户id
     * @param ip     当前用户远端ip
     */
    @Override
    public void insertLog(String action, String userId, String ip) {
        Log log = new Log();
        log.setId(UUID.UU32());
        log.setAction(action);
        log.setUserId(userId);
        log.setIp(ip);
        log.setCreateTime((long) DateKit.getCurrentUnixTime());
        log.setState("0");
        logMapper.insert(log);
    }

    @Override
    public List<Log> getAllLog() {
        return logMapper.selectAll();
    }

    @Override
    public List<Log> getAdminLoginLog() {
        return logMapper.selectAdminLogin();
    }

    @Override
    public Log getLastAdminLog() {
        return logMapper.getLastAdminLog();
    }
}
