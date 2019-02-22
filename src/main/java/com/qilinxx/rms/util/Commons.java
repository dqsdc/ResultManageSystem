package com.qilinxx.rms.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

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
}
