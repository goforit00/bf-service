package com.goforit.firstapple.service.model.params;

import com.goforit.firstapple.common.model.BfReadBookProgressRecord;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 用户读书进度发布参数
 *
 * Created by goforit on 18/2/24.
 */
public class BfUserReadBookProgressParams {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 读书事件id
     */
    private Long readBookEventId;

    /**
     * 读书进度
     */
    private Integer readProgress;

    /**
     * 用户书评论
     */
    private String userNote;

    public BfUserReadBookProgressParams(){}

    public BfReadBookProgressRecord buildReadBookProgressRecord(){
        BfReadBookProgressRecord record=new BfReadBookProgressRecord();
        record.setBookEventId(this.readBookEventId);
        record.setReadProgress(this.readProgress);
        record.setUserNote(this.getUserNote());

        return record;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReadBookEventId() {
        return readBookEventId;
    }

    public void setReadBookEventId(Long readBookEventId) {
        this.readBookEventId = readBookEventId;
    }

    public Integer getReadProgress() {
        return readProgress;
    }

    public void setReadProgress(Integer readProgress) {
        this.readProgress = readProgress;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
