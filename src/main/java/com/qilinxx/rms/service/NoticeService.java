package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: dqsdc
 * @Date: 2019-04-29 17:12
 * @Description:
 * 每一次审核不通过，都创建一条记录；每次提示用户最新的不通过记录，让其修改；
 * 如果此成果（不管什么类型，看id）通过审核，就让所有记录状态变成1
 */
@Service
public interface NoticeService {
    //添加一条记录
    void addNotice(Notice notice);

    //当通过审核时,将所有此成果的记录全部标记通过
    Integer passNoticeByNid(String resultId,String type);

    //根据成果Id和类型找到最新的通知
    Notice selectLastNotice(String resultId,String type);

    //查询创建者的某一大类（如项目）中最新的审核不过的个数
    Notice latestNotice(String createId,String type);


    List<Notice> getAllNoticesOrderByTime(String createId,String type);

}
