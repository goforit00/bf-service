package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * Created by goforit on 17/4/5.
 */
public class UuidInfo {

    private Long id;

    private String uuid;

    private Date gmtCreated;

    public UuidInfo(String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
