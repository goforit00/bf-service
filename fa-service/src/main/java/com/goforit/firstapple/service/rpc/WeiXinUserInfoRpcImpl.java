package com.goforit.firstapple.service.rpc;

import com.goforit.firstapple.common.consts.FaSysConst;
import com.goforit.firstapple.common.model.Response;
import com.goforit.firstapple.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by goforit on 17/5/26.
 */

@Path("/weixin/user")
public class WeiXinUserInfoRpcImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinUserInfoRpcImpl.class);

    @Autowired
    private UserService userService;

    /**
     *
     * @param nickName
     * @param token
     * @return
     */
    @Path("/checkToken")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response<Boolean> checkToken(@QueryParam("userNickName") String nickName, @QueryParam("token") String token) {

        try {
            boolean result = userService.checkUserToken(nickName, token);
            if (result) {
                LOGGER.info("用户[{}]验证token[{}]成功", nickName, token);
            } else {
                LOGGER.error("用户[{}]验证token[{}]失败", nickName, token);
            }
            return Response.buildSuccess(result);
        } catch (Exception e) {
            LOGGER.error("用户[{}]验证token[{}]异常.", nickName, token, e);
            return Response.buildFailed("验证token失败.");
        }
    }

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response<String> login(@QueryParam("userNickName") String userNickName, @QueryParam("weixinCode") String code,
        @QueryParam("weixinType") String weixinType,@Context HttpServletResponse httpResponse) {

        try {

            httpResponse.setHeader(FaSysConst.COOKIE_TOKEN_KEY,"token");


        }catch (Exception e){

        }

        return null;
    }
}
