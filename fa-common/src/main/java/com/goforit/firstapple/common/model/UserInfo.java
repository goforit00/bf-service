package com.goforit.firstapple.common.model;

import com.goforit.firstapple.common.model.params.UserRegisterParams;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.ws.rs.FormParam;
import java.sql.Date;

/**
 * Created by goforit on 17/3/30.
 */
public class UserInfo {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    @FormParam("userNickName")
    private String userNickName;

    /**
     * 密码
     */
    private transient String password;

    /**
     * 邮箱
     */
    @FormParam("email")
    private String email;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 电话号码
     */
    @FormParam("phone")
    private String phone;

    /**
     * 性别
     */
    @FormParam("sex")
    private String sex;

    /**
     * 默认地址
     */
    @FormParam("defaultAddress")
    private String defaultAddress;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 更新时间
     */
    private Date gmtModified;


    public UserInfo(){}

    public UserInfo(UserRegisterParams registerParams){
        this.userNickName=registerParams.getUserNickName();
        this.password=registerParams.getPassword();
        this.email=registerParams.getEmail();
    }


    //~~~~~ getter and setter ~~~~~


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
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
