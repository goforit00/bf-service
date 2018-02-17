package com.goforit.firstapple.service.model.params;

import com.goforit.firstapple.common.model.BfUserReadBookEvent;
import com.goforit.firstapple.common.utils.DateTimeUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * Created by goforit on 18/2/15.
 */
public class BfUserReadBookEventParams {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 图书id (暂时不用)
     */
    private Long bookId;

    /**
     * 作者
     */
    private String author;

    /**
     * 开始时间
     */
    private String beginTimeStr;

    public BfUserReadBookEvent buildBookEvent(){
        BfUserReadBookEvent bookEvent=new BfUserReadBookEvent();
        bookEvent.setUserId(this.getUserId());
        bookEvent.setBookName(this.getBookName());
        bookEvent.setReadBeginTimeStr(this.getBeginTimeStr());
        bookEvent.setBookAuthor(this.getAuthor());
        Date beginReadTime=DateTimeUtils.toTimestamp(this.getBeginTimeStr());
        bookEvent.setReadBeginTime(beginReadTime);
        return bookEvent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
