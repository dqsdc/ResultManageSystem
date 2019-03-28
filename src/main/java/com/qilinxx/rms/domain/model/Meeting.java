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

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor == null ? null : sponsor.trim();
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer == null ? null : organizer.trim();
    }

    public Long getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Long meetingTime) {
        this.meetingTime = meetingTime;
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
        return "Meeting{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", people='" + people + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", organizer='" + organizer + '\'' +
                ", meetingTime=" + meetingTime +
                ", profile='" + profile + '\'' +
                ", remake='" + remake + '\'' +
                '}';
    }
}