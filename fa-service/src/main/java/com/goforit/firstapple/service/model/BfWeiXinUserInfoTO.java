package com.goforit.firstapple.service.model;

import com.goforit.firstapple.common.model.BfWeiXinUserInfo;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created by goforit on 18/2/12.
 */
public class BfWeiXinUserInfoTO {


    private Long id;

    /**
     * 笔名
     */
    private String penName;

    /**
     * 头像
     */
    private String avatar;

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
    
    public BfWeiXinUserInfoTO(){}

    public static BfWeiXinUserInfoTO build(BfWeiXinUserInfo bfWeiXinUserInfo) {
        if (null == bfWeiXinUserInfo) {
            return null;
        }
        BfWeiXinUserInfoTO userInfoTO = new BfWeiXinUserInfoTO();
        BeanUtils.copyProperties(bfWeiXinUserInfo, userInfoTO);

        return userInfoTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
