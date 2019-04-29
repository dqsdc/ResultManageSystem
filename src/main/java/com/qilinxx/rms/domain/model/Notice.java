package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "notice")
public class Notice {
    @Id
    private String nid;

    @Column(name = "check_id")
    private String checkId;

    @Column(name = "create_id")
    private String createId;

    @Column(name = "result_id")
    private String resultId;

    @Column(name = "type")
    private String type;

    @Column(name = "message")
    private String message;

    @Column(name = "state")
    private String state;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "remake")
    private String remake;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid == null ? null : nid.trim();
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId == null ? null : checkId.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId == null ? null : resultId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

    @Override
    public String toString() {
        return "Notice{" +
                "nid='" + nid + '\'' +
                ", checkId='" + checkId + '\'' +
                ", createId='" + createId + '\'' +
                ", resultId='" + resultId + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                ", remake='" + remake + '\'' +
                '}';
    }
}