package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_item")
public class UserItem {
    @Id
    private String uid;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "remake")
    private String remake;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "uid=" + uid +
                ", itemId='" + itemId + '\'' +
                ", itemType='" + itemType + '\'' +
                ", remake='" + remake + '\'' +
                '}';
    }
}