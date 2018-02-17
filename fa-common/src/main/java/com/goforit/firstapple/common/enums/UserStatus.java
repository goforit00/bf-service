package com.goforit.firstapple.common.enums;

/**
 * Created by goforit on 17/4/20.
 */
public enum UserStatus {

    INACTIVITY("INACTIVITY","未激活"),
    ACTIVITY("ACTIVITY","激活"),
    FREEZE("FREEZE","冻结");

    UserStatus(String value,String desc){
        this.value=value;
        this.desc=desc;
    }

    private String value;

    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
