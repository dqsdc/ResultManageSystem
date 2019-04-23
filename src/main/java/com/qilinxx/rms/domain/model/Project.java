package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "project")
public class Project {
    @Id
    private String pid;

    @Column(name = "host")
    private String host;

    @Column(name = "people")
    private String people;

    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "end_time")
    private Long endTime;

    @Column(name = "project_source")
    private String projectSource;

    @Column(name = "money")
    private Integer money;

    @Column(name = "rank")
    private String rank;

    @Column(name = "type")
    private String type;

    @Column(name = "set_time")
    private Long setTime;

    @Column(name = "profile")
    private String profile;

    @Column(name = "state")
    private String state;

    @Column(name = "create_id")
    private String createId;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name = "mid")
    private Integer mid;

    @Column(name = "remake")
    private String remake;

    @Column(name = "number")
    private String number;

    @Column(name = "level")
    private String level;

    @Column(name = "topic")
    private String topic;

    @Column(name = "genre")
    private String genre;

    /**
     * 获取uuid
     *
     * @return pid - uuid
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置uuid
     *
     * @param pid uuid
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 获取主持人
     *
     * @return host - 主持人
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置主持人
     *
     * @param host 主持人
     */
    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    /**
     * 获取参与人
     *
     * @return people - 参与人
     */
    public String getPeople() {
        return people;
    }

    /**
     * 设置参与人
     *
     * @param people 参与人
     */
    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    /**
     * 获取项目名称
     *
     * @return name - 项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置项目名称
     *
     * @param name 项目名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取项目题目
     *
     * @return topic - 项目题目
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置项目题目
     *
     * @param topic 项目题目
     */
    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    /**
     * 获取项目类型
     *
     * @return genre - 项目类型
     */
    public String getGenre() {
        return genre;
    }

    /**
     * 设置项目类型
     *
     * @param genre 项目类型
     */
    public void setGenre(String genre) {
        this.genre = genre == null ? null : genre.trim();
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取项目来源
     *
     * @return project_source - 项目来源
     */
    public String getProjectSource() {
        return projectSource;
    }

    /**
     * 设置项目来源
     *
     * @param projectSource 项目来源
     */
    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource == null ? null : projectSource.trim();
    }

    /**
     * 获取项目经费（以万为单位）
     *
     * @return money - 项目经费（以万为单位）
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * 设置项目经费（以万为单位）
     *
     * @param money 项目经费（以万为单位）
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 获取项目编号
     *
     * @return number - 项目编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置项目编号
     *
     * @param number 项目编号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 获取项目等级
     *
     * @return level - 项目等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置项目等级
     *
     * @param level 项目等级
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * 获取项目级别
     *
     * @return rank - 项目级别
     */
    public String getRank() {
        return rank;
    }

    /**
     * 设置项目级别
     *
     * @param rank 项目级别
     */
    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    /**
     * 获取项目类型
     *
     * @return type - 项目类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置项目类型
     *
     * @param type 项目类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取立项时间
     *
     * @return set_time - 立项时间
     */
    public Long getSetTime() {
        return setTime;
    }

    /**
     * 设置立项时间
     *
     * @param setTime 立项时间
     */
    public void setSetTime(Long setTime) {
        this.setTime = setTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pid=").append(pid);
        sb.append(", host=").append(host);
        sb.append(", people=").append(people);
        sb.append(", name=").append(name);
        sb.append(", topic=").append(topic);
        sb.append(", genre=").append(genre);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", projectSource=").append(projectSource);
        sb.append(", money=").append(money);
        sb.append(", number=").append(number);
        sb.append(", level=").append(level);
        sb.append(", rank=").append(rank);
        sb.append(", type=").append(type);
        sb.append(", setTime=").append(setTime);
        sb.append(", profile=").append(profile);
        sb.append(", state=").append(state);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", mid=").append(mid);
        sb.append(", remake=").append(remake);
        sb.append("]");
        return sb.toString();
    }
}