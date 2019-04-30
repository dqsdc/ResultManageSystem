package com.qilinxx.rms.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * fang
 * 2019/2/26
 * 用于对本项目文件的增删操作操作
 */
public class FileKit {
    private static List<MultipartFile> projectFileList; //项目附件集合
    private static List<MultipartFile> thesisFileList;  //论文附件集合
    private static List<MultipartFile> rewardFileList;  //奖励附件集合
    private static List<MultipartFile> textbookFileList;    //教材附件的集合
    private static List<MultipartFile> meetingFileList;     //会议附件的集合


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

    public static List<MultipartFile> getTextbookFileList() {
        return textbookFileList;
    }

    public static void setTextbookFileList(List<MultipartFile> textbookFileList) {
        FileKit.textbookFileList = textbookFileList;
    }

    public static List<MultipartFile> getMeetingFileList() {
        return meetingFileList;
    }

    public static void setMeetingFileList(List<MultipartFile> meetingFileList) {
        FileKit.meetingFileList = meetingFileList;
    }

    public static List<MultipartFile> getProjectFileList() {
        return projectFileList;
    }

    public static void setProjectFileList(List<MultipartFile> projectFileList) {
        FileKit.projectFileList = projectFileList;
    }

    public static List<MultipartFile> getThesisFileList() {
        return thesisFileList;
    }

    public static void setThesisFileList(List<MultipartFile> thesisFileList) {
        FileKit.thesisFileList = thesisFileList;
    }

    public static List<MultipartFile> getRewardFileList() {
        return rewardFileList;
    }

    public static void setRewardFileList(List<MultipartFile> rewardFileList) {
        FileKit.rewardFileList = rewardFileList;
    }
}
