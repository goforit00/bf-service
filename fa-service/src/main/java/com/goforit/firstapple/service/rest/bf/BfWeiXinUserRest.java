package com.goforit.firstapple.service.rest.bf;

import com.goforit.firstapple.common.model.BfWeiXinUserInfo;
import com.goforit.firstapple.common.model.Response;
import com.goforit.firstapple.core.service.BfUserService;
import com.goforit.firstapple.service.model.BfWeiXinUserInfoTO;
import com.goforit.firstapple.service.model.params.BfWeiXinUserLoginParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by goforit on 18/2/4.
 */

@Path("/bf/user/")
public class BfWeiXinUserRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BfWeiXinUserRest.class);

    @Autowired
    private BfUserService bfUserService;

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BfWeiXinUserInfoTO> login(BfWeiXinUserLoginParams userLoginParams) {

        BfWeiXinUserInfo userInfo =
            bfUserService.login(userLoginParams.getCode(), userLoginParams.getNickName(),
                userLoginParams.getAvatarUrl());

        if (null == userInfo) {
            return Response.buildFailed("登录异常");
        }

        return Response.buildSuccess(BfWeiXinUserInfoTO.build(userInfo));
    }
}
