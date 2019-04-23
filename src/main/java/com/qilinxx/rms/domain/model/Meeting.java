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

    @Column(name = "profile")
    private String profile;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name = "remake")
    private String remake;

    @Column(name = "state")
    private String state;

    @Column(name = "create_id")
    private String createId;

    @Column(name = "mid")
    private Integer mid;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "end_time")
    private Long endTime;

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
     * 获取会议开始时间
     *
     * @return start_time - 会议开始时间
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 设置会议开始时间
     *
     * @param startTime 会议开始时间
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取会议结束时间
     *
     * @return end_time - 会议结束时间
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 设置会议结束时间
     *
     * @param endTime 会议结束时间
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
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
     * 获取0为待审核，1为审核未通过，2为通过审核
     *
     * @return state - 0为待审核，1为审核未通过，2为通过审核
     */
    public String getState() {
        return state;
    }

    /**
     * 设置0为待审核，1为审核未通过，2为通过审核
     *
     * @param state 0为待审核，1为审核未通过，2为通过审核
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 获取发布人的id
     *
     * @return create_id - 发布人的id
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 设置发布人的id
     *
     * @param createId 发布人的id
     */
    public void setCreateId(String createId) {
        this.createId = createId;
    }

    /**
     * 获取专业的id
     *
     * @return mid - 专业的id
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * 设置专业的id
     *
     * @param mid 专业的id
     */
    public void setMid(Integer mid) {
        this.mid = mid;
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
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", profile=").append(profile);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remake=").append(remake);
        sb.append(", state=").append(state);
        sb.append(", createId=").append(createId);
        sb.append(", mid=").append(mid);
        sb.append("]");
        return sb.toString();
    }
}