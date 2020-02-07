package com.qilinxx.rms.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * fang
 * 2019/2/26
 * 用于对本项目文件的增删操作操作
 */
public class FileKit {
    //item集合
    public static Map<String,List<MultipartFile>> projectMap;
    public static Map<String,List<MultipartFile>> thesisMap;
    public static Map<String,List<MultipartFile>> rewardMap;
    public static Map<String,List<MultipartFile>> textbookMap;
    public static Map<String,List<MultipartFile>> meetingMap;
    public static Map<String,List<MultipartFile>> selfMap;

    /**
     * 清除或初始化map,去除对应uid
     */
    public static synchronized Map<String,List<MultipartFile>> clearOrInitMap(Map<String,List<MultipartFile>> map,String uid){
        if(map==null){
            map=new HashMap<>();
        }else {
            Iterator iterator1 = map.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator1.next();
                if(((String) entry.getKey()).contains("-"+uid)){
                    map.remove(entry.getKey());
                }
            }
        }
        return map;
    }
    /**
     * 清除或初始化list
     */
    public static synchronized List<MultipartFile> clearOrInitList(List<MultipartFile> list){
        if(list==null){
            list=new ArrayList<>();
        }else {
            list.clear();
        }
        return list;
    }
    /**
     * 删除文件或目录
     * @param dirFile 要被删除的文件或者目录
     * @return 删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(File dirFile) {
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return false;
        }

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {
            for (File file : dirFile.listFiles()) {
                deleteFile(file);
            }
        }
        return dirFile.delete();
    }

}
