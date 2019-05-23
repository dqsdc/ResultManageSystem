package com.qilinxx.rms.domain.model.eo;

/**
 * @Auther: dqsdc
 * @Date: 2019-04-16 17:24
 * @Description:
 */
public class ProjectEo {
   // String[] columnNames = {"项目名称", "项目类型","项目题目","项目编号", "主持人","参与者","研究开始时间","研究结束时间","立项时间","项目经费","项目来源","项目等级","项目级别","项目类别","简介","创建时间"};
    private String name;
    private String genre;
    private String topic;
    private String number;
    private String host;
    private String people;
    private String startTime;
    private String endTime;
    private String setTime;
    private Double money;
    private String projectSource;
    private String level;
    private String rank;
    private String type;
    private String profile;
    private String createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "ProjectEo{" +
                "name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", genre='" + genre + '\'' +
                ", host='" + host + '\'' +
                ", people='" + people + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", setTime='" + setTime + '\'' +
                ", projectSource='" + projectSource + '\'' +
                ", money='" + money + '\'' +
                ", number='" + number + '\'' +
                ", level='" + level + '\'' +
                ", rank='" + rank + '\'' +
                ", type='" + type + '\'' +
                ", profile='" + profile + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
