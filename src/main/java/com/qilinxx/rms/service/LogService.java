package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Log;

import java.util.List;

/**
 * @Auther: dqsdc
 * @Date: 2019-03-20 20:53
 * @Description:
 */

public interface LogService {
    void insertLog(String action,String userId,String Ip);

    List<Log> getAllLog();

    List<Log> getAdminLoginLog();

    Log getLastAdminLog();
}
