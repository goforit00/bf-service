package com.goforit.firstapple.dashboard.controller;

import com.goforit.firstapple.common.consts.FaSysConst;
import com.goforit.firstapple.common.dao.FAConfigDao;
import com.goforit.firstapple.common.enums.FAPageLoginUserType;
import com.goforit.firstapple.common.model.FAConfigInfo;
import com.goforit.firstapple.common.model.MerchantManagerInfo;
import com.goforit.firstapple.common.model.MerchantManagerToken;
import com.goforit.firstapple.common.model.Response;
import com.goforit.firstapple.core.service.MerchantManagerService;
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
 * Created by goforit on 17/4/18.
 */

@Path("/merchant/manager")
public class MerchantManagerInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantManagerInfoController.class);

    @Autowired
    private MerchantManagerService merchantManagerService;

    @Autowired
    private FAConfigDao faConfigDao;


    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<MerchantManagerInfo> login(@FormParam("email") String email, @FormParam("password") String password,
                                               @Context HttpServletResponse httpResponse) {
        try {

            MerchantManagerToken merchantManagerToken = merchantManagerService.login(email, password);

            MerchantManagerInfo merchantManagerInfo = merchantManagerService.findByEmail(email);

            FAConfigInfo domainConfig = faConfigDao.getByKey(FaSysConst.PAGE_DOMAIN_KEY);
            FAConfigInfo pathConfig = faConfigDao.getByKey(FaSysConst.PAGE_PATH_KEY);

            Cookie tokenCookie = new Cookie(FaSysConst.PAGE_COOKIE_MANAGER_TOKEN_KEY, merchantManagerToken.getToken());
            tokenCookie.setDomain(domainConfig.getValue());
            tokenCookie.setPath(pathConfig.getValue());
            httpResponse.addCookie(tokenCookie);

            Cookie managerCookie = new Cookie(FaSysConst.PAGE_COOKIE_MANAGER_CODE_KEY, merchantManagerInfo.getManagerCode());
            managerCookie.setDomain(domainConfig.getValue());
            managerCookie.setPath(pathConfig.getValue());
            httpResponse.addCookie(managerCookie);

            Cookie pageLoginTypeCookie =new Cookie(FaSysConst.PAGE_COOKIE_USER_PAGE_LOGIN_TYPE, FAPageLoginUserType.MERCHANT.toString());
            pageLoginTypeCookie.setDomain(domainConfig.getValue());
            pageLoginTypeCookie.setPath(pathConfig.getValue());
            httpResponse.addCookie(pageLoginTypeCookie);

            return Response.buildSuccess(merchantManagerInfo);
        } catch (Exception e) {
            LOGGER.error("登录异常", e);
            return Response.buildFailed(e.toString());
        }
    }


    /**
     * 商铺管理者暂时只通过邮件审批,后台人员添加
     *
     * @param email
     * @param password
     * @param idCardNum
     * @param realName
     * @return
     */
    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response<Boolean> register(@FormParam("email") String email, @FormParam("password") String password,
                                      @FormParam("idCardNum")String idCardNum,@FormParam("realName") String realName) {
        try {
            //暂时不开放此接口
//            merchantManagerService.register(new MerchantManagerRegisterParams(email, password, idCardNum,realName));

            return Response.buildSuccess(true);

        } catch (Exception e) {
            LOGGER.error("商家注册异常,email[{}],realName[{}]", email, realName, e);
            return Response.buildFailed(e.toString());
       }
    }


    @Path("/token")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response<Boolean> checkToken(@QueryParam("managerCode") String managerCode, @QueryParam("token") String token) {

        try {
            if (merchantManagerService.checkManagerToken(managerCode, token)) {
                return Response.buildSuccess(true);
            }

            return Response.buildSuccess(false);
        } catch (Exception e) {
            LOGGER.error("验证manager[{}],token[{}]异常", managerCode, token, e);

            return Response.buildFailed(e.toString());
        }
    }

    @Path("/getDetail/{managerCode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response<MerchantManagerInfo> getManagerDetail(@PathParam("managerCode") String managerCode){

        try {
            Assert.isTrue(StringUtils.isNotBlank(managerCode),"管理者标识不能为空!");
            
            MerchantManagerInfo managerInfo=merchantManagerService.findByManagerCode(managerCode);

            return Response.buildSuccess(managerInfo);

        }catch (Exception e){
            LOGGER.error("获得managerCode[{}]详细信息异常", managerCode, e);

            return Response.buildFailed(e.toString());
        }
    }
}
