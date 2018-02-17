package com.goforit.firstapple.dashboard.controller;

import com.goforit.firstapple.common.consts.FaSysConst;
import com.goforit.firstapple.common.dao.FAConfigDao;
import com.goforit.firstapple.common.enums.FAPageLoginUserType;
import com.goforit.firstapple.common.model.*;
import com.goforit.firstapple.common.model.params.UserRegisterParams;
import com.goforit.firstapple.core.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by goforit on 17/3/30.
 */
@Path("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FAConfigDao faConfigDao;

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<UserInfo> login(@FormParam("username") String username, @FormParam("password") String password,
        @Context HttpServletResponse httpResponse) {
        try {

            UserToken userToken = userService.login(username, password);

            UserInfo userInfo = userService.findByName(username);

            FAConfigInfo domainConfig = faConfigDao.getByKey(FaSysConst.PAGE_DOMAIN_KEY);
            FAConfigInfo pathConfig = faConfigDao.getByKey(FaSysConst.PAGE_PATH_KEY);

            Cookie tokenCookie = new Cookie(FaSysConst.COOKIE_TOKEN_KEY, userToken.getToken());
            tokenCookie.setDomain(domainConfig.getValue());
            tokenCookie.setPath(pathConfig.getValue());
            httpResponse.addCookie(tokenCookie);

            Cookie userCookie = new Cookie(FaSysConst.COOKIE_USERNAME_KEY, username);
            userCookie.setDomain(domainConfig.getValue());
            userCookie.setPath(pathConfig.getValue());
            httpResponse.addCookie(userCookie);

            Cookie pageLoginTypeCookie =new Cookie(FaSysConst.PAGE_COOKIE_USER_PAGE_LOGIN_TYPE, FAPageLoginUserType.MERCHANT.toString());
            pageLoginTypeCookie.setDomain(domainConfig.getValue());
            pageLoginTypeCookie.setPath(pathConfig.getValue());
            httpResponse.addCookie(pageLoginTypeCookie);

            return Response.buildSuccess(userInfo);
        } catch (Exception e) {
            LOGGER.error("登录异常", e);
            return Response.buildFailed(e.toString());
        }
    }

    @Path("/token")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response<Boolean> checkToken(@QueryParam("userNickName") String username, @QueryParam("token") String token) {

        try {
            if (userService.checkUserToken(username, token)) {
                return Response.buildSuccess(true);
            }

            return Response.buildSuccess(false);
        } catch (Exception e) {
            LOGGER.error("验证user[{}],token[{}]异常", username, token, e);

            return Response.buildFailed(e.toString());
        }
    }

    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response<Boolean> register(@FormParam("username") String username, @FormParam("password") String password,
        @FormParam("email") String email) {
        try {

            userService.register(new UserRegisterParams(username, password, email));

            return Response.buildSuccess(true);

        } catch (Exception e) {
            LOGGER.error("用户注册异常,username[{}],email[{}]", username, email, e);
            return Response.buildFailed(e.toString());
        }
    }

    @Path("/{userNickName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response<UserInfo> getUserInfo(@PathParam("userNickName") String username) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(username), "用户名不能为空");

            UserInfo userInfo = userService.findByName(username);

            return Response.buildSuccess(userInfo);

        } catch (Exception e) {
            LOGGER.error("查询用户[{}]信息异常", username, e);
            return Response.buildFailed(e.toString());
        }
    }

    /**
     * 获得user 详细信息
     * @param username
     * @return
     */
    @Path("/getDetail/{userNickName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response<UserFullInfo> getUserDetailInfo(@PathParam("userNickName") String username) {

        try {
            Assert.isTrue(StringUtils.isNotBlank(username), "昵称不能为空");

            UserFullInfo fullInfo = userService.findUserDetailInfoByName(username);
            return Response.buildSuccess(fullInfo);

        } catch (Exception e) {
            LOGGER.error("查询用户[{}]详细信息异常", username, e);
            return Response.buildFailed(e.toString());
        }
    }

    @Path("/changeBase/{userNickName}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response<UserInfo> updateUserInfo(@PathParam("userNickName") String userNickName,@BeanParam UserInfo userInfo){
        try {
            Assert.isTrue(StringUtils.isNotBlank(userNickName),"昵称不能为空");
            Assert.isTrue(null!=userInfo,"修改内容不能为空");
            Assert.isTrue(StringUtils.equals(userNickName,userInfo.getUserNickName()),"修改人信息和当前使用者不一致.");

            userInfo=userService.changeUserBaseInfo(userInfo);
            return Response.buildSuccess(userInfo);
        }catch (Exception e){
            LOGGER.error("修改用户[{}]基本信息异常.修改内容为:{}", userNickName, userInfo, e);
            return Response.buildFailed(e.toString());
        }
    }

    @Path("/changeReal/{userNickName}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response<UserRealInformation> updateUserRealInfo(@PathParam("userNickName") String userNickName,@BeanParam UserRealInformation userRealInformation){

        try {
            Assert.isTrue(StringUtils.isNotBlank(userNickName), "昵称不能为空");
            Assert.isTrue(StringUtils.equals(userNickName,userRealInformation.getUserNickName()),"修改人信息和当前使用者不一致.");

            userRealInformation=userService.changeUserRealInfo(userRealInformation);

            return Response.buildSuccess(userRealInformation);
        }catch (Exception e){
            LOGGER.error("更新用户[{}]实名信息异常", userNickName, e);
            return Response.buildFailed(e.toString());
        }
    }
}
