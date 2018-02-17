package com.goforit.firstapple.common.enums;

/**
 * 商家状态
 * Created by goforit on 17/4/20.
 */
public enum MerchantManagerStatus {

    UN_CHECK("UN_CHECK","未审核"),
    ACTIVITY("ACTIVITY","激活"),
    CHECK_FAILED("CHECK_FAILED","审核失败"),
    FREEZE("FREEZE","冻结");

    MerchantManagerStatus(String value,String desc){
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
