package com.goforit.firstapple.common.enums;

/**
 * Created by goforit on 17/4/20.
 */

/**
 * 商家管理者类型
 */
public enum MerchantManagerType {

    CORPORATION("CORPORATION","公司法人"),

    SHOPKEEPER("SHOPKEEPER","店主"),

    NORMAL("NORMAL","普通注册店主管理人");

    MerchantManagerType(String value,String desc){
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
