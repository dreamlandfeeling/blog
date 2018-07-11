package com.xin.yxblog.dto;

import com.xin.yxblog.utils.StringUtils;

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

    public Query(Map map) {
        this.putAll(map);
        Object offset = map.get("offset");
        Object limit = map.get("limit");
        if (offset != null && limit != null) {
            this.offset = Integer.parseInt(offset.toString());
            this.limit = Integer.parseInt(limit.toString());
        } else {
            this.offset = 0;
            this.limit = 10;
        }
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
