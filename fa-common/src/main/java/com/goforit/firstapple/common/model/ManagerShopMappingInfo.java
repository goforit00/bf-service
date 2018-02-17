package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * 管理者和店铺映射关系
 *
 * Created by goforit on 17/4/18.
 */
public class ManagerShopMappingInfo {

    private Long id;

    /**
     * 管理者唯一标识
     */
    private String managerCode;

    /**
     * 店铺唯一标识
     */
    private String shopCode;

    /**
     * 是否删除
     */
    private boolean isDeleted;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    public ManagerShopMappingInfo() {
    }

    public ManagerShopMappingInfo(String managerCode, String shopCode) {
        this.managerCode = managerCode;
        this.shopCode = shopCode;
        isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
