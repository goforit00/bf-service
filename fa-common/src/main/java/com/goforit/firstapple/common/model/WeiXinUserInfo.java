package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by goforit on 17/5/26.
 */
public class WeiXinUserInfo {

    /**
     * id
     */
    private Long id;

    /**
     * 主体用户昵称
     */
    private String userNickName;

    /**
     * 微信openId (唯一标识微信用户)
     * 注意:同一用户,不同公众账号,openId不同
     * 多账号打通需要使用unionId
     */
    private String openId;

    /**
     * 微信用户登录时的code
     * wx.login
     */
    private String weixinLoginCode;

    /**
     * 后端验证的sessionKey
     */
    private String sessionKey;

    /**
     * 微信公众账号类型
     */
    private String weixinAppType;

    /**
     * 微信用户状态(是否登记过详细信息)
     */
    private String weixinUserStatus;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWeixinLoginCode() {
        return weixinLoginCode;
    }

    public void setWeixinLoginCode(String weixinLoginCode) {
        this.weixinLoginCode = weixinLoginCode;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getWeixinAppType() {
        return weixinAppType;
    }

    public void setWeixinAppType(String weixinAppType) {
        this.weixinAppType = weixinAppType;
    }

    public String getWeixinUserStatus() {
        return weixinUserStatus;
    }

    public void setWeixinUserStatus(String weixinUserStatus) {
        this.weixinUserStatus = weixinUserStatus;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
