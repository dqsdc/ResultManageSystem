package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "meeting")
public class Meeting {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "people")
    private String people;

    @Column(name = "sponsor")
    private String sponsor;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "meeting_time")
    private Long meetingTime;

    @Column(name = "profile")
    private String profile;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name = "remake")
    private String remake;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取会议名称
     *
     * @return name - 会议名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置会议名称
     *
     * @param name 会议名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取参会人员
     *
     * @return people - 参会人员
     */
    public String getPeople() {
        return people;
    }

    /**
     * 设置参会人员
     *
     * @param people 参会人员
     */
    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    /**
     * 获取主办单位
     *
     * @return sponsor - 主办单位
     */
    public String getSponsor() {
        return sponsor;
    }

    /**
     * 设置主办单位
     *
     * @param sponsor 主办单位
     */
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor == null ? null : sponsor.trim();
    }

    /**
     * 获取承办单位
     *
     * @return organizer - 承办单位
     */
    public String getOrganizer() {
        return organizer;
    }

    /**
     * 设置承办单位
     *
     * @param organizer 承办单位
     */
    public void setOrganizer(String organizer) {
        this.organizer = organizer == null ? null : organizer.trim();
    }

    /**
     * 获取会议时间
     *
     * @return meeting_time - 会议时间
     */
    public Long getMeetingTime() {
        return meetingTime;
    }

    /**
     * 设置会议时间
     *
     * @param meetingTime 会议时间
     */
    public void setMeetingTime(Long meetingTime) {
        this.meetingTime = meetingTime;
    }

    /**
     * 获取备注
     *
     * @return profile - 备注
     */
    public String getProfile() {
        return profile;
    }

    /**
     * 设置备注
     *
     * @param profile 备注
     */
    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    /**
     * 获取备用字段
     *
     * @return remake - 备用字段
     */
    public String getRemake() {
        return remake;
    }

    /**
     * 设置备用字段
     *
     * @param remake 备用字段
     */
    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }
    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", people=").append(people);
        sb.append(", sponsor=").append(sponsor);
        sb.append(", organizer=").append(organizer);
        sb.append(", meetingTime=").append(meetingTime);
        sb.append(", profile=").append(profile);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remake=").append(remake);
        sb.append("]");
        return sb.toString();
    }
}