package com.qilinxx.rms.domain.model.eo;

/**
 * @Auther: dqsdc
 * @Date: 2019-03-26 17:56
 * @Description:
 */
public class UserInfoEo {
//    String[] columnNames = {"工号", "姓名", "密码","性别","专业","院系"};
    private int id;
    private String name;
    private String password;
    private String sex;
    private String major;
    private String belong;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", major='" + major + '\'' +
                ", belong='" + belong + '\'' +
                '}';
    }
}
