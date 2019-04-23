package com.qilinxx.rms.domain.model.eo;

/**
 * @Auther: dqsdc
 * @Date: 2019-03-26 17:56
 * @Description:
 */
public class UserInfoEo {
//    String[] columnNames = {"工号", "姓名", "密码","性别","专业","院系"};
    private String uid;
    private String name;
    private String password;
    private String sex;
    private Integer mid;
    private String belong;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    @Override
    public String toString() {
        return "UserInfoEo{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", mid=" + mid +
                ", belong='" + belong + '\'' +
                '}';
    }
}
