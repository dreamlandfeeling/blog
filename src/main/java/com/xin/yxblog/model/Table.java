package com.xin.yxblog.model;

import java.util.List;

public class Table {
    private String packageName;
    private String tableName;
    private String className;
    private String classname;
    /**
     * 类注释
     */
    private String classComment;
    /**
     * 主键
     */
    private Property primaryKey;

    /**
     * 表里是否有大文本
     */
    private Boolean hasBigText;
    /**
     * 所有列信息
     */
    private List<Property> properties;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassComment() {
        return classComment;
    }

    public void setClassComment(String classComment) {
        this.classComment = classComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Property getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Property primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Boolean getHasBigText() {
        return hasBigText;
    }

    public void setHasBigText(Boolean hasBigText) {
        this.hasBigText = hasBigText;
    }
}
