package com.goforit.firstapple.common.model.params;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by goforit on 17/4/19.
 */
public class MerchantManagerRegisterParams {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 身份证
     */
    private String idCardNum;

    /**
     * 真实姓名
     */
    private String realName;

    public MerchantManagerRegisterParams(String email, String password, String idCardNum, String realName) {
        this.email = email;
        this.password = password;
        this.idCardNum = idCardNum;
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
