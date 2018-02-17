package com.goforit.firstapple.core.service;

import com.goforit.firstapple.common.model.UserFullInfo;
import com.goforit.firstapple.common.model.UserInfo;
import com.goforit.firstapple.common.model.UserRealInformation;
import com.goforit.firstapple.common.model.UserToken;
import com.goforit.firstapple.common.model.params.UserRegisterParams;

/**
 * Created by goforit on 17/4/1.
 */
public interface UserService {

    //~~~~~ Modified Method ~~~~~
    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    UserToken login(String username,String password);

    /**
     * 用户注册
     * @param registerParams
     * @return
     */
    UserInfo register(UserRegisterParams registerParams);

    /**
     * 修改用户基本信息
     *
     * @param userInfo
     * @return
     */
    UserInfo changeUserBaseInfo(UserInfo userInfo);

    /**
     * 修改 user detail
     * @param userRealInformation
     * @return
     */
    UserRealInformation changeUserRealInfo(UserRealInformation userRealInformation);


    //~~~~~ Query Method ~~~~~

    /**
     * 查看user token是否正常 (存在、过期等)
     *
     * @param username
     * @param token
     * @return
     */
    boolean checkUserToken(String username,String token);

    /**
     * 根据nick name查询用户
     *
     * @param username
     * @return
     */
    UserInfo findByName(String username);

    /**
     * 根据nick name查询用户详细信息
     *
     * @param username
     * @return
     */
    UserFullInfo findUserDetailInfoByName(String username);

}
