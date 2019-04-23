package com.qilinxx.rms.service;


import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: dqsdc
 * @Date: 2019-03-25 16:07
 * @Description:
 */
public interface ExportExcelService {
    void exportUserInfo(String[] ids, HttpServletResponse response);

    void exportProject(String[] ids, HttpServletResponse response);

    void exportThesis(String[] ids, HttpServletResponse response);

    void exportReward (String[] ids, HttpServletResponse response);

    void exportTextbook (String[] ids, HttpServletResponse response);

    void exportMeeting (String[] ids, HttpServletResponse response);
}