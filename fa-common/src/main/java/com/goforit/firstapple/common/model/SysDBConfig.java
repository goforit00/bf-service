package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * 系统数据库配置信息
 *
 * Created by goforit on 18/2/4.
 */
public class SysDBConfig {

    private Long id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * key
     */
    private String cfgKey;

    /**
     * value
     */
    private String cfgValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCfgKey() {
        return cfgKey;
    }

    public void setCfgKey(String cfgKey) {
        this.cfgKey = cfgKey;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
