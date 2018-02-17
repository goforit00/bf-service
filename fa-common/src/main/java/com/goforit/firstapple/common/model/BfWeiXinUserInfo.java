package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 书友微信用户
 * Created by goforit on 18/2/4.
 */
public class BfWeiXinUserInfo implements Serializable {

    private Long id;

    /**
     * 微信id
     */
    private transient String openId;

    /**
     * 用户头像url
     */
    private String avatar;
    /**
     * 笔名
     */
    private String penName;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 省
     */
    private String locProvince;

    /**
     * 市
     */
    private String locCity;

    /**
     * 区
     */
    private String locCounty;

    private Date createdTime;

    private Date updatedTime;

    public BfWeiXinUserInfo(String openId, String penName, String avatar) {
        this.openId = openId;
        this.penName = penName;
        this.avatar = avatar;
    }

    public BfWeiXinUserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLocProvince() {
        return locProvince;
    }

    public void setLocProvince(String locProvince) {
        this.locProvince = locProvince;
    }

    public String getLocCity() {
        return locCity;
    }

    public void setLocCity(String locCity) {
        this.locCity = locCity;
    }

    public String getLocCounty() {
        return locCounty;
    }

    public void setLocCounty(String locCounty) {
        this.locCounty = locCounty;
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
