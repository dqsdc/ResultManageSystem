package com.qilinxx.rms.domain.model.vo;

import com.qilinxx.rms.domain.model.UserInfo;

/**
 * @Auther: dqsdc
 * @Date: 2019-03-12 13:55
 * @Description:
 */
public class UserInfoVo extends UserInfo {
    private String major;

    private String permission;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
