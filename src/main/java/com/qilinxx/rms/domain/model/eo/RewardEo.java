package com.qilinxx.rms.domain.model.eo;

/**
 * @Auther: dqsdc
 * @Date: 2019-04-18 16:04
 * @Description:
 */
public class RewardEo {
    //String[] columnNames = {"获奖名称", "获奖人", "奖励级别"，"奖励等级","授奖单位","获奖时间","奖励简介","创建时间"};
    private String name;
    private String people;
    private String level;
    private String rank;
    private String company;
    private String getTime;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
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
        return "RewardEo{" +
                "name='" + name + '\'' +
                ", people='" + people + '\'' +
                ", level='" + level + '\'' +
                ", rank='" + rank + '\'' +
                ", company='" + company + '\'' +
                ", getTime='" + getTime + '\'' +
                ", profile='" + profile + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
