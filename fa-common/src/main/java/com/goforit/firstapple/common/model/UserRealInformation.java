package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.ws.rs.FormParam;

/**
 * Created by goforit on 17/4/10.
 */
public class UserRealInformation {

    /**
     * id
     */
    private Long id;

    /**
     * 昵称
     */
    @FormParam("userNickName")
    private String userNickName;

    /**
     * 身份证号
     */
    @FormParam("idCardNum")
    private String idCardNum;

    /**
     * 真实姓名
     */
    @FormParam("realName")
    private String realName;

    public UserRealInformation() {
    }

    public UserRealInformation(String userNickName) {
        this.userNickName = userNickName;
    }

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

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
