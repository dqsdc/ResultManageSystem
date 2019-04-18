package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "reward")
public class Reward {
    @Id
    private String rid;

    @Column(name = "name")
    private String name;

    @Column(name="people")
    private String people;

    @Column(name ="company")
    private String company;

    @Column(name = "get_time")
    private Long getTime;

    @Column(name = "profile")
    private String profile;

    @Column(name = "state")
    private String state;

    @Column(name = "create_id")
    private Integer createId;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name = "mid")
    private Integer mid;

    @Column(name = "remake")
    private String remake;

    @Column(name = "level")
    private String level;

    /**
     * 获取uuid
     *
     * @return rid - uuid
     */
    public String getRid() {
        return rid;
    }

    /**
     * 设置uuid
     *
     * @param rid uuid
     */
    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    /**
     * 获取获奖名称
     *
     * @return name - 获奖名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置获奖名称
     *
     * @param name 获奖名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取获奖人
     *
     * @return people - 获奖人
     */
    public String getPeople() {
        return people;
    }

    /**
     * 设置获奖人
     *
     * @param people 获奖人
     */
    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    /**
     * 获取授奖单位
     *
     * @return company - 授奖单位
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置授奖单位
     *
     * @param company 授奖单位
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * 获取奖励等级
     *
     * @return level - 奖励等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置奖励等级
     *
     * @param level 奖励等级
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * 获取获奖时间
     *
     * @return get_time - 获奖时间
     */
    public Long getGetTime() {
        return getTime;
    }

    /**
     * 设置获奖时间
     *
     * @param getTime 获奖时间
     */
    public void setGetTime(Long getTime) {
        this.getTime = getTime;
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
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 设置发布人的id
     *
     * @param createId 发布人的id
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取奖励发布到系统的时间
     *
     * @return create_time - 奖励发布到系统的时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置奖励发布到系统的时间
     *
     * @param createTime 奖励发布到系统的时间
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
     * 获取专业id
     *
     * @return mid - 专业id
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * 设置专业id
     *
     * @param mid 专业id
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
        sb.append(", rid=").append(rid);
        sb.append(", name=").append(name);
        sb.append(", people=").append(people);
        sb.append(", company=").append(company);
        sb.append(", level=").append(level);
        sb.append(", getTime=").append(getTime);
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