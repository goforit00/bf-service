package com.goforit.firstapple.service.model.params;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 用户提问或建议
 *
 * Created by goforit on 18/2/13.
 */
public class BfUserQuestionParams {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 问题内容
     */
    private String question;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
