package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "textbook")
public class Textbook {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "people")
    private String people;

    @Column(name = "press")
    private String press;

    @Column(name = "publish_time")
    private Long publishTime;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "profile")
    private String profile;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name ="remake")
    private String remake;

    @Column(name = "state")
    private String state;

    @Column(name = "create_id")
    private String createId;

    @Column(name = "mid")
    private Integer mid;

    @Column(name = "type")
    private String type;

    @Column(name = "reward")
    private String reward;

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
     * 获取教材名称
     *
     * @return name - 教材名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置教材名称
     *
     * @param name 教材名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取主编
     *
     * @return people - 主编
     */
    public String getPeople() {
        return people;
    }

    /**
     * 设置主编
     *
     * @param people 主编
     */
    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    /**
     * 获取出版社
     *
     * @return press - 出版社
     */
    public String getPress() {
        return press;
    }

    /**
     * 设置出版社
     *
     * @param press 出版社
     */
    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    /**
     * 获取出版年月
     *
     * @return publish_time - 出版年月
     */
    public Long getPublishTime() {
        return publishTime;
    }

    /**
     * 设置出版年月
     *
     * @param publishTime 出版年月
     */
    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取ISBN编号
     *
     * @return ISBN - ISBN编号
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * 设置ISBN编号
     *
     * @param isbn ISBN编号
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    /**
     * 获取规划类别
     *
     * @return type - 规划类别
     */
    public String getType() {
        return type;
    }

    /**
     * 设置规划类别
     *
     * @param type 规划类别
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取教材获奖
     *
     * @return reward - 教材获奖
     */
    public String getReward() {
        return reward;
    }

    /**
     * 设置教材获奖
     *
     * @param reward 教材获奖
     */
    public void setReward(String reward) {
        this.reward = reward == null ? null : reward.trim();
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
        sb.append(", press=").append(press);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", isbn=").append(isbn);
        sb.append(", type=").append(type);
        sb.append(", reward=").append(reward);
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