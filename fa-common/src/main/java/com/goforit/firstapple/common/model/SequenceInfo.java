package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * Created by goforit on 17/4/19.
 */
public class SequenceInfo {

    private Long id;

    /**
     * 序列名称
     */
    private String seqName;

    /**
     * 当前起步值
     */
    private Long value;

    /**
     * 最小
     */
    private Long min;

    /**
     * 最大
     */
    private Long max;

    /**
     * 获取的步长
     */
    private Short step;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Short getStep() {
        return step;
    }

    public void setStep(Short step) {
        this.step = step;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
