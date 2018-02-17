package com.goforit.firstapple.core.service;

import com.goforit.firstapple.common.model.MerchantShopInfo;

import java.util.List;

/**
 * Created by goforit on 17/4/20.
 */
public interface MerchantShopService {

    //~~~~~ Modification Method ~~~~~


    /**
     * 创建 店铺
     * @param merchantShopInfo
     * @param managerCode
     * @return
     */
    MerchantShopInfo createShop(MerchantShopInfo merchantShopInfo, String managerCode);

    /**
     * 修改店铺信息
     * @param merchantShopInfo
     * @return
     */
    MerchantShopInfo updateShop(MerchantShopInfo merchantShopInfo);


    //~~~~~ Query Method ~~~~~

    /**
     * 查询某个管理者下的店铺
     * @param managerCode
     * @return
     */
    List<MerchantShopInfo> findByManagerCode(String managerCode);

    /**
     * 根据shop code查询店铺
     * @param shopCode
     * @return
     */
    MerchantShopInfo findByShopCode(String shopCode);
}
