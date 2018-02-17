package com.goforit.firstapple.common.model;

import com.goforit.firstapple.common.enums.MerchantShopStatus;
import com.goforit.firstapple.common.utils.GeoHashUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.ws.rs.FormParam;
import java.util.Date;

/**
 * 店铺信息
 * Created by goforit on 17/4/18.
 */
public class MerchantShopInfo {

    private Long id;

    /**
     * 店铺编号
     */
    @FormParam("shopCode")
    private String shopCode;

    /**
     * 店铺名称
     */
    @FormParam("shopName")
    private String shopName;

    /**
     * 店铺所属公司编号
     */
    private String companyCode;

    /**
     * 国家
     */
    @FormParam("country")
    private String country;

    /**
     * 省份
     */
    @FormParam("province")
    private String province;

    /**
     * 市
     */
    @FormParam("city")
    private String city;

    /**
     * 区
     */
    @FormParam("county")
    private String county;

    /**
     * 店铺地址
     */
    @FormParam("address")
    private String address;

    /**
     * 店铺状态
     */
    private MerchantShopStatus status;

    /**
     * 描述
     */
    @FormParam("description")
    private String description;

    /**
     * 经度
     */
    @FormParam("posLongitude")
    private Double posLongitude;

    /**
     * 纬度
     */
    @FormParam("posLatitude")
    private Double posLatitude;

    /**
     * geo hash
     */
    private String geoHash;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    public MerchantShopInfo updateByAnother(MerchantShopInfo another) {
        if (StringUtils.isNotBlank(another.getShopName())) {
            this.shopName = another.shopName;
        }

        if (StringUtils.isNotBlank(another.getCountry())) {
            this.country = another.country;
        }

        if (StringUtils.isNotBlank(another.getProvince())) {
            this.province = another.province;
        }
        if (StringUtils.isNotBlank(another.city)) {
            this.city = another.city;
        }
        if (StringUtils.isNotBlank(another.county)) {
            this.county = another.county;
        }
        if (StringUtils.isNotBlank(another.address)) {
            this.address = another.address;
        }
        if (null != another.posLongitude) {
            this.posLongitude = another.posLongitude;
        }

        if (null != another.posLatitude) {
            this.posLatitude = another.posLatitude;
        }

        String geoHash= GeoHashUtils.getPositionGeoHash(this.posLongitude,this.posLatitude);
        this.geoHash=geoHash;


        return this;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MerchantShopStatus getStatus() {
        return status;
    }

    public void setStatus(MerchantShopStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPosLongitude() {
        return posLongitude;
    }

    public void setPosLongitude(Double posLongitude) {
        this.posLongitude = posLongitude;
    }

    public Double getPosLatitude() {
        return posLatitude;
    }

    public void setPosLatitude(Double posLatitude) {
        this.posLatitude = posLatitude;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
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
