package com.qilinxx.rms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "document")
public class Document {
    @Id
    private Integer did;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "path")
    private String path;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "remake")
    private String remake;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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
        return "Document{" +
                "did=" + did +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", path='" + path + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemType='" + itemType + '\'' +
                ", remake='" + remake + '\'' +
                '}';
    }
}