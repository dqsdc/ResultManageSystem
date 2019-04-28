package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "thesis")
public class Thesis {
    @Id
    private String tid;

    @Column(name = "host")
    private String host;

    @Column(name = "people")
    private String people;

    @Column(name = "name")
    private String name;

    @Column(name = "book")
    private String book;

    @Column(name = "year")
    private Integer year;

    @Column(name = "dossier")
    private Integer dossier;

    @Column(name = "issue")
    private Integer issue;

    @Column(name = "page_num")
    private String pageNum;

    @Column(name = "rank")
    private String rank;

    @Column(name = "type")
    private String type;

    @Column(name = "publish_time")
    private Long publishTime;

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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book == null ? null : book.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDossier() {
        return dossier;
    }

    public void setDossier(Integer dossier) {
        this.dossier = dossier;
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum == null ? null : pageNum.trim();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
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

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Thesis{" +
                "tid='" + tid + '\'' +
                ", host='" + host + '\'' +
                ", people='" + people + '\'' +
                ", name='" + name + '\'' +
                ", book='" + book + '\'' +
                ", year=" + year +
                ", dossier=" + dossier +
                ", issue=" + issue +
                ", pageNum='" + pageNum + '\'' +
                ", rank='" + rank + '\'' +
                ", type='" + type + '\'' +
                ", publishTime=" + publishTime +
                ", profile='" + profile + '\'' +
                ", state='" + state + '\'' +
                ", createId='" + createId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", mid=" + mid +
                ", remake='" + remake + '\'' +
                '}';
    }
}