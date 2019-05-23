package com.qilinxx.rms.domain.model.eo;

/**
 * @Auther: dqsdc
 * @Date: 2019-04-17 15:24
 * @Description:
 */
public class ThesisEo {
    // String[] columnNames = {"论文名称", "第一作者", "其他作者","期刊","年","卷","期","页码","论文级别","论文类型","论文摘要","发表时间","创建时间"};
    private String name;
    private String host;
    private String people;
    private String book;
    private Integer year;
    private Integer dossier;
    private Integer issue;
    private String pageNum;
    private String rank;
    private String type;
    private String profile;
    private String publishTime;
    private String createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
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
        this.pageNum = pageNum;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ThesisEo{" +
                "name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", people='" + people + '\'' +
                ", book='" + book + '\'' +
                ", year=" + year +
                ", dossier=" + dossier +
                ", issue=" + issue +
                ", pageNum='" + pageNum + '\'' +
                ", rank='" + rank + '\'' +
                ", type='" + type + '\'' +
                ", profile='" + profile + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
