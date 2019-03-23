package com.qilinxx.rms.util;

import com.qilinxx.rms.domain.model.Major;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Commons {
    /**
     * 格式化unix时间戳为日期
     *
     * @param unixTime unix格式时间
     * @return 格式化后的时间
     */
    public static String fmtdate(Integer unixTime) {
        return fmtdate(unixTime, "yyyy-MM-dd HH:mm");
    }

    /**
     * 格式化unix时间戳为日期
     *
     * @param unixTime unix格式时间
     * @param patten 日期格式样式
     * @return 转化后的String
     */
    public static String fmtdate(Integer unixTime, String patten) {
        if (null != unixTime && StringUtils.isNotBlank(patten)) {
            return DateKit.formatDateByUnixTime(unixTime, patten);
        }
        return "";
    }

    /***
     * 匹配
     * @param mid List<UserMajor>中取出的单个值
     * @param majors 所有的major
     * @return
     */
    public static boolean contains(String mid,List<Major> majors){
        System.out.println(majors);
        List<Integer> ids = new ArrayList<>();
        for(Major m: majors){
            ids.add(m.getMid());
        }
        return (ids.contains(mid));
    }

}
