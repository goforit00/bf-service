package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * 用户阅读书本事件
 * Created by goforit on 18/2/4.
 */
public class BfUserReadBookEvent {

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户 笔名
     */
    private String penName;

    /**
     * 书本id
     */
    private Long bookId;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 图书作者
     */
    private String bookAuthor;

    /**
     * 书封面 url
     */
    private String bookCoverUrl;

    /**
     * 阅读本书后的评分
     */
    private Double score;

    /**
     * 阅读开始时间
     */
    private Date readBeginTime;

    private String readBeginTimeStr;

    /**
     * 阅读结束时间
     */
    private Date readEndTime;

    private Date createdTime;

    private Date updatedTime;

    public BfBookInfo buildBookInfo(){
        BfBookInfo bookInfo=new BfBookInfo();
        bookInfo.setBookName(this.getBookName());
        bookInfo.setAuthor(this.getBookAuthor());

        return bookInfo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getReadBeginTimeStr() {
        return readBeginTimeStr;
    }

    public void setReadBeginTimeStr(String readBeginTimeStr) {
        this.readBeginTimeStr = readBeginTimeStr;
    }

    public Date getReadBeginTime() {
        return readBeginTime;
    }

    public void setReadBeginTime(Date readBeginTime) {
        this.readBeginTime = readBeginTime;
    }

    public Date getReadEndTime() {
        return readEndTime;
    }

    public void setReadEndTime(Date readEndTime) {
        this.readEndTime = readEndTime;
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

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
