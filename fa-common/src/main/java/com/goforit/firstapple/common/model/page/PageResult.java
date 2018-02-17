package com.goforit.firstapple.common.model.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by goforit on 17/4/20.
 */
public class PageResult<T extends Serializable> implements Serializable {

    /**
     * 总条数
     */
    private int total;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 目前第几页
     */
    private int currentPage;

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 错误信息
     */
    private String message;

    /**
     * 分页结果
     */
    private List<T> list;

    public int getTotal() {
        return total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getList() {
        return list;
    }
}
