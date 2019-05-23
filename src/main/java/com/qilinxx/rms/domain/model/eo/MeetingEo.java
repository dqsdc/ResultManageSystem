package com.qilinxx.rms.domain.model.eo;

/**
 * @Auther: dqsdc
 * @Date: 2019-04-18 16:24
 * @Description:
 */
public class MeetingEo {
    //String[] columnNames = {"会议名称","会议地点", "参会人员", "主办单位","承办单位","会议开始时间","会议结束时间","会议简介","创建时间"};
    private String name;
    private String position;
    private String people;
    private String sponsor;
    private String organizer;
    private String startTime;
    private String endTime;
    private String profile;
    private String createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
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
        return "MeetingEo{" +
                "name='" + name + '\'' +
                ", people='" + people + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", organizer='" + organizer + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", profile='" + profile + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
