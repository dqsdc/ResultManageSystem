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

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "profile")
    private String profile;

    @Column(name = "remake")
    private String remake;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

    @Override
    public String toString() {
        return "Textbook{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", people='" + people + '\'' +
                ", press='" + press + '\'' +
                ", publishTime=" + publishTime +
                ", isbn='" + isbn + '\'' +
                ", profile='" + profile + '\'' +
                ", remake='" + remake + '\'' +
                '}';
    }
}