package com.qilinxx.rms.util;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Auther: dqsdc
 * @Date: 2018-11-21 15:13
 * @Description: 上传文件工具类
 */
public class UploadUtil {

    /**
     * @return 得到上传文件位置的绝对路径
     */
    public static String getUploadFilePath() {
        String path = UploadUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(1, path.length());
        try {
            path = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int lastIndex = path.lastIndexOf("/") + 1;
        path = path.substring(0, lastIndex);
        File file = new File("");
        return file.getAbsolutePath() + "/";
    }
}
