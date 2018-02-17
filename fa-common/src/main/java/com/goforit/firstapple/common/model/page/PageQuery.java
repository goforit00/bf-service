package com.goforit.firstapple.common.model.page;

import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Created by goforit on 17/4/20.
 */
public class PageQuery implements Serializable {

    private static final int DEFAULT_OFFSET = 0;

    private static final int DEFAULT_LIMIT = 10;

    private static final int MAX_LIMIT_NUM = 200;

    /**
     * 每页大小
     */
    private int limit = DEFAULT_LIMIT;

    /**
     * 偏离量
     */
    private int offset = DEFAULT_OFFSET;

    public PageQuery() {
        this.limit = DEFAULT_LIMIT;
        this.offset = DEFAULT_OFFSET;
    }

    public PageQuery(int offset, int limit) {
        Assert.isTrue(limit <= MAX_LIMIT_NUM, "一次查询数量不能超过 " + MAX_LIMIT_NUM);
        Assert.isTrue(offset >= 0, "查询偏移量不能小于0");
        this.offset = offset;
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
