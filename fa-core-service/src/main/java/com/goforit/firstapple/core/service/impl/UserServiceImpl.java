package com.goforit.firstapple.core.service.impl;

import com.goforit.firstapple.common.consts.FaSysConst;
import com.goforit.firstapple.common.dao.UserInfoDao;
import com.goforit.firstapple.common.dao.UserRealInformationDao;
import com.goforit.firstapple.common.dao.UserTokenDao;
import com.goforit.firstapple.common.model.UserFullInfo;
import com.goforit.firstapple.common.model.UserInfo;
import com.goforit.firstapple.common.model.UserRealInformation;
import com.goforit.firstapple.common.model.UserToken;
import com.goforit.firstapple.common.model.params.UserRegisterParams;
import com.goforit.firstapple.core.service.UserService;
import com.goforit.firstapple.core.service.UuidService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created by goforit on 17/4/1.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userDao;
    
    @Autowired
    private UserRealInformationDao userRealInformationDao;

    @Autowired
    private UserTokenDao userTokenDao;

    @Autowired
    private UuidService uuidService;

    @Override
    public UserToken login(String username, String password) {

        UserInfo userInfo=userDao.getByName(username);
        if(null==userInfo){
            throw new RuntimeException("此用户未注册");
        }

        if(!StringUtils.equals(userInfo.getPassword(),password)){
            throw new RuntimeException("用户密码不正确");
        }

        return generatorUserToken(username);
    }

    @Override
    public UserInfo register(UserRegisterParams registerParams) {

        String userNickName=registerParams.getUserNickName();
        UserInfo userInfo=userDao.getByName(userNickName);
        if(null!=userInfo){
            throw new RuntimeException(userNickName+"用户已经存在");
        }

        userDao.create(new UserInfo(registerParams));

        userRealInformationDao.create(new UserRealInformation(userNickName));

        return userDao.getByName(userNickName);
    }

    @Override
    public UserInfo changeUserBaseInfo(UserInfo userInfo) {

        Assert.isTrue(StringUtils.isNotBlank(userInfo.getEmail()),"邮箱不能为空");

        userDao.updateUser(userInfo);

        return userDao.getByName(userInfo.getUserNickName());
    }

    @Override
    public UserInfo findByName(String username) {
        return userDao.getByName(username);
    }

    @Override
    public UserFullInfo findUserDetailInfoByName(String username) {

        UserInfo userInfo=userDao.getByName(username);
        if(null==userInfo){
            throw new RuntimeException(username+"此用户不存在");
        }

        UserRealInformation realInformation = userRealInformationDao.getByName(username);

        UserFullInfo fullInfo=UserFullInfo.buildFullInfo(userInfo,realInformation);

        return fullInfo;
    }

    @Override
    public UserRealInformation changeUserRealInfo(UserRealInformation userRealInformation) {
        Assert.notNull(userRealInformation,"userRealInformation参数不能为空");
        String userNickName=userRealInformation.getUserNickName();
        Assert.isTrue(StringUtils.isNotBlank(userNickName),"用户昵称不能为空");

        userRealInformationDao.update(userRealInformation);

        userDao.updateModifiedTime(userNickName);

        return userRealInformationDao.getByName(userNickName);
    }

    @Override
    public boolean checkUserToken(String username, String token) {

        UserToken userToken = userTokenDao.findByNickName(username);

        if (null == userToken) {
            return false;
        }

        if (!StringUtils.equals(token, userToken.getToken())) {
            return false;
        }

        //检查是否超时
        Date tokenTimeout=DateUtils.addMinutes(userToken.getGmtModified(),FaSysConst.LOGIN_TOKEN_TIME_OUT_MINUTES);

        if(tokenTimeout.before(new Date())){
            //超时
            throw new RuntimeException("登录超时");
        }

        return true;
    }

    private UserToken generatorUserToken(String username){

        //创建token
        String tokenStr=uuidService.buildUUID();

        UserToken oldToken=userTokenDao.findByNickName(username);
        if(null==oldToken){

            UserToken token=new UserToken(username,tokenStr);
            userTokenDao.created(token);

        }else {

            oldToken.setToken(tokenStr);
            userTokenDao.update(oldToken);
        }

        return userTokenDao.findByNickName(username);
    }
}
