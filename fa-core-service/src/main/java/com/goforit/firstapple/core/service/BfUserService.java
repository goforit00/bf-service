package com.goforit.firstapple.core.service;

import com.goforit.firstapple.common.model.BfWeiXinUserInfo;

/**
 * Book Friend User Service
 * 暂时做一个service ,以后再考虑整合一套
 * Created by goforit on 18/2/9.
 */
public interface BfUserService {

    /**
     * 微信 用户登录
     * @param code
     * @param nickName
     * @param userAvatar
     * @return
     */
    BfWeiXinUserInfo login(String code, String nickName, String userAvatar);

    /**
     * 用户提交问题
     *
     * @param userId
     * @param question
     */
    void submitQuestion(Long userId,String question);
}
