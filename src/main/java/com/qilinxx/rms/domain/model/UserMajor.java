package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_major")
public class UserMajor {
    @Id
    private Integer uid;

    @Column(name = "mid")
    private Integer mid;

    @Column(name = "remake")
    private String remake;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "UserMajor{" +
                "uid=" + uid +
                ", mid=" + mid +
                ", remake='" + remake + '\'' +
                '}';
    }
}