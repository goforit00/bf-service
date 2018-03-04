package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by goforit on 18/2/4.
 */
public class BfReadBookProgressRecord implements Serializable {

    private Long id;

    private Long bookEventId;

    private Integer readProgress;

    private String userNote;

    private Date createdTime;

    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookEventId() {
        return bookEventId;
    }

    public void setBookEventId(Long bookEventId) {
        this.bookEventId = bookEventId;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
