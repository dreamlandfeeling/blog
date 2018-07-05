package com.xin.yxblog.dto;

import java.util.HashMap;
import java.util.Map;

public class Query extends HashMap {
    /**
     * 数据开始行号
     */
    private int offset;
    /**
     * 每页显示数量
     */
    private int limit;

    public Query(Map map){
        this.putAll(map);
        this.offset = Integer.parseInt(map.get("offset").toString());
        this.limit = Integer.parseInt(map.get("limit").toString());

    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
