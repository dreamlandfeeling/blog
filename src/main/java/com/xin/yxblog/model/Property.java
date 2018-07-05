package com.xin.yxblog.model;

public class Property {
    private String type;
    private String name;

    private String columnName;

    private String columnType;

    private String jdbcType;
    /**
     * 是否是主键
     */
    private Boolean primaryKey;
    /**
     * 判读是否是大文本类型
     */
    private Boolean bigText;
    /**
     * 注释
     */
    private String comment;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }


    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Boolean getBigText() {
        return bigText;
    }

    public void setBigText(Boolean bigText) {
        this.bigText = bigText;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
