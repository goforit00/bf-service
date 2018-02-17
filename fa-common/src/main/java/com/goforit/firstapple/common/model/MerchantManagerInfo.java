package com.goforit.firstapple.common.model;

import com.goforit.firstapple.common.enums.MerchantManagerStatus;
import com.goforit.firstapple.common.enums.MerchantManagerType;
import com.goforit.firstapple.common.model.params.MerchantManagerRegisterParams;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * 管理者信息
 * Created by goforit on 17/4/14.
 */
public class MerchantManagerInfo {

    private Long id;

    /**
     * 管理者唯一标识
     */
    private String managerCode;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 身份证号
     */
    private String idCardNum;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 类型
     * 法人,店主
     */
    private MerchantManagerType type;

    /**
     * 状态
     */
    private MerchantManagerStatus status;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    public MerchantManagerInfo(){}

    public MerchantManagerInfo(MerchantManagerRegisterParams registerParams) {
        this.email = registerParams.getEmail();
        this.idCardNum = registerParams.getIdCardNum();
        this.realName = registerParams.getRealName();
        this.password = registerParams.getPassword();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public MerchantManagerType getType() {
        return type;
    }

    public void setType(MerchantManagerType type) {
        this.type = type;
    }

    public MerchantManagerStatus getStatus() {
        return status;
    }

    public void setStatus(MerchantManagerStatus status) {
        this.status = status;
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
