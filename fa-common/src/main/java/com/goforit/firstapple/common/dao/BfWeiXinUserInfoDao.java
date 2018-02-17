package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.BfWeiXinUserInfo;

/**
 * Created by goforit on 18/2/11.
 */
public interface BfWeiXinUserInfoDao {

    //~~~~~~ modified method ~~~~~~

    void insert(BfWeiXinUserInfo bfWeiXinUserInfo);

    //~~~~~~ query method ~~~~~~

    BfWeiXinUserInfo getUserById(Long userId);

    BfWeiXinUserInfo getUserByWeiXinId(String openId);
}
