package com.qilinxx.rms.domain.model.eo;

/**
 * @Auther: dqsdc
 * @Date: 2019-04-18 16:08
 * @Description:
 */
public class TextbookEo {
    //String[] columnNames = {"教材名称", "主编", "出版社","出版年月","ISBN编号","规划类别","教材获奖","教材简介","创建时间"};
    private String name;
    private String people;
    private String press;
    private String publishTime;
    private String isbn;
    private String type;
    private String reward;
    private String profile;
    private String createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TextbookEo{" +
                "name='" + name + '\'' +
                ", people='" + people + '\'' +
                ", press='" + press + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", isbn='" + isbn + '\'' +
                ", type='" + type + '\'' +
                ", reward='" + reward + '\'' +
                ", profile='" + profile + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
