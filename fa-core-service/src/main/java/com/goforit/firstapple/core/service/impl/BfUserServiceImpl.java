package com.goforit.firstapple.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goforit.firstapple.common.consts.BfSysConst;
import com.goforit.firstapple.common.dao.BfUserQuestionDao;
import com.goforit.firstapple.common.dao.BfWeiXinUserInfoDao;
import com.goforit.firstapple.common.model.BfWeiXinUserInfo;
import com.goforit.firstapple.common.utils.HttpUtil;
import com.goforit.firstapple.core.service.BfUserService;
import com.goforit.firstapple.core.service.DBConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by goforit on 18/2/9.
 */
@Service
public class BfUserServiceImpl implements BfUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BfUserServiceImpl.class);

    @Autowired
    private BfWeiXinUserInfoDao bfWeiXinUserInfoDao;

    @Autowired
    private BfUserQuestionDao bfUserQuestionDao;

    @Autowired
    private DBConfigService dbConfigService;

    @Override
    public BfWeiXinUserInfo login(String code, String nickName, String userAvatar) {

        try {
            String appId = dbConfigService.getValueByAppNameAndKey(BfSysConst.APP_NAME, BfSysConst.APP_ID);
            String secret = dbConfigService.getValueByAppNameAndKey(BfSysConst.APP_NAME, BfSysConst.APP_SECRET);

            Map<String, String> params = new HashMap<>();
            params.put("appid", appId);
            params.put("secret", secret);
            params.put("js_code", code);
            params.put("grant_type", "authorization_code");

            String result = HttpUtil.doGet("https://api.weixin.qq.com/sns/jscode2session", params);

            JSONObject resultObj = JSONObject.parseObject(result);

            String sessionKey = resultObj.getString("session_key");
            String openId = resultObj.getString("openid");

            BfWeiXinUserInfo userInfo = bfWeiXinUserInfoDao.getUserByWeiXinId(openId);
            if (null == userInfo) {
                userInfo = new BfWeiXinUserInfo(openId, nickName, userAvatar);
                try {
                    bfWeiXinUserInfoDao.insert(userInfo);
                } catch (DuplicateKeyException de) {
                    LOGGER.error("创建用户[{}]主键冲突", nickName, de);
                }

                userInfo = bfWeiXinUserInfoDao.getUserById(userInfo.getId());
            }

            return userInfo;

        } catch (Exception e) {
            LOGGER.error("用户[{}]登录异常", nickName, e);
            return null;
        }

    }

    @Override
    public void submitQuestion(Long userId, String question) {
        bfUserQuestionDao.insert(userId, question);
    }
}
