package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "major")
public class Major {
    @Id
    private Integer mid;

    @Column(name = "name")
    private String name;

    @Column(name = "profile")
    private String profile;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name = "state")
    private String state;

    @Column(name = "sort_num")
    private Integer sortNum;


    @Column(name = "remake")
    private String remake;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    @Override
    public String toString() {
        return "Major{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", profile='" + profile + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", state='" + state + '\'' +
                ", sortNum=" + sortNum +
                ", remake='" + remake + '\'' +
                '}';
    }
}