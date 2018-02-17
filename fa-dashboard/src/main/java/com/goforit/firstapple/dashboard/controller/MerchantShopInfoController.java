package com.goforit.firstapple.dashboard.controller;

import com.goforit.firstapple.common.model.MerchantShopInfo;
import com.goforit.firstapple.common.model.Response;
import com.goforit.firstapple.core.service.MerchantShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by goforit on 17/5/3.
 */

@Path("/merchant/shopInfo")
public class MerchantShopInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantShopInfoController.class);

    @Autowired
    private MerchantShopService merchantShopService;

    /**
     * 创建店铺 (暂时不支持,只能通过邮件申请,然后审核通过后由管理员手动添加到数据库)
     * @param managerCode
     * @param merchantShopInfo
     * @return
     */
    @Path("/{managerCode}/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response<MerchantShopInfo> createMerchantShop(@PathParam("managerCode") String managerCode,
        @BeanParam MerchantShopInfo merchantShopInfo) {

        try {
            MerchantShopInfo shopInfo=merchantShopService.createShop(merchantShopInfo,managerCode);

            return Response.buildSuccess(shopInfo);

        }catch (Exception e){
            LOGGER.error("商家[{}]创建店铺异常,店铺信息{}", managerCode, merchantShopInfo, e);
            return Response.buildFailed(e.toString());
        }
    }

    /**
     * 修改店铺信息
     * @param shopCode
     * @param shopInfo
     * @return
     */
    @Path("/{shopCode}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response<MerchantShopInfo> modifiedByShopCode(@PathParam("shopCode") String shopCode,@BeanParam MerchantShopInfo shopInfo) {
        try {
            MerchantShopInfo merchantShopInfo =merchantShopService.updateShop(shopInfo);

            return Response.buildSuccess(merchantShopInfo);
        } catch (Exception e) {
            LOGGER.error("更新店铺[{}]异常", shopCode, e);
            return Response.buildFailed(e.toString());
        }
    }

    /**
     * 通过 managerCode查询 shop
     * 
     * @param managerCode
     * @return
     */
    @Path("/getByManagerCode")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response<List<MerchantShopInfo>> getAllMerchantShopByManagerCode(
        @QueryParam("managerCode") String managerCode) {

        try {

            List<MerchantShopInfo> shopInfos = merchantShopService.findByManagerCode(managerCode);
            return Response.buildSuccess(shopInfos);

        } catch (Exception e) {
            LOGGER.error("根据managerCode[{}]查询店铺信息出错", managerCode, e);
            return Response.buildFailed(e.toString());
        }
    }

    /**
     * 通过shop code获得商铺详情
     * @return
     */
    @Path("/{shopCode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response<MerchantShopInfo> getDetailByShopCode(@PathParam("shopCode") String shopCode) {
        try {
            MerchantShopInfo merchantShopInfo = merchantShopService.findByShopCode(shopCode);

            Assert.notNull(merchantShopInfo, MessageFormat.format("未找到shop code为[{0}]的店铺", shopCode));

            return Response.buildSuccess(merchantShopInfo);
        } catch (Exception e) {
            LOGGER.error("查询店铺[{}]异常", shopCode, e);
            return Response.buildFailed(e.toString());
        }
    }
}
