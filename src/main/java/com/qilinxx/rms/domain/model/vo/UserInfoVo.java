package com.qilinxx.rms.domain.model.vo;

import com.qilinxx.rms.domain.model.UserInfo;

/**
 * @Auther: dqsdc
 * @Date: 2019-03-12 13:55
 * @Description:
 */
public class UserInfoVo extends UserInfo {
    private String major;

    private String readPermission;

    private String checkPermission;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getReadPermission() {
        return readPermission;
    }

    public void setReadPermission(String readPermission) {
        this.readPermission = readPermission;
    }

    public String getCheckPermission() {
        return checkPermission;
    }

    public void setCheckPermission(String checkPermission) {
        this.checkPermission = checkPermission;
    }
}
